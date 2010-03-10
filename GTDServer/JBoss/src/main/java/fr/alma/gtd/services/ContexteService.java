package fr.alma.gtd.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneesserveur.Contexte;
import fr.alma.gtd.isessions.IContexteServiceRemote;
import fr.alma.gtd.isessions.IProjetServiceRemote;
import fr.alma.gtd.isessions.ITacheServiceRemote;

/**
 * Bean Session charge de gerer la creation, modification, suppression et l'interrogation des Beans Entites Contexte.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Stateless
@Local(value = IContexteServiceRemote.class)
@Remote(value = IContexteServiceRemote.class)
public final class ContexteService implements IContexteServiceRemote {

	
	/**
	 * On utilise le service gerant les projets lors de la suppression de contexte.
	 */
	@EJB
	private IProjetServiceRemote serviceProjet;
	
	/**
	 * On utilise le service gerant les taches lors de la suppression de contexte.
	 */
	@EJB
	private ITacheServiceRemote serviceTache;
	
	/**
	 * Charge de persister les objets, de creer les requetes...
	 */
	@PersistenceContext 
	private EntityManager em;

	/**
	 * Constructeur par defaut.
	 */
	public ContexteService() {
		super();
	}
	
	@Override
	public IContexte creerContexte(final IContexte c) {
		final Contexte contexte = new Contexte(c);
		this.em.persist(contexte);
		return contexte;
	}


	@Override
	public List<IContexte> getAllContexte() {
		final String queryText = "from Contexte";
		final Query q = this.em.createQuery(queryText);
		final List<IContexte> resultList = (List<IContexte>) q.getResultList();
		return resultList;
	}

	@Override
	public IContexte getContexteById(final String identifiantServeur) {
		return this.em.find(Contexte.class, identifiantServeur);
	}

	@Override
	public List<IContexte> getContexteByName(final String nom) {
		final String queryText = "from Contexte where nom = :name";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("name", nom);
		final List<IContexte> resultList = (List<IContexte>) q.getResultList();
		return resultList;
	}
	
	@Override
	public List<IContexte> getContexteByCreateur(final String idCreateur) {
		final String queryText = "from Contexte where createur.identifiantServeur = :idCreator";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("idCreator", idCreateur);
		final List<IContexte> resultList = (List<IContexte>) q.getResultList();
		return resultList;
	}

	@Override
	public IContexte updateContexte(final String identifiantServeur, final IContexte contexte) {
		final Contexte cToUpdate = this.em.find(Contexte.class, identifiantServeur);
		cToUpdate.copier(contexte);
		em.merge(cToUpdate);
		return cToUpdate;		
	}
	
	@Override
	public void removeAll() {
		final String queryText = "delete Contexte";
		final Query q = this.em.createQuery(queryText);
		try {
			q.executeUpdate();
		} catch (Exception e){
			System.out.println("Probleme lors de la suppression des contextes. ");
		}
	}

	@Override
	public void removeContexteById(final String identifiantServeur) throws Exception {
		final Contexte cToRemove = this.em.find(Contexte.class, identifiantServeur);
		// On cherche les projets et taches lies a ce contexte
		if((serviceProjet.getProjetByCtx(cToRemove).size()>0)||
		  (serviceTache.getTacheByCtx(cToRemove).size()>0)){
			throw new Exception("Suppression du contexte "+cToRemove.getNom()+" impossible : des projets ou taches y font reference." +
					" Veuillez les supprimer ou modifier le contexte qui leurs sont associees.");
		}
		
		this.em.remove(cToRemove);
	}

}
