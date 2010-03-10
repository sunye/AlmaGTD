package com.alma.client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.alma.client.serializables.Project;
import com.alma.client.serializables.Task;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.widgets.tree.MultiSelectionModel;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.event.TreeNodeListener;
import com.smartgwt.client.core.DataClass;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Node;
import com.gwtext.client.data.NodeTraversalCallback;
import com.gwtext.client.data.Tree;
import com.google.gwt.user.client.ui.FormPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.layout.AccordionLayout;
import com.gwtext.client.widgets.layout.FormLayout;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.NumberField;
import com.gwtext.client.widgets.layout.CardLayout;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.TabPanel;
import com.google.gwt.user.client.ui.DecoratedStackPanel;
import com.google.gwt.user.client.ui.DeckPanel;

public class LiveOrganizePanel extends AbsolutePanel{
	private AlmaGTD agtdgui;
	private HashMap<String,Integer> taskKeyToRecid=new HashMap<String,Integer>();
	private HashMap<String,Integer> projKeyToRecid=new HashMap<String,Integer>();
	private HashMap<Node,DataClass> nodeDt = new HashMap<Node,DataClass>();
	private HashMap<String,Project> projectsList = new HashMap<String,Project>();
	private HashMap<String,Task> tasksList = new HashMap<String,Task>();
	private TreePanel treePanel;
	private TreeNode root;
	private int projListId=0;
	private int taskListId=0;
	private FormPanel formPanel;
	private TextField textField_6;
	private TextField textField_7;
	private TextField textField_8;
	private TextField textField_9;
	private Panel panel;
	private FormPanel formPanel_2;
	private TextField textField_1;
	private NumberField numberField;
	private TextField textField_2;
	private TextField textField_3;
	private TextField textField_4;
	private TextField textField_5;
	private TextField textField;
	
	public LiveOrganizePanel(AlmaGTD agtdgui){	
		super();
		this.agtdgui = agtdgui;
		this.addStyleName("panel");
		setSize("99%", "400px");
		
		treePanel = new TreePanel();  
        treePanel.setTitle("Tasks and Projects");  
        treePanel.setAnimate(true);  
        treePanel.setEnableDD(true);  
        treePanel.setContainerScroll(true);  
        treePanel.setRootVisible(true);  
        treePanel.setWidth(400);  
        treePanel.setHeight(500);  
        treePanel.setSelectionModel(new MultiSelectionModel());  
             
        root = new TreeNode("Workspace");
        treePanel.setRootNode(root);
       		
		treePanel.setHeight("400px");
		add(treePanel, 6, 6);
		
	
		
		Panel pnlInformations = new Panel("Tasks Informations");
		pnlInformations.setLayout(new FormLayout());
		
		TextField textField_10 = new TextField("ID", "text_field", 100);
		textField_10.setReadOnly(true);
		pnlInformations.add(textField_10);
		textField_10.setWidth("60%");
		
		TextField textField_11 = new TextField("Name", "text_field", 100);
		pnlInformations.add(textField_11);
		textField_11.setWidth("60%");
		
		TextField textField_12 = new TextField("Start Date", "text_field", 100);
		pnlInformations.add(textField_12);
		textField_12.setWidth("60%");
		
		TextField textField_13 = new TextField("End Date", "text_field", 100);
		pnlInformations.add(textField_13);
		textField_13.setWidth("60%");
		
		TextField textField_14 = new TextField("Frequency", "text_field", 100);
		pnlInformations.add(textField_14);
		textField_14.setWidth("60%");
		
		TextField textField_15 = new TextField("Progress", "text_field", 100);
		textField_15.setReadOnly(true);
		pnlInformations.add(textField_15);
		textField_15.setWidth("60%");
		add(pnlInformations, 412, 6);
		pnlInformations.setSize("400px", "176px");
		
		Panel pnlProjectsInformations = new Panel("Projects Informations");
		pnlProjectsInformations.setLayout(new FormLayout());
		
		TextField textField_16 = new TextField("ID", "text_field", 100);
		pnlProjectsInformations.add(textField_16);
		textField_16.setWidth("60%");
		
		TextField textField_17 = new TextField("Name", "text_field", 100);
		textField_17.setSize("60%", "");
		pnlProjectsInformations.add(textField_17);
		
		TextField textField_18 = new TextField("Frequency", "text_field", 100);
		pnlProjectsInformations.add(textField_18);
		textField_18.setWidth("60%");
		add(pnlProjectsInformations, 412, 214);
		pnlProjectsInformations.setSize("400px", "162px");
       
		
	}
	
	@Override
	public void setVisible(boolean visible){
		super.setVisible(visible);
		
		if(visible){
			fetchData();
		}
		  
		
	}
	
	
	public void fetchData(){
		
		System.out.println("Organize fetching data");
		//Remove all old nodes
		for(Node n : root.getChildNodes()) n.remove();
		taskListId=0;
		projListId=0;
		
		try {		
			
				agtdgui.getWebserver().getTasks(agtdgui.getLogin(), new AsyncCallback<List<Task>>() {
					public void onSuccess(List<Task> result) {
						for(Task task : result){
							tasksList.put(task.getIdentifiant(), task);
							
							System.out.println( task.getNom()+"=>"+task.getProjetConteneur());
							
							if(task.getProjetConteneur()==null){								
								ListGridRecord taskRec = new ListGridRecord();
					        	taskRec.setAttribute("id", task.getIdentifiant());
					        	taskRec.setAttribute("name", task.getNom());
					        	taskRec.setAttribute("effortRate", task.getTauxEffort());
					        	taskRec.setAttribute("startDate", task.getDateDebut());
					        	taskRec.setAttribute("endDate", task.getDateFin());
					        	taskRec.setAttribute("frequency", task.getFrequence());
					        	taskRec.setAttribute("state", task.getAvancement());
					        	taskRec.setAttribute("projectId", "");
					        	taskRec.setAttribute("progress", "");
					        	
					        	TreeNode taskNode = dataClassToNode(taskRec,"task"); 
					        	
					        	root.appendChild(taskNode);
					        	
					        	addNodeClickListener(taskNode);
						
							}
							
						}
					}
	
					public void onFailure(Throwable caught) {
						caught.printStackTrace();					
					}
				});
			
			agtdgui.getWebserver().getProjects(agtdgui.getLogin(), new AsyncCallback<List<Project>>() {
				
				public void onSuccess(List<Project> result) {
					for(Project proj : result){
						final ListGridRecord rec = new ListGridRecord();
						//System.out.println("Project"+proj.getIdentifiant()+":"+proj.getNom());
						rec.setAttribute("id", proj.getIdentifiant());
				        rec.setAttribute("name", proj.getNom()); 
				        rec.setAttribute("projectId", "");
				        rec.setAttribute("project", "");
				        rec.setAttribute("type", "project");
				        
				        projectsList.put(proj.getIdentifiant(), proj);
				        
				    	//node creation with attributes from datasource
			        	TreeNode curNode = dataClassToNode(rec,"project");        	
			            //If node has'nt project, adding it to root	
			        	if(rec.getAttribute("project")==null  || rec.getAttribute("project").equals("")){
			        		root.appendChild(curNode);
			        	}
			        	//Else, seeking correct owner
			        	else{
			        		Node projectNode = root.findChildBy(new NodeTraversalCallback() {
			        			public boolean execute(Node node) {
			                       return node.getAttribute("id").equals(rec.getAttribute("projectId"));
			                    }
			                });
			                if (projectNode != null) {
			                    projectNode.appendChild(curNode);
			                }
			        	}
			        	//Adding listener to the node
			        	addNodeClickListener(curNode);
			        	

			        	//Adding tasks if there is in it
			        	for(Task task : proj.getListeDeTaches()){
			        		System.out.println(task.getNom() + " dans un pORJEOJFIjdksjfklsdjfklsdjfkldsjfklj");
			        		
			        		final ListGridRecord taskRec = new ListGridRecord();
				        	taskRec.setAttribute("id", task.getIdentifiant());
				        	taskRec.setAttribute("name", task.getNom());
				        	taskRec.setAttribute("effortRate", task.getTauxEffort());
				        	taskRec.setAttribute("startDate", task.getDateDebut());
				        	taskRec.setAttribute("endDate", task.getDateFin());
				        	taskRec.setAttribute("frequency", task.getFrequence());
				        	taskRec.setAttribute("state", task.getAvancement());
				        	taskRec.setAttribute("projectId", "");
				        	taskRec.setAttribute("progress", "");
				        	
				        	TreeNode taskNode = dataClassToNode(taskRec,"task"); 
				        	
				        	curNode.appendChild(taskNode);
				        	
				        	addNodeClickListener(taskNode);
			        	}
			        	
			        	
					}					   
				}

				public void onFailure(Throwable caught) {
					caught.printStackTrace();					
				}
			});
			
			
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    root.expand(); 
	    treePanel.setRootNode(root);	      
	
	    treePanel.expandAll(); 
	    	   
	}
	
	/**
	 * Adding a listener on the node for click and move
	 * @param node : node on which adding listener.
	 */
	private void addNodeClickListener(TreeNode node) {
		     node.addListener(new TreeNodeListener() {
        	 @Override
        	 public void onClick(Node node, EventObject e) {
        		 
        		 if(node.getAttribute("type").equals("task")){
        			 /*projectForm.clearValues();        			 
        			 tasksListGrid.deselectAllRecords();
        			 System.out.println("id taks :"+node.getAttribute("id")+":"+taskKeyToRecid.get(node.getAttribute("id")));
        			 tasksListGrid.selectRecord(taskKeyToRecid.get(node.getAttribute("id")));
        			 taskForm.editSelectedData(tasksListGrid);*/
        		 }
        		 if(node.getAttribute("type").equals("project")){ 
        			/* taskForm.clearValues();
        			 projectsListGrid.deselectAllRecords();
        			 System.out.println("id proj :"+node.getAttribute("id")+":"+projKeyToRecid.get(node.getAttribute("id")));
        			 projectsListGrid.selectRecord(projKeyToRecid.get(node.getAttribute("id")));
        			 projectForm.editSelectedData(projectsListGrid);*/        			
        		 }
        	 }

			@Override
			public boolean doBeforeChildrenRendered(Node node) {return true;}

			@Override
			public boolean doBeforeClick(Node node, EventObject e) {return true;}

			@Override
			public boolean doBeforeCollapse(Node node, boolean deep,boolean anim) {return true;}

			@Override
			public boolean doBeforeExpand(Node node, boolean deep, boolean anim) {return true;}

			@Override
			public void onCheckChanged(Node node, boolean checked) {}

			@Override
			public void onCollapse(Node node) {}

			@Override
			public void onContextMenu(Node node, EventObject e) {}

			@Override
			public void onDblClick(Node node, EventObject e) {}

			@Override
			public void onDisabledChange(Node node, boolean disabled) {}

			@Override
			public void onExpand(Node node) {}

			@Override
			public void onTextChange(Node node, String text, String oldText) {}

			@Override
			public boolean doBeforeAppend(Tree tree, Node self, Node node) {return true;}

			@Override
			public boolean doBeforeInsert(Tree tree, Node self, Node node,Node refNode) {return true;}

			@Override
			public boolean doBeforeMove(Tree tree, Node self, Node oldParent,Node newParent, int index) {
				//If new parent is root, project owner attribute set to "".
				if(newParent.equals(root)){
					nodeDt.get(self).setAttribute("projectId", "");
					nodeDt.get(self).setAttribute("project", "");					
					return true;
				}
				//If new parent is a project, project owner attribute set to its id.				
				else if (newParent.getAttribute("type").equals("project")){
					nodeDt.get(self).setAttribute("projectId", newParent.getAttribute("id"));
					nodeDt.get(self).setAttribute("project", newParent.getAttribute("name"));					
					return true;
				}
				
				//Display the node in the correct form	
				if(self.getAttribute("type").equals("task")){
        			/* projectForm.clearValues();        			 
        			 tasksListGrid.deselectAllRecords();
        			 tasksListGrid.selectRecord(taskKeyToRecid.get(self.getAttribute("id")));
        			 taskForm.editSelectedData(tasksListGrid);*/
        		}
        		if(self.getAttribute("type").equals("project")){ 
        			/* taskForm.clearValues();
        			 projectsListGrid.deselectAllRecords();
        			 projectsListGrid.selectRecord(projKeyToRecid.get(self.getAttribute("id")));
        			 projectForm.editSelectedData(projectsListGrid); */       			
        		}
				//Else, fobide the move (return false)
				return false;				
			}

			@Override
			public boolean doBeforeRemove(Tree tree, Node self, Node node) {return true;}

			@Override
			public void onAppend(Tree tree, Node self, Node node, int index) {}

			@Override
			public void onInsert(Tree tree, Node self, Node node, Node refNode) {}
			
			@Override
			public void onMove(Tree tree, Node self, Node oldParent,Node newParent, int index) {}

			@Override
			public void onRemove(Tree tree, Node self, Node node) {}			
         });
         
   }
	
	/**
	 * Create a node from a dataclass,knowing his type (task or project)
	 * @param dt : dataclass containing task or project attribute
	 * @param type : project or task
	 * @return Node with task or project attributes.
	 */
	private TreeNode dataClassToNode(DataClass dt,String type){
		TreeNode node = new TreeNode(dt.getAttribute("name"));
		node.setAttribute("type",type);
		
		if(type.equals("task")){
			node.setLeaf(true);
			node.setIcon("img/Writing-Journal-64.png");
			System.out.println("Create task : "+dt.getAttribute("id"));
			taskKeyToRecid.put(dt.getAttribute("id"), taskListId++);
		}			
		else{
			node.setIcon("img/case1.png");
			projKeyToRecid.put(dt.getAttribute("id"), projListId++);
		}	
		
    	for(String attr : dt.getAttributes()){
    		node.setAttribute(attr,dt.getAttribute(attr));
    	}
    	
    	nodeDt.put(node, dt);
    	
    	return node;
	}
	
	private void showProject(String id){
		
	}
}
