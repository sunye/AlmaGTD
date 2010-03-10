package fr.alma.gtd.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.alma.gtd.donneespartagees.ITag;
import fr.alma.gtd.donneesserveur.Tag;
import fr.alma.gtd.isessions.ITagServiceRemote;

/**
 * Bean Session charge de gerer la creation, modification, suppression et l'interrogation des Beans Entites Tag.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Stateless
@Local(value = ITagServiceRemote.class)
@Remote(value = ITagServiceRemote.class)
public final class TagService implements ITagServiceRemote {

	/**
	 * Charge de persister les objets, de creer les requetes.
	 */
	@PersistenceContext 
	private EntityManager em;

	/**
	 * Constructeur par defaut.
	 */
	public TagService() {
		super();
	}
	
	@Override
	public ITag creerTag(final ITag tag) {
		final Tag t = new Tag(tag);
		this.em.persist(t);
		return t;
	}


	@Override
	public List<ITag> getAllTag() {
		final String queryText = "from Tag";
		final Query q = this.em.createQuery(queryText);
		final List<ITag> resultList = (List<ITag>) q.getResultList();
		return resultList;
	}

	@Override
	public ITag getTagById(final String identifiantServeur) {
		return em.find(Tag.class, identifiantServeur);
	}

	@Override
	public List<ITag> getTagByName(final String nom) {
		final String queryText = "from Tag where nom = :name";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("name", nom);
		final List<ITag> resultList = (List<ITag>) q.getResultList();
		return resultList;
	}
	
	@Override
	public List<ITag> getTagByCreator(final String idCreateur) {
		final String queryText = "from Tag where createur.identifiantServeur = :idcreator";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("idcreator", idCreateur);
		final List<ITag> resultList = (List<ITag>) q.getResultList();
		return resultList;
	}
	
	@Override
	public List<ITag> getTagByCreator(final String idCreateur, final Date dateModif) {
		final String queryText = "from Tag where createur.identifiantServeur = :idcreator and dateDeDerniereModification >= :dateModif";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("idcreator", idCreateur);
		q.setParameter("dateModif", dateModif);
		final List<ITag> resultList = (List<ITag>) q.getResultList();
		return resultList;
	}
	
	@Override
	public ITag updateTag(final String identifiantServeur, final ITag tag) {
		Tag tToUpdate = em.find(Tag.class, identifiantServeur);
		tToUpdate.copier(tag);
		em.merge(tToUpdate);
		return tToUpdate;		
	}

	@Override
	public void removeAll() {
		final String queryText = "delete Tag";
		final Query q = this.em.createQuery(queryText);
		q.executeUpdate();
	}

	@Override
	public void removeTagById(final String identifiantServeur) {
		Tag cToRemove = em.find(Tag.class, identifiantServeur);
		em.remove(cToRemove);
	}

}
