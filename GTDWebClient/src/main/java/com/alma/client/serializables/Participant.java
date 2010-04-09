package com.alma.client.serializables;

import java.io.Serializable;


public class Participant implements Serializable{

	private static final long serialVersionUID = 623852275697805855L;
	private String pseudonyme;
	
	
	public Participant(final String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}

	public String getPseudonyme() {
		return pseudonyme;
	}

	public void setPseudonyme(final String pseudonyme) {
		this.pseudonyme = pseudonyme;
	}
	
	
}
