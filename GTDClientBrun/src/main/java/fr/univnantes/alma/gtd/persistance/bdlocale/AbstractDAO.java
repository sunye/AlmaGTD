package fr.univnantes.alma.gtd.persistance.bdlocale;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;

public abstract class AbstractDAO {
	public Integer create(final GTDEntity entity)
	{
		// Start of user code for create method body
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        s.persist(entity);
        tx.commit();
        return entity.getId();		
		// End of user code
	}
	
	public Boolean update(final GTDEntity entity)
	{
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        s.update(entity);
        tx.commit();
        return true;
	}
	
	public Boolean delete(final GTDEntity entity)
	{
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        s.delete(entity);
        tx.commit();
        return true;
	}
}
