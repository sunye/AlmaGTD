package fr.alma.gtd.donneespartagees;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
	protected List<IParticipant> listeParticipants;
	
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
	 * Liste des sous-projets associ�s � ce projet.
	 */
	protected List<IProjet> listeSousProjets;
	

	/**
	 * Le constructeur par defaut.
	 */
	public AbstractProjet() {
		super();
		this.dansLaPoubelle = false;
		this.dateDeDerModif = new Date();
		this.listeDesTaches = new ArrayList<ITache>();
		this.listeParticipants = new ArrayList<IParticipant>();
		this.listeSousProjets = new ArrayList<IProjet>();
	}
	
	/**
	 * Le constructeur avec initialisation du nom, du contexte par defaut et du createur.
	 * @param n Le nom choisi.
	 * @param contexte Le contexte choisi.
	 * @param c Le createur du projet.
	 */
	public AbstractProjet(final String nom, final IContexte contexte, final IParticipant createur) {
		super();
		this.nom = nom;
		this.contexteParDefaut = contexte;
		this.createur = createur;
	}
	
	/**
	 * Constructeur de recopie d'un projet.
	 * @param p Projet a recopier
	 */
	public AbstractProjet(final IProjet proj) {
		this.copier(proj);
	}

	
	public void copier(final IProjet proj ){
		this.nom = proj.getNom();
		this.createur = proj.getCreateur();
		this.dateDeDerModif = proj.getDateDeDerModif();
		this.identServeur = proj.getIdentifiantServeur();
		this.contexteParDefaut = proj.getContexteParDefaut();
		this.listeDesTaches = proj.getListeDeTaches();
		this.listeParticipants = proj.getListeDeParticipants();
		this.avancement = proj.getAvancement();
		this.dansLaPoubelle = proj.isDansLaPoubelle();
		this.contacts = proj.getListeContacts();
		this.listeSousProjets = proj.getListeDeSousProjets();
	}
	
	@Override
	public final void ajoutTache(final ITache task) {
		//TODO
	}
	
	@Override
	public final void supprimeTache(final ITache task) {
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
	public final void setNom(final String nom) {
		this.nom = nom;
	}

	@Override
	public final void setDansLaPoubelle(final boolean estDansPoubelle) {
		this.dansLaPoubelle = estDansPoubelle;
	}

	@Override
	public final void setAvancement(final Avancement avancement) {
		this.avancement = avancement;
	}

	@Override
	public final void setContexteParDefaut(final IContexte contexteDefaut) {
		this.contexteParDefaut = contexteDefaut;
	}
	

	
	@Override
	public final void setCreateur(final IParticipant createur) {
		this.createur = createur;
	}
	
	@Override
	public final void setListeContacts(final List<IContact> contacts){
		this.contacts = contacts;
	}
	
	@Override
	public void setDansArchive(final boolean dansArchive) {
	 this.dansArchive = dansArchive;
	}

	public String getNom() {
		return nom;
	}

	public Avancement getAvancement() {
		return avancement;
	}

	public IContexte getContexteParDefaut() {
		return contexteParDefaut;
	}

	public List<ITache> getListeDesTaches() {
		return listeDesTaches;
	}

	public List<IParticipant> getListeParticipants() {
		return listeParticipants;
	}

	public void setListeParticipants(final List<IParticipant> listeParticipants) {
		this.listeParticipants = listeParticipants;
	}

	public IParticipant getCreateur() {
		return createur;
	}

	public boolean isDansLaPoubelle() {
		return dansLaPoubelle;
	}

	public boolean isDansArchive() {
		return dansArchive;
	}

	public List<IContact> getContacts() {
		return contacts;
	}

	public void setContacts(final List<IContact> contacts) {
		this.contacts = contacts;
	}

	public List<IProjet> getListeSousProjets() {
		return listeSousProjets;
	}

	public void setListeSousProjets(final List<IProjet> listeSousProjets) {
		this.listeSousProjets = listeSousProjets;
	}

	public void setListeDesTaches(final List<ITache> listeDesTaches) {
		this.listeDesTaches = listeDesTaches;
	}
}
