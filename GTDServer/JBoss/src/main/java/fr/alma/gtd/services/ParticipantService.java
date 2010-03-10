package fr.alma.gtd.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneesserveur.Participant;
import fr.alma.gtd.isessions.IParticipantServiceRemote;

/**
 * Bean Session charge de gerer la creation, modification, suppression et l'interrogation des Beans Entites Participant.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Stateless
@Local(value = IParticipantServiceRemote.class)
@Remote(value = IParticipantServiceRemote.class)
public final class ParticipantService implements IParticipantServiceRemote {

	/**
	 * Charge de persister les objets, de creer les requetes...
	 */
	@PersistenceContext 
	private EntityManager em;

	/**
	 * Constructeur par defaut.
	 */
	public ParticipantService() {
		super();
	}
	
	@Override
	public IParticipant creerParticipant(final IParticipant p) {
		final Participant participant = new Participant(p);
		this.em.persist(participant);
		return participant;
	}


	@Override
	public List<IParticipant> getAllParticipant() {
		final String queryText = "from Participant";
		final Query q = this.em.createQuery(queryText);
		final List<IParticipant> resultList = (List<IParticipant>) q.getResultList();
		return resultList;
	}

	@Override
	public IParticipant getParticipantById(final String identifiantServeur) {
		return this.em.find(Participant.class, identifiantServeur);
	}

	@Override
	public List<IParticipant> getParticipantByPseudo(final String pseudonyme) {
		final String queryText = "from Participant where pseudonyme = :pseudo";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("pseudo", pseudonyme);
		final List<IParticipant> resultList = (List<IParticipant>) q.getResultList();
		return resultList;
	}
	
	@Override
	public IParticipant updateParticipant(final String identifiantServeur, final IParticipant contexte) {
		final Participant pToUpdate = this.em.find(Participant.class, identifiantServeur);
		pToUpdate.copier(contexte);
		this.em.merge(pToUpdate);
		return pToUpdate;		
	}
	

	@Override
	public void removeAll() {
		final String queryText = "delete Participant";
		final Query q = this.em.createQuery(queryText);
		q.executeUpdate();
	}

	@Override
	public void removeParticipantById(final String identifiantServeur) {
		final Participant cToRemove = this.em.find(Participant.class, identifiantServeur);
		this.em.remove(cToRemove);
	}

}
