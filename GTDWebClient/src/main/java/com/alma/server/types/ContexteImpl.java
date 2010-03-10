
package com.alma.server.types;

import java.io.Serializable;
import java.util.Date;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IParticipant;

public class ContexteImpl implements Context, Serializable {

	  
    /**
     * Identifiant de serialisation.
     */
    private static final long serialVersionUID = 2868136479716970129L;

    /**
     * Le nom.
     */
    private String nom;
    
    /**
     * Le createur.
     */
    private IParticipant createur;
    
    /**
     * Date de dernière modif 
     */
    
    private Date dateDeDerniereModification;
    
    /**
     * Identifiant
     */
    private String identifiant;
    
    
    /**
     * Le constructeur par defaut.
     */
    public ContexteImpl() {
            super();
    }
    
    /**
     * Le constructeur initialisant le nom.
     * @param n Le nom choisi.
     */
    public ContexteImpl(final String n) {
            super();
            this.nom = n;
    }

    /**
     * Constructeur de recopie d'un contexte.
     * @param ctx Contexte a recopier.
     */
    public ContexteImpl(final IContexte ctx) {
            this.nom = ctx.getNom();
            this.createur = ctx.getCreateur();
            this.dateDeDerniereModification = ctx.getDateDeDerniereModification();
    }

    @Override
    public final void setNom(final String n) {
            this.nom = n;
    }

    @Override
    public final void setCreateur(final IParticipant c) {
            this.createur = c;
    }

	
	public String getNom() {		
		return this.nom;
	}
	
	public Date getDateDeDerniereModification() {
		return this.dateDeDerniereModification;
	}
	
	public IParticipant getCreateur() {
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

	@Override
	public void setIdentifiantServeur(String idServeur) {	
		
	}

	
	
	

}
