package fr.univnantes.alma.test;

import junit.framework.TestCase;

import fr.univnantes.alma.gtd.controler.connexion.ControlerConnexion;


public class TestControlerConnexion extends TestCase {

	public void testConnect() {
		ControlerConnexion controler = new ControlerConnexion();
		assertFalse(controler.connect("billou", "billou"));
	}

	public void testDisconnect() {
		ControlerConnexion controler = new ControlerConnexion();
		controler.disconnect();
		assertEquals(ControlerConnexion.utilisateurConnecte,null);
	}

	public void testVerifyConnection() {
		ControlerConnexion controler = new ControlerConnexion();
		assertFalse(controler.verifyConnection());
	}

}
