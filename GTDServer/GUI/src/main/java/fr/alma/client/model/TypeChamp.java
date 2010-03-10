package fr.alma.client.model;

import com.extjs.gxt.ui.client.data.BaseModel;

public class TypeChamp extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2080804320009097900L;
	
	private String type;
	

	public TypeChamp(String type) {
		this.type=type;
		 set("name", type);
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	

}
