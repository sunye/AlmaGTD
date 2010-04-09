package com.alma.client.serializables;

import java.io.Serializable;

public class Contact implements Serializable{

	private static final long serialVersionUID = 381223757392510612L;
	private String nom;
	private String email;
	private String adresse;
	private String telephone;
	
	public Contact(final String nom, final String email, final String adresse, final String telephone) {
		super();
		this.nom = nom;
		this.email = email;
		this.adresse = adresse;
		this.telephone = telephone;
	}
		
	
	public String getNom() {
		return nom;
	}
	public void setNom(final String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(final String email) {
		this.email = email;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(final String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(final String telephone) {
		this.telephone = telephone;
	}
	
	
	
	
}
