package server.types;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.alma.server.types.ContexteImpl;
import com.alma.server.types.ProjetImpl;


import fr.alma.gtd.donneespartagees.Avancement;
import fr.alma.gtd.donneespartagees.Frequence;
import fr.alma.gtd.donneespartagees.IContact;
import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;

public class ProjetImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public final void testProjetImpl() {
		
		ProjetImpl Pi = new ProjetImpl();
	}





	@Test
	public final void testSetNom() {
		ProjetImpl Pi = new ProjetImpl();
		Pi.setNom("nom");
		assertTrue(Pi.getNom().equals("nom"));
		assertFalse(Pi.getNom().equals(""));
	}

	@Test
	public final void testSetDansLaPoubelle() {
		ProjetImpl Pi = new ProjetImpl();
		Pi.setDansLaPoubelle(false);
		Pi.setDansLaPoubelle(true);
	}

	@Test
	public final void testSetAvancement() {
		ProjetImpl Pi = new ProjetImpl();
		Pi.setAvancement(Avancement.AFAIRE);
		assertTrue(Pi.getAvancement().equals(Avancement.AFAIRE));
		assertFalse(Pi.getAvancement().equals(Avancement.DELEGUE));
	}

	@Test
	public final void testSetContexteParDefaut() {
		ProjetImpl Pi = new ProjetImpl();
		Pi.setContexteParDefaut(new ContexteImpl());
		assertTrue(Pi.getContexteParDefaut().equals(new ContexteImpl()));
	}



	
	@Test
	public final void testGetSetListeDeTaches() {
		ProjetImpl Pi = new ProjetImpl();
		Pi.setListeDeTaches(new ArrayList<ITache>());
		assertTrue(Pi.getListeDeTaches().equals(new ArrayList<ITache>()));
	}

	@Test
	public final void testGetSetNom() {
		ProjetImpl Pi = new ProjetImpl();
		Pi.setNom("nom");
		assertTrue(Pi.getNom().equals("nom"));
	}
}
	