package fr.univnantes.alma.gtd.controler.utilisateurs;

import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;

public interface IControlerUtilisateurs {
	public Utilisateur nouveauUtilisateur(String login, String pwd);
}
