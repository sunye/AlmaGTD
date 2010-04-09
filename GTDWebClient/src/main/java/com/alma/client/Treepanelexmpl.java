package com.alma.client;

import com.gwtext.client.core.Connection;
import com.gwtext.client.data.Node;
import com.gwtext.client.dd.DragData;
import com.gwtext.client.dd.DragDrop;
import com.gwtext.client.widgets.Component;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Radio;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;
import com.gwtext.client.widgets.tree.AsyncTreeNode;
import com.gwtext.client.widgets.tree.DropNodeCallback;
import com.gwtext.client.widgets.tree.MultiSelectionModel;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.XMLTreeLoader;
import com.gwtext.client.widgets.tree.event.TreePanelListenerAdapter;

public class Treepanelexmpl {

	public static Panel getPanel(){
		final Panel panel = new Panel();  
		panel.setBorder(false);  
		panel.setPaddings(15);  

		//create form for nody drop style  
		final FormPanel formPanel = new FormPanel();  
		formPanel.setBorder(false);  
		formPanel.setHideLabels(true);  

		final FieldSet fieldSet = new FieldSet("Drop style");  
		fieldSet.setWidth(420);  
		formPanel.add(fieldSet);  

		final Radio moveRadio = new Radio();  
		moveRadio.setName("dropstyle");  
		moveRadio.setBoxLabel("Move");  
		moveRadio.setChecked(true);  
		fieldSet.add(moveRadio);  

		final Radio copyRadio = new Radio();  
		copyRadio.setName("dropstyle");  
		copyRadio.setBoxLabel("Copy");  
		fieldSet.add(copyRadio);  

		//create source countries tree  
		final TreePanel treePanel = new TreePanel();  
		treePanel.setTitle("Countries");  
		treePanel.setAnimate(true);  
		treePanel.setEnableDD(true);  
		treePanel.setContainerScroll(true);  
		treePanel.setRootVisible(true);  
		treePanel.setWidth(200);  
		treePanel.setHeight(400);  
		treePanel.setSelectionModel(new MultiSelectionModel());  

		final XMLTreeLoader loader = new XMLTreeLoader();  
		loader.setDataUrl("data/countries-grouped.xml");  
		loader.setMethod(Connection.GET);  
		loader.setRootTag("countries");  
		loader.setFolderTitleMapping("@title");  
		loader.setFolderTag("continent");  
		loader.setLeafTitleMapping("@title");  
		loader.setLeafTag("country");  
		loader.setQtipMapping("@qtip");  
		loader.setIconMapping("@icon");  

		final AsyncTreeNode root = new AsyncTreeNode("Countries", loader);  
		treePanel.setRootNode(root);  
		root.expand();  
		treePanel.expandAll();  

		//create target vacation tree  
		final TreePanel tripTreePanel = new TreePanel();  
		tripTreePanel.setTitle("Trip Planner");  
		tripTreePanel.setAnimate(true);  
		tripTreePanel.setEnableDD(true);  
		tripTreePanel.setContainerScroll(true);  
		tripTreePanel.setRootVisible(true);  
		tripTreePanel.setWidth(200);  
		tripTreePanel.setHeight(400);  

		final XMLTreeLoader tripLoader = new XMLTreeLoader();  
		tripLoader.setDataUrl("data/trip.xml");  
		tripLoader.setMethod(Connection.GET);  
		tripLoader.setRootTag("vacations");  
		tripLoader.setFolderIdMapping("@title");  
		tripLoader.setFolderTag("trip");  
		tripLoader.setQtipMapping("@qtip");  
		tripLoader.setIconMapping("@icon");  
		tripLoader.setAttributeMappings(new String[]{"@trip"});  

		final AsyncTreeNode tripRoot = new AsyncTreeNode("Places to Visit", tripLoader);  

		tripTreePanel.setRootNode(tripRoot);  

		//add trip tree listener that handles move / copy logic  
		tripTreePanel.addListener(new TreePanelListenerAdapter() {  
			public void onRender(final Component component) {  
				tripRoot.expand();  
				tripTreePanel.expandAll();  
			}  

			public boolean doBeforeNodeDrop(final TreePanel treePanel, final TreeNode target, final DragData dragData,  
					final String point, final DragDrop source, final TreeNode dropNode,  
					final DropNodeCallback dropNodeCallback) {  
				if ("true".equals(target.getAttribute("trip"))) {  
					if (copyRadio.getValue()) {  
						final TreeNode copyNode = dropNode.cloneNode();  
						final Node[] children = copyNode.getChildNodes();  
						for (int i = 0; i < children.length; i++) {  
							final Node child = children[i];  
							copyNode.removeChild(child);  
						}  
						dropNodeCallback.setDropNode(copyNode);  
					}  
				}  
				return true;  
			}  
		});  

		final Panel horizontalPanel = new Panel();  
		horizontalPanel.setLayout(new HorizontalLayout(20));  
		horizontalPanel.add(treePanel);  
		horizontalPanel.add(tripTreePanel);  

		final Panel verticalPanel = new Panel();  
		verticalPanel.setLayout(new VerticalLayout(15));  

		verticalPanel.add(formPanel);  
		verticalPanel.add(horizontalPanel);  

		panel.add(verticalPanel);  

		return panel;


	}

}
