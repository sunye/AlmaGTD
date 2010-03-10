package fr.univnantes.alma.gtd.persistance.bdlocale;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.GestionnaireUtilisateurs;

public class GestionnaireUtilisateursDAOImpl extends AbstractDAO implements GestionnaireUtilisateursDAO{

	@Override
	public Boolean deleteAll() {
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("delete from GestionnaireUtilisateurs");
        q.executeUpdate();
        tx.commit();
        return true;
	}

	@Override
	public GestionnaireUtilisateurs find(Integer id) {
		Session s = HibernateUtil.getSession();
		GestionnaireUtilisateurs entity = (GestionnaireUtilisateurs) s.get(GestionnaireUtilisateurs.class.getCanonicalName(), id);
        return entity;
	}

	@Override
	public List<GestionnaireUtilisateurs> findAll() {
		Session s = HibernateUtil.getSession();
        Criteria criteria = s.createCriteria(GestionnaireUtilisateurs.class.getCanonicalName());
        List<GestionnaireUtilisateurs> results = (List<GestionnaireUtilisateurs>) criteria.list();
        return results;	
	}

}
