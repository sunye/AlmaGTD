package com.alma.server.types;

import java.io.Serializable;
import java.util.List;

public class DataManagerResult<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3484492434799758582L;
	private List<T> toodleDoList;
	private List<T> GTDlist;
	private boolean synchronizd;
	
	public DataManagerResult() {
		synchronizd = false;
	}
		
	public void setToodleDoList(List<T> toodleDoList){
		this.toodleDoList = toodleDoList;
	}
	
	public void setGTDList(List<T> toodleDoList){
		this.toodleDoList = toodleDoList;
	}
	public List<T> getGTDlist() {
		return GTDlist;
	}
	
	public List<T> getToodleDoList() {
		return toodleDoList;
	}

	public boolean isSynchronized() {
		return synchronizd;
	}

	public void setSynchronized(boolean synchronizd) {
		this.synchronizd = synchronizd;
	}
}
