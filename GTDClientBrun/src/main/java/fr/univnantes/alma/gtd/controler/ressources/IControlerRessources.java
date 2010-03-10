package fr.univnantes.alma.gtd.controler.ressources;

import java.util.Vector;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Contact;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Contexte;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Date;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EtatTache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Idee;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Projet;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Tache;

public interface IControlerRessources {
	public Integer deleteContexte(Contexte c);
	public Tache agir(Vector<Tache> listeTaches);
	public Vector<Tache> getListeTache(Contexte c);
	public Vector<Projet> getListeProjet();
	public Vector<Contexte> getListeContexte();
	public void deleteProject(Projet p);
	public Boolean deleteTache(Tache t);
	public void addProjet(Projet p);
	public Boolean addContexte(String nom);
	public Boolean addTache(String nom, Integer priorite,
			Integer effort, EtatTache etat, Date reveil,
			Date echeance, Projet projet);
	public Boolean addProject(String name);
	public Boolean addNoteToProject(Projet p, String note);
	public Integer updateBasket(Idee e, String contenu);
	public Boolean addBasket(String nom, String element);
	public Integer deleteBasket(Idee e);
	public Vector<Idee> getElements();
	public Object[] getProjetNotes(Projet p);
	public Boolean deleteNoteFromProject(Projet p, String note);
	public Boolean addContactToProject(Projet p, String text);
	public Object[] getProjetContacts(Projet p);
	public Boolean deleteContactFromProject(Projet p, Contact c);
	public Tache getProjetUrgentTask(Projet p);
	public Boolean addProjet(String nomProjet, String contexte, Projet projetParent);
	public Boolean setProjectContext(Projet p, String contexte);
	public Boolean setProjetName(Projet p,String nom);
	public Projet getTaskProjet(Tache t);
	public Vector<Tache> getProjectTasks(Projet p);
	public Vector<Projet> getProjectSubProject(Projet p);
	public Vector<Contact> getContacts();
	public void deleteContact(Contact c);	
}
