package fr.alma.gtd.services;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneesserveur.Idee;
import fr.alma.gtd.isessions.IIdeeServiceRemote;


/**
 * Bean Session charge de gerer la creation, modification, suppression et l'interrogation des Beans Entites Idee.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Stateless
@Local(value = IIdeeServiceRemote.class)
@Remote(value = IIdeeServiceRemote.class)
public final class IdeeService implements IIdeeServiceRemote {

	/**
	 * Charge de persister les objets, de creer les requetes...
	 */
	@PersistenceContext 
	private EntityManager em;

	/**
	 * Constructeur par defaut.
	 */
	public IdeeService() {
		super();
	}
	
	@Override
	public IIdee creerIdee(final IIdee i) {
		final Idee idee = new Idee(i);
		this.em.persist(idee);
		return idee;
	}


	@Override
	public List<IIdee> getAllIdee() {
		final String queryText = "from Idee";
		final Query q = this.em.createQuery(queryText);
		final List<IIdee> resultList = (List<IIdee>) q.getResultList();
		return resultList;
	}

	@Override
	public IIdee getIdeeById(final String identifiantServeur) {
		return this.em.find(Idee.class, identifiantServeur);
	}

	@Override
	public List<IIdee> getIdeeByName(final String nom) {
		final String queryText = "from Idee where nom = :name";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("name", nom);
		final List<IIdee> resultList = (List<IIdee>) q.getResultList();
		return resultList;
	}
	
	@Override
	public List<IIdee> getIdeeByCreator(final String idCreateur) {
		final String queryText = "from Idee where createur.identifiantServeur = :idcreator";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("idcreator", idCreateur);
		final List<IIdee> resultList = (List<IIdee>) q.getResultList();
		return resultList;
	}
	
	@Override
	public List<IIdee> getIdeeByCreator(final String idCreateur, final Date dateModif) {
		final String queryText = "from Idee where createur.identifiantServeur = :idcreator and dateDeDerniereModification >= :dateModif";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("idcreator", idCreateur);
		q.setParameter("dateModif", dateModif);
		final List<IIdee> resultList = (List<IIdee>) q.getResultList();
		return resultList;
	}

	@Override
	public IIdee updateIdee(final String identifiantServeur, final IIdee idee) {
		final Idee iToUpdate = this.em.find(Idee.class, identifiantServeur);
		iToUpdate.copier(idee);
		this.em.merge(iToUpdate);
		return iToUpdate;		
	}
	
	@Override
	public void removeAll() {
		final String queryText = "delete Idee";
		final Query q = this.em.createQuery(queryText);
		q.executeUpdate();
	}

	@Override
	public void removeIdeeById(final String identifiantServeur) {
		final Idee iToRemove = this.em.find(Idee.class, identifiantServeur);
		this.em.remove(iToRemove);
	}

}
