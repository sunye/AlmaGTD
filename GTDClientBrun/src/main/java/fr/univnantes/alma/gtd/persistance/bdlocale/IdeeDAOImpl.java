
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.persistance.bdlocale;

// Start of user code for imports in IdeeDAOImpl
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Idee;

// Start of user code for comments in IdeeDAOImpl
/**
 * 
 */
// End of user code

// Start of user code for comments in IdeeDAOImpl
/**
 * 
 */
// End of user code

public class IdeeDAOImpl extends AbstractDAO implements IdeeDAO{ 

	
	
	// Start of user code for attributes in IdeeDAOImpl
	
	// End of user code
	
	// Start of user code for constructors in IdeeDAOImpl
	public IdeeDAOImpl() {
		super();
		// TODO implementez-moi !
	}
	
	/* // contructeur généré
	public IdeeDAOImpl() {
		super();
	}
	*/
	// End of user code
	
	
	
	
	/**
	 * @see IdeeDAO#find
	 */
	public Idee find(final Integer id) {
		// Start of user code for find method body
		Session s = HibernateUtil.getSession();
        Idee entity = (Idee) s.get(Idee.class.getCanonicalName(), id);
        return entity;		
		// End of user code
	}

	/**
	 * @see IdeeDAO#deleteAll
	 */
	public Boolean deleteAll() {
		// Start of user code for deleteAll method body
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("delete from Idee");
        q.executeUpdate();
        tx.commit();
        return true;
		// End of user code
	}
	/**
	 * @see IdeeDAO#findAll
	 */
	@SuppressWarnings("unchecked")
	public List<Idee> findAll() {
		// Start of user code for findAll method body
		Session s = HibernateUtil.getSession();
        Criteria criteria = s.createCriteria(Idee.class.getCanonicalName());
        List<Idee> results = (List<Idee>) criteria.list();
        return results;		
		// End of user code
	}
}
