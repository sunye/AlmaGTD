package fr.alma.gtd.dao.impl;

import fr.alma.gtd.dao.ElementDao;
import fr.alma.gtd.entities.Contact;
import fr.alma.gtd.entities.Contexte;
import fr.alma.gtd.entities.Element;
import fr.alma.gtd.entities.Projet;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class ElementDaoImpl extends AbstractDao<Element> implements ElementDao {

	@Override
	public void delete (Long id) {
		Element element = this.find(id);
		// Suppression des sous-elements d'un projet
		if (!element.estTache()) {
			Projet projet = (Projet) element;
			for (Element contenu : projet.getElements()) {
				this.delete(contenu.getId());
			}
		}

		// Suppression de la reference dans le projet conteneur
		Projet conteneur = element.getProjetConteneur();
		conteneur.removeElements(element);
		element.setProjetConteneur(null);
		this.update(element);
		this.update(conteneur);

		// Suppression de l'element
		super.delete(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Element> findAllOfProjet(Projet projet) {
		EntityManager em = AbstractDao.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Element WHERE projetconteneur_id = :idProjet";
		Query query = em.createQuery(requete);
		query.setParameter("idProjet", projet.getId());
		List<Element> resultats = query.getResultList();

		tx.commit();
		em.close();
		return resultats;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Element> findWithContext(Contexte contexte) {
		EntityManager em = AbstractDao.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Element WHERE contexte_id = :idContexte";
		Query query = em.createQuery(requete);
		query.setParameter("idContexte", contexte.getId());
		Collection<Element> resultats = query.getResultList();

		tx.commit();
		em.close();
		return resultats;
	}

	@Override
	public Collection<Contact> getContacts(Element element) {
		EntityManager em = AbstractDao.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Contact WHERE ID IN (SELECT CONTACTS_ID FROM Element_Contact WHERE ELEMENT_ID = :idElement)";
		Query query = em.createQuery(requete);
		query.setParameter("idElement", element.getId());
		Collection<Contact> resultats = query.getResultList();

		tx.commit();
		em.close();
		return resultats;
	}

}
