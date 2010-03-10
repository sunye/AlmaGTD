/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package fr.alma.client.model;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;

public class Stock extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 398785368452627127L;
	
	private String id;

	private int pos;

	public Stock() {
	}

	public Stock(String id, int pos, String name, String type, String createur, Date date) {
		this.id=id;
		this.pos=pos;
		set("name", name);
		set("type", type);
		set("createur", createur);
		set("date", date);
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	public int getPos() {
		return pos;
	}
	
	

}
