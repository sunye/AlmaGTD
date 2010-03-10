package fr.alma.modele.persistance;

import java.util.List;

import fr.alma.modele.noyau.Contact;
import fr.alma.modele.noyau.EntiteGTD;
import fr.alma.modele.noyau.IContact;
import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.ITache;
import fr.alma.modele.noyau.Projet;
import fr.alma.modele.noyau.Tache;
import fr.alma.modele.noyau.Utilisateur;
import fr.alma.modele.persistance.dao.ContactDao;
import fr.alma.modele.persistance.dao.ProjetDao;
import fr.alma.modele.persistance.dao.TacheDao;
import fr.alma.modele.persistance.dao.UtilisateurDao;
import fr.alma.modele.persistance.dao.impl.AbstractDao;

/**
 * Classe BC représentant la base de données locale
 * @version 1.0
 * @author Université de Nantes
 */
public class BD extends AbstractDao<EntiteGTD> implements IGestionnaireComptes, ICopieLocale {

	/**
	 * Constructeur.
	 */
	public BD () {
		super();
	}
	
	///////////////////////////////////////////////////////////////////////////
	// METHODES DE L'INTERFACES IGestionnaireComptes
	///////////////////////////////////////////////////////////////////////////

	/** Vérifie l'existence d'un utilisateur dans la base de données.
	 * @param login : login de l'utilisateur
	 * @return Vrai si le login est présent */
	@Override
	public Boolean existeUtilisateur(String login) {
		//Start of user code for existeUtilisateur method body
		UtilisateurDao u = DaoFactory.createUtilisateurDao();
		System.out.println(login + " a été trouvé ? : " + u.trouverUtilisateur(login));
		return (u.trouverUtilisateur(login) != null);
		//End of user code
	}

	/** Vérifie la cohérence d'un identifiant et de son mot de passe avec ceux stockés dans la base de données.
	 * @param login : login de l'utilisateur
	 * @param mdp : mot de passe de l'utilisateur
	 * @return Un identifiant stocké dans la base de données, sinon null */
	@Override
	public Long existeUtilisateur(String login, char[] mdp) {
		//Start of user code for existeUtilisateur method body
		UtilisateurDao uDao = DaoFactory.createUtilisateurDao();
		if (uDao.trouverUtilisateur(login, mdp) != null) {
			Utilisateur u = uDao.trouverUtilisateur(login);
			return u.getId();
		}
		return null;
		//End of user code
	}

	/** Récupère simplement l'identifiant d'un utilisateur existant.
	 * @param login : login de l'utilisateur
	 * @return Un identifiant stocké dans la base de données, sinon null */
	@Override
	public Long getIdUtilisateur(String login) {
		//Start of user code for existeUtilisateur method body
		UtilisateurDao uDao = DaoFactory.createUtilisateurDao();
		Utilisateur u = uDao.trouverUtilisateur(login);
		return u.getId();
		//End of user code
	}

	/** Créer un compte en local dans la base de données.
	 * @param login : login de l'utilisateur
	 * @param password : mot de passe de l'utilisateur
	 * @param email : email pour pouvoir récupérer son mot de passe
	 * @return Vrai si tout s'est bien passé */
	@Override
	public Boolean creerCompte(String login, char[] password, String email) {
		//Start of user code for creerCompte method body
		Boolean result = false; 
		if (!existeUtilisateur(login)) {
			UtilisateurDao d = DaoFactory.createUtilisateurDao();
			Utilisateur u = new Utilisateur(login, password, email);
			Long idUtilisateur = d.creer(u);
			
			Projet root = new Projet("GTD", null, "");
			Projet panier = new Projet("Panier", null, "");
			Projet corbeille = new Projet("Corbeille", null, "");
			
			root.setIdUtilisateur(idUtilisateur);
			panier.setIdUtilisateur(idUtilisateur);
			corbeille.setIdUtilisateur(idUtilisateur);
			
			root.ajouterSousProjet(panier);
			root.ajouterSousProjet(corbeille);

			ProjetDao projetDao = DaoFactory.createProjetDao();
			Long idRacine = projetDao.creer(root);
			
			panier.setIdPere(idRacine);
			corbeille.setIdPere(idRacine);
			
			projetDao.creer(panier);
			projetDao.creer(corbeille);
			
			result = true;
		} else {
			System.out.println("L'utilisateur est déjà présent !");
		}
		return result;
		//End of user code
	}

	/**
	 * @param login
	 * @return Boolean
	 */
	@Override
	public Boolean existeServer(String login) {
		//Start of user code for existeServer method body
		//TODO
		return null;
		//End of user code
	}

	/**
	 * @param login
	 * @param value
	 * @return Boolean
	 */
	@Override
	public Boolean setServer(String login, Boolean value) {
		//Start of user code for setServer method body
		//TODO
		return null;
		//End of user code
	}
	

	///////////////////////////////////////////////////////////////////////////
	// METHODES DE L'INTERFACES ICopieLocale
	///////////////////////////////////////////////////////////////////////////

	/**
	 * @param projet
	 * @param idProjet
	 * @return Boolean
	 */
	@Override
	public Long ajouterProjetBD(IProjet projet, Long idProjet) {
		//Start of user code for ajouterProjetBD method body
		ProjetDao dao = DaoFactory.createProjetDao();
		projet.setIdPere(idProjet);
		return dao.creer((Projet) projet);
		//End of user code
	}

	/**
	 * @param tache
	 * @param projet
	 * @return Boolean
	 */
	@Override
	public Boolean ajouterTacheBD(ITache tache, IProjet projet) {
		//Start of user code for ajouterTacheBD method body
		TacheDao dao = DaoFactory.createTacheDao();
		
		if (projet == null) {
			ProjetDao projetDao = DaoFactory.createProjetDao();
			projet = projetDao.recupererPanier();
		}
		projet.ajouterTache(tache);
		tache.setIdProjet(projet.getId());
		dao.creer((Tache) tache);
		return null;
		//End of user code
	}

	/**
	 * @param t
	 * @return Boolean
	 */
	@Override
	public Boolean modifierTache(ITache t) {
		//Start of user code for modifierTache method body
		TacheDao dao = DaoFactory.createTacheDao();
		dao.maj((Tache) t);
		return true;
		//End of user code
	}

	/**
	 * @param projet
	 * @return Boolean
	 */
	@Override
	public Boolean modifierProjet(IProjet projet) {
		//Start of user code for modifierTache method body
		ProjetDao dao = DaoFactory.createProjetDao();
		dao.maj((Projet) projet);
		return true;
		//End of user code
	}

	@Override
	public Boolean supprimerContact(IContact contact) {
		//Start of user code for modifierTache method body
		ContactDao dao = DaoFactory.createContactDao();
		dao.supprimer(contact.getId());
		return true;
		//End of user code
	}

	/**
	 * @param tache
	 * @return Boolean
	 */
	@Override
	public Boolean supprimerTache(ITache tache) {
		//Start of user code for supprimerTache method body
		ProjetDao p = DaoFactory.createProjetDao();
		Projet projet = p.recuperer(tache.getIdProjet());
		projet.supprimerTache(tache.getId());
		TacheDao t = DaoFactory.createTacheDao();
		t.supprimer(tache.getId());
		return true;
		//End of user code
	}

	/**
	 * @param projet
	 * @return Boolean
	 */
	@Override
	public Boolean supprimerProjet(IProjet projet) {
		//Start of user code for supprimerProjet method body
		ProjetDao projetDao = DaoFactory.createProjetDao();
		List<Projet> sousProjets = projetDao.recupererSousProjets(projet.getId());
		
		// Suppression récursive des sous projets d'abord
		for (IProjet sousProjet : sousProjets) {
			supprimerProjet(sousProjet);
		}

		TacheDao tacheDao = DaoFactory.createTacheDao();
		List<Tache> taches = tacheDao.recupererTaches(projet.getId());
		for(ITache t : taches){
			supprimerTache(t);
		}
		projetDao.supprimer(projet.getId());
		return true;
		//End of user code
	}

	@Override
	public Boolean ajouterContactBD(IContact contact) {
		ContactDao c = DaoFactory.createContactDao();
		c.creer((Contact) contact);
		return true;
	}
	
	public IProjet recupererProjetRacine() {
		ProjetDao dao = DaoFactory.createProjetDao();
		Projet p = dao.recupererProjetRacine();
		return p;
	}

	@Override
	public void mettreDansCorbeille(IProjet p) {
		ProjetDao dao = DaoFactory.createProjetDao();
		Projet corbeille = dao.recupererCorbeille();
		p.setIdPere(corbeille.getId());
		corbeille.ajouterSousProjet(p);
		modifierProjet(p);		
	}

	@Override
	public void mettreDansCorbeille(ITache t) {
		ProjetDao dao = DaoFactory.createProjetDao();
		Projet corbeille = dao.recupererCorbeille();
		TacheDao daot = DaoFactory.createTacheDao();
		Tache tache = daot.recuperer(t.getId());
		tache.setIdProjet(corbeille.getId());
		corbeille.ajouterTache(tache);
		daot.maj((Tache) tache);
		dao.maj(corbeille);
	}

	@Override
	public void viderCorbeille(IProjet corbeille) {

		Long idCorbeille = corbeille.getId();

		TacheDao tacheDao = DaoFactory.createTacheDao();
		List<Tache> taches = tacheDao.recupererTaches(idCorbeille);
		for (ITache tache : taches) {
			supprimerTache(tache);
		}

		ProjetDao projetDao = DaoFactory.createProjetDao(); 
		List<Projet> sousProjets = projetDao.recupererSousProjets(idCorbeille);
		for (Projet p : sousProjets) {
			supprimerProjet(p);
		}
	}
}