package fr.univnantes.alma.gtd.controler.connexion;

import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.GestionnaireUtilisateurs;
import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;

/**
 * 
 * Class de controle de connexion utilisateur
 *
 */
public class ControlerConnexion implements IControlerConnexion {
	
	private GestionnaireUtilisateurs gu;
	public static Utilisateur utilisateurConnecte;

	

	/**
	 * Tentative de connexion de l'utilisateur
	 * @param log login utilisateur
	 * @param pwd mot de passe utilisateur
	 * @return true si connexion reussie
	 */
	public Boolean connect(String log, String pwd) {
		gu = GestionnaireUtilisateurs.getGestionnaireUtilisateur();
		Utilisateur util = gu.find(log);
		if (util != null && util.getPwd().equals(pwd)){
			utilisateurConnecte = util;
			return true;
		}
		else {
			System.out.println("erreur connection");
			return false;
		}
	}

	
	/**
	 * Deconnexion utilisateur
	 * @return true
	 */
	public Boolean disconnect() {
		utilisateurConnecte = null;
		return true;
	}


	/**
	 * Verifier si un utilisateur est connecte
	 * @return true si utilisateur connecte
	 */
	public Boolean verifyConnection() {
		return (utilisateurConnecte != null);
	}

}
