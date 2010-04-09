package com.alma.server;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.alma.client.serializables.Context;
import com.alma.client.serializables.Participant;
import com.alma.client.serializables.Project;
import com.alma.client.serializables.Task;
import com.alma.server.Converter;
import com.alma.server.types.ProjetImpl;

import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;

public class ConverterTest {

	Participant participant;
	Context context;
	Project project;
	IProjet iproject;
	Task task;
	ITache itask;
	
	
	@Before
	public void initialise() {
		participant = new Participant("bob");
		context = new Context("nom du contexte");
		project = new Project("identifiant","nom du projet",context,participant);
		task = new Task("idTask","nomTask",1,1,context,participant);
		// TODO le projetConteneur doit etre initialisé à l'ajout de la tache.
		//project.ajoutTache(task);
		//assertNotNull(task.getProjetConteneur());
	}
		
	
	
	@Test
	public void getClientTypeProject(){
		//on prend la classe IProjet du package fr.alma.gtd.donneespartagees
		iproject = Converter.getClientType(project);
		//on test la correspondance entre l'élément converti et celui d'origine
		assertEquals(iproject.getNom(),project.getNom());
		assertEquals(iproject.getAvancement(),project.getAvancement());
		assertEquals(iproject.getDateDeDerModif(),project.getDateDeDerModif());
		assertEquals(iproject.getIdentifiantServeur(),project.getIdentifiantServeur());
		assertEquals(iproject.getListeDeTaches(),project.getListeDeTaches());
		//TODO initialiser le createur a la creation de iProjet...
		//assertEquals(iproject.getCreateur(),project.getCreateur());
		//TODOITache initialiser le contextParDefaut a la creation de iProjet
		//assertEquals(iproject.getContexteParDefaut(),project.getContexteParDefaut());
	}
	
	@Test
	public void getClientTypeTask(){
		task.setProjetConteneur(project);
		itask = Converter.getClientType(task);
		//on test la correspondance entre l'élément converti et celui d'origine
		assertEquals(itask.getAvancement(),task.getAvancement());		
		assertEquals(itask.getDateDebut(),task.getDateDebut());
		assertEquals(itask.getDateDeDerModif(),task.getDateDeDerModif());
		assertEquals(itask.getDateFin(),task.getDateFin());
		assertEquals(itask.getFrequence(),task.getFrequence());
		assertEquals(itask.getIdentifiantServeur(),task.getIdentifiantServeur());
		assertEquals(itask.getListeContacts(),task.getListeContacts());
		assertEquals(itask.getListeDesURLs(),task.getListeDesURLs());
		assertEquals(itask.getListeDeTags(),task.getListeDeTags());
		assertEquals(itask.getListeTachesAnterieures(),task.getListeTachesAnterieures());
		assertEquals(itask.getNom(),task.getNom());
		assertEquals(itask.getParticipant(),task.getParticipant());
		assertEquals(itask.getPriorite(),task.getPriorite());
		assertEquals(itask.getTauxEffort(),task.getTauxEffort());
		task.setProjetConteneur(project);
		ITache itask = Converter.getClientType(task);
		//assertEquals(itask.getProjetConteneur(),task.getProjetConteneur());
		//assertEquals(itask.getContexte(),task.getContexte());
		//assertEquals(itask.getCreateur(),task.getCreateur());
	}

	
}
