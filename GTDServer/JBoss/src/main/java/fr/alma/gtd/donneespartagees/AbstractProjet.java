package fr.alma.gtd.donneespartagees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;

/**
 * Classe representant les projets.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public abstract class AbstractProjet extends AbstractObjetServeur implements IProjet {
	
	/**
	 * Identifiant de serialisation.
	 */
	private static final long serialVersionUID = -1035410260532175513L;


	/**
	 * Le nom du projet.
	 */
	protected String nom;
	
	
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
	 * Indique si le projet est dans les archives
	 */
	protected boolean dansArchive;
	
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
		this.dansArchive = false;
		this.dateDeDerniereModification = new Date();
		this.listeDesTaches = new ArrayList<ITache>();
		this.listeDesParticipants = new ArrayList<IParticipant>();
		this.listeDesSousProjets = new ArrayList<IProjet>();
		this.contacts = new ArrayList<IContact>();
		this.avancement = Avancement.AFAIRE;
	}
	
	/**
	 * Le constructeur par defaut.
	 */
	public AbstractProjet() {
		super();
	}
	
	/**
	 * Le constructeur avec initialisation du nom, du contexte par defaut et du createur.
	 * @param n Le nom choisi.
	 * @param contexte Le contexte choisi.
	 * @param c Le createur du projet.
	 */
	public AbstractProjet(final String n, final IContexte contexte, final IParticipant c) {
		super();
		this.nom = n;
		this.contexteParDefaut = contexte;
		this.createur = c;
	}
	
	/**
	 * Constructeur de recopie d'un projet.
	 * @param p Projet a recopier
	 */
	public AbstractProjet(final IProjet p) {
		this.copier(p);
	}

	/**
	 * Copie l'ensemble des attributs du projet passe en parametre.
	 * @param p Projet a recopier
	 */
	public void copier(final IProjet p ){
		this.nom = p.getNom();
		this.createur = p.getCreateur();
		this.dateDeDerniereModification = p.getDateDeDerniereModification();
		this.identifiantServeur = p.getIdentifiantServeur();
		this.contexteParDefaut = p.getContexteParDefaut();
		this.listeDesTaches = p.getListeDeTaches();
		this.listeDesParticipants = p.getListeDeParticipants();
		this.avancement = p.getAvancement();
		this.dansLaPoubelle = p.isDansLaPoubelle();
		this.contacts = p.getListeContacts();
		this.listeDesSousProjets = p.getListeDeSousProjets();
	}
	
	@Override
	public final void ajoutTache(final ITache t) {
		//TODO
	}
	
	@Override
	public final void supprimeTache(final ITache t) {
		//TODO
	}
	
	@Override
	public final void supprimerToutesTaches() {
		//TODO
	}
	
	@Override
	public final void archiver() {
		//TODO
	}
	
	@Override
	public final void mettreALaPoubelle() {
		//TODO
	}
	
	@Override
	public final void restaurer() {
		//TODO
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
	public final void setListeDeParticipants(final List<IParticipant> listeParticipants) {
		this.listeDesParticipants = listeParticipants;
	}
	
	@Override
	public final void setCreateur(final IParticipant c) {
		this.createur = c;
	}
	
	@Override
	public final void setListeContacts(final List<IContact> contacts){
		this.contacts = contacts;
	}
	
	@Override
	public void setDansArchive(boolean dansArchive) {
	 this.dansArchive = dansArchive;
	}
}
