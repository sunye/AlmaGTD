package fr.alma.modele.noyau;

import java.util.List;

/**
 * Classe utilisateur représentant les gens qui utilise l'application
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public class Utilisateur extends EntiteGTD {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 8933900590762191474L;
	
	/** son login  */
	private String login;
	
	/** son mot de passe  */
	private char[] password;
	
	/** l'adresse email de l'utilisateur  */
	private String email;
	
	/** la liste de contact associée à cet utilisateur */
	private List<Contact> contacts;
	
	/**
	 * Constructeur.
	 */
	public Utilisateur() {
		super();
	}

	/**
	 * Constructeur avec paramètre.
	 * @param login login de l'utilsiateur
	 * @param password mot de apsse de l'utilsiateur
	 * @param email adresse email de l'utilisateur
	 */
	public Utilisateur(String login, char[] password, String email) {
		super();
		this.login = login;
		this.password = password;
		this.email = email;
	}
	
	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	public String getLogin() {
		return login;
	}

	public char[] getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public List<Contact> getContacts() {
		return contacts;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

}
