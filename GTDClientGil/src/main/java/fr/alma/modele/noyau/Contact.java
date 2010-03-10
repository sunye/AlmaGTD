package fr.alma.modele.noyau;

import fr.alma.modele.noyau.IContact;
import fr.alma.modele.noyau.Tache;

//Start of user code for imports
import java.util.List;

/**
 * Classe Contact représentant les personnes auxquels l'utilisateur entretient une relation.
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public class Contact extends EntiteGTD implements IContact {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -3696699389922716244L;

	/** le nom du contact  */
	private String nom;

	/** l'adresse du contact */
	private String adresse;

	/** l'adresse email */
	private String email;

	/** le numéro de téléphone */
	private String tel;

	/** la liste de tâche associée */
	private List<Tache> taches;
	
	/**  */
	private Long idUtilisateur;

	/**
	 * Constructeur.
	 */
	public Contact () {
		super();
	}
	
	/**
	 * Constructeur avec paramètre.
	 * @param nom nom du contact
	 * @param email adresse email du contact
	 * @param adresse adresse du contact
	 * @param tel téléphone du contact
	 */
	public Contact(String nom, String email, String adresse, String tel) {
		super();
		this.nom = nom;
		this.email = email;
		this.adresse = adresse;
		this.tel = tel;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	
	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************

	/* (non-Javadoc)
	 * @see fr.alma.modele.noyau.IContact#getTaches()
	 */
	public List<Tache> getTaches() {
		return taches;
	}

	/* (non-Javadoc)
	 * @see fr.alma.modele.noyau.IContact#getNom()
	 */
	public String getNom() {
		return nom;
	}

	/* (non-Javadoc)
	 * @see fr.alma.modele.noyau.IContact#getPrenom()
	 */
	public String getAdresse() {
		return adresse;
	}

	/* (non-Javadoc)
	 * @see fr.alma.modele.noyau.IContact#getEmail()
	 */
	public String getEmail() {
		return email;
	}

	/* (non-Javadoc)
	 * @see fr.alma.modele.noyau.IContact#getTel()
	 */
	public String getTel() {
		return tel;
	}
	
	/* (non-Javadoc)
	 * @see fr.alma.modele.noyau.IContact#setTaches(java.util.List)
	 */
	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}
	
	/* (non-Javadoc)
	 * @see fr.alma.modele.noyau.IContact#setNom(java.lang.String)
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/* (non-Javadoc)
	 * @see fr.alma.modele.noyau.IContact#setAdresse(java.lang.String)
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	/* (non-Javadoc)
	 * @see fr.alma.modele.noyau.IContact#setEmail(java.lang.String)
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/* (non-Javadoc)
	 * @see fr.alma.modele.noyau.IContact#setTel(java.lang.String)
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Long getIdUtilisateur() {
		return idUtilisateur;
	}
}