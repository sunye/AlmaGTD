package fr.univnantes.alma.gtd.persistance.bdlocale;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;

public class UtilisateurDAOImpl extends AbstractDAO implements UtilisateurDAO{

	@Override
	public Boolean deleteAll() {
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("delete from Utilisateur");
        q.executeUpdate();
        tx.commit();
        return true;
	}

	@Override
	public Utilisateur find(Integer id) {
		Session s = HibernateUtil.getSession();
        Utilisateur entity = (Utilisateur) s.get(Utilisateur.class.getCanonicalName(), id);
        return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> findAll() {
		Session s = HibernateUtil.getSession();
        Criteria criteria = s.createCriteria(Utilisateur.class.getCanonicalName());
        List<Utilisateur> results = (List<Utilisateur>) criteria.list();
        return results;	
	}

}
