/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.alma.modele.serveur;

import java.util.Date;
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
public class ConnexionServeurTest {

    public ConnexionServeurTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
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
     * Test of creerCompte method, of class ConnexionServeur.
     */
    @Test
    public void testCreerCompte() {
        System.out.println("creerCompte");
        String login = "";
        String mdp = "";
        ConnexionServeur instance = new ConnexionServeur();
        Boolean expResult = null;
        Boolean result = instance.creerCompte(login, mdp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of supprimerCompte method, of class ConnexionServeur.
     */
    @Test
    public void testSupprimerCompte() {
        System.out.println("supprimerCompte");
        String login = "";
        String mdp = "";
        ConnexionServeur instance = new ConnexionServeur();
        Boolean expResult = null;
        Boolean result = instance.supprimerCompte(login, mdp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of connect method, of class ConnexionServeur.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        String login = "";
        String mdp = "";
        ConnexionServeur instance = new ConnexionServeur();
        String expResult = "";
        String result = instance.connect(login, mdp);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disconnect method, of class ConnexionServeur.
     */
    @Test
    public void testDisconnect() {
        System.out.println("disconnect");
        ConnexionServeur instance = new ConnexionServeur();
        String expResult = "";
        String result = instance.disconnect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInfosServeur method, of class ConnexionServeur.
     */
    @Test
    public void testGetInfosServeur() {
        System.out.println("getInfosServeur");
        ConnexionServeur instance = new ConnexionServeur();
        String expResult = "";
        String result = instance.getInfosServeur();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetLog method, of class ConnexionServeur.
     */
    @Test
    public void testResetLog() {
        System.out.println("resetLog");
        ConnexionServeur instance = new ConnexionServeur();
        String expResult = "";
        String result = instance.resetLog();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listeProjetsServeur method, of class ConnexionServeur.
     */
    @Test
    public void testListeProjetsServeur_Date() {
        System.out.println("listeProjetsServeur");
        Date date = null;
        ConnexionServeur instance = new ConnexionServeur();
        List expResult = null;
        List result = instance.listeProjetsServeur(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listeProjetsServeur method, of class ConnexionServeur.
     */
    @Test
    public void testListeProjetsServeur_0args() {
        System.out.println("listeProjetsServeur");
        ConnexionServeur instance = new ConnexionServeur();
        List expResult = null;
        List result = instance.listeProjetsServeur();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getISynchronisation method, of class ConnexionServeur.
     */
    @Test
    public void testGetISynchronisation() {
        System.out.println("getISynchronisation");
        ConnexionServeur instance = new ConnexionServeur();
        ISynchronisation expResult = null;
        ISynchronisation result = instance.getISynchronisation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setISynchronisation method, of class ConnexionServeur.
     */
    @Test
    public void testSetISynchronisation() {
        System.out.println("setISynchronisation");
        ISynchronisation iSynchronisation = null;
        ConnexionServeur instance = new ConnexionServeur();
        instance.setISynchronisation(iSynchronisation);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}