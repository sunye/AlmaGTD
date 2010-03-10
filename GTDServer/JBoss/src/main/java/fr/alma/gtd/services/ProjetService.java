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
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneesserveur.Projet;
import fr.alma.gtd.isessions.IProjetServiceRemote;


/**
 * Bean Session charge de gerer la creation, modification, suppression et l'interrogation des Beans Entites Projet.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Stateless
@Local(value = IProjetServiceRemote.class)
@Remote(value = IProjetServiceRemote.class)
public final class ProjetService implements IProjetServiceRemote {

	/**
	 * Charge de persister les objets, de creer les requetes...
	 */
	@PersistenceContext 
	private EntityManager em;

	/**
	 * Constructeur par defaut.
	 */
	public ProjetService() {
		super();
	}
	
	@Override
	public IProjet creerProjet(final IProjet i) {
		final Projet Projet = new Projet(i);
		this.em.persist(Projet);
		return Projet;
	}


	@Override
	public List<IProjet> getAllProjet() {
		final String queryText = "from Projet";
		final Query q = this.em.createQuery(queryText);
		final List<IProjet> resultList = (List<IProjet>) q.getResultList();
		return resultList;
	}

	@Override
	public IProjet getProjetById(final String identifiantServeur) {
		return em.find(Projet.class, identifiantServeur);
	}

	@Override
	public List<IProjet> getProjetByName(final String nom) {
		final String queryText = "from Projet where nom = :name";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("name", nom);
		final List<IProjet> resultList = (List<IProjet>) q.getResultList();
		return resultList;
	}
	
	@Override
	public List<IProjet> getProjetByCreator(final String idCreateur) {
		final String queryText = "from Projet where createur.identifiantServeur = :idcreator";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("idcreator", idCreateur);
		final List<IProjet> resultList = (List<IProjet>) q.getResultList();
		return resultList;
	}
	
	@Override
	public List<IProjet> getProjetByCreator(final String idCreateur, final Date dateModif) {
		final String queryText = "from Projet where createur.identifiantServeur = :idcreator and dateDeDerniereModification >= :dateModif";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("idcreator", idCreateur);
		q.setParameter("dateModif", dateModif);
		final List<IProjet> resultList = (List<IProjet>) q.getResultList();
		return resultList;
	}
	
	@Override
	public List<IProjet> getProjetByCtx(final IContexte c) {
		final String queryText = "from Projet where contexteParDefaut = :ctx";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("ctx", c);
		final List<IProjet> resultList = (List<IProjet>) q.getResultList();
		return resultList;
	}

	@Override
	public IProjet updateProjet(final String identifiantServeur, final IProjet Projet) {
		Projet iToUpdate = em.find(Projet.class, identifiantServeur);
		iToUpdate.copier(Projet);
		validerTaches(iToUpdate);
		em.merge(iToUpdate);
		
		return iToUpdate;		
	}
	
	@Override
	public void removeAll() {
		final String queryText = "delete Projet";
		final Query q = this.em.createQuery(queryText);
		q.executeUpdate();
	}

	@Override
	public void removeProjetById(final String identifiantServeur) {
		Projet iToRemove = em.find(Projet.class, identifiantServeur);
		em.remove(iToRemove);
	}
	
	private void validerTaches(final Projet p){
		for(ITache t : p.getListeDeTaches()){
			em.merge(t);
		}
	}

}
