package fr.alma.gtd.entities;

//Start of user code Tag.import
import javax.persistence.*;
//End of user code

//Start of user code Tag.annotation
@Entity
//End of user code
/**
 * Classe Tag.
 */
public class Tag extends GtdEntity {

	//Start of user code Tag.id.annotation
	@Id
	@GeneratedValue
	//End of user code
	/** Id. */
	private Long id;

	//Start of user code Tag.nom.annotation
	@Basic
	//End of user code
	/** Nom. */
	private String nom;

	/** 
	 * Constructeur par defaut.
	 */
	public Tag() {}
	
	
	/**
	 * Constructeur.
	 * @param nom valeur de nom dans le Tag construit
	 */
	public Tag(String nom) {
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