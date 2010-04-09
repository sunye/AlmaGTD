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
  
    public ProjectsLocalDS(final String identif) { 
    	super();    	
    	this.setID(identif);
        this.setClientOnly(true);
        final DataSourceIntegerField pkField = new DataSourceIntegerField("id");  
        pkField.setHidden(false);  
        pkField.setPrimaryKey(true);  
                  
        final DataSourceTextField nameField = new DataSourceTextField("name", "Name", 128, true);
        final DataSourceTextField projectField = new DataSourceTextField("project", "Project", 128, false);
        final DataSourceTextField projectIdField = new DataSourceTextField("projectId", "Project Id", 128, false);
        
        setFields(pkField,nameField,projectField,projectIdField);
          
    }  
  
    protected Object transformRequest(final DSRequest dsRequest) {  
        return super.transformRequest(dsRequest);  
    }  
    
        
    public void saveData(){
    	//TODO savedata
    	//this.saveData();
    }
    
    public ListGridRecord getSampleRec(){
    	final ListGridRecord rec = new ListGridRecord();
    	rec.setAttribute("id", AlmaGTD.nextId());
        rec.setAttribute("name", "New project"); 
        rec.setAttribute("projectId", "");
        rec.setAttribute("project", "");        
	    return rec;
    }
}  