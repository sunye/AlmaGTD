package fr.univnantes.alma.gtd.controler.utilisateurs;

import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.GestionnaireUtilisateurs;
import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;

/**
 * 
 * Classe de controle utilisateur
 *
 */
public class ControlerUtilisateurs implements IControlerUtilisateurs{
	
	private GestionnaireUtilisateurs gu;

	/**
	 * cree un nouvel utilisateur
	 * @param login
	 * @param pwd
	 * @return Utilisateur
	 */
	public Utilisateur nouveauUtilisateur(String login, String pwd) {
		Utilisateur util = new Utilisateur(login,pwd);
		gu = GestionnaireUtilisateurs.getGestionnaireUtilisateur();
		gu.add(util);
		return util;
	}

}
