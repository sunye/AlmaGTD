package fr.alma.gtd.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.alma.gtd.donneesserveur.Utilisateur;
import fr.alma.gtd.isessions.IUtilisateurServiceRemote;

/**
 * Bean Session charge de gerer la creation, modification, suppression et l'interrogation des Beans Entites Utilisateur.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Stateless
@Local(value = IUtilisateurServiceRemote.class)
@Remote(value = IUtilisateurServiceRemote.class)
public final class UtilisateurService implements IUtilisateurServiceRemote {

	/**
	 * Charge de persister les objets, de creer les requetes...
	 */
	@PersistenceContext 
	private EntityManager em;

	/**
	 * Constructeur par defaut.
	 */
	public UtilisateurService() {
		super();
	}
	
	@Override
	public Utilisateur creerUtilisateur(final Utilisateur p) {
		final Utilisateur Utilisateur = new Utilisateur(p);
		this.em.persist(Utilisateur);
		return Utilisateur;
	}


	@Override
	public List<Utilisateur> getAllUtilisateur() {
		final String queryText = "from Utilisateur";
		final Query q = this.em.createQuery(queryText);
		final List<Utilisateur> resultList = (List<Utilisateur>) q.getResultList();
		return resultList;
	}

	@Override
	public Utilisateur getUtilisateurById(final String identifiantServeur) {
		return this.em.find(Utilisateur.class, identifiantServeur);
	}

	@Override
	public List<Utilisateur> getUtilisateurByLogin(final String login) {
		final String queryText = "from Utilisateur where login = :login";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("login", login);
		final List<Utilisateur> resultList = (List<Utilisateur>) q.getResultList();
		return resultList;
	}
	
	@Override
	public Utilisateur updateUtilisateur(final String identifiantServeur, final Utilisateur util) {
		final Utilisateur pToUpdate = this.em.find(Utilisateur.class, identifiantServeur);
		pToUpdate.copier(util);
		this.em.merge(pToUpdate);
		return pToUpdate;		
	}
	

	@Override
	public void removeAll() {
		final String queryText = "delete Utilisateur";
		final Query q = this.em.createQuery(queryText);
		q.executeUpdate();
	}

	@Override
	public void removeUtilisateurById(final String identifiantServeur) {
		final Utilisateur cToRemove = this.em.find(Utilisateur.class, identifiantServeur);
		this.em.remove(cToRemove);
	}

}
