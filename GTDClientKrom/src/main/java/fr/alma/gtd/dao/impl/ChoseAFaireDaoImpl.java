package fr.alma.gtd.dao.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import fr.alma.gtd.dao.ChoseAFaireDao;
import fr.alma.gtd.entities.ChoseAFaire;

public class ChoseAFaireDaoImpl extends AbstractDao<ChoseAFaire> implements ChoseAFaireDao {

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ChoseAFaire> findAFaireUnJour() {
		EntityManager em = AbstractDao.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM AFaireUnJour";
		Collection<ChoseAFaire> resultat = em.createQuery(requete).getResultList();

		tx.commit();
		em.close();
		return resultat;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ChoseAFaire> findATraiter() {
		EntityManager em = AbstractDao.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String requete = "FROM ATraiter";
		Collection<ChoseAFaire> resultat = em.createQuery(requete).getResultList();

		tx.commit();
		em.close();
		return resultat;
	}

}
