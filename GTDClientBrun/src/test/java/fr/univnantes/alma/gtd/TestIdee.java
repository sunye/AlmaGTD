package fr.univnantes.alma.gtd.tests;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Idee;
import junit.framework.TestCase;

public class TestIdee extends TestCase {

	public void testIdeeStringString() {
		Idee idee = new Idee("nom","description");
		assertEquals(idee.getNom(),"nom");
		assertEquals(idee.getDescription(),"description");
	}

	public void testToString() {
		Idee idee = new Idee("nom","description");
		assertEquals(idee.toString(),"nom");
	}

	public void testGetNom() {
		Idee idee = new Idee("nom","description");
		assertEquals(idee.getNom(),"nom");
	}

	public void testSetNom() {
		Idee idee = new Idee();
		idee.setNom("nom");
		assertEquals(idee.getNom(),"nom");
	}

	public void testGetDescription() {
		Idee idee = new Idee("nom","description");
		assertEquals(idee.getDescription(),"description");
	}

	public void testSetDescription() {
		Idee idee = new Idee("nom","description");
		idee.setDescription("description");
		assertEquals(idee.getDescription(),"description");
	}

}
