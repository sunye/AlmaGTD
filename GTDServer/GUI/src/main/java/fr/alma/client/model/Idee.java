package fr.alma.client.model;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;

public class Idee extends BaseModel {
	
	private String id;

	private int pos;
	
	private static final long serialVersionUID = 9212573633292195284L;

	public Idee() {
	  }

	  public Idee(String id, int pos, String name, String desc, String createur, Date date) {
	    this.id = id;
	    this.setPos(pos);
		set("name", name);
	    set("desc", desc);
	    set("createur", createur);
	    set("date", date);
	  }

	/**
	 * @param pos the pos to set
	 */
	public void setPos(int pos) {
		this.pos = pos;
	}

	/**
	 * @return the pos
	 */
	public int getPos() {
		return pos;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

}
