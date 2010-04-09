package fr.univnantes.alma.gtd.controler.ressources;

import java.util.Vector;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Contact;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Contexte;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Date;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EtatTache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Idee;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Projet;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Tache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.TacheExtendInfo;

public interface IControlerRessources {
	/**
	 * 
	 * @param c
	 * @return
	 */
	public Integer deleteContexte(Contexte c);
	
	/**
	 * 
	 * @param listeTaches
	 * @return
	 */
	public Tache agir(Vector<Tache> listeTaches);
	
	/**
	 * 
	 * @param c
	 * @return
	 */
	public Vector<Tache> getListeTache(Contexte c);
	
	/**
	 * 
	 * @return
	 */
	public Vector<Projet> getListeProjet();
	
	/**
	 * 
	 * @return
	 */
	public Vector<Contexte> getListeContexte();
	
	/**
	 * 
	 * @param p
	 */
	public void deleteProject(Projet p);
	
	/**
	 * 
	 * @param t
	 * @return
	 */
	public Boolean deleteTache(Tache t);
	
	/**
	 * 
	 * @param p
	 */
	public void addProjet(Projet p);
	
	/**
	 * 
	 * @param nom
	 * @return
	 */
	public Boolean addContexte(String nom);
	
	/**
	 * 
	 * @param info
	 * @param etat
	 * @param reveil
	 * @param echeance
	 * @param projet
	 * @return
	 */
	public Boolean addTache(TacheExtendInfo info, EtatTache etat, Date reveil, Date echeance, Projet projet);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public Boolean addProject(String name);
	
	/**
	 * 
	 * @param p
	 * @param note
	 * @return
	 */
	public Boolean addNoteToProject(Projet p, String note);
	
	/**
	 * 
	 * @param e
	 * @param contenu
	 * @return
	 */
	public Integer updateBasket(Idee e, String contenu);
	
	/**
	 * 
	 * @param nom
	 * @param element
	 * @return
	 */
	public Boolean addBasket(String nom, String element);
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	public Integer deleteBasket(Idee e);
	
	/**
	 * 
	 * @return
	 */
	public Vector<Idee> getElements();
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Object[] getProjetNotes(Projet p);
	
	/**
	 * 
	 * @param p
	 * @param note
	 * @return
	 */
	public Boolean deleteNoteFromProject(Projet p, String note);
	
	/**
	 * 
	 * @param p
	 * @param text
	 * @return
	 */
	public Boolean addContactToProject(Projet p, String text);
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Object[] getProjetContacts(Projet p);
	
	/**
	 * 
	 * @param p
	 * @param c
	 * @return
	 */
	public Boolean deleteContactFromProject(Projet p, Contact c);
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Tache getProjetUrgentTask(Projet p);
	
	/**
	 * 
	 * @param nomProjet
	 * @param contexte
	 * @param projetParent
	 * @return
	 */
	public Boolean addProjet(String nomProjet, String contexte, Projet projetParent);
	
	/**
	 * 
	 * @param p
	 * @param contexte
	 * @return
	 */
	public Boolean setProjectContext(Projet p, String contexte);
	
	/**
	 * 
	 * @param p
	 * @param nom
	 * @return
	 */
	public Boolean setProjetName(Projet p,String nom);
	
	/**
	 * 
	 * @param t
	 * @return
	 */
	public Projet getTaskProjet(Tache t);
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Vector<Tache> getProjectTasks(Projet p);
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Vector<Projet> getProjectSubProject(Projet p);
	
	/**
	 * 
	 * @return
	 */
	public Vector<Contact> getContacts();
	
	/**
	 * 
	 * @param c
	 */
	public void deleteContact(Contact c);	
}
