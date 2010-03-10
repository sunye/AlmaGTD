package fr.univnantes.alma.gtd.controler.connexion;


public interface IControlerConnexion {
	public Boolean connect(String log, String pwd);
	public Boolean verifyConnection();
	public Boolean disconnect();


}
