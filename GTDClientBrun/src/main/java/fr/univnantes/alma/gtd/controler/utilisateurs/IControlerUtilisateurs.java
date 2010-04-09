package fr.univnantes.alma.gtd.controler.utilisateurs;

import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;

public interface IControlerUtilisateurs {
	
	/**
	 * 
	 * @param login
	 * @param pwd
	 * @return
	 */
	public Utilisateur nouveauUtilisateur(String login, String pwd);
}
