package com.alma.client;

import java.util.HashMap;
import java.util.List;

import com.alma.client.serializables.Project;
import com.alma.client.serializables.Task;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
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
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.FormLayout;
import com.gwtext.client.widgets.form.TextField;


public class LiveOrganizePanel extends AbsolutePanel{
	private final AlmaGTD agtdgui;
	private final HashMap<String,Integer> taskKeyToRecid=new HashMap<String,Integer>();
	private final HashMap<String,Integer> projKeyToRecid=new HashMap<String,Integer>();
	private final HashMap<Node,DataClass> nodeDt = new HashMap<Node,DataClass>();
	private final HashMap<String,Project> projectsList = new HashMap<String,Project>();
	private final HashMap<String,Task> tasksList = new HashMap<String,Task>();
	private final TreePanel treePanel;
	private final TreeNode root;
	private static int projListId=0;
	private static int taskListId=0;

	
	public LiveOrganizePanel(final AlmaGTD agtdgui){	
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
		
		final Panel pnlInformations = new Panel("Tasks Informations");
		pnlInformations.setLayout(new FormLayout());
		
		final TextField textField_10 = new TextField("ID", "text_field", 100);
		textField_10.setReadOnly(true);
		pnlInformations.add(textField_10);
		textField_10.setWidth("60%");
		
		final TextField textField_11 = new TextField("Name", "text_field", 100);
		pnlInformations.add(textField_11);
		textField_11.setWidth("60%");
		
		final TextField textField_12 = new TextField("Start Date", "text_field", 100);
		pnlInformations.add(textField_12);
		textField_12.setWidth("60%");
		
		final TextField textField_13 = new TextField("End Date", "text_field", 100);
		pnlInformations.add(textField_13);
		textField_13.setWidth("60%");
		
		final TextField textField_14 = new TextField("Frequency", "text_field", 100);
		pnlInformations.add(textField_14);
		textField_14.setWidth("60%");
		
		final TextField textField_15 = new TextField("Progress", "text_field", 100);
		textField_15.setReadOnly(true);
		pnlInformations.add(textField_15);
		textField_15.setWidth("60%");
		add(pnlInformations, 412, 6);
		pnlInformations.setSize("400px", "176px");
		
		final Panel pnlProjectsInfo = new Panel("Projects Informations");
		pnlProjectsInfo.setLayout(new FormLayout());
		
		final TextField textField_16 = new TextField("ID", "text_field", 100);
		pnlProjectsInfo.add(textField_16);
		textField_16.setWidth("60%");
		
		final TextField textField_17 = new TextField("Name", "text_field", 100);
		textField_17.setSize("60%", "");
		pnlProjectsInfo.add(textField_17);
		
		final TextField textField_18 = new TextField("Frequency", "text_field", 100);
		pnlProjectsInfo.add(textField_18);
		textField_18.setWidth("60%");
		add(pnlProjectsInfo, 412, 214);
		pnlProjectsInfo.setSize("400px", "162px");
       
		
	}
	
	@Override
	public void setVisible(final boolean visible){
		super.setVisible(visible);
		
		if(visible){
			fetchData();
		}
		  
		
	}
	
	
	public void fetchData(){
		
		System.out.println("Organize fetching data");
		//Remove all old nodes
		for(Node n : root.getChildNodes()) {n.remove();}
		taskListId=0;
		projListId=0;
		
		try {		
			
				agtdgui.getWebserver().getTasks(agtdgui.getLogin(), new AsyncCallback<List<Task>>() {
					public void onSuccess(final List<Task> result) {
						for(Task task : result){
							tasksList.put(task.getIdentifiant(), task);
							
							System.out.println( task.getNom()+"=>"+task.getProjetConteneur());
							
							if(task.getProjetConteneur()==null){								
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
					        	
					        	final TreeNode taskNode = dataClassToNode(taskRec,"task"); 
					        	
					        	root.appendChild(taskNode);
					        	
					        	addNodeClickListener(taskNode);
						
							}
							
						}
					}
	
					public void onFailure(final Throwable caught) {
						caught.printStackTrace();					
					}
				});
			
			agtdgui.getWebserver().getProjects(agtdgui.getLogin(), new AsyncCallback<List<Project>>() {
				
				public void onSuccess(final List<Project> result) {
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
				        final TreeNode curNode = dataClassToNode(rec,"project");        	
			            //If node has'nt project, adding it to root	
			        	if(rec.getAttribute("project")==null  || rec.getAttribute("project").equals("")){
			        		root.appendChild(curNode);
			        	}
			        	//Else, seeking correct owner
			        	else{
			        		final Node projectNode = root.findChildBy(new NodeTraversalCallback() {
			        			public boolean execute(final Node node) {
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
				        	
				        	final TreeNode taskNode = dataClassToNode(taskRec,"task"); 
				        	
				        	curNode.appendChild(taskNode);
				        	
				        	addNodeClickListener(taskNode);
			        	}
			        	
			        	
					}					   
				}

				public void onFailure(final Throwable caught) {
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
	private void addNodeClickListener(final  TreeNode node) {
		     node.addListener(new TreeNodeListener() {

			@Override
			public boolean doBeforeChildrenRendered(final Node node) {return true;}

			@Override
			public boolean doBeforeClick(final Node node, final EventObject eventObject) {return true;}

			@Override
			public boolean doBeforeCollapse(final Node node, final boolean deep,final boolean anim) {return true;}

			@Override
			public boolean doBeforeExpand(final Node node, final boolean deep, final boolean anim) {return true;}

			@Override
			public void onCollapse(final Node node) {
				//TODO
			}

			@Override
			public void onContextMenu(final Node node, final EventObject event) {
				//TODO
			}

			@Override
			public void onDblClick(final Node node, final EventObject event) {
				//TODO
			}

			@Override
			public void onDisabledChange(final Node node, final boolean disabled) {
				//TODO
			}

			@Override
			public void onExpand(final Node node) {
				//TODO
			}

			@Override
			public void onTextChange(final Node node, final String text, final String oldText) {
				//TODO
			}

			@Override
			public boolean doBeforeAppend(final Tree tree, final Node self, final Node node) {return true;}

			@Override
			public boolean doBeforeInsert(final Tree tree, final Node self, final Node node,final Node refNode) {return true;}

			@Override
			public boolean doBeforeMove(final Tree tree, final Node self, final Node oldParent,final Node newParent, final int index) {
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
				//Else, fobide the move (return false)
				return false;				
			}

			@Override
			public boolean doBeforeRemove(final Tree tree, final Node self, final Node node) {return true;}

			@Override
			public void onAppend(final Tree tree, final Node self, final Node node, final int index) {
				//TODO
			}

			@Override
			public void onInsert(final Tree tree, final Node self, final Node node, final Node refNode) {
				//TODO
			}
			
			@Override
			public void onMove(final Tree tree, final Node self, final Node oldParent,final Node newParent, final int index) {
				//TODO
			}

			@Override
			public void onRemove(final Tree tree, final Node self, final Node node) {
				//TODO
			}

			@Override
			public void onClick(final Node node, final EventObject event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onCheckChanged(final Node node, final boolean checked) {
				// TODO Auto-generated method stub
				
			}			
         });
         
   }
	
	/**
	 * Create a node from a dataclass,knowing his type (task or project)
	 * @param dt : dataclass containing task or project attribute
	 * @param type : project or task
	 * @return Node with task or project attributes.
	 */
	private TreeNode dataClassToNode(final DataClass dataClass,final String type){
		final TreeNode node = new TreeNode(dataClass.getAttribute("name"));
		node.setAttribute("type",type);
		
		if(type.equals("task")){
			node.setLeaf(true);
			node.setIcon("img/Writing-Journal-64.png");
			System.out.println("Create task : "+dataClass.getAttribute("id"));
			taskKeyToRecid.put(dataClass.getAttribute("id"), taskListId++);
		}			
		else{
			node.setIcon("img/case1.png");
			projKeyToRecid.put(dataClass.getAttribute("id"), projListId++);
		}	
		
    	for(String attr : dataClass.getAttributes()){
    		node.setAttribute(attr,dataClass.getAttribute(attr));
    	}
    	
    	nodeDt.put(node, dataClass);
    	
    	return node;
	}
}
