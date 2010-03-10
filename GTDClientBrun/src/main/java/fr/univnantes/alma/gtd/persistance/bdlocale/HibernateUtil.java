package fr.univnantes.alma.gtd.persistance.bdlocale;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;

public class HibernateUtil {
	private static Session session;

    private static SessionFactory factory;

    private static Map<String, String> specificProperties = new HashMap<String, String>();

    public static void addSpecificProperty(String key, String value) {
        specificProperties.put(key, value);
    }

    public static void addSpecificProperties(Properties properties) {
        for (Object o : properties.keySet()) {
            if (o instanceof String) {
                String key = (String) o;
                addSpecificProperty(key, properties.getProperty(key));
            }
        }
    }

    private static SessionFactory getSessionFactory() {
        if (factory == null) {
            Configuration configuration = new Configuration();
            configuration = configuration.configure();
            for (String key : specificProperties.keySet()) {
                String value = specificProperties.get(key);
                configuration.setProperty(key, value);
            }
            factory = configuration.buildSessionFactory();
        }
        return factory;
    }

    protected static Session getSession() {
        if (session == null || !session.isOpen()) {
            session = getSessionFactory().openSession();
        }
        return session;
    }

    @SuppressWarnings("unchecked")
    protected Class<GTDEntity> getEntity() {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        Class<GTDEntity> clazz = (Class<GTDEntity>) type
                .getActualTypeArguments()[0];
        return clazz;
    }

    protected String getEntitySimpleName() {
        return getEntity().getSimpleName();
    }

    protected String getEntityName() {
        return getEntity().getName();
    }
    
    public static void valide() {
    	Session s = HibernateUtil.getSession();
        Transaction tx = s.beginTransaction();
        tx.commit();
    }
}
