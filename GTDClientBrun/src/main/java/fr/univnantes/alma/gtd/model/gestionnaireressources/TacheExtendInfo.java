package fr.univnantes.alma.gtd.model.gestionnaireressources;

import java.util.List;


/**
 * classe possedant des informations permettant de construire une Tache
 * fait pour diminuer le nombre de paramètre à 5 au lieu de 7,8 dans : 
 * - 2 constructeurs Tache
 * - method addTache
 */
public class TacheExtendInfo {
	
	private Integer priorite;
	private Integer effort;
	private List<String> liens;
	private List<String> notes;
	private String nom;

	public TacheExtendInfo(){
		super();
	} 

	/**
	 * Constructor
	 * @param nom
	 * @param priorite
	 * @param effort
	 * @param liens
	 * @param notes
	 */
	public TacheExtendInfo(final String nom, final Integer priorite, final Integer effort, final List<String> liens, final List<String> notes) {
		this.nom = nom;
		this.priorite = priorite;
		this.effort = effort;
		this.liens = liens;
		this.notes = notes;
	}

	
	/*
	 * Getters & Setters
	 */
	
	
	/**
	 * @return nom
	 */	
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * 
	 * @return priorite
	 */
	public Integer getPriorite() {
		return priorite;
	}

	/**
	 * 
	 * @param priorite
	 */
	public void setPriorite(Integer priorite) {
		this.priorite = priorite;
	}

	/**
	 * 
	 * @return effort
	 */
	public Integer getEffort() {
		return effort;
	}

	/**
	 * 
	 * @param effort
	 */
	public void setEffort(Integer effort) {
		this.effort = effort;
	}

	/**
	 * 
	 * @return liens
	 */
	public List<String> getLiens() {
		return liens;
	}

	/**
	 * 
	 * @param liens
	 */
	public void setLiens(List<String> liens) {
		this.liens = liens;
	}

	/**
	 * 
	 * @return notes
	 */
	public List<String> getNotes() {
		return notes;
	}

	/**
	 * 
	 * @param notes
	 */
	public void setNotes(List<String> notes) {
		this.notes = notes;
	}


}
