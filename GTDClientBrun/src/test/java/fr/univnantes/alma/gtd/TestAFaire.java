package fr.univnantes.alma.test;

import fr.univnantes.alma.gtd.model.gestionnaireressources.AFaire;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EtatTache;
import junit.framework.TestCase;

public class TestAFaire extends TestCase {

	public void testDeleguee() {
		AFaire aFaire = new AFaire();
		aFaire.deleguee();
		assertEquals(aFaire.getEtatTache(),EtatTache.DELEGUEE);
	}

	public void testEnAttente() {
		AFaire aFaire = new AFaire();
		aFaire.enAttente();
		assertEquals(aFaire.getEtatTache(),EtatTache.ENATTENTE);
	}

	public void testFinie() {
		AFaire aFaire = new AFaire();
		aFaire.finie();
		assertEquals(aFaire.getEtatTache(),EtatTache.FINIE);
	}

	public void testEtatsProchains() {
		AFaire aFaire = new AFaire();
		
	}

	public void testAFaire() {
		AFaire aFaire = new AFaire();
		aFaire.aFaire();
		assertEquals(aFaire.getEtatTache(),EtatTache.AFAIRE);
	}

	public void testToString() {
		AFaire aFaire = new AFaire();
		aFaire.deleguee();
		assertEquals(aFaire.toString(),"A faire");
	}

}
