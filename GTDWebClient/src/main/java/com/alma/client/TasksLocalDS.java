package com.alma.client;

import com.smartgwt.client.data.DSRequest;  
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;  
import com.smartgwt.client.widgets.form.validator.FloatPrecisionValidator;  
import com.smartgwt.client.widgets.form.validator.FloatRangeValidator;
import com.smartgwt.client.widgets.grid.ListGridRecord;
  
public class TasksLocalDS extends DataSource {  
  
    private static TasksLocalDS instance = null;  
    
    public static TasksLocalDS getInstance() {  
        if (instance == null) {  
            instance = new TasksLocalDS("tasksLocalDS");            
        }  
        return instance;  
    }  
  
    public TasksLocalDS(final String identif) {  
    	super();
    	this.setClientOnly(true);      	
        this.setID(identif);  
        final DataSourceIntegerField pkField = new DataSourceIntegerField("id");  
        pkField.setHidden(true);  
        pkField.setPrimaryKey(true);         
        final DataSourceTextField nameField = new DataSourceTextField("name", "Name", 128, true);  
        final DataSourceFloatField effortRateField = new DataSourceFloatField("effortRate", "Effort Rate", 5); 
        final FloatRangeValidator rangeValidator = new FloatRangeValidator();  
        rangeValidator.setMin(0);  
        rangeValidator.setMax(100); 
        rangeValidator.setErrorMessage("Please enter a number beetwen 0 and 100");  
        final FloatPrecisionValidator precValidator = new FloatPrecisionValidator();
        precValidator.setPrecision(2);
        precValidator.setErrorMessage("Please enter a number with a maximum precision of 2.");  
        effortRateField.setValidators(rangeValidator,precValidator);
  
        final DataSourceDateField startDateField = new DataSourceDateField("startDate", "Start Date"); 
        final DataSourceDateField endDateField = new DataSourceDateField("endDate", "End Date");
          
        final DataSourceEnumField frequencyField = new DataSourceEnumField("frequency", "Frequency", 25);  
        frequencyField.setValueMap("JOURNALIER","HEBDOMADAIRE","MENSUEL","ANNUEL");  
  
        final DataSourceEnumField stateField = new DataSourceEnumField("state", "State", 25);  
        stateField.setValueMap("AFAIRE","ENATTENTE","DELEGUE","TERMINE"); 
       
        
        final DataSourceTextField projectIdField = new DataSourceTextField("projectId", "ProjectId", 25);  
        
        
        final DataSourceIntegerField progressField = new DataSourceIntegerField("progress", "Progress", 3);   
        progressField.setValidators(rangeValidator);
  
        setFields(pkField,nameField, effortRateField, startDateField, endDateField, frequencyField,progressField,projectIdField,stateField);  
        
        
       
    }  
  
    protected Object transformRequest(final DSRequest dsRequest) {  
        return super.transformRequest(dsRequest);  
    }  
    
   
    public void saveData(){
    	//TODO savedata
    	//this.saveData();
    }
    
    public ListGridRecord getSampleRec(final int ident){
    	ListGridRecord rec = new ListGridRecord();
    	//rec.setAttribute("_selection_1",false);
    	rec.setAttribute("id", AlmaGTD.nextId());
        //rec.setAttribute("tasksLocalDS", 222);
    	rec.setAttribute("name", "newTask"+ident); 
	    rec.setAttribute("effortRate", ident+"0");  
	    rec.setAttribute("startDate", "01/01/2010");
	    rec.setAttribute("endDate", "02/01/2010"); 
	    rec.setAttribute("frequency", "Once"); 
	    rec.setAttribute("progress", ident+"0");
	    rec.setAttribute("project", "");
	    rec.setAttribute("state", "To Do");
	    
	    return rec;
    }
}  