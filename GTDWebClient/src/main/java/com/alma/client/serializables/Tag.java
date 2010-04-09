package com.alma.client.serializables;

import java.io.Serializable;

public class Tag implements Serializable{
	
	private static final long serialVersionUID = 9184461490162759266L;
	private String nom;
	private Participant createur;
	
	
	public Tag(final String nom,final Participant createur){
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
	public void setNom(final String nom){
		this.nom=nom;
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
	public void setCreateur(final Participant createur){
		this.createur=createur;
	}

}
