package fr.alma.gtd.services;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.alma.gtd.donneespartagees.IContact;
import fr.alma.gtd.donneesserveur.Contact;
import fr.alma.gtd.isessions.IContactServiceRemote;

/**
 * Bean Session charge de gerer la creation, modification, suppression et l'interrogation des Beans Entites contact.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Stateless
@Local(value = IContactServiceRemote.class)
@Remote(value = IContactServiceRemote.class)
public final class ContactService implements IContactServiceRemote {

	/**
	 * Charge de persister les objets, de creer les requetes...
	 */
	@PersistenceContext 
	private EntityManager em;

	/**
	 * Constructeur par defaut.
	 */
	public ContactService() {
		super();
	}
	
	@Override
	public IContact creerContact(final IContact c) {
		final Contact contact = new Contact(c);
		this.em.persist(contact);
		return contact;
	}


	@Override
	public List<IContact> getAllContact() {
		final String queryText = "from Contact";
		final Query q = this.em.createQuery(queryText);
		final List<IContact> resultList = (List<IContact>) q.getResultList();
		return resultList;
	}

	@Override
	public IContact getContactById(final String identifiantServeur) {
		return em.find(Contact.class, identifiantServeur);
	}

	@Override
	public List<IContact> getContactByName(final String nom) {
		final String queryText = "from Contact where nom = :pseudo";
		final Query q = this.em.createQuery(queryText);
		q.setParameter("pseudo", nom);
		final List<IContact> resultList = (List<IContact>) q.getResultList();
		return resultList;
	}

	@Override
	public IContact updateContact(final String identifiantServeur, final IContact contact) {
		Contact cToUpdate = em.find(Contact.class, identifiantServeur);
		cToUpdate.copier(contact);
		em.merge(cToUpdate);
		return cToUpdate;		
	}
	
	@Override
	public void removeAll() {
		final String queryText = "delete Contact";
		final Query q = this.em.createQuery(queryText);
		q.executeUpdate();
	}

	@Override
	public void removeContactById(final String identifiantServeur) {
		Contact cToRemove = em.find(Contact.class, identifiantServeur);
		em.remove(cToRemove);
	}

}
