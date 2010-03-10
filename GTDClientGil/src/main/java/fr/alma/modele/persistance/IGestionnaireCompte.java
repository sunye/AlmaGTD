package fr.alma.modele.persistance;

public interface IGestionnaireCompte {
	public Boolean existeUtilisateur(String login);
	public Boolean existeUtilisateur(String login, String mdp);
	public Boolean creerCompte(String login, char[] mdp);
	public Boolean existeServer(String login);
	public Boolean setServer(String login, Boolean value);
}
