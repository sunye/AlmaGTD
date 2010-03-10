package com.alma.client.serializables;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


public class Project implements Serializable {

	private static final long serialVersionUID = 6151805554978202997L;
	protected String identifiant;
	protected String nom;
	protected Date dateDeDerniereModification;
	protected Avancement avancement;
	protected Context contexteParDefaut;
	protected List<Task> listeDesTaches = new ArrayList<Task>();
	protected List<Participant> listeDesParticipants  = new ArrayList<Participant>();
	protected Participant createur;
	protected boolean dansLaPoubelle = false;
	protected List<Contact> contacts;
	protected List<Project> listeDesSousProjets = new ArrayList<Project>();

	/**
	 * Le constructeur par defaut.
	 */
	public Project() {
		super();
	}

	/**
	 * Le constructeur avec initialisation de l'identifiant, du nom, du contexte
	 * par defaut et du createur.
	 * 
	 * @param n
	 *            Le nom choisi.
	 * @param contexte
	 *            Le contexte choisi.
	 * @param c
	 *            Le createur du projet.
	 */
	public Project(final String identifiant, final String n,
			final Context contexte, final Participant c) {
		super();
		this.identifiant = identifiant;
		this.nom = n;
		this.contexteParDefaut = contexte;
		this.createur = c;
	}

	/**
	 * Le constructeur avec initialisation du nom, du contexte par defaut et du
	 * createur.
	 * 
	 * @param n
	 *            Le nom choisi.
	 * @param contexte
	 *            Le contexte choisi.
	 * @param c
	 *            Le createur du projet.
	 */
	public Project(final String n, final Context contexte, final Participant c) {
		super();
		this.nom = n;
		this.contexteParDefaut = contexte;
		this.createur = c;
	}

	/**
	 * Constructeur de recopie d'un projet.
	 * 
	 * @param p
	 *            Projet a recopier
	 */
	public Project(final Project p) {
		this.nom = p.getNom();
		this.createur = p.getCreateur();
		this.listeDesTaches = p.getListeDeTaches();
		this.listeDesParticipants = p.getListeDeParticipants();
		this.avancement = p.getAvancement();
		this.dansLaPoubelle = p.isDansLaPoubelle();
		this.contacts = p.getListeContacts();
		this.listeDesSousProjets = p.getListeDeSousProjets();
	}

	
	public final void ajoutTache(final Task t) {
		// TODO
	}

	
	public final void supprimeTache(final Task t) {
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

	
	public final void setNom(final String n) {
		this.nom = n;
	}

	
	public final void setDansLaPoubelle(final boolean estDansPoubelle) {
		this.dansLaPoubelle = estDansPoubelle;
	}

	
	public final void setAvancement(final Avancement a) {
		this.avancement = a;
	}

	
	public final void setContexteParDefaut(final Context contexteDefaut) {
		this.contexteParDefaut = contexteDefaut;
	}

	
	public final void setListeDeTaches(final ArrayList<Task> listeTaches) {
		this.listeDesTaches = listeTaches;
	}

	
	public final void setListeDeSousProjets(final List<Project> listeProjets) {
		this.listeDesSousProjets = listeProjets;
	}

	
	public final void setListeDeParticipants(
			final List<Participant> listeParticipants) {
		this.listeDesParticipants = listeParticipants;
	}

	
	public final void setCreateur(final Participant c) {
		this.createur = c;
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
		return listeDesParticipants;
	}

	
	public List<Project> getListeDeSousProjets() {
		return listeDesSousProjets;
	}

	
	public List<Task> getListeDeTaches() {
		return listeDesTaches;
	}

	
	public String getNom() {
		return nom;
	}

	
	public boolean isDansLaPoubelle() {
		return dansLaPoubelle;
	}

	
	public Date getDateDeDerniereModification() {
		return dateDeDerniereModification;
	}

	public String getIdentifiant() {
		return this.identifiant;
	}

	
	public void setDateDeDerniereModification(Date dateDerniereModification) {
		this.dateDeDerniereModification = dateDerniereModification;

	}

	public void setIdentifiant(String id) {
		this.identifiant = id;

	}

	
	public String getIdentifiantServeur() {
		return null;
	}

	
	public void setIdentifiantServeur(String idServeur) {
	}

	
	public String toString() {
		String result = "";
		result = "Projet " + this.getIdentifiant() + " " + this.getNom();
		for (Task tache : listeDesTaches) {
			result += "   >" + tache;
		}
		return result;
	}

	
	public boolean equals(Object obj) {
		if (((Project) obj).getIdentifiant() == this.getIdentifiant()) {
			return true;
		}
		return false;
	}

	
	public boolean isDansArchive() {
		return false;
	}

	
	public void setDansArchive(boolean dansArchive) {}
}
