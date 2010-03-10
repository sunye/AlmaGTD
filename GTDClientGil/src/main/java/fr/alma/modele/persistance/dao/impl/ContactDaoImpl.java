package fr.alma.modele.persistance.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import fr.alma.modele.Modele;
import fr.alma.modele.noyau.Contact;
import fr.alma.modele.persistance.dao.ContactDao;

/**
 * Classe ContactDaoImpl
 * @version 1.0
 * @author Université de Nantes
 */
public class ContactDaoImpl extends AbstractDao<Contact> implements ContactDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> recupererTout() {
		Session s = getSession();
		Query query = s.createQuery("from Contact where idutilisateur = :idutilisateur order by nom");
		query.setParameter("idutilisateur", Modele.getIdUtilisateur());
		List<Contact> results = (List<Contact>) query.list();
		return results;
	}
}
