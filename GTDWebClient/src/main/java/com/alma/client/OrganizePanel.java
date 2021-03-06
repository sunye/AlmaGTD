package com.alma.client;

import java.util.HashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.gwtext.client.widgets.tree.MultiSelectionModel;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;
import com.gwtext.client.widgets.tree.event.TreeNodeListener;
import com.smartgwt.client.core.DataClass;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Node;
import com.gwtext.client.data.NodeTraversalCallback;
import com.gwtext.client.data.Tree;


public class OrganizePanel extends HorizontalPanel{
	private final AlmaGTD agtdgui;
	private final HashMap<String,Integer> taskKeyToRecid=new HashMap<String,Integer>();
	private final HashMap<String,Integer> projKeyToRecid=new HashMap<String,Integer>();
	private final HashMap<Node,DataClass> nodeDt = new HashMap<Node,DataClass>();
	
	private final TreePanel treePanel;
	private final TreeNode root;
	private final ListGrid projectsListGrid = new ListGrid();
	private final ListGrid tasksListGrid = new ListGrid();
	private final DynamicForm taskForm ;
	private final DynamicForm projectForm;
	private int projListId=0;
	private int taskListId=0;
	private final ProjectsLocalDS projectDataSource = ProjectsLocalDS.getInstance();
	private final TasksLocalDS taskDataSource = TasksLocalDS.getInstance();
	
	public OrganizePanel(final AlmaGTD agtdgui){	
		super();
		this.agtdgui = agtdgui;
		this.addStyleName("panel");
		this.setSize("99%", "70%");		
				
		final VerticalPanel col1 = new VerticalPanel();		
		//create source tasks/projects tree  
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
        col1.add(treePanel);
        this.setCellWidth(col1,"400px");
        this.add(col1);
        
        
        /* ******************************** PANEL INFO TASK ********************************/
	    		
        final HorizontalPanel formPanel = new HorizontalPanel();
        formPanel.setSize("100%", "400px");
        //formPanel.addStyleName("panel");
        final VLayout layoutOrganize = new VLayout(15);  
		/*layoutOrganize.setWidth("45%");
		layoutOrganize.setHeight("400px");*/
		
        final Label labelOrganize = new Label();  
        labelOrganize.setHeight(10);  
        labelOrganize.setWidth("150px");  
        labelOrganize.setContents("Current Task");  
        labelOrganize.setStyleName("label");
        layoutOrganize.addMember(labelOrganize);  
  	  
        taskForm = new DynamicForm();  
        taskForm.setIsGroup(true);  
        taskForm.setGroupTitle("Informations");  
        taskForm.setNumCols(2); 
        taskForm.setCellSpacing(10);
        taskForm.setDataSource(taskDataSource);
        //taskForm.setAutoFetchData(true);
        layoutOrganize.addMember(taskForm);	       			        
      
        tasksListGrid.setDataSource(taskDataSource);  
        tasksListGrid.setAutoFetchData(true);  
        tasksListGrid.addRecordClickHandler(new RecordClickHandler() {  
            public void onRecordClick(final RecordClickEvent event) {  
                taskForm.reset();  
                taskForm.editSelectedData(tasksListGrid);
            }  
        });
        layoutOrganize.addMember(tasksListGrid);
       //stasksListGrid.addStyleName("hidden");
        
        formPanel.add(layoutOrganize);
        //formPanel.setCellHeight(layoutOrganize, "30%");
        
        /* ******************************** PANEL PROJECT CREATION  ********************************/
	    
        final VLayout layoutProjects = new VLayout(15);  
        layoutProjects.setWidth("45%");
        
        final Label label = new Label();  
        label.setHeight(10);  
        label.setWidth100();  
        label.setContents("Current Projects List");
        label.setStyleName("label");
        layoutProjects.addMember(label);  
            
        projectForm = new DynamicForm();  
        projectForm.setIsGroup(true);  
        projectForm.setGroupTitle("Create Project");  
        projectForm.setNumCols(2); 
        projectForm.setCellSpacing(10);
        projectForm.setDataSource(projectDataSource);  
        projectForm.setAutoFetchData(true);
        
        projectsListGrid.setWidth100();  
        projectsListGrid.setHeight(200);  
        projectsListGrid.setDataSource(projectDataSource);  
        projectsListGrid.setAutoFetchData(true);  
        projectsListGrid.addRecordClickHandler(new RecordClickHandler() {  
            public void onRecordClick(final RecordClickEvent event) {  
                projectForm.reset();  
                projectForm.editSelectedData(projectsListGrid);
            }  
        });	  
        layoutProjects.addMember(projectsListGrid);  
        layoutProjects.addMember(projectForm);  
  
        final Button buttonSave = new Button("Save");  
        buttonSave.addClickHandler(new ClickHandler() {  
			public void onClick(final ClickEvent event) {  
                projectForm.saveData();
                projectDataSource.saveData();
                init();
            }  
        });
        
        final Button buttonAdd = new Button("Add");  
        buttonAdd.addClickHandler(new ClickHandler() {  
			public void onClick(final ClickEvent event) {
				final ListGridRecord rec = projectDataSource.getSampleRec();			        	              
		        projectsListGrid.addData(rec);
		        projectsListGrid.selectRecord(rec);
		        projectForm.editSelectedData(projectsListGrid);
		        init();
            }  
        });
        
        final Button buttonDel = new Button("Del");     
        buttonDel.addClickHandler(new ClickHandler() {  
			public void onClick(final ClickEvent event) {
				projectsListGrid.removeSelectedData();
		        projectForm.saveData();
		        init();
			}  
        });
           
        final HorizontalPanel cmdPanel = new HorizontalPanel();
        cmdPanel.add(buttonSave);
        cmdPanel.add(buttonAdd);
        cmdPanel.add(buttonDel);
		cmdPanel.setSpacing(10);  
        
        layoutProjects.addMember(cmdPanel);
	   	
        formPanel.add(layoutProjects);
        
        this.setSpacing(0);
        
        this.add(formPanel);
        
		
	}
	
	@Override
	public void setVisible(final boolean visible){
		super.setVisible(visible);
		
		if(visible){
			init();
			taskForm.clearValues();
		}
		  
		
	}
	
	
	
	public void init(){
		taskListId=0;
		projListId=0;
		
		//Remove all old nodes
		for(Node node : root.getChildNodes()) {node.remove();}
        		
        for(final DataClass data : projectDataSource.getTestData()){        	        	        	
        	//node creation with attributes from datasource
        	final TreeNode curNode = dataClassToNode(data,"project");        	
            //If node has'nt project, adding it to root	
        	
        	System.out.println("Project tree "+data.getAttribute("name"));
        	
        	if(data.getAttribute("project")== null  || data.getAttribute("project").equals("")){
        		root.appendChild(curNode);
        	}
        	//Else, seeking correct owner
        	else{
        		final Node projectNode = root.findChildBy(new NodeTraversalCallback() {
        			public boolean execute(final Node node) {
                       return node.getAttribute("id").equals(data.getAttribute("projectId"));
                    }
                });
                if (projectNode != null) {
                    projectNode.appendChild(curNode);
                }
        	}
        	//Adding listener to the node
        	addNodeClickListener(curNode);
	        }
				
        for(final DataClass dt : taskDataSource.getTestData()){        	        	        	
        	
        	System.out.println("Task tree : "+dt.getAttribute("name"));
        	//node creation with attributes from datasource
        	final TreeNode curNode = dataClassToNode(dt,"task");        	
        	//If node has'nt project, adding it to root	
        	if(dt.getAttribute("projectId")==null  || dt.getAttribute("projectId").equals("")){
        		root.appendChild(curNode);
        	}
        	//Else, seeking correct owner
        	else{
        		final Node projectNode = root.findChildBy(new NodeTraversalCallback() {
        			public boolean execute(final Node node) {
                       return node.getAttribute("id").equals(dt.getAttribute("projectId"));
                    }
                });
                if (projectNode != null) {
                    projectNode.appendChild(curNode);             	
                }
        	}
        	//Adding listener to the node
        	addNodeClickListener(curNode);
	    }
        
	    root.expand(); 
	    treePanel.setRootNode(root);	      
	
	    treePanel.expandAll(); 
	    	   
	    System.out.println(taskKeyToRecid.keySet());
		System.out.println(projKeyToRecid.keySet());		
	
	}
	
	/**
	 * Adding a listener on the node for click and move
	 * @param node : node on which adding listener.
	 */
	private void addNodeClickListener(final TreeNode node) {
		     node.addListener(new TreeNodeListener() {
        	 @Override
        	 public void onClick(final Node node, final EventObject event) {
        		 
        		 if(node.getAttribute("type").equals("task")){
        			 projectForm.clearValues();        			 
        			 tasksListGrid.deselectAllRecords();
        			 System.out.println("id taks :"+node.getAttribute("id")+":"+taskKeyToRecid.get(node.getAttribute("id")));
        			 tasksListGrid.selectRecord(taskKeyToRecid.get(node.getAttribute("id")));
        			 taskForm.editSelectedData(tasksListGrid);
        		 }
        		 if(node.getAttribute("type").equals("project")){ 
        			 taskForm.clearValues();
        			 projectsListGrid.deselectAllRecords();
        			 System.out.println("id proj :"+node.getAttribute("id")+":"+projKeyToRecid.get(node.getAttribute("id")));
        			 projectsListGrid.selectRecord(projKeyToRecid.get(node.getAttribute("id")));
        			 projectForm.editSelectedData(projectsListGrid);        			
        		 }
        	 }

			@Override
			public boolean doBeforeChildrenRendered(final Node node) {return true;}

			@Override
			public boolean doBeforeClick(final Node node, final EventObject event) {return true;}

			@Override
			public boolean doBeforeCollapse(final Node node, final boolean deep,final boolean anim) {return true;}

			@Override
			public boolean doBeforeExpand(final Node node, final boolean deep, final boolean anim) {return true;}

			@Override
			public void onCheckChanged(final Node node, final boolean checked) {
				//TODO
			}

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
			public final boolean doBeforeInsert(final Tree tree, final Node self, final Node node,final Node refNode) {return true;}

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
				
				//Display the node in the correct form	
				if(self.getAttribute("type").equals("task")){
        			 projectForm.clearValues();        			 
        			 tasksListGrid.deselectAllRecords();
        			 tasksListGrid.selectRecord(taskKeyToRecid.get(self.getAttribute("id")));
        			 taskForm.editSelectedData(tasksListGrid);
        		}
        		if(self.getAttribute("type").equals("project")){ 
        			 taskForm.clearValues();
        			 projectsListGrid.deselectAllRecords();
        			 projectsListGrid.selectRecord(projKeyToRecid.get(self.getAttribute("id")));
        			 projectForm.editSelectedData(projectsListGrid);        			
        		}
				//Else, fobide the move (return false)
				return false;				
			}

			@Override
			public final boolean doBeforeRemove(final Tree tree, final Node self, final Node node) {return true;}

			@Override
			public final void onAppend(final Tree tree, final Node self, final Node node, final int index) {
				//TODO
			}

			@Override
			public final void onInsert(final Tree tree, final Node self, final Node node, final Node refNode) {
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
         });
         
   }
	
	/**
	 * Create a node from a dataclass,knowing his type (task or project)
	 * @param data : dataclass containing task or project attribute
	 * @param type : project or task
	 * @return Node with task or project attributes.
	 */
	private TreeNode dataClassToNode(final DataClass data,final String type){
		final TreeNode node = new TreeNode(data.getAttribute("name"));
		node.setAttribute("type",type);
		
		if(type.equals("task")){
			node.setLeaf(true);
			node.setIcon("img/Writing-Journal-64.png");
			System.out.println("Create task : "+data.getAttribute("id"));
			taskKeyToRecid.put(data.getAttribute("id"), taskListId++);
		}			
		else{
			node.setIcon("img/case1.png");
			projKeyToRecid.put(data.getAttribute("id"), projListId++);
		}	
		
    	for(String attr : data.getAttributes()){
    		node.setAttribute(attr,data.getAttribute(attr));
    	}
    	
    	nodeDt.put(node, data);
    	
    	return node;
	}
	
}
