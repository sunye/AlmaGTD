package fr.alma.client.model;

import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;

public class Tache extends BaseModel {

	private String id;

	private int pos;
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 3978412705085543563L;

	public Tache() {
	  }

	  public Tache(String id, int pos, String name, String contexte, String createur, Date date, int priorite, String avancement) {
	    this.setId(id);
	    this.setPos(pos);
		set("name", name);
	    set("contexte", contexte);
	    set("createur", createur);
	    set("date", date);
	    set("priorite",priorite);
	    set("avancement",avancement);
	  }

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
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
}
