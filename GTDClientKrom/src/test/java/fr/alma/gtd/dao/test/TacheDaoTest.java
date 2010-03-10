package fr.alma.gtd.dao.test;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import fr.alma.gtd.dao.ContexteDao;
import fr.alma.gtd.dao.ElementDao;
import fr.alma.gtd.dao.TacheDao;
import fr.alma.gtd.dao.impl.AbstractDao;
import fr.alma.gtd.dao.impl.ContexteDaoImpl;
import fr.alma.gtd.dao.impl.ElementDaoImpl;
import fr.alma.gtd.dao.impl.TacheDaoImpl;
import fr.alma.gtd.entities.Avancement;
import fr.alma.gtd.entities.Contexte;
import fr.alma.gtd.entities.Echeancier;
import fr.alma.gtd.entities.Frequence;
import fr.alma.gtd.entities.Tache;
import junit.framework.TestCase;

public class TacheDaoTest extends TestCase {
	
	private TacheDao dao;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		AbstractDao.addSpecificProperty("hibernate.connection.url", "jdbc:h2:~/.gtd/test/db");
		this.dao = new TacheDaoImpl();
		this.dao.deleteAll();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		this.dao.deleteAll();
	}
	
	public void testContexte() throws Exception {
		ContexteDao contexteDao = new ContexteDaoImpl();
		long id1_contexte = contexteDao.create(new Contexte("Maison"));
		ElementDao elementDao = new ElementDaoImpl();
		Long id = elementDao.create(new Tache(2, 20, Avancement.AFAIRE,
				"Nettoyer les toilettes", contexteDao.find(id1_contexte)));
		assertNotNull(id);
	}
	
	public void testLiens() throws Exception {
		ContexteDao contexteDao = new ContexteDaoImpl();
		Contexte contexte = new Contexte("Aucun contexte");
		contexteDao.create(contexte);
		
		String nom = "Exemple de tache";
		Tache tache = new Tache(2, 10, Avancement.AFAIRE, nom, contexte);
		
		Long id = dao.create(tache);
		assertNotNull(id);
		
		assertEquals(nom, dao.find(id).getNom());
		
		tache.addLiens("http://www.univ-nantes.fr/");
		tache.addLiens("http://alma.univ-nantes.fr/");
		dao.update(tache);
		
		Collection<String> liens = dao.find(id).getLiens();
		assertEquals(2, liens.size());		
	}
	
	public void testEcheancier() throws Exception {
		ContexteDao contexteDao = new ContexteDaoImpl();
		Contexte contexte = new Contexte("Aucun contexte");
		contexteDao.create(contexte);
		
		Tache tache = new Tache(2, 10, Avancement.AFAIRE, "Nom de la tache", contexte);
		Calendar c = new GregorianCalendar();
		c.set(2010, 1, 4, 12, 25);
		Date d1 = c.getTime();
		c.set(2010, 1, 12, 0, 0);
		Date d2 = c.getTime();
		Echeancier echeancier = new Echeancier(d1, Frequence.JOURNALIERE, d2);
		tache.setEcheancier(echeancier);
		Long id = dao.create(tache);
		
		assertNotNull(dao.find(id).getEcheancier());
	}
	
}
