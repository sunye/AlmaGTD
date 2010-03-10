package fr.alma.gtd.entities;

//Start of user code Contact.import
import javax.persistence.*;
//End of user code

//Start of user code Contact.annotation
@Entity
//End of user code
/**
 * Classe Contact.
 */
public class Contact extends GtdEntity {

	//Start of user code Contact.id.annotation
	@Id
	@GeneratedValue
	//End of user code
	/** Id. */
	private Long id;

	//Start of user code Contact.nom.annotation
	@Basic
	//End of user code
	/** Nom. */
	private String nom;

	//Start of user code Contact.telephone.annotation
	@Basic
	//End of user code
	/** Telephone. */
	private String telephone;

	//Start of user code Contact.adresse.annotation
	@Basic
	//End of user code
	/** Adresse. */
	private String adresse;

	//Start of user code Contact.prenom.annotation
	@Basic
	//End of user code
	/** Prenom. */
	private String prenom;

	//Start of user code Contact.email.annotation
	@Basic
	//End of user code
	/** Email. */
	private String email;

	/** 
	 * Constructeur par defaut.
	 */
	public Contact() {}
	
	
	/**
	 * Constructeur.
	 * @param nom valeur de nom dans le Contact construit
	 * @param prenom valeur de prenom dans le Contact construit
	 */
	public Contact(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
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
	 * Getter de l'attribut telephone.
	 * @return telephone 
	 */
	public String getTelephone() {
		return this.telephone;
	}
	
	/**
	 * Setter de l'attribut telephone.
	 * @param telephone 
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * Getter de l'attribut adresse.
	 * @return adresse 
	 */
	public String getAdresse() {
		return this.adresse;
	}
	
	/**
	 * Setter de l'attribut adresse.
	 * @param adresse 
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	/**
	 * Getter de l'attribut prenom.
	 * @return prenom 
	 */
	public String getPrenom() {
		return this.prenom;
	}
	
	/**
	 * Setter de l'attribut prenom.
	 * @param prenom 
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * Getter de l'attribut email.
	 * @return email 
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Setter de l'attribut email.
	 * @param email 
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Methode toString.
	 */
	public String toString(){
		//Start of user code Contact.toString
		return this.nom + ", " + this.prenom;
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