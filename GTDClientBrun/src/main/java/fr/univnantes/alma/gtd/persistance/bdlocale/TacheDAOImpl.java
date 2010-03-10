
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.persistance.bdlocale;

// Start of user code for imports in TacheDAOImpl
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Tache;

// Start of user code for comments in TacheDAOImpl
/**
 * 
 */
// End of user code

// Start of user code for comments in TacheDAOImpl
/**
 * 
 */
// End of user code

public class TacheDAOImpl extends AbstractDAO implements TacheDAO { 

	
	
	// Start of user code for attributes in TacheDAOImpl
	
	// End of user code
	
	// Start of user code for constructors in TacheDAOImpl
	public TacheDAOImpl() {
		super();
		// TODO implementez-moi !
	}
	
	/* // contructeur généré
	public TacheDAOImpl() {
		super();
	}
	*/
	// End of user code
	
	
	
	
	
	/**
	 * @see TacheDAO#find
	 */
	public Tache find(final Integer id) {
		// Start of user code for find method body
		Session s = HibernateUtil.getSession();
        Tache entity = (Tache) s.get(Tache.class.getCanonicalName(), id);
        return entity;			
		// End of user code
	}
	/**
	 * @see TacheDAO#deleteAll
	 */
	public Boolean deleteAll() {
		// Start of user code for deleteAll method body
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("delete from Tache");
        q.executeUpdate();
        tx.commit();
        return true;		
		// End of user code
	}
	/**
	 * @see TacheDAO#findAll
	 */
	@SuppressWarnings("unchecked")
	public List<Tache> findAll() {
		// Start of user code for findAll method body
		Session s = HibernateUtil.getSession();
        Criteria criteria = s.createCriteria(Tache.class.getCanonicalName());
        List<Tache> results = (List<Tache>) criteria.list();
        return results;		
		// End of user code
	}
}
