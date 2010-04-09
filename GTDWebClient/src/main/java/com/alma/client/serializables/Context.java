
package com.alma.client.serializables;

import java.io.Serializable;
import java.util.Date;


public class Context implements Serializable {
	
    private static final long serialVersionUID = 2868136479716970129L;
    private String nom;
    private Participant createur;
    private Date dateDeDerModif;
    private String identifiant;

    
    /**
     * Le constructeur initialisant le nom.
     * @param nom Le nom choisi.
     */
    public Context(final String nom) {
           this.nom = nom;
    }

    /**
     * Constructeur de recopie d'un contexte.
     * @param ctx Contexte a recopier.
     */
    public Context(final Context ctx) {
            this.nom = ctx.getNom();
            this.createur = ctx.getCreateur();
            this.dateDeDerModif = ctx.getDateDeDerModif();
            this.identifiant = ctx.getIdentifiant(); 
    }

    
    public final void setNom(final String nom) {
            this.nom = nom;
    }

    public String getNom() {		
		return this.nom;
	}
    
    public final void setCreateur(final Participant createur) {
            this.createur = createur;
    }

    public Participant getCreateur() {
		return createur;
	}
	
	
	public Date getDateDeDerModif() {
		return this.dateDeDerModif;
	}
	

	public void setDateDeDerModif(final Date dateDeDerModif) {
		this.dateDeDerModif = dateDeDerModif;
	}
	
	public String getIdentifiant() {
		return this.identifiant;
	}

	
	public void setIdentifiant(final String identifiant) {	
		this.identifiant=identifiant;
	}

}
