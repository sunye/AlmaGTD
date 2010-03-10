package fr.alma.modele.serveur;

import java.util.Date;
import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.serveur.IServeur;
import fr.alma.modele.serveur.ISynchronisation;

//Start of user code for imports
import java.util.List;

//End of user code


/**
 * Classe de connexion au serveur.
 * @version 1.0
 * @author Université de Nantes
 */
public class ConnexionServeur implements IServeur {
	

	/**  */
	private ISynchronisation iSynchronisation;

	
	public ConnexionServeur () {
		super();
	}
	 	
	/**
	* @param login
	* @param mdp
	* @return Boolean
	*/
	public Boolean creerCompte(String login, String mdp) {
		//Start of user code for creerCompte method body
		//TODO
		return null;
		//End of user code
	}
	/**
	* @param login
	* @param mdp
	* @return Boolean
	*/
	public Boolean supprimerCompte(String login, String mdp) {
		//Start of user code for supprimerCompte method body
		//TODO
		return null;
		//End of user code
	}
	/**
	* @param login
	* @param mdp
	* @return String
	*/
	public String connect(String login, String mdp) {
		//Start of user code for connect method body
		//TODO
		return null;
		//End of user code
	}
	/**
	* @return String
	*/
	public String disconnect() {
		//Start of user code for disconnect method body
		//TODO
		return null;
		//End of user code
	}
	/**
	* @return String
	*/
	public String getInfosServeur() {
		//Start of user code for getInfosServeur method body
		//TODO
		return null;
		//End of user code
	}
	/**
	* @return String
	*/
	public String resetLog() {
		//Start of user code for resetLog method body
		//TODO
		return null;
		//End of user code
	}
	/**
	* @param projets
	* @return ListeActionProjet
	*/
	/*public ListeActionProjet synchroniser(List<IProjet> projets) {
		//Start of user code for synchroniser method body
		//TODO
		return null;
		//End of user code
	}*/
	/**
	* @param date
	* @return List<IProjet>
	*/
	public List<IProjet> listeProjetsServeur(Date date) {
		//Start of user code for listeProjetsServeur method body
		//TODO
		return null;
		//End of user code
	}
	/**
	* @return List<IProjet>
	*/
	public List<IProjet> listeProjetsServeur() {
		//Start of user code for listeProjetsServeur method body
		//TODO
		return null;
		//End of user code
	}

	/*public void commitSurServeur(ListeActionProjet listeAction) {
		//Start of user code for commitSurServeur method body
		//TODO
		//End of user code
	}*/
	
	 	
	 
	//GETTER
	public ISynchronisation getISynchronisation() {
		return iSynchronisation;
	}
	
	
	//SETTER
	public void setISynchronisation(ISynchronisation iSynchronisation) {
		this.iSynchronisation = iSynchronisation;
	}
	
}


