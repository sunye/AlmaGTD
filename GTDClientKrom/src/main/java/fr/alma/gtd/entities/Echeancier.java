package fr.alma.gtd.entities;

//Start of user code Echeancier.import
import java.util.Date;

import javax.persistence.*;
//End of user code

//Start of user code Echeancier.annotation
@Entity
//End of user code
/**
 * Classe Echeancier.
 */
public class Echeancier extends GtdEntity {

	//Start of user code Echeancier.id.annotation
	@Id
	@GeneratedValue
	//End of user code
	/** Id. */
	private Long id;

	//Start of user code Echeancier.dateDebut.annotation
	@Temporal(value = TemporalType.DATE)
	//End of user code
	/** DateDebut. */
	private Date dateDebut;

	//Start of user code Echeancier.frequence.annotation
	@Enumerated(EnumType.STRING)
	//End of user code
	/** Frequence. */
	private Frequence frequence;

	//Start of user code Echeancier.dateArret.annotation
	@Temporal(value = TemporalType.DATE)
	//End of user code
	/** DateArret. */
	private Date dateArret;

	/** 
	 * Constructeur par defaut.
	 */
	public Echeancier() {}
	
	
	/**
	 * Constructeur.
	 * @param dateDebut valeur de dateDebut dans le Echeancier construit
	 * @param frequence valeur de frequence dans le Echeancier construit
	 * @param dateArret valeur de dateArret dans le Echeancier construit
	 */
	public Echeancier(Date dateDebut, Frequence frequence, Date dateArret) {
		this.dateDebut = dateDebut;
		this.frequence = frequence;
		this.dateArret = dateArret;
	}
			
	
	/**
	 * Getter de l'attribut dateDebut.
	 * @return dateDebut 
	 */
	public Date getDateDebut() {
		return this.dateDebut;
	}
	
	/**
	 * Setter de l'attribut dateDebut.
	 * @param dateDebut 
	 */
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	
	/**
	 * Getter de l'attribut frequence.
	 * @return frequence 
	 */
	public Frequence getFrequence() {
		return this.frequence;
	}
	
	/**
	 * Setter de l'attribut frequence.
	 * @param frequence 
	 */
	public void setFrequence(Frequence frequence) {
		this.frequence = frequence;
	}
	
	/**
	 * Getter de l'attribut dateArret.
	 * @return dateArret 
	 */
	public Date getDateArret() {
		return this.dateArret;
	}
	
	/**
	 * Setter de l'attribut dateArret.
	 * @param dateArret 
	 */
	public void setDateArret(Date dateArret) {
		this.dateArret = dateArret;
	}
	
	/**
 	 * Methode getId.
 	 */
 	@Override
	public Long getId(){
 	//Start of user code getId
		return this.id;
	//End of user code
 	}
 	
}