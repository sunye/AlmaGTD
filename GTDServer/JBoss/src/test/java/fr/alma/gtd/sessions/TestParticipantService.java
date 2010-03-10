package fr.alma.gtd.sessions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneesserveur.Participant;
import fr.alma.gtd.isessions.IParticipantServiceRemote;

/**
 * Classe de test du bean session ParticipantService.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public final class TestParticipantService {
	
	/**
	 * Le service a tester.
	 */
	private IParticipantServiceRemote service;
	
	/**
	 * Nom du premier participant du test.
	 */
	private final String nomParticipant1 = "Minus";
	
	/**
	 * Nom du second participant du test.
	 */
	private final String nomParticipant2 = "Cortex";
	
	/**
	 * Nom du troisieme participant du test.
	 */
	private final String nomParticipant3 = "Titi";
	
	/**
	 * Nom du quatrieme participant du test.
	 */
	private final String nomParticipant4 = "Gros Minet";
	
	/**
	 * Nom du cinquieme participant du test.
	 */
	private final String nomParticipant5 = "Tom";
	
	/**
	 * Nom du sixieme participant du test.
	 */
	private final String nomParticipant6 = "Jerry";
	
	/**
	 * Liste des participants crees au long du test.
	 */
	private final List<IParticipant> liste = new ArrayList<IParticipant>();
	
	/**
	 * Le constructeur.
	 */
	public TestParticipantService() {
		super();
	}
	
	/**
	 * Methode d'initialisation des tests.
	 */
	@BeforeClass
	public void setUpClass() {
		try {
			final Properties env = new Properties();
			env.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
			env.setProperty("java.naming.provider.url", "localhost:1099");
			env.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming"); 
			
			final Context context = new InitialContext(env);
			this.service = (IParticipantServiceRemote) context.lookup("ParticipantService/remote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test des methodes creerParticipant et getAllParticipant.
	 * @throws Exception En cas de probleme avec les beans sessions.
	 */
	@Test
	public void testCreerParticipantEtGetAllParticipant() throws Exception {
		final IParticipant p1 = this.service.creerParticipant(new Participant(this.nomParticipant1));
		final IParticipant p2 = this.service.creerParticipant(new Participant(this.nomParticipant2));
		
		assertTrue("Le participant p1 n'a pas ete cree correctement", this.service.getAllParticipant().contains(p1));
		assertTrue("Le participant p2 n'a pas ete cree correctement", this.service.getAllParticipant().contains(p2));
		
		this.liste.add(p1);
		this.liste.add(p2);
	}
	
	/**
	 * Test des methodes creerParticipant et getParticipantById.
	 * @throws Exception En cas de probleme avec les beans sessions.
	 */
	@Test
	public void testCreerParticipantEtGetParticipantById() throws Exception {
		final IParticipant p3 = this.service.creerParticipant(new Participant(this.nomParticipant3));
		final IParticipant p4 = this.service.creerParticipant(new Participant(this.nomParticipant4));
		
		assertTrue("Le participant p3 n'a pas ete retrouve par son identifiant", this.service.getParticipantById(p3.getIdentifiantServeur()).equals(p3));
		assertTrue("Le participant p4 n'a pas ete retrouve par son identifiant", this.service.getParticipantById(p4.getIdentifiantServeur()).equals(p4));
		
		this.liste.add(p3);
		this.liste.add(p4);
	}
	
	/**
	 * Test des methodes creerParticipant et getParticipantByPseudo.
	 * @throws Exception En cas de probleme avec les beans sessions.
	 */
	@Test
	public void testCreerParticipantEtGetParticipantByPseudo() throws Exception {
		final IParticipant p5 = this.service.creerParticipant(new Participant(this.nomParticipant5));
		final IParticipant p6 = this.service.creerParticipant(new Participant(this.nomParticipant6));
		
		assertTrue("Le participant p5 n'a pas ete retrouve par son pseudo", this.service.getParticipantByPseudo(p5.getPseudonyme()).equals(p5));
		assertTrue("Le participant p6 n'a pas ete retrouve par son pseudo", this.service.getParticipantByPseudo(p6.getPseudonyme()).equals(p6));
		
		this.liste.add(p5);
		this.liste.add(p6);
	}
	
	/**
	 * Test des methodes creerParticipant, getAllParticipant et RemoveAll.
	 * @throws Exception En cas de probleme avec les beans sessions.
	 */
	@Test
	public void testGetAllParticipantEtRemoveAll() throws Exception {
		assertTrue("Tous les participants n'ont pas ete recuperes", this.service.getAllParticipant().containsAll(this.liste));
		
		this.service.removeAll();
		
		assertTrue("Tous les participants n'ont pas ete supprimes", this.service.getAllParticipant().containsAll(new ArrayList<IParticipant>()));
	}
	
	/**
	 * Test des mthodes creerParticipant, getParticipantById et removeParticipantById.
	 * @throws Exception En cas de probleme avec les beans sessions.
	 */
	@Test
	public void testCreerParticipantEtGetParticipantByIdEtRemoveParticipantbyId() throws Exception {
		final IParticipant p1 = this.service.creerParticipant(new Participant(this.nomParticipant1));
		final IParticipant p2 = this.service.creerParticipant(new Participant(this.nomParticipant2));

		assertTrue("Le participant p1 n'a pas ete retrouve par son identifiant", this.service.getParticipantById(p1.getIdentifiantServeur()).equals(p1));
		assertTrue("Le participant p2 n'a pas ete retrouve par son identifiant", this.service.getParticipantById(p2.getIdentifiantServeur()).equals(p2));
		
		this.service.removeParticipantById(p1.getIdentifiantServeur());
		this.service.removeParticipantById(p2.getIdentifiantServeur());
		
		assertFalse("Le participant p1 n'a pas ete supprime par son identifiant", this.service.getParticipantById(p1.getIdentifiantServeur()).equals(p1));
		assertFalse("Le participant p2 n'a pas ete supprime par son identifiant", this.service.getParticipantById(p2.getIdentifiantServeur()).equals(p2));
		
	}
	
	/**
	 * Suppresion des donnees du test.
	 */
	@AfterClass
	public void tearDown() {
		for (IParticipant p : this.liste) {
			this.service.removeParticipantById(p.getIdentifiantServeur());
		}
	}
}
