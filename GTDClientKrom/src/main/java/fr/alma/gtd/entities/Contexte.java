package fr.alma.gtd.entities;

//Start of user code Contexte.import
import javax.persistence.*;
//End of user code

//Start of user code Contexte.annotation
@Entity
//End of user code
/**
 * Classe Contexte.
 */
public class Contexte extends GtdEntity {

	private static final long serialVersionUID = 1755110874367891898L;

	//Start of user code Contexte.id.annotation
	@Id
	@GeneratedValue
	//End of user code
	/** Id. */
	private Long id;

	//Start of user code Contexte.nom.annotation
	@Basic
	//End of user code
	/** Nom. */
	private String nom;

	/** 
	 * Constructeur par defaut.
	 */
	public Contexte() {}
	
	
	/**
	 * Constructeur.
	 * @param nom valeur de nom dans le Contexte construit
	 */
	public Contexte(String nom) {
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
	 * Methode toString.
	 */
	public String toString(){
		//Start of user code Contexte.toString
		return this.nom;
		//End of user code
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