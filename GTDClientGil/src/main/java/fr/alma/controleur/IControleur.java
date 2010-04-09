package fr.alma.controleur;

//Start of user code for imports
import java.util.HashMap;
import java.util.Map;

import fr.alma.modele.noyau.IContact;

//End of user code

/**
 * Interface IControleur faisant le lien entre l'IHM et le noyau de l'application 
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public interface IControleur {
	
	public static enum TypeAction {AJOUTER, EDITER};

	//ci-dessous la liste des différentes actions utilisateurs.
	
	void connecter(String login, char[] mdp);
	void deconnecter();
	
	void creerCompte(String login, char[] pwd, char[] pwd2, String email);
	
	void ajouterEditerTache(Map<Integer, Object> valeurs, TypeAction aTypeAction);
	void ajouterEditerProjet(Map<Integer, Object> valeurs, TypeAction aTypeAction);

	void mettreDansCorbeille(Object object);
	void viderCorbeille();
	
	void creerContact(String nom, String email, String adresse, String tel);
	void supprimerContact(IContact contact);
	
	void commit();
	void update();
	void synchro();
}
