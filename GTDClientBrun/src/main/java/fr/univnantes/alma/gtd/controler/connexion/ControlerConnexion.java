package fr.univnantes.alma.gtd.controler.connexion;

import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.GestionnaireUtilisateurs;
import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;

public class ControlerConnexion implements IControlerConnexion {
	
	private GestionnaireUtilisateurs gu;
	public static Utilisateur utilisateurConnecte;

	

	@Override
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

	@Override
	public Boolean disconnect() {
		utilisateurConnecte = null;
		return true;
	}


	@Override
	public Boolean verifyConnection() {
		return (utilisateurConnecte != null);
	}

}
