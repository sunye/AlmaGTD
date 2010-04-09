package fr.alma.gtd.dao.test;

import java.util.List;

import fr.alma.gtd.dao.ChoseAFaireDao;
import fr.alma.gtd.dao.ContexteDao;
import fr.alma.gtd.dao.ProjetDao;
import fr.alma.gtd.dao.impl.AbstractDao;
import fr.alma.gtd.dao.impl.ChoseAFaireDaoImpl;
import fr.alma.gtd.dao.impl.ContexteDaoImpl;
import fr.alma.gtd.dao.impl.ProjetDaoImpl;
import fr.alma.gtd.entities.AFaireUnJour;
import fr.alma.gtd.entities.ATraiter;
import fr.alma.gtd.entities.ChoseAFaire;
import fr.alma.gtd.entities.Contexte;
import fr.alma.gtd.entities.Projet;
import junit.framework.TestCase;

public class ChoseAFaireDaoTest extends TestCase {

	private ChoseAFaireDao dao;
	
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		AbstractDao.addSpecificProperty("hibernate.connection.url", "jdbc:h2:~/.gtd/test/db");
		this.dao = new ChoseAFaireDaoImpl();
		this.dao.deleteAll();
		
		
		
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		this.dao.deleteAll();
		
	}
	
	
	public void testCreateChose() throws Exception {
		ChoseAFaire chose = new AFaireUnJour("manger un ponay");
		Long id = this.dao.create(chose);
		assertNotNull(id);
		
		ChoseAFaire chose2= new ATraiter("manger un ponay le retour");
		id = this.dao.create(chose2);
		assertNotNull(id);
	}
	
	public void testDeleteChose() throws Exception {
		ChoseAFaire chose = new AFaireUnJour("manger un ponay");
		Long id = this.dao.create(chose);
		this.dao.delete(id);
		assertNull(this.dao.find(id));
		
		
		ChoseAFaire chose2= new ATraiter("manger un ponay le retour");
		id = this.dao.create(chose2);
		this.dao.delete(id);
		assertNull(this.dao.find(id));
	}
	
	public void testFindChose() throws Exception {
		String ndesc="manger un ponay";
		
		ChoseAFaire chose = new AFaireUnJour(ndesc);
		Long id = this.dao.create(chose);
		
						
		ChoseAFaire chose2= new ATraiter("manger un ponay le retour");
		this.dao.create(chose2);
			
		ChoseAFaire untruc = this.dao.find(id);
		assertNotNull(untruc);
		assertEquals(untruc.getDescription(), ndesc);
		
		
		
		List<ChoseAFaire> choses = this.dao.findAll();
		assertEquals(2, choses.size());
	}
	
	public void testUpdateChose() throws Exception {
		
		String ndesc="manger un ponay";
		String nDesc2="manger un ponay le retour";
		
		ChoseAFaire chose = new AFaireUnJour(ndesc);
		Long id = this.dao.create(chose);

		assertEquals(ndesc, this.dao.find(id).getDescription());
		
		chose.setDescription(nDesc2);
		this.dao.update(chose);
		assertEquals(nDesc2, this.dao.find(id).getDescription());
	}
	
	
	public void testfindAFaireUnJour() throws Exception{
		ChoseAFaire chose = new AFaireUnJour("manger un ponay");
		Long id = this.dao.create(chose);
		assertEquals(dao.findAFaireUnJour().size(),1);
		
		
	}
	public void testfindATraiter() throws Exception{
		ChoseAFaire chose2= new ATraiter("manger un ponay le retour");
		Long id = this.dao.create(chose2);
		assertEquals(dao.findATraiter().size(),1);
		
		
	}
	
}
