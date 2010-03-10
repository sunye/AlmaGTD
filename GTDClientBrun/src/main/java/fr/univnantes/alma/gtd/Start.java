package fr.univnantes.alma.gtd;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Tache;
import fr.univnantes.alma.gtd.view.Connexion;


public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(Tache.class.getCanonicalName());
		
		new Connexion("GTD");

	}

}
