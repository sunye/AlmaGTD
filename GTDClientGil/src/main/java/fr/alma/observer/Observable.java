package fr.alma.observer;

import fr.alma.modele.AbstractModele;

/**
 * Interface Observable (damier).
 * @author Matthieu BACHELIER, Florian BARBIN, Florent GILET, Jean-Marie GILET.
 * @version 1.0
 */
public interface Observable {
	
	/**
	 * Ajoute un observer.
	 * @param obs l'observer à ajouter.
	 */
	public void addObserver(Observer obs);
	
	/**
	 * Enlève un observer.
	 */
	public void removeObserver();
	
	/**
	 * Méthode appliquée lors d'un changement dans la vue.
	 * @param modele le modèle courant.
	 */
	public void notifyObserver(AbstractModele modele);
	
}
