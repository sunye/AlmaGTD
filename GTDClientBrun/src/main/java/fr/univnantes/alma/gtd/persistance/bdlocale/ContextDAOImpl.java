
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.persistance.bdlocale;

// Start of user code for imports in ContextDAOImpl
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Contact;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Contexte;

// Start of user code for comments in ContextDAOImpl
/**
 * 
 */
// End of user code

// Start of user code for comments in ContextDAOImpl
/**
 * 
 */
// End of user code

public class ContextDAOImpl extends AbstractDAO implements ContextDAO { 

	
	
	// Start of user code for attributes in ContextDAOImpl
	
	// End of user code
	
	// Start of user code for constructors in ContextDAOImpl
	public ContextDAOImpl() {
		super();
		// TODO implementez-moi !
	}
	
	/* // contructeur généré
	public ContextDAOImpl() {
		super();
	}
	*/
	// End of user code
	
	
	
	
	
	/**
	 * @see ContextDAO#find
	 */
	public Contexte find(final Integer id) {
		// Start of user code for find method body
		Session s = HibernateUtil.getSession();
        Contexte entity = (Contexte) s.get(Contexte.class.getCanonicalName(), id);
        return entity;		
		// End of user code
	}
	
	/**
	 * @see ContextDAO#deleteAll
	 */
	public Boolean deleteAll() {
		// Start of user code for deleteAll method body
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("delete from Contexte");
        q.executeUpdate();
        tx.commit();
        return true;		
		// End of user code
	}
	/**
	 * @see ContextDAO#findAll
	 */
	@SuppressWarnings("unchecked")
	public List<Contexte> findAll() {
		// Start of user code for findAll method body
		Session s = HibernateUtil.getSession();
        Criteria criteria = s.createCriteria(Contact.class.getCanonicalName());
        List<Contexte> results = (List<Contexte>) criteria.list();
        return results;		
		// End of user code
	}
}
