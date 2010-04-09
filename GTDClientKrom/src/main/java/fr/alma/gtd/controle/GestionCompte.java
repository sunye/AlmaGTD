package fr.alma.gtd.controle;

//Start of user code GestionCompte.import
import java.io.File;
import java.util.Collection;

import fr.alma.gtd.dao.CompteDao;
import fr.alma.gtd.dao.impl.AbstractDao;
import fr.alma.gtd.dao.impl.CompteDaoImpl;
import fr.alma.gtd.entities.Compte;
//End of user code

/**
 * Classe GestionCompte.
 */
public class GestionCompte {

	/** Login. */
	private String login;

	/** Dao. */
	private CompteDao dao;

	/**
	 * Constructeur.
	 * @param login valeur de login dans le GestionCompte construit
	 * @param dao valeur de dao dans le GestionCompte construit
	 */
	public GestionCompte(String login) {
		this.login = login;
		this.dao = new CompteDaoImpl();
	}
			
	/**
	 * Getter de l'attribut login.
	 * @return login 
	 */
	public String getLogin() {
		return this.login;
	}
	
	/**
	 * Getter de l'attribut dao.
	 * @return dao 
	 */
	public CompteDao getDao() {
		return this.dao;
	}
	
	public static void deconnexion() {
		AbstractDao.deconnexion();
	}

	/**
	 * Methode setDaoProperty.
	 */
	public static void setDaoProperty(String login){
		//Start of user code GestionCompte.setDaoProperty
		String chemin = "./" + login + "/db";
		AbstractDao.addSpecificProperty("hibernate.connection.url", "jdbc:h2:" + chemin);
		//End of user code
	}
	
	/**
	 * Methode getCompte.
	 */
	public Compte getCompte(){
		//Start of user code GestionCompte.getCompte
		if (!this.compteCorrect()) {
			return null;
		}

		Collection<Compte> comptes = this.dao.findAll();		
		Compte compte = (Compte) comptes.toArray()[0];
		return compte;
		//End of user code
	}
	
	/**
	 * Methode modifierMdp.
	 */
	public void modifierMdp(String mdp){
		//Start of user code GestionCompte.modifierMdp
		Compte compte = this.getCompte();
		compte.setMdp(Hashage.hasher(mdp));
		this.dao.update(compte);
		//End of user code
	}
	
	/**
	 * Methode comparaisonMdp.
	 */
	public Boolean comparaisonMdp(String mdp){
		//Start of user code GestionCompte.comparaisonMdp
		Compte compte = this.getCompte();
		return compte.getMdp().equals(Hashage.hasher(mdp));
		//End of user code
	}
	
	/**
	 * Methode existeCompte.
	 */
	public Boolean existeCompte(){
		//Start of user code GestionCompte.existeCompte
		String chemin = "./" + this.login + "/db";
		File fichier = new File(chemin + ".h2.db");
		return fichier.exists();
		//End of user code
	}
	
	/**
	 * Methode compteCorrect.
	 */
	public Boolean compteCorrect(){
		//Start of user code GestionCompte.compteCorrect
		Collection<Compte> comptes = this.dao.findAll();
		if (comptes.size() != 1) {
			return false;
		}
		
		Compte compte = (Compte) comptes.toArray()[0];
		return compte.getNom().equals(login);
		//End of user code
	}
	
}