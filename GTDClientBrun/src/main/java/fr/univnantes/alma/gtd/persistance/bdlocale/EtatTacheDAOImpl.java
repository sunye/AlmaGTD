package fr.univnantes.alma.gtd.persistance.bdlocale;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.univnantes.alma.gtd.model.gestionnaireressources.AFaire;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Deleguee;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EnAttente;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EtatTache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Finie;

public class EtatTacheDAOImpl extends AbstractDAO implements EtatTacheDAO{

	@Override
	public Boolean deleteAll() {
		Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("delete from EtatTache");
        q.executeUpdate();
        tx.commit();
        return true;	
	}

	@Override
	public AFaire findAFaire(Integer id) {
		Session s = HibernateUtil.getSession();
		AFaire entity = (AFaire) s.get(AFaire.class.getCanonicalName(), id);
        return entity;	
	}

	@Override
	public List<EtatTache> findAll() {
		Session s = HibernateUtil.getSession();
        Criteria criteria = s.createCriteria(EtatTache.class.getCanonicalName());
        List<EtatTache> results = (List<EtatTache>) criteria.list();
        return results;		
	}

	@Override
	public Deleguee findDeleguee(Integer id) {
		Session s = HibernateUtil.getSession();
		Deleguee entity = (Deleguee) s.get(Deleguee.class.getCanonicalName(), id);
        return entity;
	}

	@Override
	public EnAttente findEnAttente(Integer id) {
		Session s = HibernateUtil.getSession();
		EnAttente entity = (EnAttente) s.get(EnAttente.class.getCanonicalName(), id);
        return entity;
	}

	@Override
	public Finie findFinie(Integer id) {
		Session s = HibernateUtil.getSession();
		Finie entity = (Finie) s.get(Finie.class.getCanonicalName(), id);
        return entity;
	}

}
