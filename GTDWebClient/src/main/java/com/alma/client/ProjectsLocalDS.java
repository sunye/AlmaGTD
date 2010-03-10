package com.alma.client;

import com.smartgwt.client.data.DSRequest;  
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;  
import com.smartgwt.client.widgets.grid.ListGridRecord;
  
public class ProjectsLocalDS extends DataSource {  
  
    private static ProjectsLocalDS instance = null;  
    
    public static ProjectsLocalDS getInstance() {  
        if (instance == null) {  
            instance = new ProjectsLocalDS("projectsLocalDS");  
        }  
        return instance;  
    }  
  
    public ProjectsLocalDS(String id) { 
    	super();    	
    	this.setID(id);
        this.setClientOnly(true);
        DataSourceIntegerField pkField = new DataSourceIntegerField("id");  
        pkField.setHidden(false);  
        pkField.setPrimaryKey(true);  
                  
        DataSourceTextField nameField = new DataSourceTextField("name", "Name", 128, true);
        DataSourceTextField projectField = new DataSourceTextField("project", "Project", 128, false);
        DataSourceTextField projectIdField = new DataSourceTextField("projectId", "Project Id", 128, false);
        
        setFields(pkField,nameField,projectField,projectIdField);
          
    }  
  
    protected Object transformRequest(DSRequest dsRequest) {  
        return super.transformRequest(dsRequest);  
    }  
    
        
    public void saveData(){
    	//TODO savedata
    	//this.saveData();
    }
    
    public ListGridRecord getSampleRec(){
    	ListGridRecord rec = new ListGridRecord();
    	rec.setAttribute("id", AlmaGTD.nextId());
        rec.setAttribute("name", "New project"); 
        rec.setAttribute("projectId", "");
        rec.setAttribute("project", "");        
	    return rec;
    }
}  