package fr.alma.gtd.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.alma.gtd.dao.ContexteDao;
import fr.alma.gtd.entities.Contexte;

public class ContexteDaoImpl extends AbstractDao<Contexte> implements ContexteDao {

	@Override
	public Contexte findContexteNul() {
		EntityManager em = AbstractDao.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Contexte WHERE nom = '<Aucun contexte>'";
		Contexte resultat = (Contexte) em.createQuery(requete).getSingleResult();

		tx.commit();
		em.close();
		return resultat;
	}

	@Override
	public boolean exist(String nom) {
		EntityManager em = AbstractDao.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM Contexte WHERE nom = :nomContexte";
		Query query = em.createQuery(requete);
		query.setParameter("nomContexte", nom);
		List<?> resultats = query.getResultList();

		tx.commit();
		em.close();
		return (resultats.size() > 0);
	}

}
