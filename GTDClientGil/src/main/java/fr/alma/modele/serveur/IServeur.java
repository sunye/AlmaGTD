package fr.alma.modele.serveur;

import fr.alma.modele.noyau.IProjet;
import java.util.Date;

//Start of user code for imports
import java.util.List;

//End of user code

public interface IServeur {
	public Boolean creerCompte(String login, String mdp);
	public Boolean supprimerCompte(String login, String mdp);
	public String connect(String login, String mdp);
	public String disconnect();
	public String getInfosServeur();
	public String resetLog();
	//public ListeActionProjet synchroniser(List<IProjet> projets);
	public List<IProjet> listeProjetsServeur(Date date);
	public List<IProjet> listeProjetsServeur();
	//public  void commitSurServeur(ListeActionProjet listeAction);
}
