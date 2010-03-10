package fr.alma.gtd.dao.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import fr.alma.gtd.dao.TacheDao;
import fr.alma.gtd.entities.Avancement;
import fr.alma.gtd.entities.Tache;

public class TacheDaoImpl extends AbstractDao<Tache> implements TacheDao {

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Tache> findATraiterWithContexts(List<String> noms) {
		if (noms.isEmpty()) {
			return new LinkedList<Tache>();
		}

		EntityManager em = AbstractDao.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		String clause = "AND ";
		for (int i = 0; i < noms.size(); i++) {
			if (i != 0) {
				clause += " OR ";
			}
			clause += "contexte.nom = '" + noms.get(i) + "'";
		}

		String requete = "FROM Tache WHERE avancement = :avancement " + clause + " ORDER BY prioriteDynamique DESC";

		Query query = em.createQuery(requete);
		query.setParameter("avancement", Avancement.AFAIRE);
		Collection<Tache> resultats = query.getResultList();

		tx.commit();
		em.close();
		return resultats;
	}

}
