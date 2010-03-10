package com.alma.server.types;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import fr.alma.gtd.donneespartagees.Avancement;
import fr.alma.gtd.donneespartagees.IContact;
import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.IProjet;

import com.alma.server.types.Project;
import fr.alma.gtd.donneespartagees.ITache;

public class ProjetImpl implements Project, Serializable{

	/**
	 * Identifiant du projet.
	 */
	protected String identifiant;

	/**
	 * Le nom du projet.
	 */
	protected String nom;

	/**
	 * La date de derniere modification.
	 */
	protected Date dateDeDerniereModification;

	/**
	 * L'avancement du projet.
	 */
	protected Avancement avancement;

	/**
	 * Le contexte par defaut des taches ajoutees au projet.
	 */
	protected IContexte contexteParDefaut;

	/**
	 * La liste des taches.
	 */
	protected List<ITache> listeDesTaches;

	/**
	 * La liste des participants.
	 */
	protected List<IParticipant> listeDesParticipants;

	/**
	 * Le createur du projet.
	 */
	protected IParticipant createur;

	/**
	 * Indique si le projet est dans la poubelle.
	 */
	protected boolean dansLaPoubelle;

	/**
	 * Liste des contacts associes a ce projet.
	 */
	protected List<IContact> contacts;

	/**
	 * Liste des sous-projets associés à ce projet.
	 */
	protected List<IProjet> listeDesSousProjets;

	/**
	 * Initialisation des variables.
	 */
	{
		this.dansLaPoubelle = false;
		this.listeDesTaches = new ArrayList<ITache>();
		this.listeDesParticipants = new ArrayList<IParticipant>();
		this.listeDesSousProjets = new ArrayList<IProjet>();
	}

	/**
	 * Le constructeur par defaut.
	 */
	public ProjetImpl() {
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
	public ProjetImpl(final String identifiant, final String n,
			final IContexte contexte, final IParticipant c) {
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
	public ProjetImpl(final String n, final IContexte contexte, final IParticipant c) {
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
	public ProjetImpl(final Project p) {
		this.nom = p.getNom();
		this.createur = p.getCreateur();
		this.listeDesTaches = p.getListeDeTaches();
		this.listeDesParticipants = p.getListeDeParticipants();
		this.avancement = p.getAvancement();
		this.dansLaPoubelle = p.isDansLaPoubelle();
		this.contacts = p.getListeContacts();
		this.listeDesSousProjets = p.getListeDeSousProjets();
	}

	@Override
	public final void ajoutTache(final ITache t) {
		listeDesTaches.add(t);
		t.setProjetConteneur(this);
	}

	@Override
	public final void supprimeTache(final ITache t) {
		// TODO
	}

	@Override
	public final void supprimerToutesTaches() {
		// TODO
	}

	@Override
	public final void archiver() {
		// TODO
	}

	@Override
	public final void mettreALaPoubelle() {
		// TODO
	}

	@Override
	public final void restaurer() {
		// TODO
	}

	@Override
	public final void setNom(final String n) {
		this.nom = n;
	}

	@Override
	public final void setDansLaPoubelle(final boolean estDansPoubelle) {
		this.dansLaPoubelle = estDansPoubelle;
	}

	@Override
	public final void setAvancement(final Avancement a) {
		this.avancement = a;
	}

	@Override
	public final void setContexteParDefaut(final IContexte contexteDefaut) {
		this.contexteParDefaut = contexteDefaut;
	}

	@Override
	public final void setListeDeTaches(final ArrayList<ITache> listeTaches) {
		this.listeDesTaches = listeTaches;
	}

	@Override
	public final void setListeDeSousProjets(final List<IProjet> listeProjets) {
		this.listeDesSousProjets = listeProjets;
	}

	@Override
	public final void setListeDeParticipants(
			final List<IParticipant> listeParticipants) {
		this.listeDesParticipants = listeParticipants;
	}

	@Override
	public final void setCreateur(final IParticipant c) {
		this.createur = c;
	}

	@Override
	public final void setListeContacts(final List<IContact> contacts) {
		this.contacts = contacts;
	}

	@Override
	public Avancement getAvancement() {
		return avancement;
	}

	@Override
	public IContexte getContexteParDefaut() {
		return this.contexteParDefaut;
	}

	@Override
	public IParticipant getCreateur() {
		return this.createur;
	}

	@Override
	public List<IContact> getListeContacts() {
		return contacts;
	}

	@Override
	public List<IParticipant> getListeDeParticipants() {
		return listeDesParticipants;
	}

	@Override
	public List<IProjet> getListeDeSousProjets() {
		return listeDesSousProjets;
	}

	@Override
	public List<ITache> getListeDeTaches() {
		return listeDesTaches;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public boolean isDansLaPoubelle() {
		return dansLaPoubelle;
	}

	@Override
	public Date getDateDeDerniereModification() {
		return dateDeDerniereModification;
	}

	public String getIdentifiant() {
		return this.identifiant;
	}

	@Override
	public void setDateDeDerniereModification(Date dateDerniereModification) {
		this.dateDeDerniereModification = dateDerniereModification;

	}

	public void setIdentifiant(String id) {
		this.identifiant = id;

	}

	@Override
	public String getIdentifiantServeur() {
		return null;
	}

	@Override
	public void setIdentifiantServeur(String idServeur) {
	}

	@Override
	public String toString() {
		String result = "";
		result = "Projet " + this.getIdentifiant() + " " + this.getNom();
		for (ITache tache : listeDesTaches) {
			result += "   >" + tache;
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (((ProjetImpl) obj).getIdentifiant() == this.getIdentifiant()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isDansArchive() {
		return false;
	}

	@Override
	public void setDansArchive(boolean dansArchive) {}
}
