package fr.alma.gtd.donneespartagees;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Classe representant les taches.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public abstract class AbstractTache extends AbstractObjetServeur implements ITache {
	
	/**
	 * Identifiant de serialisation.
	 */
	private static final long serialVersionUID = 1305177706132419383L;

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
	protected IParticipant createur;
	
	/**
	 * Le participant.
	 */
	protected IParticipant participant;
	
	/**
	 * Le contexte.
	 */
	protected IObjetServeur contexte;
	
	/**
	 * La liste des tags.
	 */
	protected List<ITag> listeDesTags;
	
	/**
	 * La liste des URLs.
	 */
	protected List<String> listeDesURLs;
	
	/**
	 * Le projet contenant la tache.
	 */
	protected IProjet projetConteneur;

	/**
	 * Liste des contacts associes a cette tache.
	 */
	protected List<IContact> contacts;
	
	/**
	 * Liste des taches devant etre executee avant la tache consideree.
	 */
	protected List<ITache> tachesAnterieures;
	

	/**
	 * Le constructeur par defaut.
	 */
	public AbstractTache() {
		super();
		this.priorite = 0;
		this.tauxEffort = 0;
		this.dateDebut = new Date();
		this.dateFin = new Date();
		this.dansLaPoubelle = false;
		this.dateDeDerModif = new Date();
		this.listeDesTags = new ArrayList<ITag>();
		this.listeDesURLs = new ArrayList<String>();
		this.tachesAnterieures = new ArrayList<ITache>();
		this.contacts = new ArrayList<IContact>();
	}
	
	/**
	 * Le constructeur avec initialisation du nom, de la priorite, du taux d'effort, du contexte et du createur.
	 * @param n Le nom.
	 * @param p La priorite.
	 * @param effort Le taux d'effort.
	 * @param c Le contexte.
	 * @param cr Le createur de la tache.
	 */
	public AbstractTache(final String nom, final int priorite, final int effort, final IObjetServeur contexte, final IParticipant createur) {
		super();
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
	public AbstractTache(final ITache task) {
		super();
		this.copier(task);
	}

	public void copier(final ITache task){
		this.nom = task.getNom();
		this.priorite =  task.getPriorite();
		this.tauxEffort = task.getTauxEffort();
		this.dateDebut = task.getDateDebut();
		this.dateFin = task.getDateFin();
		this.dansLaPoubelle = task.isDansLaPoubelle();
		this.identServeur = task.getIdentifiantServeur();
		this.dateDeDerModif = task.getDateDeDerModif();
		this.createur = task.getCreateur();
		this.participant = task.getParticipant();
		this.contexte = task.getContexte();
		this.listeDesTags = task.getListeDeTags();
		this.listeDesURLs = task.getListeDesURLs();
		this.projetConteneur = task.getProjetConteneur();
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
	public final  void restaurer() {
		//TODO
	}

	@Override
	public abstract String getNom();

	@Override
	public final void setNom(final String nom) {
		this.nom = nom;
	}

	@Override
	public abstract int getPriorite();

	@Override
	public final void setPriorite(final int priorite) {
		this.priorite = priorite;
	}

	@Override
	public abstract int getTauxEffort();

	@Override
	public final void setTauxEffort(final int effort) {
		this.tauxEffort = effort;
	}

	@Override
	public final void setAvancement(final Avancement avancement) {
		this.avancement = avancement;
	}

	@Override
	public final void setFrequence(final Frequence frequence) {
		this.frequence = frequence;
	}

	@Override
	public final void setDateDebut(final Date debut) {
		this.dateDebut = debut;
	}

	@Override
	public final void setDateFin(final Date fin) {
		this.dateFin = fin;
	}

	@Override
	public final void setDansLaPoubelle(final boolean dansPoubelle) {
		this.dansLaPoubelle = dansPoubelle;
	}

	@Override
	public final void setCreateur(final IParticipant createur) {
		this.createur = createur;
	}

	@Override
	public final void setParticipant(final IParticipant participant) {
		this.participant = participant;
	}

	@Override
	public final void setContexte(final IContexte contexte) {
		this.contexte = contexte;
	}

	@Override
	public final void setListeDeTags(final List<ITag> listeTags) {
		this.listeDesTags = listeTags;
	}

	@Override
	public final void setListeDesURLs(final List<String> listeURLs) {
		this.listeDesURLs = listeURLs;
	}
	
	@Override
	public final void setProjetConteneur(final IProjet projet) {
		this.projetConteneur = projet;
	}
	
	@Override 
	public final void setListeTachesAnterieures(final List<ITache> tachesPrecedentes){
		this.tachesAnterieures = tachesPrecedentes;
	}
	
	@Override
	public final void setListeContacts(final List<IContact> contacts){
		this.contacts = contacts;
	}

	public Avancement getAvancement() {
		return avancement;
	}

	public Frequence getFrequence() {
		return frequence;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public boolean isDansLaPoubelle() {
		return dansLaPoubelle;
	}

	public IParticipant getCreateur() {
		return createur;
	}

	public IParticipant getParticipant() {
		return participant;
	}

	public IObjetServeur getContexte() {
		return contexte;
	}

	public void setContexte(final IObjetServeur contexte) {
		this.contexte = contexte;
	}

	public List<ITag> getListeDesTags() {
		return listeDesTags;
	}

	public void setListeDesTags(final List<ITag> listeDesTags) {
		this.listeDesTags = listeDesTags;
	}

	public List<String> getListeDesURLs() {
		return listeDesURLs;
	}

	public IProjet getProjetConteneur() {
		return projetConteneur;
	}

	public List<IContact> getContacts() {
		return contacts;
	}

	public void setContacts(final List<IContact> contacts) {
		this.contacts = contacts;
	}

	public List<ITache> getTachesAnterieures() {
		return tachesAnterieures;
	}

	public void setTachesAnterieures(final List<ITache> tachesAnterieures) {
		this.tachesAnterieures = tachesAnterieures;
	}
}
