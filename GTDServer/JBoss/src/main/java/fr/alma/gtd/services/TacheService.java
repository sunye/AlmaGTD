package fr.alma.gtd.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneesserveur.Tache;
import fr.alma.gtd.isessions.ITacheServiceRemote;


/**
 * Bean Session charge de gerer la creation, modification, suppression et l'interrogation des Beans Entites Tache.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Stateless
@Local(value = ITacheServiceRemote.class)
@Remote(value = ITacheServiceRemote.class)
public final class TacheService implements ITacheServiceRemote {

	/**
	 * Charge de persister les objets, de creer les requetes...
	 */
	@PersistenceContext 
	private EntityManager em;

	/**
	 * Constructeur par defaut.
	 */
	public TacheService() {
		super();
	}
	
	@Override
	public ITache creerTache(final ITache i) {
		final Tache Tache = new Tache(i);
		this.em.persist(Tache);
		return Tache;
	}


	@Override
	public List<ITache> getAllTache() {
		final String queryText = "from Tache";
		final Query q = this.em.createQuery(queryText);
		final List<ITache> resultList = (List<ITache>) q.getResultList();
		return resultList;
	}

	@Override
	public ITache getTacheById(final String identifiantServeur) {
		return em.find(Tache.class, identifiantServeur);
	}

	@Override
	public List<ITache> getTacheByName(final String nom) {
		final String queryText = "from Tache where nom = :name";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("name", nom);
		final List<ITache> resultList = (List<ITache>) q.getResultList();
		return resultList;
	}
	
	@Override
	public List<ITache> getTacheByCreator(final String idCreateur) {
		final String queryText = "from Tache where createur.identifiantServeur = :idcreator";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("idcreator", idCreateur);
		final List<ITache> resultList = (List<ITache>) q.getResultList();
		return resultList;
	}
	
	@Override
	public List<ITache> getTacheByCreator(final String idCreateur, final Date dateModif) {
		final String queryText = "from Tache where createur.identifiantServeur = :idcreator and dateDeDerniereModification >= :dateModif";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("idcreator", idCreateur);
		q.setParameter("dateModif", dateModif);
		final List<ITache> resultList = (List<ITache>) q.getResultList();
		return resultList;
	}

	@Override
	public List<ITache> getTacheByCtx(final IContexte c) {
		final String queryText = "from Tache where contexte = :ctx";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("ctx", c);
		final List<ITache> resultList = (List<ITache>) q.getResultList();
		return resultList;
	}
	
	@Override
	public List<ITache> getTacheByCtx(final IContexte c, final Date dateModif) {
		final String queryText = "from Tache where contexte = :ctx and dateDeDerniereModification >= :dateModif";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("ctx", c);
		q.setParameter("dateModif", dateModif);
		final List<ITache> resultList = (List<ITache>) q.getResultList();
		return resultList;
	}
	
	@Override
	public List<ITache> getTacheByCtxAndCreator(final IContexte ctx, final IParticipant createur) {
		final String queryText = "from Tache where contexte = :ctx and createur = :createur";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("ctx", ctx);
		q.setParameter("createur", createur);
		final List<ITache> resultList = (List<ITache>) q.getResultList();
		return resultList;
	}
	
	@Override
	public List<ITache> getTacheByCtxAndCreator(final IContexte ctx, final IParticipant createur, final Date dateModif) {
		final String queryText = "from Tache where contexte = :ctx and createur = :createur and dateDeDerniereModification >= :dateModif";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("ctx", ctx);
		q.setParameter("createur", createur);
		q.setParameter("dateModif", dateModif);
		final List<ITache> resultList = (List<ITache>) q.getResultList();
		return resultList;
	}
	
	@Override
	public ITache updateTache(final String identifiantServeur, final ITache tache) {
		Tache iToUpdate = em.find(Tache.class, identifiantServeur);
		iToUpdate.copier(tache);
		em.merge(iToUpdate);
		return iToUpdate;		
	}
	
	@Override
	public void removeAll() {
		final String queryText = "delete Tache";
		final Query q = this.em.createQuery(queryText);
		q.executeUpdate();
	}

	@Override
	public void removeTacheById(final String identifiantServeur) {
		Tache iToRemove = em.find(Tache.class, identifiantServeur);
		em.remove(iToRemove);
	}

}
