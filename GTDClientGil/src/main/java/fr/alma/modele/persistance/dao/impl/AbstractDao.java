package fr.alma.modele.persistance.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fr.alma.modele.noyau.EntiteGTD;
import fr.alma.modele.persistance.dao.EntiteDao;

/**
 * Classe abstraite AbstractDao
 * @version 1.0
 * @author Université de Nantes
 */
public abstract class AbstractDao<EntiteGenerique extends EntiteGTD> implements EntiteDao<EntiteGenerique> {

	private Session session;

	private static SessionFactory factory;

	private static Map<String, String> proprietesSpecifiques = new HashMap<String, String>();

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public static void ajouterProprietesSpecifiques(String key, String value) {
		proprietesSpecifiques.put(key, value);
	}

	/**
	 * 
	 * @param properties
	 */
	public static void ajouterProprietesSpecifiques(Properties properties) {
		for (Object o : properties.keySet()) {
			if (o instanceof String) {
				String key = (String) o;
				ajouterProprietesSpecifiques(key, properties.getProperty(key));
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	private SessionFactory getSessionFactory() {
		if (factory == null) {
			Configuration configuration = new Configuration();
			configuration.configure();
			for (String key : proprietesSpecifiques.keySet()) {
				String value = proprietesSpecifiques.get(key);
				configuration.setProperty(key, value);
			}
			factory = configuration.buildSessionFactory();
		}
		return factory;
	}

	/**
	 * 
	 * @return
	 */
	protected Session getSession() {
		if (session == null || !session.isOpen()) {
			session = getSessionFactory().openSession();
		}
		return session;
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<EntiteGenerique> getEntity() {
		ParameterizedType type = (ParameterizedType) this.getClass()
		.getGenericSuperclass();
		Class<EntiteGenerique> clazz = (Class<EntiteGenerique>) type
		.getActualTypeArguments()[0];
		return clazz;
	}

	/**
	 * 
	 * @return
	 */
	protected String getEntitySimpleName() {
		return getEntity().getSimpleName();
	}

	/**
	 * 
	 * @return
	 */
	protected String getEntityName() {
		return getEntity().getName();
	}

	@Override
	public Long creer(EntiteGenerique entity) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		s.persist(entity);
		tx.commit();
		return entity.getId();
	}

	@Override
	public void supprimer(Long entityId) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		EntiteGenerique entity = recuperer(entityId);
		s.delete(entity);
		tx.commit();
	}

	@Override
	public void supprimerTout() {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		Query q = s.createQuery("delete from " + getEntitySimpleName());
		q.executeUpdate();
		tx.commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public EntiteGenerique recuperer(Long entityId) {
		Session s = getSession();
		EntiteGenerique entity = (EntiteGenerique) s.get(getEntity(), entityId);
		return entity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntiteGenerique> recupererTout() {
		Session s = getSession();
		Criteria criteria = s.createCriteria(getEntity());
		List<EntiteGenerique> results = (List<EntiteGenerique>) criteria.list();
		return results;
	}

	@Override
	public void maj(EntiteGenerique entity) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		s.update(entity);
		tx.commit();
	}
}
