package fr.alma.modele.persistance;

/**
 * Interface de gestion des différents comptes d'utilisateurs.
 * @version 1.0
 * @author Université de Nantes
 */
public interface IGestionnaireComptes {

	/** Vérifie l'existence d'un utilisateur dans la base de données.
	 * @param login : login de l'utilisateur
	 * @return Vrai si le login est présent */
	public Boolean existeUtilisateur(String login);

	/** Vérifie la cohérence d'un identifiant et de son mot de passe avec ceux stockés dans la base de données.
	 * @param login : login de l'utilisateur
	 * @param mdp : mot de passe de l'utilisateur
	 * @return Un identifiant stocké dans la base de données, sinon null */
	public Long existeUtilisateur(String login, char[] mdp);
	
	/** Récupère simplement l'identifiant d'un utilisateur existant.
	 * @param login : login de l'utilisateur
	 * @return Un identifiant stocké dans la base de données, sinon null */
	public Long getIdUtilisateur(String login);

	/** Créer un compte en local dans la base de données.
	 * @param login : login de l'utilisateur
	 * @param mdp : mot de passe de l'utilisateur
	 * @param email : email pour pouvoir récupérer son mot de passe
	 * @return Vrai si tout s'est bien passé */
	public Boolean creerCompte(String login, char[] mdp, String email);

	/**
	 * 
	 * @param login
	 * @return Vrai si le serveur existe
	 */
	public Boolean existeServer(String login);

	/**
	 * 
	 * @param login
	 * @param value
	 * @return Vrai si l'attribution du serveur a réussi
	 */
	public Boolean setServer(String login, Boolean value);
}
