package fr.alma.modele.persistance.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.alma.modele.Modele;
import fr.alma.modele.noyau.Projet;
import fr.alma.modele.persistance.dao.ProjetDao;

/**
 * Classe ProjetDaoImpl
 * @version 1.0
 * @author Université de Nantes
 */
public class ProjetDaoImpl extends AbstractDao<Projet> implements ProjetDao {
    
        private final static String IDUTILISATEURVAR = "utilisateurid";
        private final static String STARTREQUETE = "from Projet where idutilisateur = :"+IDUTILISATEURVAR;
    

	@SuppressWarnings("unchecked")
	@Override
	public List<Projet> recupererTout() {
		Session s = getSession();
		Query query = s.createQuery(STARTREQUETE);
		query.setParameter(IDUTILISATEURVAR, Modele.getIdUtilisateur());
		return (List<Projet>) query.list();
	}

	@Override
	public Projet recupererProjetRacine() {
		Session s = getSession();
		Query query = s.createQuery(STARTREQUETE+" and idpere is null");
		query.setParameter(IDUTILISATEURVAR, Modele.getIdUtilisateur());
		return (Projet) query.uniqueResult();
	}
	
	@Override
	public Projet recupererPanier() {
		Session s = getSession();
		Query query = s.createQuery(STARTREQUETE+" and nom = 'Panier'");
		query.setParameter(IDUTILISATEURVAR, Modele.getIdUtilisateur());
		return (Projet) query.uniqueResult();
	}
	
	@Override
	public Projet recupererCorbeille() {
		Session s = getSession();
		Query query = s.createQuery(STARTREQUETE+" and nom = 'Corbeille'");
		query.setParameter(IDUTILISATEURVAR, Modele.getIdUtilisateur());
		return (Projet) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projet> recupererSousProjets(Long idPere) {
		Session s = getSession();
		Query query = s.createQuery(STARTREQUETE+" and idpere = :idpere");
		query.setParameter(IDUTILISATEURVAR, Modele.getIdUtilisateur());
		query.setParameter("idpere", idPere);
		return (List<Projet>) query.list();
	}
}
