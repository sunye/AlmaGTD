
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireutilisateur;

// Start of user code for imports in GestionnaireUtilisateur
import java.util.ArrayList;
import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireressources.AFaire;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Deleguee;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EnAttente;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EtatTache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Finie;
import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;
import fr.univnantes.alma.gtd.persistance.IPersistance;

// Start of user code for comments in GestionnaireUtilisateur
/**
 * 
 */
// End of user code

// Start of user code for comments in GestionnaireUtilisateur
/**
 * 
 */
// End of user code

public class GestionnaireUtilisateurs extends GTDEntity implements IGestionnaireUtilisateurs { 

	private List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	private static GestionnaireUtilisateurs gestionnaireUtilisateurs;
	
	
	
	
	
	// Start of user code for attributes in GestionnaireUtilisateur
	private static IPersistance persistance = GTDEntity.PERSISTANCE;
	// End of user code
	
	// Start of user code for constructors in GestionnaireUtilisateur
	public GestionnaireUtilisateurs() {
		super();
	}
	
	/* // contructeur généré
	public GestionnaireUtilisateur(final Utilisateur utilisateurs, final GestionnaireUtilisateur gestionnaireUtilisateur) {
		super();
		this.setUtilisateurs(utilisateurs);
		this.setGestionnaireUtilisateur(gestionnaireUtilisateur);
	}
	*/
	// End of user code
	
	public List<Utilisateur> getUtilisateurs() {
		// Start of user code for getter of utilisateurs
		return this.utilisateurs;
		// End of user code
	}
	
	public static GestionnaireUtilisateurs getGestionnaireUtilisateur() {
		// Start of user code for getter of gestionnaireUtilisateur
		gestionnaireUtilisateurs = persistance.findGestionnaireUtilisateurs(1);
		if (gestionnaireUtilisateurs == null) {
			gestionnaireUtilisateurs = new GestionnaireUtilisateurs();
			persistance.create(gestionnaireUtilisateurs);
			EtatTache.AFAIRE = new AFaire();
			EtatTache.DELEGUEE = new Deleguee();
			EtatTache.FINIE = new Finie();
			EtatTache.ENATTENTE = new EnAttente();
			persistance.create(EtatTache.AFAIRE);
			persistance.create(EtatTache.DELEGUEE);
			persistance.create(EtatTache.FINIE);
			persistance.create(EtatTache.ENATTENTE);
		}
		else {
			EtatTache.AFAIRE = persistance.findAFaire(1);
			EtatTache.DELEGUEE = persistance.findDeleguee(2);
			EtatTache.FINIE = persistance.findFinie(3);
			EtatTache.ENATTENTE = persistance.findEnAttente(4);
		}
		return gestionnaireUtilisateurs;
		// End of user code
	}
	
		// attribut readOnly
	private static void setGestionnaireUtilisateur(GestionnaireUtilisateurs _gestionnaireUtilisateur) {
		// Start of user code for setter of gestionnaireUtilisateur
		gestionnaireUtilisateurs = _gestionnaireUtilisateur;
		// End of user code
	}

	/**
	 * @see IGestionnaireUtilisateurs#create
	 */
	public Integer add(final Utilisateur user) {
		// Start of user code for create method body
		this.utilisateurs.add(user);
		persistance.update(this);
		return user.getId();
		// End of user code
	}
	/**
	 * @see IGestionnaireUtilisateurs#delete
	 */
	public Boolean remove(final Utilisateur user) {
		// Start of user code for delete method body
		this.utilisateurs.remove(user);
		persistance.update(this);
		return persistance.delete(user);		
		// End of user code
	}
	/**
	 * @see IGestionnaireUtilisateurs#find
	 */
	public Utilisateur find(final Integer id) {
		// Start of user code for find method body
		return (Utilisateur) persistance.findUtilisateur(id);		
		// End of user code
	}

	/**
	 * @see IGestionnaireUtilisateurs#find
	 */
	public Utilisateur find(final String login) {
		// Start of user code for find method body
		Utilisateur res = null;
		for (Utilisateur temp : utilisateurs){
			if (temp.getLogin().equals(login))
				res = temp;
		}
		return res;		
		// End of user code
	}
	
	
}
