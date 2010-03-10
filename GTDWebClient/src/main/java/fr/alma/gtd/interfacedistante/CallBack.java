package fr.alma.gtd.interfacedistante;

/**
 * L'interface de CallBack permettant au client de recevoir sa reponse.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 * @param <T> Le type de reponse desire par le client en cas de succes.
 */
public interface CallBack<T> {
	
	/**
	 * Methode appelee apres la reussite du traitement sur le serveur. 
	 * @param result Le resultat renvoye par le serveur.
	 */
	void onSucces(T result);
	
	/**
	 * Methode appelee apres l'echec du traitement sur le serveur.
	 * @param e L'exception renvoyee par le serveur.
	 */
	void onFailure(Exception e);
}
