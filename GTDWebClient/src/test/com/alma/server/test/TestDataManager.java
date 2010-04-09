package com.alma.server.test;

import com.alma.server.datamanager.Server;
import com.alma.server.datamanager.ServerToodleDoImp;
import fr.alma.gtd.donneespartagees.ITache;

import com.alma.server.types.ProjetImpl;
import com.alma.server.types.Session;
import com.alma.server.types.TacheImpl;

import fr.alma.gtd.donneespartagees.IProjet;

import junit.framework.TestCase;

public class TestDataManager extends TestCase {
	
	
	public void testToodleDoServer() throws Exception{
		
		//Session session = new Session("", "", "allservv@free.fr", "allserv");
		//Session session = new Session("", "", "dml_aon@hotmail.com", "damien");
		//Session session = new Session("", "", "renard001@live.fr", "renard");
		Session session = new Session("sefg", "wdvf", "damien.levin@etu.univ-nantes.fr", "damien");
		
		
		Server toodleDoServer = new ServerToodleDoImp();
		
		toodleDoServer.connect(session);
		
		
		ProjetImpl projetA =  new ProjetImpl("FolderA",null,null);
		ProjetImpl projetB =  new ProjetImpl("FolderB",null,null);
		ProjetImpl projetC =  new ProjetImpl("FolderC",null,null);

		
		TacheImpl tacheA = new TacheImpl("TacheA",0,0,null,null);
		TacheImpl tacheB = new TacheImpl("TacheB",0,0,null,null);
		TacheImpl tacheC = new TacheImpl("TacheCA",0,0,null,null);

		
		projetB.ajoutTache(tacheA);
		projetB.ajoutTache(tacheB);
		projetB.ajoutTache(tacheC);
		
		
		
		toodleDoServer.createProject(session, projetA);
		toodleDoServer.createProject(session, projetB);
		toodleDoServer.createProject(session, projetC);
		toodleDoServer.delProject(session, projetB);
		
		toodleDoServer.createTask(session, tacheA);
		toodleDoServer.createTask(session, tacheB);		
		toodleDoServer.createTask(session, tacheC);
		toodleDoServer.delTask(session, tacheC);


		System.out.println("Liste des Projets");
		for (IProjet projet : toodleDoServer.getProjects(session)) {
			System.out.println(projet);
		}
		
		System.out.println("Liste des Taches");
		for (ITache tache : toodleDoServer.getTasks(session)) {
			System.out.println(tache);
		}



	}
}
