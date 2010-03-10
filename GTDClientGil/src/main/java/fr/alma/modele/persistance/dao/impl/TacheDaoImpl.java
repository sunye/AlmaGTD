package fr.alma.modele.persistance.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.alma.modele.noyau.Tache;
import fr.alma.modele.persistance.dao.TacheDao;

/**
 * Classe TacheDaoImpl
 * @version 1.0
 * @author Université de Nantes
 */
public class TacheDaoImpl extends AbstractDao<Tache> implements TacheDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Tache> recupererTout() {
		Session s = getSession();
		Query query = s.createQuery("from Tache");
		List<Tache> results = (List<Tache>) query.list();
		return results;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Tache> recupererTaches(Long idProjet) {
		Session s = getSession();
		Query query = s.createQuery("from Tache where idprojet = :idprojet");
		query.setParameter("idprojet", idProjet);
		List<Tache> results = (List<Tache>) query.list();
		return results;
	}
	
	@Override
	public void supprimerTaches(Long idProjet) {
		Session s = getSession();
		Query query = s.createQuery("delete from Tache where idprojet = :idprojet");
		query.setParameter("idprojet", idProjet);
		query.executeUpdate();
	}
}
