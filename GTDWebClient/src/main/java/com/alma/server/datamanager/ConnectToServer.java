package com.alma.server.datamanager;

import java.io.IOException;
import com.alma.server.types.Session;


/**
 * Interface permmetant de gerer les comptes utilisateurs (creation, connexion, deconnexion, supression...).
 */
public interface ConnectToServer {

	/**
	 * Permet la connexion de l'utilisateur identifie par le parametre idSession.
	 * @param session - Session de l'utilisateur.
	 * @return renvoie l'id de la session.
	 * @throws Exception - Erreur RMI, mauvais mot de passe, identifiant inexistant.
	 */
	public String connect(final Session session) throws Exception;
	
	/**
	 * Permet la deconnexion de l'utilisateur identifie par le parametre idSession.
	 * @param session - Session de l'utilisateur.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur de session.
	 */
	public String disConnect(final Session session) throws Exception;
	
	
	/**
	 * Creer un compte utilisateur.
	 * @param login - Identifiant.
	 * @param password - Mot de passe.
	 * @param nickname - Pseudonyme.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur de session, identifiant deja existant, mot de passe invalide.
	 */
	public String createAccount(final String login, final String password, final String nickname) throws Exception;
	
	/**
	 * Changer le mot de passe d'un utilisateur.
	 * @param oldPassword
	 * @param newPassword
	 * @param session - Session de l'utilisateur.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur de session, mot de passe invalide.
	 */
	public String updateAccountPassword(final String oldPassword, final String newPassword, final Session session) throws Exception;
	
	/**
	 * Changer l'identifiant d'un utilisateur.
	 * @param oldNickname
	 * @param newNickname
	 * @param session - Session de l'utilisateur.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur de session, identifiant deja existant.
	 */
	public String updateAccountPseudo(final String oldNickname, final String newNickname, final Session session) throws Exception;
	
	/**
	 * Supprimer un compte utilisateur.
	 * @param login
	 * @param password
	 * @param session - Session de l'utilisateur.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur de session, identifiant inexistant, mot de passe invalide.
	 */
	public String deleteAccount(final String login, final String password, final Session session) throws Exception;
}
