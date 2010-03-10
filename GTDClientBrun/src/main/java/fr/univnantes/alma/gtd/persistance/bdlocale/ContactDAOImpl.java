
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.persistance.bdlocale;

// Start of user code for imports in ContactDAOImpl
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Contact;

// Start of user code for comments in ContactDAOImpl
/**
 * 
 */
// End of user code

// Start of user code for comments in ContactDAOImpl
/**
 * 
 */
// End of user code

public class ContactDAOImpl extends AbstractDAO implements ContactDAO { 

	
	
	// Start of user code for attributes in ContactDAOImpl
	
	// End of user code
	
	// Start of user code for constructors in ContactDAOImpl
	public ContactDAOImpl() {
		super();
		// TODO implementez-moi !
	}
	
	/* // contructeur généré
	public ContactDAOImpl() {
		super();
	}
	*/
	// End of user code
	
	
	
	
	
	/**
	 * @see ContactDAO#find
	 */
	public Contact find(final Integer id) {
		// Start of user code for find method body
		Session s = HibernateUtil.getSession();
        Contact entity = (Contact) s.get(Contact.class.getCanonicalName(), id);
        return entity;		
		// End of user code
	}
	
	
	/**
	 * @see ContactDAO#deleteAll
	 */
	public Boolean deleteAll() {
		// Start of user code for deleteAll method body
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("delete from Contact");
        q.executeUpdate();
        tx.commit();
        return true;		
		// End of user code
	}
	/**
	 * @see ContactDAO#findAll
	 */
	@SuppressWarnings("unchecked")
	public List<Contact> findAll() {
		// Start of user code for findAll method body
		Session s = HibernateUtil.getSession();
        Criteria criteria = s.createCriteria(Contact.class.getCanonicalName());
        List<Contact> results = (List<Contact>) criteria.list();
        return results;		
		// End of user code
	}
}
