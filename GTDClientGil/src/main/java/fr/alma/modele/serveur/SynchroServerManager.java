package fr.alma.modele.serveur;

import java.util.Date;
import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.serveur.ISynchronisation;
import fr.alma.modele.serveur.SynchroPolicy;

//Start of user code for imports
import java.util.List;

//End of user code


/**
 * Classe SynchroServerManager
 * @version 1.0
 * @author Université de Nantes
 */
public class SynchroServerManager  implements ISynchronisation {
	

	/**  */
	private SynchroPolicy synchroPolicy;

	
	public SynchroServerManager () {
		super();
	}
	 	
	/**
	* @return Date
	*/
	public Date getDerniereSynchro() {
		//Start of user code for getDerniereSynchro method body
		//TODO
		return null;
		//End of user code
	}
	/**
	* @return ListeActionProjet
	*/
	/*public ListeActionProjet updateActions() {
		//Start of user code for updateActions method body
		//TODO
		return null;
		//End of user code
	}*/
	/**
	* @return ListeActionProjet
	*/
	/*public ListeActionProjet commitActions() {
		//Start of user code for commitActions method body
		//TODO
		return null;
		//End of user code
	}*/
	/**
	* @return Boolean
	*/
	public Boolean estSynchro() {
		//Start of user code for estSynchro method body
		//TODO
		return null;
		//End of user code
	}
	/**
	* @param listeServeur
	* @param listeLocale
	*/
	public void fusionner(List<IProjet> listeServeur, List<IProjet> listeLocale) {
		//Start of user code for fusionner method body
		//TODO
		//End of user code
	}
	
	 	
	 
	//GETTER
	public SynchroPolicy getSynchroPolicy() {
		return synchroPolicy;
	}
	
	
	//SETTER
	public void setSynchroPolicy(SynchroPolicy synchroPolicy) {
		this.synchroPolicy = synchroPolicy;
	}
	
}


