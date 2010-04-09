package fr.alma.gtd.dao.test;

import java.util.List;

import fr.alma.gtd.dao.ChoseAFaireDao;
import fr.alma.gtd.dao.ContactDao;
import fr.alma.gtd.dao.impl.AbstractDao;
import fr.alma.gtd.dao.impl.ChoseAFaireDaoImpl;
import fr.alma.gtd.dao.impl.ContactDaoImpl;
import fr.alma.gtd.entities.AFaireUnJour;
import fr.alma.gtd.entities.ATraiter;
import fr.alma.gtd.entities.ChoseAFaire;
import fr.alma.gtd.entities.Contact;
import junit.framework.TestCase;

public class ContactDaoTest extends TestCase{
	
	

		private ContactDao dao;
		
		
		
		@Override
		protected void setUp() throws Exception {
			super.setUp();
			AbstractDao.addSpecificProperty("hibernate.connection.url", "jdbc:h2:~/.gtd/test/db");
			this.dao = new ContactDaoImpl();
			this.dao.deleteAll();
			
			
			
		}
		
		@Override
		protected void tearDown() throws Exception {
			super.tearDown();
			this.dao.deleteAll();
			
		}
		
		
		public void testCreateContact() throws Exception {
			String prenom="Joel";
			String nom="Sambre";
			
			Contact cont= new Contact(nom, prenom);
			Long id = this.dao.create(cont);
			assertNotNull(id);
			
		
		}
		
		public void testDeleteChose() throws Exception {
			String prenom="Joel";
			String nom="Sambre";
			
			Contact cont= new Contact(nom, prenom);
			Long id = this.dao.create(cont);
			this.dao.delete(id);
			assertNull(this.dao.find(id));
			
			
			
		}
		
		public void testFindCont() throws Exception {
			String prenom="Joel";
			String nom="Sambre";
			
			Contact cont= new Contact(nom, prenom);
			Long id = this.dao.create(cont);
			assertNotNull(this.dao.find(id));
						
			
			
			List<Contact> contacts = this.dao.findAll();
			assertEquals(1, contacts.size());
		}
		
		public void testUpdateCont() throws Exception {
			
			String prenom="Joel";
			String nom="Sambre";
			String prenom2="Zack";
			
			Contact cont= new Contact(nom, prenom);
			Long id = this.dao.create(cont);

			assertEquals(prenom, this.dao.find(id).getPrenom());
			
			cont.setPrenom(prenom2);
			this.dao.update(cont);
			assertEquals(prenom2, this.dao.find(id).getPrenom());
		}
		
		

		

}
