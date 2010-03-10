package fr.univnantes.alma.gtd.controler.utilisateurs;

import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.GestionnaireUtilisateurs;
import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;

public class ControlerUtilisateurs implements IControlerUtilisateurs{
	
	private GestionnaireUtilisateurs gu;

	@Override
	public Utilisateur nouveauUtilisateur(String login, String pwd) {
		Utilisateur util = new Utilisateur(login,pwd);
		gu = GestionnaireUtilisateurs.getGestionnaireUtilisateur();
		gu.add(util);
		return util;
	}

}
