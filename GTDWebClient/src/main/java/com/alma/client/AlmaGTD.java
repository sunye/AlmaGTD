package com.alma.client;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.core.DataClass;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.widgets.grid.ListGridRecord;

import com.alma.client.serializables.*;
/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AlmaGTD implements EntryPoint {
	private static int treeId=0;
	private String login;
	private AbsolutePanel  collectPanel;
	private AbsolutePanel reviewPanel,stepsPanel,connectPanel,connectedPanel;
	private LiveOrganizePanel organizePanel;
	private ProcessPanel processPanel;
	private ViewPanel viewPanel;
	//private Panel ;
	private Button btnCollect,btnOrganize,btnReview,btnProcess,btnView;
	private PasswordTextBox tfPass;
	private TextBox tfLogin;
	private HTML htmlLogin ;
	private HashMap<String,String> listIdeas = new HashMap<String,String>();
	private final ProjectsLocalDS projectDataSource = ProjectsLocalDS.getInstance();
	
	private final TasksLocalDS taskDataSource = TasksLocalDS.getInstance();
	

    
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final IWebServerAsync webserver = GWT.create(IWebServer.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("rootPanel");
				
		Image banner = new Image("img/bannerWhite.png");
		//banner.setSize("800px", "200px");
		rootPanel.add(banner ,6, 0);
	
		connectPanel = new AbsolutePanel();
		connectPanel.setSize("200px","200px");
		connectPanel.setStyleName("connectPanel");
		HTML labelLogin = new HTML("<h3>Login</h3>");
		tfLogin = new TextBox();
		HTML labelPass = new HTML("<h3>Password</h3>");
		tfPass = new PasswordTextBox();
		tfPass.setText("afykappi_298"); ;tfLogin.setText("afykappi_298@yopmail.com");		
		//tfPass.setText("allserv");tfLogin.setText("allservv@free.fr");
		//tfPass.setText("damien"); ;tfLogin.setText("dml_aon@hotmail.com");
		//tfPass.setText("renard");tfLogin.setText("renard001@live.fr");
		//tfPass.setText("damien");tfLogin.setText("damien.levin@etu.univ-nantes.fr");
		Button bConnect = new Button("Connect");
		//bConnect.setSize("80px","30px");
		bConnect.setStyleName("sendButton");
		connectPanel.add(labelLogin,20,30);
		connectPanel.add(tfLogin,20,50);
		connectPanel.add(labelPass,20,80);
		connectPanel.add(tfPass,20,100);
		connectPanel.add(bConnect,45,150);
		rootPanel.add(connectPanel, 300, 300);
		bConnect.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				connect();
			}
		});
		
		connectedPanel = new AbsolutePanel();
		connectedPanel.setSize("99%","37px");
		
		Image userConnected = new Image("img/user_32.png");
		Image tDoOnline = new Image("img/File-server-32.png");
		Image tDoOffline = new Image("img/File-server_off-32.png");
		Image gtdOnline = new Image("img/File-server-32.png");
		Image gtdOffline = new Image("img/File-server_off-32.png");
		connectedPanel.add(userConnected,10,3);
		htmlLogin = new HTML("<b>"+login+"</b>");
		HTML labelContext = new HTML("<b>Context</b>");
		connectedPanel.add(labelContext,260,12);
		final ListBox cbContext = new ListBox(false);
		//cbContext.setWidth("80px");
		cbContext.addItem("Paris - Work");cbContext.addItem("Nantes - Work");cbContext.addItem("New York - Work");cbContext.addItem("New York - Home");
		connectedPanel.add(cbContext,320,7);
		Button btnNewContext = new Button("<img src='img/plus.png' width='14' height='14'/>");
        btnNewContext.addClickHandler(new ClickHandler() {
        	public void onClick(ClickEvent event) {
        		//newIdeaDialogBox.center();
				//newIdeaValidButton.setFocus(true);
        	}
        });
        connectedPanel.add(btnNewContext,460,8);
		connectedPanel.add(htmlLogin,45,12);
		connectedPanel.add(gtdOffline,584,4);
		connectedPanel.add(new HTML("<b>GTD Server</b>"),616,12);
		connectedPanel.add(tDoOnline,708,4);
		connectedPanel.add(new HTML("<b>ToodleDo Server</b>"),740,12);
		connectedPanel.setStyleName("connectedPanel");
		rootPanel.add(connectedPanel,6,150);
		
		stepsPanel = new AbsolutePanel();
		stepsPanel.setSize("99%","64px");
		stepsPanel.setStyleName("stepsPanel");
		
		Image rightArrow = new Image("img/rightArrow.png");
		rightArrow.setSize("80px", "65px");
		Image rightArrow1 = new Image("img/rightArrow.png");
		rightArrow1.setSize("80px", "65px");
		Image rightArrow2 = new Image("img/rightArrow.png");
		rightArrow2.setSize("80px", "65px");
		Image rightArrow3 = new Image("img/rightArrow.png");
		rightArrow3.setSize("80px", "65px");
		
		
		btnCollect = new Button("Collect");
		btnCollect.setStyleName("stepButton");
		btnCollect.setText("Collect");
		stepsPanel.add(btnCollect, 6, 8);
		btnCollect.setSize("122px", "53px");
		btnCollect.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				show("collect");
			}
		});
		
		stepsPanel.add(rightArrow , 140,3);
		
		btnProcess = new Button("Process");
		btnProcess.setStyleName("stepButton");
		stepsPanel.add(btnProcess, 235, 8);
		btnProcess.setSize("122px", "53px");
		btnProcess.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				show("process");
			}
		});
		
		stepsPanel.add(rightArrow1 , 367,3);
		
		btnOrganize = new Button("Organize");
		btnOrganize.setStyleName("stepButton");
		stepsPanel.add(btnOrganize, 460, 8);
		btnOrganize.setSize("122px", "53px");
		btnOrganize.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				System.out.println("Organize1");
				show("organize");
			}
		});
		
		stepsPanel.add(rightArrow2 , 591, 3);
				
		btnView = new Button("View");
		btnView.setStyleName("stepButton");
		stepsPanel.add(btnView, 683, 8);
		btnView.setSize("122px", "53px");
		btnView.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				show("view");
			}
		});
		
		
		stepsPanel.add(rightArrow3 , 815, 3);
		
		btnReview = new Button("Review");
		btnReview.setStyleName("stepButton");
		stepsPanel.add(btnReview, 906, 8);
		btnReview.setSize("122px", "53px");
		btnReview.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				show("review");
			}
		});
		
		rootPanel.add(stepsPanel,6,188);
		
		
		
		/*-------------------------------------------------- COLLECT -------------------------------------------------------*/
		collectPanel = new CollectPanel(this);
		rootPanel.add(collectPanel,6,262);
		
		
		//Ajout d'un note
		this.listIdeas.put("Faire les courses","Du pain et du boursin :)");
		
		/*-------------------------------------------------- PROCESS -------------------------------------------------------*/
		processPanel = new ProcessPanel(this);						
		rootPanel.add(processPanel, 6, 262);		
		
		/*-------------------------------------------------- ORGANIZE -------------------------------------------------------*/
		organizePanel = new LiveOrganizePanel(this);	
        rootPanel.add(organizePanel, 6, 262);
        
        
        /*-------------------------------------------------- VIEW -------------------------------------------------------*/
		viewPanel = new ViewPanel(this);
        rootPanel.add(viewPanel, 6, 262);
        
        
		/*-------------------------------------------------- REVIEW -------------------------------------------------------*/
				
		reviewPanel = new AbsolutePanel();
		reviewPanel.setSize("800px", "600px");
		rootPanel.add(reviewPanel, 6, 262);
				
		show("connect");
		
	}
	
	private void connect(){
			
		try {
			webserver.connect("tete","terter",this.tfLogin.getText(),tfPass.getText(),new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
					popup("",caught.getMessage());
				}

				public void onSuccess(String result) {
					show("collect");
					login=tfLogin.getText();
					htmlLogin.setHTML("<b>"+login+"</b>");					
					initDatas();					
				}
			});
		
		} catch (Exception e) {
			popup("Connection error",e.getMessage());			
		}
	}

	/**
	 * Focus on a panel hiding the others and disabling buttons
	 * @param type Panel to who (must be collect,process,view,review,connect or organize)
	 */
	private void show(String type){
		if(type.equals("collect")){
			this.collectPanel.setVisible(true);
			this.organizePanel.setVisible(false);
			this.processPanel.setVisible(false);
			this.reviewPanel.setVisible(false);
			this.viewPanel.setVisible(false);
			
			this.btnOrganize.setEnabled(true);
			this.btnProcess.setEnabled(true);
			this.btnReview.setEnabled(true);
			this.btnCollect.setEnabled(false);
			this.btnView.setEnabled(true);
		}
		else if(type.equals("process")){					
			this.collectPanel.setVisible(false);
			this.organizePanel.setVisible(false);
			this.processPanel.setVisible(true);
			this.reviewPanel.setVisible(false);
			this.viewPanel.setVisible(false);
			
			this.btnOrganize.setEnabled(true);
			this.btnReview.setEnabled(true);
			this.btnCollect.setEnabled(true);
			this.btnProcess.setEnabled(false);
			this.btnView.setEnabled(true);
		}
		else if(type.equals("review")){
			this.collectPanel.setVisible(false);
			this.organizePanel.setVisible(false);
			this.processPanel.setVisible(false);
			this.reviewPanel.setVisible(true);
			this.viewPanel.setVisible(false);
			
			this.btnOrganize.setEnabled(true);
			this.btnProcess.setEnabled(true);
			this.btnReview.setEnabled(false);
			this.btnCollect.setEnabled(true);
			this.btnView.setEnabled(true);
		}
		else if(type.equals("organize")){			
			this.collectPanel.setVisible(false);
			this.organizePanel.setVisible(true);
			this.processPanel.setVisible(false);
			this.reviewPanel.setVisible(false);
			this.viewPanel.setVisible(false);
			
			this.btnOrganize.setEnabled(false);
			this.btnProcess.setEnabled(true);
			this.btnReview.setEnabled(true);
			this.btnCollect.setEnabled(true);
			this.btnView.setEnabled(true);
			
		}
		else if(type.equals("view")){
			this.collectPanel.setVisible(false);
			this.organizePanel.setVisible(false);
			this.processPanel.setVisible(false);
			this.reviewPanel.setVisible(false);
			this.viewPanel.setVisible(true);
			
			this.btnOrganize.setEnabled(true);
			this.btnProcess.setEnabled(true);
			this.btnReview.setEnabled(true);
			this.btnView.setEnabled(false);
			this.btnCollect.setEnabled(true);
		}
		
		if(type.equals("connect")){
			this.connectedPanel.setVisible(false);			
			this.connectPanel.setVisible(true);			
			this.stepsPanel.setVisible(false);
			this.collectPanel.setVisible(false);
			this.organizePanel.setVisible(false);
			this.processPanel.setVisible(false);
			this.reviewPanel.setVisible(false);
			this.viewPanel.setVisible(false);
		}
		
		else{
			this.connectedPanel.setVisible(true);
			this.connectPanel.setVisible(false);
			this.stepsPanel.setVisible(true);
		}
		
	}
	
	
	
	public void initDatas(){   	
		System.out.println("Initialisation");
		final List<ListGridRecord> records = new LinkedList<ListGridRecord>();
		
		try {						
			webserver.getProjects(login, new AsyncCallback<List<Project>>() {
				
				public void onSuccess(List<Project> result) {
					for(Project proj : result){
						ListGridRecord rec = new ListGridRecord();
						//System.out.println("Project"+proj.getIdentifiant()+":"+proj.getNom());
						rec.setAttribute("id", proj.getIdentifiant());
				        rec.setAttribute("name", proj.getNom()); 
				        rec.setAttribute("projectId", "");
				        rec.setAttribute("project", "");
				        rec.setAttribute("type", "project");
				        records.add(rec);
					}			
					
					ListGridRecord[] gridRecords = new ListGridRecord[records.size()];
				       
					int curId=0;		
					for(ListGridRecord rec : records)
						gridRecords[curId++]=rec;
					
			        projectDataSource.setTestData(gridRecords);
			        //System.out.println("----------------------------------Project Organize init--------------------------------");
					for(DataClass dt : projectDataSource.getTestData()){
						//System.out.println("+"+dt.getAttribute("name"));
						if(dt.getAttribute("progress")!=null){
							projectDataSource.removeData((Record) dt);
						}
					}
					//System.out.println("***********************************");			
									   
				}

				public void onFailure(Throwable caught) {
					caught.printStackTrace();					
				}
			});
			
			
			webserver.getTasks(login, new AsyncCallback<List<Task>>() {
				
				public void onSuccess(List<Task> result) {
					for(Task task : result){
						//System.out.println("Task : "+task.getIdentifiant()+":"+task.getNom());
						ListGridRecord rec = new ListGridRecord();
						rec.setAttribute("id", task.getIdentifiant());
				        rec.setAttribute("name", task.getNom());
				        rec.setAttribute("effortRate", task.getTauxEffort());
				        rec.setAttribute("startDate", task.getDateDebut());
				        rec.setAttribute("endDate", task.getDateFin());
				        rec.setAttribute("frequency", task.getFrequence());
				        rec.setAttribute("state", task.getAvancement());
				        rec.setAttribute("projectId", "");
				        rec.setAttribute("progress", "");
				        records.add(rec);
					}			
					
					ListGridRecord[] gridRecords = new ListGridRecord[records.size()];
				       
					int curId=0;		
					for(ListGridRecord rec : records)
						gridRecords[curId++]=rec;
					
			        taskDataSource.setTestData(gridRecords);
			        
			       /* System.out.println("----------------------------------Tasks Organize init--------------------------------");
					for(DataClass dt : taskDataSource.getTestData()){
						System.out.println("+"+dt.getAttribute("name"));
					}
					System.out.println("***********************************");
					*/
				}

				public void onFailure(Throwable caught) {
					caught.printStackTrace();					
				}
			});
		
		} catch (Exception e) {
			popup("Connection error",e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Popup method to show a msg
	 * @param title
	 * @param msg
	 */
	public void popup(String title,String msg){
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText(title);
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.setStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>"+msg+"</b>"));
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);
	
		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		dialogBox.center();
	}
	
	/* getters and setters*/
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public HashMap<String, String> getListIdeas() {
		return listIdeas;
	}

	public void setListIdeas(HashMap<String, String> listIdeas) {
		this.listIdeas = listIdeas;
	}
	
	public RootPanel getRootPanel(){
		return RootPanel.get("rootPanel");
	}
	
	public static int nextId(){
		return treeId++;
	}

	public IWebServerAsync getWebserver() {
		return webserver;
	}
}
