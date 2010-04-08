package fr.alma.modele.noyau;

import fr.alma.modele.noyau.IContact;
import fr.alma.modele.noyau.Tache;

//Start of user code for imports
import java.util.List;
import junit.framework.TestCase;

/**
 * Classe Contact représentant les personnes auxquels l'utilisateur entretient une relation.
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public class ContactTest extends TestCase {

    Contact testObject;
    /**
     * UID généré.
     */
    private static final long serialVersionUID = -3696699389922716244L;

    protected void setUp() throws Exception {
        super.setUp();
        testObject = new Contact("Jean", "jean@gmail.com", "5 rue des joins", "0236258923");
    }

    /**
     * Constructeur avec paramètre.
     * @param nom nom du contact
     * @param email adresse email du contact
     * @param adresse adresse du contact
     * @param tel téléphone du contact
     */
    public void testContact() {
        assertEquals(testObject.getNom(),"Jean");
    }
}
