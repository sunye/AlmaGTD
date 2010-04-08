package fr.univnantes.alma.gtd.persistance.bdlocale;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;

public class UtilisateurDAOImpl extends AbstractDAO implements UtilisateurDAO{

	
	public Boolean deleteAll() {
		Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from Utilisateur");
        query.executeUpdate();
        transaction.commit();
        return true;
	}

	
	public Utilisateur find(Integer id) {
		Session session = HibernateUtil.getSession();
        return (Utilisateur) session.get(Utilisateur.class.getCanonicalName(), id);
	}

	@SuppressWarnings("unchecked")
	public List<Utilisateur> findAll() {
		Session session = HibernateUtil.getSession();
        Criteria criteria = session.createCriteria(Utilisateur.class.getCanonicalName());
        return (List<Utilisateur>) criteria.list();
	}

}
