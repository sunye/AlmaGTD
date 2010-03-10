package fr.alma.modele.noyau;

import java.util.Date;
import fr.alma.modele.noyau.Periodicite;

/**
 * Classe Periodicite, représente le cycle de répétition d'une tâche jusqu'a sa fin. 
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public class Periodicite extends EntiteGTD {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 6664949190907280218L;

	/** la fréquence  */
	private String frequence;

	/** la date de fin de répétition  */
	private Date dateFinRepetition;


	/**
	 * Constructeur.
	 */
	public Periodicite () {
		super();
	}
	 	
	/**
	 * Constructeur avec paramètre.
	 * @param f la fréquence de répétition.
	 * @param dateFinRepet la date de fin de répétition.
	 */
	public Periodicite (Frequence f, Date dateFinRepet) {
		super();
		if(f == null){
			this.frequence = "Pas de fréquence";
		}
		else{
			this.frequence = f.toString();
		}
		this.dateFinRepetition = dateFinRepet;
	}
	 	
	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	public String getFrequence() {
		return frequence;
	}
	
	public Date getDateFinRepetition() {
		return dateFinRepetition;
	}
	
	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}
	
	public void setDateFinRepetition(Date dateFinRepetition) {
		this.dateFinRepetition = dateFinRepetition;
	}
}