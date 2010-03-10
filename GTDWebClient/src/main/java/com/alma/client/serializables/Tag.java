package com.alma.client.serializables;

import java.io.Serializable;

public class Tag implements Serializable{
	
	private static final long serialVersionUID = 9184461490162759266L;
	private String nom;
	private Participant createur;
	
	
	public Tag(String nom,Participant createur){
		this.nom=nom;
		this.createur=createur;
	}
	
	/**
	 * 
	 * @return Le nom.
	 */
	public String getNom(){
		return this.nom;
	}

	/**
	 * @param n La nouvelle valeur du nom.
	 */
	public void setNom(final String n){
		this.nom=n;
	}

	/**
	 * @return Le createur de l'idee.
	 */
	public Participant getCreateur(){
		return this.createur;
	}

	/**
	 * @param c La nouvelle valeur du createur.
	 */
	public void setCreateur(final Participant c){
		this.createur=c;;
	}

}
