package com.alma.server;

import java.util.ArrayList;

import com.alma.client.serializables.*;
import com.alma.server.types.ProjetImpl;
import com.alma.server.types.TacheImpl;

import fr.alma.gtd.donneespartagees.*;

public class Converter {

	public static IProjet getClientType(Project p){
		ProjetImpl iProjet = new ProjetImpl();
		
		ArrayList<ITache> listeTaches = new ArrayList<ITache>();
		
		for(Task t : p.getListeDeTaches())
			listeTaches.add(Converter.getClientType(t));
		
		ArrayList<IProjet> listeProjets = new ArrayList<IProjet>();
		
		for(Project pr : p.getListeDeSousProjets())
			listeProjets.add(Converter.getClientType(pr));
		
		
		iProjet.setIdentifiant(p.getIdentifiant());
		iProjet.setIdentifiantServeur(p.getIdentifiantServeur());
		//iProjet.setAvancement(fr.alma.gtd.donneespartagees.Avancement.valueOf(p.getAvancement().name()));
		iProjet.setDansLaPoubelle(p.isDansLaPoubelle());
		iProjet.setDateDeDerniereModification(p.getDateDeDerniereModification());
		iProjet.setNom(p.getNom());
		iProjet.setListeDeSousProjets(listeProjets);
		iProjet.setListeDeTaches(listeTaches);
		
		
		/*iProjet.setCreateur(c);
		iProjet.setContexteParDefaut(contexteDefaut);*/
		
		
		return iProjet;
	}
		
		
	
	public static ITache getClientType(Task t){
		TacheImpl iTache = new TacheImpl();
		
		iTache.setIdentifiant(t.getIdentifiant());
		iTache.setIdentifiantServeur(t.getIdentifiantServeur());
		//iTache.setFrequence(fr.alma.gtd.donneespartagees.Frequence.valueOf(t.getFrequence().name()));
		//iTache.setAvancement(fr.alma.gtd.donneespartagees.Avancement.valueOf(t.getAvancement().name()));
		iTache.setDansLaPoubelle(t.isDansLaPoubelle());
		iTache.setDateDebut(t.getDateDebut());
		iTache.setDateDeDerniereModification(t.getDateDeDerniereModification());
		iTache.setDateFin(t.getDateFin());
		iTache.setListeDesURLs(t.getListeDesURLs());
		iTache.setTauxEffort(t.getTauxEffort());
		iTache.setNom(t.getNom());
		iTache.setPriorite(t.getPriorite());
		iTache.setTauxEffort(t.getTauxEffort());
		iTache.setProjetConteneur(Converter.getClientType(t.getProjetConteneur()));
		
		/*iTache.setListeDeTags(t.getListeDeTags());
		iTache.setListeContacts(t.getListeContacts());
		iTache.setContexte(t.getContexte());
		iTache.setCreateur(t.getCreateur());		
		iTache.setListeTachesAnterieures(t.getListeTachesAnterieures());		
		iTache.setParticipant(t.getParticipant());*/
		
		
		return iTache;
	}
	
	public static Project getServerType(IProjet proj){
		Project clientProject = new Project();

		ArrayList<Task> listeTaches = new ArrayList<Task>();		
		for(ITache t : proj.getListeDeTaches()){
			listeTaches.add(Converter.getServerType(t));
			System.out.println(t.getNom() + " dans le projet " + proj.getNom());
		}
		
		ArrayList<Project> listeProjets = new ArrayList<Project>();		
		for(IProjet pr : proj.getListeDeSousProjets())		
			listeProjets.add(Converter.getServerType(pr));
		
		
		clientProject.setIdentifiant(((com.alma.server.types.Project)proj).getIdentifiant());
		clientProject.setNom(proj.getNom());
		clientProject.setIdentifiantServeur(proj.getIdentifiantServeur());
		//clientProject.setAvancement(com.alma.client.serializables.Avancement.valueOf(proj.getAvancement().name()));
		clientProject.setDateDeDerniereModification(proj.getDateDeDerniereModification());
		
		clientProject.setListeDeTaches(listeTaches);
		clientProject.setListeDeSousProjets(listeProjets);
		
		return clientProject;
	}
		
	
	public static Task getServerType(ITache task){
		
		Task clientTask = new Task();
		
		clientTask.setIdentifiant(((com.alma.server.types.Task)task).getIdentifiant());
		clientTask.setNom(task.getNom());
		if(task.getIdentifiantServeur()!=null) clientTask.setIdentifiantServeur(task.getIdentifiantServeur());
		//clientTask.setFrequence(com.alma.client.serializables.Frequence.valueOf(task.getFrequence().name()));
		//clientTask.setAvancement(com.alma.client.serializables.Avancement.valueOf(task.getAvancement().name()));
		if(task.getDateDebut()!=null) clientTask.setDateDebut(task.getDateDebut());
		if(task.getDateDeDerniereModification()!=null) clientTask.setDateDeDerniereModification(task.getDateDeDerniereModification());
		if(task.getDateFin()!=null) clientTask.setDateFin(task.getDateFin());
		if(task.getListeDesURLs()!=null) clientTask.setListeDesURLs(task.getListeDesURLs());
		clientTask.setTauxEffort(task.getTauxEffort());
		clientTask.setPriorite(task.getPriorite());
		
		if(task.getProjetConteneur()!=null){
			Project conteneur = null;//Converter.getServerType(task.getProjetConteneur());
			if(conteneur!=null){
				clientTask.setProjetConteneur(new Project(((com.alma.server.types.Project)task.getProjetConteneur()).getIdentifiant(),task.getProjetConteneur().getNom(),null,null));
			}
		}

		return clientTask;
		
	}
	
}
