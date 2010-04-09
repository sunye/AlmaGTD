package com.alma.client.serializables;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class Project implements Serializable {

	private static final long serialVersionUID = 6151805554978202997L;
	protected String identifiant;
	protected String nom;
	protected Date dateDeDerModif;
	protected Avancement avancement;
	protected Context contexteParDefaut;
	protected List<Task> listeTaches = new ArrayList<Task>();
	protected List<Participant> listeParticipants  = new ArrayList<Participant>();
	protected Participant createur;
	protected boolean dansLaPoubelle = false;
	protected List<Contact> contacts;
	protected List<Project> listeSousProjets = new ArrayList<Project>();

	
	public Project (){
		
	}
	/**
	 * Le constructeur avec initialisation de l'identifiant, du nom, du contexte
	 * par defaut et du createur.
	 * 
	 * @param nom
	 *            Le nom choisi.
	 * @param contexte
	 *            Le contexte choisi.
	 * @param createur
	 *            Le createur du projet.
	 */
	public Project(final String identifiant, final String nom,
			final Context contexte, final Participant createur) {
		super();
		this.identifiant = identifiant;
		this.nom = nom;
		this.contexteParDefaut = contexte;
		this.createur = createur;
	}

	/**
	 * Le constructeur avec initialisation du nom, du contexte par defaut et du
	 * createur.
	 * 
	 * @param nom
	 *            Le nom choisi.
	 * @param contexte
	 *            Le contexte choisi.
	 * @param createur
	 *            Le createur du projet.
	 */
	public Project(final String nom, final Context contexte, final Participant createur) {
		super();
		this.nom = nom;
		this.contexteParDefaut = contexte;
		this.createur = createur;
	}

	/**
	 * Constructeur de recopie d'un projet.
	 * 
	 * @param projet
	 *            Projet a recopier
	 */
	public Project(final Project projet) {
		this.nom = projet.getNom();
		this.createur = projet.getCreateur();
		this.listeTaches = projet.getListeDeTaches();
		this.listeParticipants = projet.getListeDeParticipants();
		this.avancement = projet.getAvancement();
		this.dansLaPoubelle = projet.isDansLaPoubelle();
		this.contacts = projet.getListeContacts();
		this.listeSousProjets = projet.getListeDeSousProjets();
	}

	
	public final void ajoutTache(final Task task) {
		// TODO
	}

	
	public final void supprimeTache(final Task task) {
		// TODO
	}

	
	public final void supprimerToutesTaches() {
		// TODO
	}

	
	public final void archiver() {
		// TODO
	}

	
	public final void mettreALaPoubelle() {
		// TODO
	}

	
	public final void restaurer() {
		// TODO
	}

	
	public final void setNom(final String nom) {
		this.nom = nom;
	}

	
	public final void setDansLaPoubelle(final boolean estDansPoubelle) {
		this.dansLaPoubelle = estDansPoubelle;
	}

	
	public final void setAvancement(final Avancement avancement) {
		this.avancement = avancement;
	}

	
	public final void setContexteParDefaut(final Context contexteDefaut) {
		this.contexteParDefaut = contexteDefaut;
	}

	
	public final void setListeDeTaches(final ArrayList<Task> listeTaches) {
		this.listeTaches = listeTaches;
	}

	public List<Task> getListeDeTaches() {
		return listeTaches;
	}
	
	public final void setListeDeSousProjets(final List<Project> listeProjets) {
		this.listeSousProjets = listeProjets;
	}

	
	public final void setListeDeParticipants(
			final List<Participant> listeParticipants) {
		this.listeParticipants = listeParticipants;
	}

	
	public final void setCreateur(final Participant createur) {
		this.createur = createur;
	}

	
	public final void setListeContacts(final List<Contact> contacts) {
		this.contacts = contacts;
	}

	
	public Avancement getAvancement() {
		return avancement;
	}

	
	public Context getContexteParDefaut() {
		return this.contexteParDefaut;
	}

	
	public Participant getCreateur() {
		return this.createur;
	}

	
	public List<Contact> getListeContacts() {
		return contacts;
	}

	
	public List<Participant> getListeDeParticipants() {
		return listeParticipants;
	}

	
	public List<Project> getListeDeSousProjets() {
		return listeSousProjets;
	}

	
	public String getNom() {
		return nom;
	}

	
	public boolean isDansLaPoubelle() {
		return dansLaPoubelle;
	}

	
	public Date getDateDeDerModif() {
		return dateDeDerModif;
	}

	public String getIdentifiant() {
		return this.identifiant;
	}

	
	public void setDateDeDerModif(final Date dateDerModif) {
		this.dateDeDerModif = dateDerModif;

	}

	public void setIdentifiant(final String identifiant) {
		this.identifiant = identifiant;

	}

	
	public String getIdentifiantServeur() {
		return null;
	}

	
	public void setIdentifiantServeur(final String idServeur) {
		//TODO
	}

	
	public String toString() {
		String result = "";
		result = "Projet " + this.getIdentifiant() + " " + this.getNom();
		for (Task tache : listeTaches) {
			result += "   >" + tache;
		}
		return result;
	}

	
	public boolean equals(final Object obj) {
		boolean bool = false;
		if (((Project) obj).getIdentifiant() == this.getIdentifiant()) {
			 bool = true;
		}
		return bool;
	}

	
	public boolean isDansArchive() {
		return false;
	}

	
	public void setDansArchive(final boolean dansArchive) {
		//TODO
	}
}
