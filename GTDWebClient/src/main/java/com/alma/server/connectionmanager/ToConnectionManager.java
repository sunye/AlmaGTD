package com.alma.server.connectionmanager;

import com.alma.server.types.Session;

/**
 * Interface permmetant de gerer les comptes utilisateurs (creation, connexion, deconnexion, supression...).
 */
public interface ToConnectionManager {
	
	/**
	 * Permet la connexion de l'utilisateur identifie par le parametre idSession.
	 * @param session - Session de l'utilisateur.
	 * @return renvoie l'id de la session.
	 * @throws Exception - Erreur RMI, mauvais mot de passe, identifiant inexistant.
	 */
	public String connect(String loginGTD,String passGTD, String loginTdoo, String passTDo) throws Exception;
	
	/**
	 * Permet la deconnexion de l'utilisateur identifie par le parametre idSession.
	 * @param session - Session de l'utilisateur.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur de session.
	 */
	public String disconnect(Session session) throws Exception;
	
	/**
	 * Creer un compte utilisateur.
	 * @param login - Identifiant.
	 * @param password - Mot de passe.
	 * @param nickname - Pseudonyme.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur de session, identifiant deja existant, mot de passe invalide.
	 */
	public String createAccount(String login, String password, String nickname) throws Exception;
	
	/**
	 * Supprimer un compte utilisateur.
	 * @param login
	 * @param password
	 * @param session - Session de l'utilisateur.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur de session, identifiant inexistant, mot de passe invalide.
	 */
	public String delAccount(String login, String password, Session idSession) throws Exception;
	
	/**
	 * Changer le mot de passe d'un utilisateur.
	 * @param oldPassword
	 * @param newPassword
	 * @param session - Session de l'utilisateur.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur de session, mot de passe invalide.
	 */
	public String updateAccountPassword(String oldPassword, String newPassword, Session session) throws Exception;	
	
	/**
	 * Changer l'identifiant d'un utilisateur.
	 * @param oldNickname
	 * @param newNickname
	 * @param session - Session de l'utilisateur.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur de session, identifiant deja existant.
	 */
	public String updateAccountPseudo(String oldNickname, String newNickname, Session session) throws Exception;
}

