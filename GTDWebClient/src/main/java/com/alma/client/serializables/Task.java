package com.alma.client.serializables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task implements Serializable {
    
    /**
     * Identifiant de serialisation.
     */
    private static final long serialVersionUID = 1305177706132419383L;
    
    /**
     * Identifiant de la tache.
     */
    protected String identifiant;
    /**
     * Le nom.
     */
    protected String nom;
    
    /**
     * La priorite.
     */
    protected int priorite;
    
    /**
     * Le taux d'effort.
     */
    protected int tauxEffort;
    
    /**
     * L'avancement.
     */
    protected Avancement avancement;
    
    /**
     * La frequence.
     */
    protected Frequence frequence;
    
    /**
     * La date de debut.
     */
    protected Date dateDebut;
    
    /**
     * La date de fin.
     */
    protected Date dateFin;
    
    /**
     * Indique si la tache est dans la poubelle.
     */
    protected boolean dansLaPoubelle;
    
    /**
     * Le createur.
     */
    protected Participant createur;
    
    /**
     * Le participant.
     */
    protected Participant participant;
    
    /**
     * La date de derniere modification.
     */
    protected Date dateDeDerModif;
    
    /**
     * Le contexte.
     */
    protected Context contexte;
    
    /**
     * La liste des tags.
     */
    protected List<Tag> listeDesTags;
    
    /**
     * La liste des URLs.
     */
    protected List<String> listeDesURLs;
    
    /**
     * Le projet contenant la tache.
     */
    protected Project projetConteneur;

    /**
     * Liste des contacts associes a cette tache.
     */
    protected List<Contact> contacts;
    
    /**
     * Liste des taches devant etre executee avant la tache consideree.
     */
    protected List<Task> tachesAnterieures;
    
	public Task () {
            this.priorite = 0;
            this.tauxEffort = 0;
            this.dateDebut = new Date();
            this.dateFin = new Date();
            this.dansLaPoubelle = false;
            this.dateDeDerModif = new Date();
            this.listeDesTags = new ArrayList<Tag>();
            this.listeDesURLs = new ArrayList<String>();
            this.contacts = new ArrayList<Contact>();
    }
    
    /**
     * Le constructeur avec initialisation du nom, de la priorite, du taux d'effort, du contexte et du createur.
     * @param n Le nom.
     * @param p La priorite.
     * @param effort Le taux d'effort.
     * @param c Le contexte.
     * @param cr Le createur de la tache.
     */
    public Task(final String identifiant,final String nom, final int priorite, final int effort, final Context contexte, final Participant createur) {
            this.identifiant = identifiant;
    		this.nom = nom;
            this.priorite = priorite;
            this.tauxEffort = effort;
            this.contexte = contexte;
            this.createur = createur;
    }   
    
    /**
     * Le constructeur avec initialisation du nom, de la priorite, du taux d'effort, du contexte et du createur.
     * @param n Le nom.
     * @param p La priorite.
     * @param effort Le taux d'effort.
     * @param c Le contexte.
     * @param cr Le createur de la tache.
     */
    public Task(final String nom, final int priorite, final int effort, final Context contexte, final Participant createur) {
            this.nom = nom;
            this.priorite = priorite;
            this.tauxEffort = effort;
            this.contexte = contexte;
            this.createur = createur;
    }
    
    /**
     * Constructeur de recopie d'une tache.
     * @param t Tache a recopier
     */
    public Task(final Task task) {
            this.nom = task.getNom();
            this.priorite =  task.getPriorite();
            this.tauxEffort = task.getTauxEffort();
            this.dateDebut = task.getDateDebut();
            this.dateFin = task.getDateFin();
            this.dansLaPoubelle = task.isDansLaPoubelle();

            this.createur =task.getCreateur();
            this.participant = task.getParticipant();
            this.contexte = task.getContexte();
            this.listeDesTags = task.getListeDeTags();
            this.listeDesURLs = task.getListeDesURLs();
            this.projetConteneur = task.getProjetConteneur();
    }

    
    public final void archiver() {
    	//TODO
    }
    
    
    public final void mettreALaPoubelle() {
    	//TODO
    }
    
    
    public void restaurer() {
    	//TODO
    }

    
    public String getNom(){
    	return this.nom;
    }

    
    public final void setNom(final String nom) {
            this.nom = nom;
    }

    
    public int getPriorite(){
    	return priorite;
    }

    
    public final void setPriorite(final int priorite) {
            this.priorite = priorite;
    }

    
    public int getTauxEffort(){
    	return tauxEffort;
    }

    
    public final void setTauxEffort(final int effort) {
            this.tauxEffort = effort;
    }

    
    public final void setAvancement(final Avancement avancement) {
            this.avancement = avancement;
    }

    
    public final void setFrequence(final Frequence frequence) {
            this.frequence = frequence;
    }

    
    public final void setDateDebut(final Date debut) {
            this.dateDebut = debut;
    }

    
    public final void setDateFin(final Date fin) {
            this.dateFin = fin;
    }

    
    public final void setDansLaPoubelle(final boolean dansPoubelle) {
            this.dansLaPoubelle = dansPoubelle;
    }

    
    public final void setCreateur(final Participant createur) {
            this.createur = createur;
    }

    
    public final void setParticipant(final Participant participant) {
            this.participant = participant;
    }

    
    public final void setContexte(final Context contexte) {
            this.contexte = contexte;
    }

    
    public final void setListeDeTags(final List<Tag> listeTags) {
            this.listeDesTags = listeTags;
    }

    
    public final void setListeDesURLs(final List<String> listeURLs) {
            this.listeDesURLs = listeURLs;
    }
    
    
    public final void setProjetConteneur(final Project projet) {
            this.projetConteneur = projet;
    }
    
     
    public final void setListeTachesAnterieures(final List<Task> tachesPrecedentes){
            this.tachesAnterieures = tachesPrecedentes;
    }
    
    
    public final void setListeContacts(final List<Contact> contacts){
            this.contacts = contacts;
    }

	
	public Avancement getAvancement() {
		return avancement;
	}

	
	public Context getContexte() {
		return contexte;
	}

	
	public Participant getCreateur() {
		return createur;
	}

	
	public Date getDateDebut() {
		return dateDebut;
	}

	
	public Date getDateFin() {
		return null;
	}

	
	public Frequence getFrequence() {
		return frequence;
	}

	
	public List<Contact> getListeContacts() {
		return contacts;
	}

	
	public List<Tag> getListeDeTags() {
		return listeDesTags;
	}

	
	public List<String> getListeDesURLs() {
		return listeDesURLs;
	}

	
	public List<Task> getListeTachesAnterieures() {
		return tachesAnterieures;
	}

	
	public Participant getParticipant() {
		return participant;
	}

	
	public Project getProjetConteneur() {
		return projetConteneur;
	}

	
	public boolean isDansLaPoubelle() {
		return false;
	}

	
	public Date getDateDeDerModif() {
		return dateDeDerModif;
	}

	public String getIdentifiant() {
		return identifiant;
	}


	
	public void setDateDeDerModif(final Date dateDerModif) {
		this.dateDeDerModif = dateDerModif;
		
	}

	public void setIdentifiant(final String ident) {
		this.identifiant = ident;		
	}
	
	
	public String toString() {
		String result = "";
		result = "Tache " + this.getIdentifiant() + " " + this.getNom()+" "+this.getProjetConteneur();
		return result;
	}
	
	
	public String getIdentifiantServeur() {	return null;}
	
	
	public void setIdentifiantServeur(final String idServeur) {
		//TODO
	}
	
    public List<Task> getTachesAnterieures() {
		return tachesAnterieures;
	}

	public void setTachesAnterieures(final List<Task> tachesAnterieures) {
		this.tachesAnterieures = tachesAnterieures;
	}
	
	public List<Tag> getListeDesTags() {
		return listeDesTags;
	}

	public void setListeDesTags(final List<Tag> listeDesTags) {
		this.listeDesTags = listeDesTags;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(final List<Contact> contacts) {
		this.contacts = contacts;
	}
}



