package fr.alma.gtd.dao.test;

import java.util.List;

import junit.framework.TestCase;
import fr.alma.gtd.dao.CompteDao;
import fr.alma.gtd.dao.impl.AbstractDao;
import fr.alma.gtd.dao.impl.CompteDaoImpl;
import fr.alma.gtd.entities.Compte;

public class CompteDaoTest extends TestCase {

	private CompteDao dao;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		AbstractDao.addSpecificProperty("hibernate.connection.url", "jdbc:h2:~/.gtd/test/db");
		this.dao = new CompteDaoImpl();
		this.dao.deleteAll();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		this.dao.deleteAll();
	}
	
	
	public void testCreateCompte() throws Exception {
		Compte compte = new Compte("Nom du compte", "MdP du compte");
		Long id = this.dao.create(compte);
		assertNotNull(id);
	}
	
	public void testDeleteCompte() throws Exception {
		Long id = this.dao.create(new Compte("Nom", "MdP"));
		assertNotNull(this.dao.find(id));
		this.dao.delete(id);
		assertNull(this.dao.find(id));
	}
	
	public void testFindCompte() throws Exception {
		String nom = "Nouveau nom de compte";
		Long id = this.dao.create(new Compte(nom, "MdP du compte"));
		
		Compte compte = this.dao.find(id);
		assertNotNull(compte);
		assertEquals(compte.getNom(), nom);
		
		List<Compte> comptes = this.dao.findAll();
		assertEquals(1, comptes.size());
	}
	
	public void testUpdateCompte() throws Exception {
		String mdp1 = "1er mot de passe";
		String mdp2 = "2eme mot de passe";
		Compte compte = new Compte("Nom", mdp1);
		Long id = this.dao.create(compte);
		assertEquals(mdp1, this.dao.find(id).getMdp());
		
		compte.setMdp(mdp2);
		this.dao.update(compte);
		assertEquals(mdp2, this.dao.find(id).getMdp());
	}
	
}
