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
    protected Date dateDeDerniereModification;
    
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
    
    /**
     * Initialisation des variables.
     */
    {
            this.priorite = 0;
            this.tauxEffort = 0;
            this.dateDebut = new Date();
            this.dateFin = new Date();
            this.dansLaPoubelle = false;
            this.dateDeDerniereModification = new Date();
            this.listeDesTags = new ArrayList<Tag>();
            this.listeDesURLs = new ArrayList<String>();
            this.contacts = new ArrayList<Contact>();
    }
    
    /**
     * Le constructeur par defaut.
     */
    public Task() {}

    /**
     * Le constructeur avec initialisation du nom, de la priorite, du taux d'effort, du contexte et du createur.
     * @param n Le nom.
     * @param p La priorite.
     * @param effort Le taux d'effort.
     * @param c Le contexte.
     * @param cr Le createur de la tache.
     */
    public Task(final String i,final String n, final int p, final int effort, final Context c, final Participant cr) {
            this.identifiant = i;
    		this.nom = n;
            this.priorite = p;
            this.tauxEffort = effort;
            this.contexte = c;
            this.createur = cr;
    }   
    
    /**
     * Le constructeur avec initialisation du nom, de la priorite, du taux d'effort, du contexte et du createur.
     * @param n Le nom.
     * @param p La priorite.
     * @param effort Le taux d'effort.
     * @param c Le contexte.
     * @param cr Le createur de la tache.
     */
    public Task(final String n, final int p, final int effort, final Context c, final Participant cr) {
            this.nom = n;
            this.priorite = p;
            this.tauxEffort = effort;
            this.contexte = c;
            this.createur = cr;
    }
    
    /**
     * Constructeur de recopie d'une tache.
     * @param t Tache a recopier
     */
    public Task(final Task t) {
            this.nom = t.getNom();
            this.priorite =  t.getPriorite();
            this.tauxEffort = t.getTauxEffort();
            this.dateDebut = t.getDateDebut();
            this.dateFin = t.getDateFin();
            this.dansLaPoubelle = t.isDansLaPoubelle();

            this.createur = t.getCreateur();
            this.participant = t.getParticipant();
            this.contexte = t.getContexte();
            this.listeDesTags = t.getListeDeTags();
            this.listeDesURLs = t.getListeDesURLs();
            this.projetConteneur = t.getProjetConteneur();
    }

    
    public final void archiver() {
            //TODO
    }
    
    
    public final void mettreALaPoubelle() {
            //TODO
    }
    
    
    public final  void restaurer() {
            //TODO
    }

    
    public String getNom(){
    	return this.nom;
    }

    
    public final void setNom(final String n) {
            this.nom = n;
    }

    
    public int getPriorite(){
    	return priorite;
    }

    
    public final void setPriorite(final int p) {
            this.priorite = p;
    }

    
    public int getTauxEffort(){
    	return tauxEffort;
    }

    
    public final void setTauxEffort(final int effort) {
            this.tauxEffort = effort;
    }

    
    public final void setAvancement(final Avancement a) {
            this.avancement = a;
    }

    
    public final void setFrequence(final Frequence f) {
            this.frequence = f;
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

    
    public final void setCreateur(final Participant c) {
            this.createur = c;
    }

    
    public final void setParticipant(final Participant p) {
            this.participant = p;
    }

    
    public final void setContexte(final Context c) {
            this.contexte = c;
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

	
	public Date getDateDeDerniereModification() {
		return dateDeDerniereModification;
	}

	public String getIdentifiant() {
		return identifiant;
	}


	
	public void setDateDeDerniereModification(Date dateDerniereModification) {
		this.dateDeDerniereModification = dateDerniereModification;
		
	}

	public void setIdentifiant(String id) {
		this.identifiant = id;		
	}
	
	
	public String toString() {
		String result = "";
		result = "Tache " + this.getIdentifiant() + " " + this.getNom()+" "+this.getProjetConteneur();
		return result;
	}
	
	
	public String getIdentifiantServeur() {	return null;}
	
	
	public void setIdentifiantServeur(String idServeur) {}
}



