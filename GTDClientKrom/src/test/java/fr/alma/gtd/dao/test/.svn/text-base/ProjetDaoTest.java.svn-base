package fr.alma.gtd.dao.test;

import java.util.List;

import junit.framework.TestCase;
import fr.alma.gtd.dao.ContexteDao;
import fr.alma.gtd.dao.ProjetDao;
import fr.alma.gtd.dao.impl.AbstractDao;
import fr.alma.gtd.dao.impl.ContexteDaoImpl;
import fr.alma.gtd.dao.impl.ProjetDaoImpl;
import fr.alma.gtd.entities.Contexte;
import fr.alma.gtd.entities.Projet;

public class ProjetDaoTest extends TestCase {

	private ProjetDao dao;
	private ContexteDao con;
	private Contexte contexte;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		AbstractDao.addSpecificProperty("hibernate.connection.url", "jdbc:h2:~/.gtd/test/db");
		this.dao = new ProjetDaoImpl();
		this.dao.deleteAll();
		
		this.con= new ContexteDaoImpl();
		con.deleteAll();
		
		contexte= new Contexte("un contexte");
		
		con.create(contexte);
		assertNotNull(contexte.getId());
		
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		this.dao.deleteAll();
		con.deleteAll();
	}
	
	
	public void testCreateProjet() throws Exception {
		Projet projet = new Projet(true, "un new projet", contexte);
		Long id = this.dao.create(projet);
		assertNotNull(id);
	}
	
	public void testDeleteProjet() throws Exception {
		Projet projet = new Projet(true, "un new projet", contexte);
		Long id = this.dao.create(projet);
		this.dao.delete(id);
		assertNull(this.dao.find(id));
	}
	
	public void testFindProjet() throws Exception {
		String nom="un new projet";
		Long id = this.dao.create(new Projet(true,nom , contexte));
		
		Projet projet = this.dao.find(id);
		assertNotNull(projet);
		assertEquals(projet.getNom(), nom);
		
		List<Projet> projets = this.dao.findAll();
		assertEquals(1, projets.size());
	}
	
	public void testUpdateProjet() throws Exception {
		
		String non = "premier non";
		String nan = "second nan";
		Projet pro=new Projet(true,non , contexte);
		Long id = this.dao.create(pro);
				
		
		
		assertEquals(non, this.dao.find(id).getNom());
		
		pro.setNom(nan);
		this.dao.update(pro);
		assertEquals(nan, this.dao.find(id).getNom());
	}
	
	
	public void testFindProjetRacine(){
		Projet compte = new Projet(true, "un new projet", contexte);
		Long id = this.dao.create(compte);
		
		assertNotNull(dao.findProjetRacine());
		
	}
	
}
