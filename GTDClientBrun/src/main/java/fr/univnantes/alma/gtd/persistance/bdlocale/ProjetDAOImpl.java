
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.persistance.bdlocale;

// Start of user code for imports in ProjetDAOImpl
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Projet;

// Start of user code for comments in ProjetDAOImpl
/**
 * 
 */
// End of user code

// Start of user code for comments in ProjetDAOImpl
/**
 * 
 */
// End of user code

public class ProjetDAOImpl extends AbstractDAO implements ProjetDAO { 

	
	
	// Start of user code for attributes in ProjetDAOImpl
	
	// End of user code
	
	// Start of user code for constructors in ProjetDAOImpl
	public ProjetDAOImpl() {
		super();
		// TODO implementez-moi !
	}
	
	/* // contructeur généré
	public ProjetDAOImpl() {
		super();
	}
	*/
	// End of user code
	
	
	
	
	
	/**
	 * @see ProjetDAO#find
	 */
	public Projet find(final Integer id) {
		// Start of user code for find method body
		Session s = HibernateUtil.getSession();
        Projet entity = (Projet) s.get(Projet.class.getCanonicalName(), id);
        return entity;		
		// End of user code
	}
	/**
	 * @see ProjetDAO#update
	 */
	public Boolean update(final Projet entity) {
		// Start of user code for update method body
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        s.update(entity);
        tx.commit();
        return true;		
		// End of user code
	}
	
	/**
	 * @see ProjetDAO#deleteAll
	 */
	public Boolean deleteAll() {
		// Start of user code for deleteAll method body
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("delete from Projet");
        q.executeUpdate();
        tx.commit();
        return true;			
		// End of user code
	}
	/**
	 * @see ProjetDAO#findAll
	 */
	@SuppressWarnings("unchecked")
	public List<Projet> findAll() {
		// Start of user code for findAll method body
		Session s = HibernateUtil.getSession();
        Criteria criteria = s.createCriteria(Projet.class.getCanonicalName());
        List<Projet> results = (List<Projet>) criteria.list();
        return results;			
		// End of user code
	}
}
