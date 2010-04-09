/**
 * 
 */
package server.types;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import com.alma.client.serializables.Participant;
import com.alma.server.types.ContexteImpl;

import fr.alma.gtd.donneespartagees.IParticipant;

/**
 * @author remi
 *
 */
public class ContexteImplTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link server.types.ContexteImpl#ContexteImpl()}.
	 */
	@Test
	public final void testContexteImpl() {
		ContexteImpl CI = new ContexteImpl("test");
		CI.setIdentifiant("test");
		
		assertTrue(CI.getIdentifiant().compareTo("test")==0);
		assertFalse(CI.getIdentifiant().compareTo("")==0);
	}



	/**
	 * Test method for {@link server.types.ContexteImpl#setNom(java.lang.String)}.
	 */
	@Test
	public final void testSetNom() {
		ContexteImpl CI = new ContexteImpl("test");
		CI.setNom("test2");
		
		assertEquals(CI.getNom().compareTo("test2"),0);
		assertFalse(CI.getNom().compareTo("test")==0);
	}

	/**
	 * Test method for {@link server.types.ContexteImpl#setCreateur(fr.alma.gtd.donneespartagees.IParticipant)}.
	 */
	@Test
	public final void testGetSetCreateur() {
		ContexteImpl CI = new ContexteImpl("test");
		IParticipant Ip = (IParticipant) new Participant("participant");
		CI.setCreateur(Ip);
		assertEquals(CI.getCreateur().getPseudonyme().compareTo("participant"),0);
		assertFalse(CI.getCreateur().getPseudonyme().compareTo("")==0);
		
	}

	/**
	 * Test method for {@link server.types.ContexteImpl#getNom()}.
	 */
	@Test
	public final void testGetNom() {
		ContexteImpl CI = new ContexteImpl("test");
		assertEquals(CI.getNom().compareTo("test"),0);	
	}

	
	/**
	 * Test method for {@link server.types.ContexteImpl#setDateDeDerniereModification(java.util.Date)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public final void testSetDateDeDerniereModification() {
		ContexteImpl CI = new ContexteImpl("");
		Date d = new Date(2010, 3, 20);		
		CI.setDateDeDerModif(d);
		assertTrue(CI.getDateDeDerModif().getDay()==20);
	}
	
	
	
	/**
	 * Test method for {@link server.types.ContexteImpl#getDateDeDerniereModification()}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public final void testGetDateDeDerniereModification() {
		ContexteImpl CI = new ContexteImpl("");
		Date d = new Date(2010, 3, 20);		
		CI.setDateDeDerModif(d);
		assertTrue(CI.getDateDeDerModif().getDay()==20);
		
	}
	




	/**
	 * Test method for {@link server.types.ContexteImpl#setIdentifiant(java.lang.String)}.
	 */
	@Test
	public final void testGetSetIdentifiant() {
		
		ContexteImpl CI = new ContexteImpl("test");
		CI.setIdentifiant("id");
		assertTrue(CI.getIdentifiant().compareTo("id")==0);
		assertFalse(CI.getIdentifiant().compareTo("id2")==0);
	}
	

	

	

	/**
	 * Test method for {@link server.types.ContexteImpl#getIdentifiantServeur()}.
	 */
	@Test
	public final void testGetSetIdentifiantServeur() {
		ContexteImpl CI = new ContexteImpl("test");
		CI.setIdentifiantServeur("idserver");
		assertTrue(CI.getIdentifiantServeur().compareTo("idserver")==0);
		assertFalse(CI.getIdentifiantServeur().compareTo("")==0);
	}

	

}
