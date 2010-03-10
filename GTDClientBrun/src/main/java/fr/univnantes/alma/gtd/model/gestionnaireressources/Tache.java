
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireressources;

// Start of user code for imports in Tache
import java.util.ArrayList;
import java.util.List;

import fr.univnantes.alma.gtd.persistance.IPersistance;

// Start of user code for comments in Tache
/**
 * 
 */
// End of user code

// Start of user code for comments in Tache
/**
 * 
 */
// End of user code

public class Tache extends GTDEntity{ 

	private String nom;
	private Integer priorite;
	private Integer effort;
	private Date reveil;
	private Date echeance;
	private List<String> liens = new ArrayList<String>();
	private List<String> notes = new ArrayList<String>();
	private EtatTache etatTache;

	
	
	// Start of user code for attributes in Tache
	private Projet parent;
	private IPersistance persistance = GTDEntity.PERSISTANCE;
	// End of user code

	// Start of user code for constructors in Tache
	public Tache() {
		super();
	}
	
	/**
	 * sans preciser le projet parent
	 * @param nom
	 * @param priorite
	 * @param effort
	 * @param reveil
	 * @param echeance
	 * @param liens
	 * @param note
	 */
	public Tache(final String nom, final Integer priorite, final Integer effort, final Date reveil, final Date echeance, final List<String> liens, final List<String> notes) {
		super();
		this.nom = nom;
		this.priorite = priorite;
		this.effort = effort;
		this.reveil = reveil;
		this.echeance = echeance;
		this.liens = liens;
		this.notes = notes;
		this.etatTache = EtatTache.AFAIRE;
	}
	
	public Tache( final String nom
				, final Integer priorite
				, final Integer effort
				, final Date reveil
				, final Date echeance
				, final List<String> liens
				, final List<String> note
				, final Projet parent
				) {
		this	( nom
				, priorite
				, effort
				, reveil
				, echeance
				, liens
				, note
				);
		this.parent = parent;
		this.etatTache = EtatTache.AFAIRE;
	}
	
	public Projet getParent() {
		return parent;
	}

	public void setParent(Projet parent) {
		this.parent = parent;
	}
	
	public List<String> getNotes() {
		return this.notes;
	}
	public List<String> getLiens() {
		return this.liens;
	}
	
	public void addLien(String lien) {
		this.liens.add(lien);
		persistance.update(this);
	}
	
	public void addNote(String note) {
		this.liens.add(note);
		persistance.update(this);
	}
	
	public void removeNote(String note){
		this.notes.remove(notes);
		persistance.update(this);
	}
	
	public void removeLien(String lien){
		this.notes.remove(lien);
		persistance.update(this);
	}
	
	
	// End of user code
	public String getNom() {
		// Start of user code for getter of nom
		return this.nom;
		// End of user code
	}
	
	public void setNom(String nom) {
		// Start of user code for setter of nom
		this.nom = nom;
		// End of user code
	}
	public Integer getPriorite() {
		// Start of user code for getter of priorite
		return this.priorite;
		// End of user code
	}
	
	public void setPriorite(Integer priorite) {
		// Start of user code for setter of priorite
		this.priorite = priorite;
		// End of user code
	}
	public Integer getEffort() {
		// Start of user code for getter of effort
		return this.effort;
		// End of user code
	}
	
	public void setEffort(Integer effort) {
		// Start of user code for setter of effort
		this.effort = effort;
		// End of user code
	}
	public Date getReveil() {
		// Start of user code for getter of reveil
		return this.reveil;
		// End of user code
	}
	
	public void setReveil(Date reveil) {
		// Start of user code for setter of reveil
		this.reveil = reveil;
		// End of user code
	}
	public Date getEcheance() {
		// Start of user code for getter of echeance
		return this.echeance;
		// End of user code
	}
	
	public void setEcheance(Date echeance) {
		// Start of user code for setter of echeance
		this.echeance = echeance;
		// End of user code
	}
	
	public EtatTache getEtatTache() {
	return this.etatTache;
}
	public void setEtatTache(EtatTache etatTache) {
		this.etatTache = etatTache;
	}

	public void aFaire() {
		// Start of user code for aFaire method body
		this.etatTache.aFaire();
		// End of user code
	}
	public void deleguee() {
		// Start of user code for deleguee method body
		this.etatTache.deleguee();
		
		// End of user code
	}
	public void enAttente() {
		// Start of user code for enAttente method body
		this.etatTache.enAttente();
		// End of user code
	}
	public void finie() {
		// Start of user code for finie method body
		this.etatTache.finie();
		// End of user code
	}
	public String toString(){
		return this.getNom();
	}
	
	public List<EtatTache> prochainsEtats() {
		return this.etatTache.etatsProchains();
	}
	
}
