package fr.alma.gtd.dao.test;

import java.util.List;
import junit.framework.TestCase;
import fr.alma.gtd.dao.ContexteDao;
import fr.alma.gtd.dao.impl.AbstractDao;
import fr.alma.gtd.dao.impl.ContexteDaoImpl;
import fr.alma.gtd.entities.Contexte;

public class ContexteDaoTest extends TestCase{
	private ContexteDao dao;
	
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		AbstractDao.addSpecificProperty("hibernate.connection.url", "jdbc:h2:~/.gtd/test/db");
		this.dao = new ContexteDaoImpl();
		this.dao.deleteAll();
		
		
		
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		this.dao.deleteAll();
		
	}
	
	
	public void testCreateContext() throws Exception {
		
		String nom="truite";
		
		Contexte cont= new Contexte(nom);
		Long id = this.dao.create(cont);
		assertNotNull(id);
		
	
	}
	
	public void testDeleteContext() throws Exception {
		String nom="truite";
		
		Contexte cont= new Contexte(nom);
		Long id = this.dao.create(cont);
		
		this.dao.delete(id);
		assertNull(this.dao.find(id));
		
		
		
	}
	
	public void testFindCont() throws Exception {
		String nom="truite";
		
		Contexte cont= new Contexte(nom);
		Long id = this.dao.create(cont);
		
		
		
		assertNotNull(this.dao.find(id));
					
		
		
		List<Contexte> contacts = this.dao.findAll();
		assertEquals(1, contacts.size());
	}
	
	public void testUpdateCont() throws Exception {
		
		String nom="truite";
		String nom2="Ponay";
		
		Contexte cont= new Contexte(nom);
		Long id = this.dao.create(cont);
		
		
		assertEquals(nom, this.dao.find(id).getNom());
		
		cont.setNom(nom2);
		this.dao.update(cont);
		assertEquals(nom2, this.dao.find(id).getNom());
	}
	
	
	
	
	public void testExist() throws Exception {
		
		String nom="Ponay";
		
		Contexte cont= new Contexte(nom);
		Long id = this.dao.create(cont);
		
		assertEquals(true, dao.exist(nom));
		
		
	}
	
	public void testindContexteNul() throws Exception {
		
		String nom="Ponay";
		
		Contexte cont= new Contexte(nom);
		Long id = this.dao.create(cont);
		
	
		
		
	}
	
}
