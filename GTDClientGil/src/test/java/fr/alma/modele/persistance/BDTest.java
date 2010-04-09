/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.alma.modele.persistance;

import fr.alma.modele.noyau.IContact;
import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.ITache;
import fr.alma.modele.noyau.Projet;
import fr.alma.modele.noyau.Tache;
import java.io.File;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.H2Dialect;
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
public class BDTest {

    private static char[] mdp;
    private static String login;
    private static String nologin;
    private static String mail;

    public BDTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.out.println("Penser à supprimer le fichier ~/test.h2.db");

        mdp = new char[8];
        for (int i = 0; i < mdp.length; ++i) {
            mdp[i] = 'i';
        }

        login = "judu";
        nologin = "lolilol";
        mail = "judu@moi.com";
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
     * Test of existeUtilisateur method, of class BD.
     */
    @Test
    public void testExisteUtilisateur_String() {
        System.out.println("existeUtilisateur");
        BD instance = new BD();
        Boolean expResult = false;
        Boolean result = instance.existeUtilisateur(login);
        assertEquals("normalement, l'utilisateur n'existe pas", expResult, result);

        // creerCompte a été testé, on peut rajouter les tests suivants :
        instance.creerCompte(login, mdp, mail);
        result = instance.existeUtilisateur(login);
        expResult = true;
        assertEquals("L'utilisateur devrait exister", expResult, result);


        result = instance.existeUtilisateur(nologin);
        expResult = false;
        assertEquals("L'utilisateur ne devrait pas exister", expResult, result);

    }

    /**
     * Test of existeUtilisateur method, of class BD.
     */
    @Test
    public void testExisteUtilisateur_String_charArr() {
        System.out.println("existeUtilisateur");


        BD instance = new BD();
        Long expResult = null;
        Long result = instance.existeUtilisateur(nologin, mdp);
        assertEquals(expResult, result);

        // creerCompte testé : on peut tester la suite :
        instance.creerCompte(login, mdp, mail);
        expResult = new Long(0);
        result = instance.existeUtilisateur(login, mdp);
    }

    /**
     * Test of getIdUtilisateur method, of class BD.
     */
    @Test
    public void testGetIdUtilisateur() {
        System.out.println("getIdUtilisateur");

        BD instance = new BD();
        Long expResult = null;
        Long result = instance.getIdUtilisateur(nologin);
        assertEquals(expResult, result);
    }

    /**
     * Test of creerCompte method, of class BD.
     */
    @Test
    public void testCreerCompte() {
        String loginCreation = "loginCreation";

        System.out.println("creerCompte");
        BD instance = new BD();
        Boolean expResult = true;
        Boolean result = instance.creerCompte(loginCreation, mdp, mail);
        assertEquals(expResult, result);

        expResult = false;
        result = instance.creerCompte(loginCreation, mdp, mail);
        assertEquals(expResult, result);
    }

    /**
     * Test of ajouterProjetBD method, of class BD.
     */
    @Test
    public void testAjouterProjetBD() {
        System.out.println("ajouterProjetBD");
        IProjet projet = new Projet("projet1", "contexte1", "notes");
        Long idProjet = new Long(1);
        BD instance = new BD();
        Long result = instance.ajouterProjetBD(projet, idProjet);
        assertNotNull(result);
    }

    /**
     * Test of recupererProjetRacine method, of class BD.
     */
    @Test
    public void testRecupererProjetRacine() {
        System.out.println("recupererProjetRacine");
        BD instance = new BD();
        IProjet expResult = null;
        IProjet result = instance.recupererProjetRacine();
        assertEquals(expResult, result);
    }
}
