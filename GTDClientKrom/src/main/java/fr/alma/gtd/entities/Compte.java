package fr.alma.gtd.entities;

//Start of user code Compte.import
import javax.persistence.*;
//End of user code

//Start of user code Compte.annotation
@Entity
//End of user code
/**
 * Classe Compte.
 */
public class Compte extends GtdEntity {

	//Start of user code Compte.id.annotation
	@Id
	@GeneratedValue
	//End of user code
	/** Id. */
	private Long id;

	//Start of user code Compte.nom.annotation
	@Basic
	//End of user code
	/** Nom. */
	private String nom;

	//Start of user code Compte.mdp.annotation
	@Basic
	//End of user code
	/** Mdp. */
	private String mdp;

	/** 
	 * Constructeur par defaut.
	 */
	public Compte() {}
	
	
	/**
	 * Constructeur.
	 * @param nom valeur de nom dans le Compte construit
	 * @param mdp valeur de mdp dans le Compte construit
	 */
	public Compte(String nom, String mdp) {
		this.nom = nom;
		this.mdp = mdp;
	}
			
	
	/**
	 * Getter de l'attribut nom.
	 * @return nom 
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Getter de l'attribut mdp.
	 * @return mdp 
	 */
	public String getMdp() {
		return this.mdp;
	}
	
	/**
	 * Setter de l'attribut mdp.
	 * @param mdp 
	 */
	public void setMdp(String mdp) {
		this.mdp = mdp;
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