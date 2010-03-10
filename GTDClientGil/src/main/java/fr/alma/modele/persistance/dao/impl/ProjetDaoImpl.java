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

	@SuppressWarnings("unchecked")
	@Override
	public List<Projet> recupererTout() {
		Session s = getSession();
		Query query = s.createQuery("from Projet where idutilisateur = :idutilisateur");
		query.setParameter("idutilisateur", Modele.getIdUtilisateur());
		return (List<Projet>) query.list();
	}

	@Override
	public Projet recupererProjetRacine() {
		Session s = getSession();
		Query query = s.createQuery("from Projet where idutilisateur = :idutilisateur and idpere is null");
		query.setParameter("idutilisateur", Modele.getIdUtilisateur());
		return (Projet) query.uniqueResult();
	}
	
	@Override
	public Projet recupererPanier() {
		Session s = getSession();
		Query query = s.createQuery("from Projet where idutilisateur = :idutilisateur and nom = 'Panier'");
		query.setParameter("idutilisateur", Modele.getIdUtilisateur());
		return (Projet) query.uniqueResult();
	}
	
	@Override
	public Projet recupererCorbeille() {
		Session s = getSession();
		Query query = s.createQuery("from Projet where idutilisateur = :idutilisateur and nom = 'Corbeille'");
		query.setParameter("idutilisateur", Modele.getIdUtilisateur());
		return (Projet) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Projet> recupererSousProjets(Long idPere) {
		Session s = getSession();
		Query query = s.createQuery("from Projet where idutilisateur = :idutilisateur and idpere = :idpere");
		query.setParameter("idutilisateur", Modele.getIdUtilisateur());
		query.setParameter("idpere", idPere);
		return (List<Projet>) query.list();
	}
}
