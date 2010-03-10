package fr.univnantes.alma.gtd.persistance.bdlocale;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Date;

public class DateDAOImpl extends AbstractDAO implements DateDAO{

	@Override
	public Boolean deleteAll() {
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("delete from Date");
        q.executeUpdate();
        tx.commit();
        return true;
	}

	@Override
	public Date find(Integer id) {
		Session s = HibernateUtil.getSession();
		Date entity = (Date) s.get(Date.class.getCanonicalName(), id);
        return entity;
	}

	@Override
	public List<Date> findAll() {
		Session s = HibernateUtil.getSession();
        Criteria criteria = s.createCriteria(Date.class.getCanonicalName());
        List<Date> results = (List<Date>) criteria.list();
        return results;		
	}

}
