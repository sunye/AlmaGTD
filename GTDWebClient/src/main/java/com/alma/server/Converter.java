package com.alma.server;

import java.util.ArrayList;

import com.alma.client.serializables.*;
import com.alma.server.types.ProjetImpl;
import com.alma.server.types.TacheImpl;

import fr.alma.gtd.donneespartagees.*;

public class Converter {

	/**
	 *.
	 * @param project
	 * @return
	 */
	public static IProjet getClientType(final Project project){
		final ProjetImpl iProjet = new ProjetImpl();
		
		final ArrayList<ITache> listeTaches = new ArrayList<ITache>();
		
		for(Task task : project.getListeDeTaches())
			{listeTaches.add(Converter.getClientType(task));}
		
		final ArrayList<IProjet> listeProjets = new ArrayList<IProjet>();
		
		for(Project projo : project.getListeDeSousProjets())
			{listeProjets.add(Converter.getClientType(projo));}
		
		
		iProjet.setIdentifiant(project.getIdentifiant());
		iProjet.setIdentifiantServeur(project.getIdentifiantServeur());
		//iProjet.setAvancement(fr.alma.gtd.donneespartagees.Avancement.valueOf(p.getAvancement().name()));
		iProjet.setDansLaPoubelle(project.isDansLaPoubelle());
		iProjet.setDateDeDerModif(project.getDateDeDerModif());
		iProjet.setNom(project.getNom());
		iProjet.setListeDeSousProjets(listeProjets);
		iProjet.setListeDeTaches(listeTaches);
		
		
		/*iProjet.setCreateur(c);
		iProjet.setContexteParDefaut(contexteDefaut);*/
		
		
		return iProjet;
	}
		
		
	/**
	 *.
	 * @param task
	 * @return
	 */
	public static ITache getClientType(final Task task){
		final TacheImpl iTache = new TacheImpl();
		
		iTache.setIdentifiant(task.getIdentifiant());
		iTache.setIdentifiantServeur(task.getIdentifiantServeur());
		//iTache.setFrequence(fr.alma.gtd.donneespartagees.Frequence.valueOf(t.getFrequence().name()));
		//iTache.setAvancement(fr.alma.gtd.donneespartagees.Avancement.valueOf(t.getAvancement().name()));
		iTache.setDansLaPoubelle(task.isDansLaPoubelle());
		iTache.setDateDebut(task.getDateDebut());
		iTache.setDateDeDerModif(task.getDateDeDerModif());
		iTache.setDateFin(task.getDateFin());
		iTache.setListeDesURLs(task.getListeDesURLs());
		iTache.setTauxEffort(task.getTauxEffort());
		iTache.setNom(task.getNom());
		iTache.setPriorite(task.getPriorite());
		iTache.setTauxEffort(task.getTauxEffort());
		iTache.setProjetConteneur(Converter.getClientType(task.getProjetConteneur()));
		
		/*iTache.setListeDeTags(t.getListeDeTags());
		iTache.setListeContacts(t.getListeContacts());
		iTache.setContexte(t.getContexte());
		iTache.setCreateur(t.getCreateur());		
		iTache.setListeTachesAnterieures(t.getListeTachesAnterieures());		
		iTache.setParticipant(t.getParticipant());*/
		
		
		return iTache;
	}
	
	
	/**
	 * .
	 * @param proj
	 * @return
	 */
	public static Project getServerType(final IProjet proj){
		final Project clientProject = new Project();

		final ArrayList<Task> listeTaches = new ArrayList<Task>();		
		for(ITache task : proj.getListeDeTaches()){
			listeTaches.add(Converter.getServerType(task));
			System.out.println(task.getNom() + " dans le projet " + proj.getNom());
		}
		
		final ArrayList<Project> listeProjets = new ArrayList<Project>();		
		for(IProjet projo : proj.getListeDeSousProjets()){
			listeProjets.add(Converter.getServerType(projo));}
		
		
		clientProject.setIdentifiant(((com.alma.server.types.Project)proj).getIdentifiant());
		clientProject.setNom(proj.getNom());
		clientProject.setIdentifiantServeur(proj.getIdentifiantServeur());
		//clientProject.setAvancement(com.alma.client.serializables.Avancement.valueOf(proj.getAvancement().name()));
		clientProject.setDateDeDerModif(proj.getDateDeDerModif());
		
		clientProject.setListeDeTaches(listeTaches);
		clientProject.setListeDeSousProjets(listeProjets);
		
		return clientProject;
	}
		
	/**
	 *.
	 * @param task
	 * @return
	 */
	public static Task getServerType(final ITache task){
		
		final Task clientTask = new Task();
		
		clientTask.setIdentifiant(((com.alma.server.types.Task)task).getIdentifiant());
		clientTask.setNom(task.getNom());
		if(task.getIdentifiantServeur()!=null) {clientTask.setIdentifiantServeur(task.getIdentifiantServeur());}
		//clientTask.setFrequence(com.alma.client.serializables.Frequence.valueOf(task.getFrequence().name()));
		//clientTask.setAvancement(com.alma.client.serializables.Avancement.valueOf(task.getAvancement().name()));
		if(task.getDateDebut()!=null) {clientTask.setDateDebut(task.getDateDebut());}
		if(task.getDateDeDerModif()!=null) {clientTask.setDateDeDerModif(task.getDateDeDerModif());}
		if(task.getDateFin()!=null) {clientTask.setDateFin(task.getDateFin());}
		if(task.getListeDesURLs()!=null) {clientTask.setListeDesURLs(task.getListeDesURLs());}
		clientTask.setTauxEffort(task.getTauxEffort());
		clientTask.setPriorite(task.getPriorite());
		
		
		if(task.getProjetConteneur()!=null){
			final Project conteneur = null;
			if(conteneur!=null){
				clientTask.setProjetConteneur(new Project(((com.alma.server.types.Project)task.getProjetConteneur()).getIdentifiant(),task.getProjetConteneur().getNom(),null,null));
			}
		}

		return clientTask;
		
	}
	
}
