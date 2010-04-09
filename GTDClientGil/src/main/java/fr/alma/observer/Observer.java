package fr.alma.observer;

import fr.alma.modele.AbstractModele;

/**
 * Interface Observer(Afficheur).
 * @author Matthieu BACHELIER, Florian BARBIN, Florent GILET, Jean-Marie GILET.
 * @version 1.0
 */
public interface Observer {
	
	public void update(AbstractModele modele);
}
