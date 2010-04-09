package com.alma.client;

import java.util.HashMap;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class ProcessPanel extends HorizontalPanel{
	private final AbsolutePanel processIdeasPanel,processTasksPanel;
	private Grid ideasBox;	
	private final ListGrid listGrid = new ListGrid();  
	private AlmaGTD agtdgui;
	private final DynamicForm taskForm;
	private final TasksLocalDS dataSource = TasksLocalDS.getInstance();  
	
	public ProcessPanel(final AlmaGTD agtdgui){
		super();
		this.agtdgui=agtdgui;
		this.setSize("99%", "550px");
		
		//this.setSize("99%", "60%");
		//this.setSpacing(5);
		//this.setBorderWidth(1);
				
		processIdeasPanel = new AbsolutePanel();
		processIdeasPanel.setSize("100%", "100%");
		//processIdeasPanel.addStyleName("panel");
		this.add(processIdeasPanel);
		this.setCellWidth(processIdeasPanel,"400px"); 		
		
		processTasksPanel = new AbsolutePanel();
		processTasksPanel.setSize("100%", "100%");
		//processTasksPanel.addStyleName("panel");
			  
		final VLayout layout = new VLayout(15);  
        layout.setWidth("45%");
        
        final Label label = new Label();  
        label.setHeight(10);  
        label.setWidth100();  
        label.setContents("Current Tasks List");  
        label.setStyleName("label");
        layout.addMember(label);  
  
         
        taskForm = new DynamicForm();  
        taskForm.setIsGroup(true);  
        taskForm.setGroupTitle("Create");  
        taskForm.setNumCols(4); 
        taskForm.setCellSpacing(10);
        taskForm.setDataSource(dataSource);  
  
        listGrid.setWidth100();  
        listGrid.setHeight(200);  
        listGrid.setDataSource(dataSource);  
        //listGrid.setAutoFetchData(true);  
        listGrid.addRecordClickHandler(new RecordClickHandler() {  
            public void onRecordClick(final RecordClickEvent event) {  
                taskForm.reset();  
                taskForm.editSelectedData(listGrid);  
            }  
        });  
  
        layout.addMember(listGrid);  
        layout.addMember(taskForm);  
  
        
               
        final Button buttonSave = new Button("Save");  
        buttonSave.addClickHandler(new ClickHandler() {  
			public void onClick(final ClickEvent event) {  
                taskForm.saveData();
                dataSource.saveData();
            }  
        });
        buttonSave.setStyleName("stdButton");
        
        final Button buttonAdd = new Button("Add");  
        buttonAdd.addClickHandler(new ClickHandler() {  
			public void onClick(final ClickEvent event) {
				final ListGridRecord rec = dataSource.getSampleRec(0);		        	              
		        listGrid.addData(rec);
		        listGrid.deselectAllRecords();
		        listGrid.selectRecord(rec);
		        taskForm.editSelectedData(listGrid);		       		      
            }  
        });
        buttonAdd.setStyleName("stdButton");
        
        final Button buttonDel = new Button("Del");     
        buttonDel.addClickHandler(new ClickHandler() {  
			public void onClick(final ClickEvent event) {
				listGrid.removeSelectedData();
		        taskForm.saveData();			
			}  
        });
        buttonDel.setStyleName("stdButton");
           
        final Grid cmdPanel = new Grid(1,3);
        cmdPanel.setWidth("250px");
        cmdPanel.setCellSpacing(0);
        cmdPanel.setWidget(0,0,buttonSave);
        cmdPanel.setWidget(0,1,buttonAdd);
        cmdPanel.setWidget(0,2,buttonDel); 
        
        layout.addMember(cmdPanel);
	   	
		processTasksPanel.add(layout,0,0);
				
		this.setStyleName("panel");
		this.add(processTasksPanel);
	}
	
	@Override
	public void setVisible(final boolean visible){
		if(visible){
			init();
			taskForm.fetchData();
			listGrid.fetchData();
		}
		super.setVisible(visible);	
	}
	
	public void init(){
		
		final HashMap<String,String >listIdeas = agtdgui.getListIdeas();
				
		ideasBox = new Grid(listIdeas.keySet().size(),2);
		
		int integer=0;
		
		for(final String title : listIdeas.keySet()){			
			final DisclosurePanel pan = new DisclosurePanel(title);
			pan.setStyleName("disclosure");
			pan.setContent(new HTML(listIdeas.get(title)));
			
			ideasBox.setWidget(integer,0,pan);
			final int row = integer;						
			final Button loadButton = new Button("Load",
				        new ClickHandler() {
				          public void onClick(final ClickEvent event) {
				        	  ListGridRecord rec = new ListGridRecord();  
						        rec.setAttribute("name", title); 
						        rec.setAttribute("effortRate", "0");  
						        rec.setAttribute("startDate", "01/01/2010");
						        rec.setAttribute("endDate", "02/01/2010"); 
						        rec.setAttribute("frequency", "Daily"); 
						        rec.setAttribute("progress", "0");                 
						        listGrid.addData(rec);
						        listGrid.selectRecord(rec);
						        ideasBox.removeRow(row);
						        listIdeas.remove(title);
				          }
				        });
			 ideasBox.setWidget(integer,1,loadButton);
			 
			 integer++;
		}
		
		ideasBox.setWidth("400px");
		
		final Label label = new Label();
		label.setHeight(10);  
        label.setWidth100();  
        label.setContents("IdeasBox"); 
		label.setStyleName("label");
		
		processIdeasPanel.clear();
		processIdeasPanel.add(label,6,6);
		processIdeasPanel.add(ideasBox,6,36);
		
		/*System.out.println("-----------------------Datas Process init-----------------------");
		for(DataClass dt : dataSource.getTestData()){
			System.out.println("***********************************");
			for(String property : dt.getAttributes())
				System.out.println("+"+dt.getAttribute(property));
		}*/
		
		listGrid.setDataSource(dataSource);
		taskForm.setDataSource(dataSource);
		
	}
}
