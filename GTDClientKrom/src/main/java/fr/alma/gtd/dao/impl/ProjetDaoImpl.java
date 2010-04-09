package fr.alma.gtd.dao.impl;

import fr.alma.gtd.dao.ProjetDao;
import fr.alma.gtd.entities.Projet;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ProjetDaoImpl extends AbstractDao<Projet> implements ProjetDao {

	@Override
	public Projet findProjetRacine() {
		EntityManager em = AbstractDao.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Projet WHERE projetconteneur_id = NULL";
		Projet resultat = (Projet) em.createQuery(requete).getSingleResult();

		tx.commit();
		em.close();
		return resultat;
	}

}
