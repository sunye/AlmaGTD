package fr.alma.modele.persistance.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.alma.modele.noyau.Utilisateur;
import fr.alma.modele.persistance.dao.UtilisateurDao;

/**
 * Classe UtilisateurDaoImpl
 * @version 1.0
 * @author Université de Nantes
 */
public class UtilisateurDaoImpl extends AbstractDao<Utilisateur> implements UtilisateurDao {


        /**
         * Renvoie tous les utilisateurs de la base
         * @return
         */
	@SuppressWarnings("unchecked")
	@Override
	public List<Utilisateur> recupererTout() {
		Session s = getSession();
		String hql = "from Utilisateur order by login";
		Query query = s.createQuery(hql);
		List<Utilisateur> results = (List<Utilisateur>) query.list();
		return results;
	}

	@Override
	public Utilisateur trouverUtilisateur(String login) {
		Session s = getSession();

		Query query = s.createQuery("from Utilisateur where login = :login");
		query.setParameter("login", login);
		return (Utilisateur)query.uniqueResult();
	}

	@Override
	public Utilisateur trouverUtilisateur(String login, char[] pwd) {
		Session s = getSession();
		Query query = s.createQuery("from Utilisateur where login = :login and password = :password");
		query.setParameter("login", login);
		query.setParameter("password", pwd);
		System.out.println("connexion ok ? : " + query.uniqueResult() == null);
		return (Utilisateur)query.uniqueResult();
	}
}
