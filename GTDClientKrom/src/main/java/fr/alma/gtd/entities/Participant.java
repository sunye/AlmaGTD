package fr.alma.gtd.entities;

//Start of user code Participant.import
import javax.persistence.*;
//End of user code

//Start of user code Participant.annotation
@Entity
//End of user code
/**
 * Classe Participant.
 */
public class Participant extends GtdEntity {

	//Start of user code Participant.id.annotation
	@Id
	@GeneratedValue
	//End of user code
	/** Id. */
	private Long id;

	//Start of user code Participant.nom.annotation
	@Basic
	//End of user code
	/** Nom. */
	private String nom;

	/** 
	 * Constructeur par defaut.
	 */
	public Participant() {}
	
	
	/**
	 * Constructeur.
	 * @param nom valeur de nom dans le Participant construit
	 */
	public Participant(String nom) {
		this.nom = nom;
	}
			
	
	/**
	 * Getter de l'attribut nom.
	 * @return nom 
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Setter de l'attribut nom.
	 * @param nom 
	 */
	public void setNom(String nom) {
		this.nom = nom;
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