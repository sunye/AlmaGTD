/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.alma.modele.persistance.dao.impl;

import fr.alma.modele.noyau.Utilisateur;
import fr.alma.modele.persistance.BD;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author judu
 */
public class UtilisateurDaoImplTest {

    private static char[] mdp;
    private static String login;
    private static String nologin;
    private static String mail;


    public UtilisateurDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        char[] mmm = {'i','i','i','i','i','i','i','i'};
        mdp = mmm;

        login = "judu2";
        nologin = "lolilol";
        mail = "judu@moi.com";

        BD database = new BD();

        database.creerCompte(login, mdp, mail);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of recupererTout method, of class UtilisateurDaoImpl.
     */
    @Test
    public void testRecupererTout() {
        System.out.println("recupererTout");
        UtilisateurDaoImpl instance = new UtilisateurDaoImpl();
        List result = instance.recupererTout();
        System.out.println(result.toString());
        assertTrue(result.size() > 0 );
    }

    /**
     * Test of trouverUtilisateur method, of class UtilisateurDaoImpl.
     */
    @Test
    public void testTrouverUtilisateur_String() {
        System.out.println("trouverUtilisateur");
        UtilisateurDaoImpl instance = new UtilisateurDaoImpl();
        Utilisateur expResult = new Utilisateur(login, mdp, mail);
        Utilisateur result = instance.trouverUtilisateur(login);
        assertEquals(expResult.getLogin(), result.getLogin());

        result = instance.trouverUtilisateur("nonexistant");
        assertNull(result);

    }

    /**
     * Test of trouverUtilisateur method, of class UtilisateurDaoImpl.
     */
    @Test
    public void testTrouverUtilisateur_String_charArr() {
        System.out.println("trouverUtilisateur");
        UtilisateurDaoImpl instance = new UtilisateurDaoImpl();
        Utilisateur result = instance.trouverUtilisateur(login, mdp);
        assertNotNull(result);

        char[] mauvaispass = {'m','a','u','v','a','i','s'};
        result = instance.trouverUtilisateur(login, mauvaispass);
        assertNull(result);

        result = instance.trouverUtilisateur("mauvais", mdp);
    }

}