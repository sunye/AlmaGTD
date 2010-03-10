package com.alma.server.types;

import java.io.Serializable;

import fr.alma.gtd.donneespartagees.IProjet;

public interface Project extends IProjet , Serializable{

	public void setIdentifiant(String id);
	
	public String getIdentifiant();
}
