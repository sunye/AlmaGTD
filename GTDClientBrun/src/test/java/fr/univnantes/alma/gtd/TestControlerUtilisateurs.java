package fr.univnantes.alma.gtd.tests;

import junit.framework.TestCase;

import fr.univnantes.alma.gtd.controler.utilisateurs.ControlerUtilisateurs;
import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;

public class TestControlerUtilisateurs extends TestCase{
	
	public void testNouveauUtilisateur() {
		ControlerUtilisateurs controlUtilisateur = new ControlerUtilisateurs() ;
		Utilisateur user = controlUtilisateur.nouveauUtilisateur("abou", "dabi");
				
		assertEquals(user.getLogin(), "abou");
		assertEquals(user.getPwd(), "dabi");
	}

}
