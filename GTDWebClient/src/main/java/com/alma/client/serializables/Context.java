
package com.alma.client.serializables;

import java.io.Serializable;
import java.util.Date;


public class Context implements Serializable {
	
    private static final long serialVersionUID = 2868136479716970129L;
    private String nom;
    private Participant createur;
    private Date dateDeDerniereModification;
    private String identifiant;
    
  
    public Context(){
    	
    }
    
    /**
     * Le constructeur initialisant le nom.
     * @param n Le nom choisi.
     */
    public Context(final String n) {
           this.nom = n;
    }

    /**
     * Constructeur de recopie d'un contexte.
     * @param ctx Contexte a recopier.
     */
    public Context(final Context ctx) {
            this.nom = ctx.getNom();
            this.createur = ctx.getCreateur();
            this.dateDeDerniereModification = ctx.getDateDeDerniereModification();
    }

    
    public final void setNom(final String n) {
            this.nom = n;
    }

    
    public final void setCreateur(final Participant c) {
            this.createur = c;
    }

	
	public String getNom() {		
		return this.nom;
	}
	
	public Date getDateDeDerniereModification() {
		return this.dateDeDerniereModification;
	}
	
	public Participant getCreateur() {
		return createur;
	}

	public void setDateDeDerniereModification(Date dateDeDerniereModification) {
		this.dateDeDerniereModification = dateDeDerniereModification;
	}
	
	public String getIdentifiant() {
		return this.identifiant;
	}

	public void setIdentifiant(String id) {
		this.identifiant = id;		
	}

	
	public String getIdentifiantServeur() {
		return this.identifiant;
	}

	
	public void setIdentifiantServeur(String idServeur) {	
		this.identifiant=idServeur;
	}

	
	
	

}
