package fr.univnantes.alma.gtd.controler.connexion;


public interface IControlerConnexion {
	/**
	 * se connecter
	 * @param log
	 * @param pwd
	 * @return
	 */
	public Boolean connect(String log, String pwd);
	
	/**
	 * verifie la connexion de l'utilisateur
	 * @return Boolean
	 */
	public Boolean verifyConnection();
	
	/**
	 * se deconnecter
	 * @return Boolean
	 */
	public Boolean disconnect();


}
