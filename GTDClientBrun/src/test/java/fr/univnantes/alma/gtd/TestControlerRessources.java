package fr.univnantes.alma.gtd.tests;


import java.util.List;
import java.util.Vector;

import fr.univnantes.alma.gtd.controler.ressources.ControlerRessources;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Contexte;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Date;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EtatTache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Projet;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Tache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.TacheExtendInfo;


import junit.framework.TestCase;

public class TestControlerRessources extends TestCase{

	public void testDeleteContexte(){
			ControlerRessources controlRes = new ControlerRessources();
			Contexte c = new Contexte("context");
			
			controlRes.addContexte(c.getTitre());
			controlRes.deleteContexte(c);
						
			assertEquals(controlRes.getListeContexte().size(), 0);
	}

	public void testAgir() {
		ControlerRessources controlRes = new ControlerRessources();
		Vector<Tache> listeTaches = new Vector<Tache>();
		Tache t1 = new Tache();
		t1.setPriorite(1);
		Tache t2 = new Tache();
		t1.setPriorite(2);
		Tache t3 = new Tache();
		t1.setPriorite(3);
		
		listeTaches.add(t1);
		listeTaches.add(t2);
		listeTaches.add(t3);
		
		assertEquals(t3, controlRes.agir(listeTaches));
	}
	
	
	public void testDeleteProject(){
		ControlerRessources controlRes = new ControlerRessources();
		Projet p = new Projet();
		
		controlRes.addProjet(p);
		controlRes.deleteProject(p);
		
		assertEquals(controlRes.getListeProjet().size() , 0);
		
	}

	public void testDeleteTache(){
		ControlerRessources controlRes = new ControlerRessources();
		Tache t = new Tache();
		
		assertTrue(controlRes.deleteTache(t));
		
	}


	
	public void testAddProjet(){}
	public void testAddProjet2(){}

	public void testAddContexte(){}

	public void testAddTache(){}

	public void testAddProject(){}

	public void testAddNoteToProject(){}

	public void testUpdateBasket(){}

	public void testAddBasket(){}

	public void testDeleteContact(){}
	
	public void testDeleteBasket(){}

	public void testDeleteNoteFromProject(){}

	public void testAddContactToProject(){}

	public void testDeleteContactFromProject(){}
	
	
	
	
	public void testGetProjetContacts(){}

	public void testGetProjetUrgentTask(){}


	public void testSetProjectContext(){}


	public void testSetProjetName(){}


	public void testGetTaskvoid(){}


	public void testGetProjectTasks(){}


	public void testGetProjectSubProject(){}


	public void testGetContacts(){}	

	public void testGetListeTache(){}

	public void testGetListeProjet(){}

	public void testGetListeContexte(){}

	public void testGetElements(){}

	public void testGetProjetNotes(){}


}
