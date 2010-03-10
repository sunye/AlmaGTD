package fr.alma.controleur;

//Start of user code for imports
import java.util.HashMap;

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
	
	public void connecter(String login, char[] mdp);
	public void deconnecter();
	
	public void creerCompte(String login, char[] pwd, char[] pwd2, String email);
	
	public void ajouterEditerTache(HashMap<Integer, Object> valeurs, TypeAction a);
	public void ajouterEditerProjet(HashMap<Integer, Object> valeurs, TypeAction a);

	public void mettreDansCorbeille(Object o);
	public void viderCorbeille();
	
	public void creerContact(String nom, String email, String adresse, String tel);
	public void supprimerContact(IContact contact);
	
	public void commit();
	public void update();
	public void synchro();
}
