package fr.alma.modele.persistance.dao;

import fr.alma.modele.noyau.Utilisateur;

/**
 * interface UtilisateurDao
 * @version 1.0
 * @author Université de Nantes
 */
public interface UtilisateurDao extends EntiteDao<Utilisateur>{
	
	/**
	 * Trouve l'utilisateur correspondant lors d'une connexion 
	 * @param login le login 
	 * @return l'utilisateur s'il a été trouvé sinon null
	 */
	public Utilisateur trouverUtilisateur(String login);

	/**
	 * Trouve l'utilisateur correspondant lors d'une connexion 
	 * @param login le login 
	 * @param pwd le mot de passe 
	 * @return l'utilisateur s'il a été trouvé sinon null
	 */
	public Utilisateur trouverUtilisateur(String login, char[] pwd);
}
