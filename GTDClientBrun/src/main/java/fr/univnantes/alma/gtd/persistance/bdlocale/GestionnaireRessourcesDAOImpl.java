package fr.univnantes.alma.gtd.persistance.bdlocale;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.univnantes.alma.gtd.model.gestionnaireressources.GestionnaireRessources;

public class GestionnaireRessourcesDAOImpl extends AbstractDAO implements GestionnaireRessourcesDAO{

	@Override
	public Boolean deleteAll() {
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("delete from IGestionnaireRessources");
        q.executeUpdate();
        tx.commit();
        return true;
	}

	@Override
	public GestionnaireRessources find(Integer id) {
		Session s = HibernateUtil.getSession();
		GestionnaireRessources entity = (GestionnaireRessources) s.get(GestionnaireRessources.class.getCanonicalName(), id);
        return entity;	
	}

	@Override
	public List<GestionnaireRessources> findAll() {
		Session s = HibernateUtil.getSession();
        Criteria criteria = s.createCriteria(GestionnaireRessources.class.getCanonicalName());
        List<GestionnaireRessources> results = (List<GestionnaireRessources>) criteria.list();
        return results;	
	}

}
