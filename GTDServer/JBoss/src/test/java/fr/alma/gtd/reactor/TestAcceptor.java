package fr.alma.gtd.reactor;

import java.lang.reflect.Field;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Classe de test de l'acceptor..
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public final class TestAcceptor {
	
	/**
	 * L'acceptor d'identification a tester.
	 */
	private Acceptor acceptor;
	
	/**
	 * Le nom de l'attribut.
	 */
	private final String nomChamps = "jetonActif";
	
	/**
	 * Constructeur par defaut.
	 */
	public TestAcceptor() {
		super();
	}
	
	/**
	 * Methode d'initialisation des tests.
	 */
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		this.acceptor = Acceptor.getInstance();
		try {
			final Field f = this.acceptor.getClass().getDeclaredField(this.nomChamps);
			f.setAccessible(true);
			final Map<String, String> map =  (Map<String, String>) f.get(this.acceptor);
			map.clear();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Test de l'enregistrement d'un identifiant dans l'acceptor.
	 * @throws Exception Si le champs recherche n'existe pas.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testEnregistre() throws Exception {
		final String id = "abcde";
		boolean result = this.acceptor.enregistre(id);
		final Field f = this.acceptor.getClass().getDeclaredField(this.nomChamps);
		f.setAccessible(true);
		final Map<String, String> map =  (Map<String, String>) f.get(this.acceptor);
		assertTrue("L'identifiant n'a pas ete enregistre", map.containsValue(id));
		assertTrue("La methode ne confirme pas l'ajout de l'identifiant", result);
		
		result = this.acceptor.enregistre(id);
		assertFalse("La methode confirme l'ajout de l'identifiant", result);
	}
	
	/**
	 * Test de l'acceptation d'une identification dans l'acceptor.
	 */
	@Test
	public void testAccept() {
		final String id = "fedcba";
		assertFalse("L'acceptor contient deja le jeton", this.acceptor.accept(id));
		
		this.acceptor.enregistre(id);
		assertTrue("L'acceptor ne contient pas le jeton", this.acceptor.accept(id));
	}
	
	/**
	 * Test de la suppression d'un identifiant dans l'acceptor.
	 * @throws Exception Si le champs n'existe pas.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testRetire() throws Exception {
		final String id = "tictac";
		this.acceptor.enregistre(id);
		
		final Field f = this.acceptor.getClass().getDeclaredField(this.nomChamps);
		f.setAccessible(true);
		final Map<String, String> map =  (Map<String, String>) f.get(this.acceptor);
		assertTrue("L'identifiant n'a pas ete enregistre", map.containsValue(id));
		
		this.acceptor.retire(id);
		assertFalse("L'identifiant a ete conserve", map.containsValue(id));
	}
	
	/**
	 * Test de l'encodage d'une chaine de caractere.
	 */
	@Test
	public void testEncode() {
		final String id = "abcdef";
		final String result = this.acceptor.encode(id);
		assertTrue("L'encodage SHA-512 a echoue",
				"e32ef19623e8ed9d267f657a81944b3d07adbb768518068e88435745564e8d4150a0a703be2a7d88b61e3d390c2bb97e2d4c311fdc69d6b1267f05f59aa920e7".equals(result));
		System.out.println("Resultat : " + result);
	}
	
	/**
	 * Methode de nettoyage des tests.
	 */
	@SuppressWarnings("unchecked")
	@After
	public void tearDown() {
		try {
			final Field f = this.acceptor.getClass().getDeclaredField(this.nomChamps);
			f.setAccessible(true);
			final Map<String, String> map =  (Map<String, String>) f.get(this.acceptor);
			map.clear();
			f.setAccessible(false);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
