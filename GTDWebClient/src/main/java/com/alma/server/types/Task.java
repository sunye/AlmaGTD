package com.alma.server.types;

import java.io.Serializable;

import fr.alma.gtd.donneespartagees.ITache;

public interface Task extends ITache,Serializable {

	public void setIdentifiant(String id);
	
	public String getIdentifiant();
}
