package fr.alma.modele.noyau;

import java.util.ArrayList;
import java.util.Date;
import fr.alma.modele.noyau.Periodicite;

//Start of user code for imports
import java.util.List;

//End of user code


/**
 * Classe représentant une tâche.
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public class Tache extends EntiteGTD implements ITache {
	
	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -2123095878794932178L;

	/** la priorité d'une tâche  */
	private Integer priorite;

	/** le tauc d'effort à fournir pour une tâche  */
	private Integer tauxEffort;

	/** la date de commencement de la tâche  */
	private Date dateDebut;

	/** les notes de la tâches  */
	private String notes;
	
	/** la date d'échéance de la tâche */
	private Date dateEcheance;

	/** la liste d'urls de la tâche */
	private List<String> urls = new ArrayList<String>();

	/** la liste de tags de la tâche (pour la recherche notamment */
	private List<String> tags = new ArrayList<String>();

	/** le nom de la tâche  */
	private String nom;

	/** la fréquence et la date d'arret de la répétition de la tâche  */
	private Periodicite periodicite;

	/** le contexte de la tâche */
	private String contexte;
	
	/** l'identifiant du projet conteneur */
	private Long idProjet;

	/**
	 * Constructeur.
	 */
	public Tache () {
		super();
	}
	 	
	/**
	 * Constructeur avec paramètre.
	 * @param nomTache le nom de la tâche.
	 * @param contexte2 le contexte de la tâche.
	 * @param notes les notes de la tâches.
	 * @param dDebut la date de commencement de la tâche .
	 * @param dEcheance la date d'échéance de la tâche.
	 * @param priorite  la priorité d'une tâche.
	 * @param tauxEffort le tauc d'effort à fournir pour une tâche.
	 * @param listeContact la liste de contact associée à la tâche.
	 * @param frequence la fréquence de répétition de la tâche.
	 * @param arretFrequence la date d'arret de répétition de la tâche.
	 * @param urls la liste d'urls de la tâche.
	 * @param tags  la liste de tags de la tâche
	 */
	public Tache(String nomTache, String contexte2, String notes, Date dDebut, Date dEcheance, Integer priorite, 
			Integer tauxEffort, IContact[] listeContact, Frequence frequence, Date arretFrequence, List<String> urls, List<String> tags) {
		super();
		this.nom = nomTache;
		this.contexte = contexte2;
		this.notes = notes;
		this.dateDebut = dDebut;
		this.dateEcheance = dEcheance;
		this.tauxEffort = tauxEffort;
		this.urls = urls;
		this.tags = tags;
		this.priorite = priorite;
		this.periodicite = new Periodicite(frequence, arretFrequence);
	}
	 	
	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	
	@Override
	public Integer getPriorite() {
		if (priorite == null) {
			priorite = -1;
		}
		return priorite;
	}
	
	@Override
	public Integer getTauxEffort() {
		return tauxEffort;
	}
	
	@Override
	public Date getDateDebut() {
		return dateDebut;
	}
	
	@Override
	public String getNotes() {
		return notes;
	}
	
	@Override
	public Date getDateEcheance() {
		return dateEcheance;
	}
	
	@Override
	public List<String> getUrls() {
		return urls;
	}
	
	@Override
	public List<String> getTags() {
		return tags;
	}
	
	@Override
	public String getNom() {
		return nom;
	}
	
	@Override
	public Periodicite getPeriodicite() {
		return periodicite;
	}
	
	@Override
	public String getContexte() {
		return contexte;
	}
	
	@Override
	public void setPriorite(Integer priorite) {
		this.priorite = priorite;
	}
	
	@Override
	public void setTauxEffort(Integer tauxEffort) {
		this.tauxEffort = tauxEffort;
	}
	
	@Override
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Override
	public void setDateEcheance(Date dateEcheance) {
		this.dateEcheance = dateEcheance;
	}
	
	@Override
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}
	
	@Override
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	@Override
	public void setPeriodicite(Periodicite periodicite) {
		this.periodicite = periodicite;
	}
	
	@Override
	public void setContexte(String contexte) {
		this.contexte = contexte;
	}

	@Override
	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}

	@Override
	public Long getIdProjet() {
		return idProjet;
	}
}