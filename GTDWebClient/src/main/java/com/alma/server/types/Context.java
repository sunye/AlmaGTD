package com.alma.server.types;

import java.io.Serializable;

import fr.alma.gtd.donneespartagees.IContexte;

public interface Context extends IContexte, Serializable {

	public void setIdentifiant(String id);
	
	public String getIdentifiant();
}
