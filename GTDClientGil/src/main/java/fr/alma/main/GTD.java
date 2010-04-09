package fr.alma.main;

import fr.alma.controleur.Controleur;
import fr.alma.controleur.IControleur;
import fr.alma.ihm.vues.ApplicationGTD;
import fr.alma.modele.Modele;
import fr.alma.modele.AbstractModele;

/**
 * Classe principale.
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public class GTD {

	/**
	 * Méthode de lancement de l'application
	 * @param args
	 */
	public static void main(String[] args) {

		//pattern mvc
		AbstractModele modele = new Modele();
		IControleur controler = new Controleur((Modele) modele);
		ApplicationGTD appli = ApplicationGTD.getInstance();
		appli.setControler(controler);

		//Ajout de la fenêtre comme observer de notre modèle
		modele.addObserver(appli);
	}
}
