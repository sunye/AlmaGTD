package fr.univnantes.alma.gtd.controler.ressources;

import java.util.Vector;

import fr.univnantes.alma.gtd.controler.connexion.ControlerConnexion;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Contact;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Contexte;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Date;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EtatTache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.GestionnaireRessources;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Idee;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Projet;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Tache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.TacheExtendInfo;



// Temporairement abstract pour ne pas avoir a implementer l'interface
// qui n'est pas finie
public class ControlerRessources implements IControlerRessources {
	
	private GestionnaireRessources gestRes = (GestionnaireRessources)ControlerConnexion.utilisateurConnecte.getIGestionnaireRessources();
	
	public ControlerRessources() {
		super();
	}

	
	public Boolean addBasket(String nom, String element) {
		Idee idee = new Idee(nom, element);
		return (gestRes.add(idee) >= 0);
	}

	
	public Boolean addContactToProject(Projet p, String text) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		projetSauv.addContact(new Contact(text));
		return true;
	}

	
	public Boolean addContexte(String nom) {
		Contexte contexte = new Contexte(nom);
		return (gestRes.add(contexte) >= 0);
	}

	
	public Boolean addNoteToProject(Projet p, String note) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		projetSauv.addNote(note);
		return true;
	}

	
	public void addProjet(Projet p) {
		gestRes.add(p);
	}

	
	public Boolean addProjet(String nomProjet, String contexte,
			Projet projetParent) {
		Vector<Contexte> temp = this.getListeContexte();
		Contexte tempc = null;
		for(Contexte c : temp){
			if(c.getTitre().equals(contexte)){
				tempc = c;
			}
		}
		if(tempc == null){
			this.addContexte(contexte);
			temp = this.getListeContexte();
			for(Contexte c : temp){
				if(c.getTitre().equals(contexte)){
					tempc = c;
				}
			}
		}
		if (projetParent==null)
			gestRes.add(new Projet(nomProjet, tempc, projetParent));
		else
			projetParent.addSousProjet(new Projet(nomProjet,tempc, projetParent));
		return true;
	}

	
	public Boolean addTache(TacheExtendInfo info, EtatTache etat, Date reveil, Date echeance, Projet projet) {
		
		Tache tache = new Tache(info,reveil,echeance);
		if (etat.getId() != null) {
			tache.setEtatTache(etat);
			projet.addTache(tache);
			return true;
		}
		return false;
	}

	
	public Tache agir(Vector<Tache> listeTaches) {
		Tache temp = null;
		int prior = 0;
		for (Tache t : listeTaches)
			if(t.getPriorite()>=prior){
				temp = t;
				prior = t.getPriorite();
			}
		return temp;
	}

	
	public Boolean addProject(String name) {
		Projet projet = new Projet(name);
		gestRes.add(projet);
		return true;
	}

	
	public Integer deleteBasket(Idee e) {
		gestRes.remove(e);
		return null;
	}

	
	public Boolean deleteContactFromProject(Projet p, Contact c) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		projetSauv.remove(c);
		return true;
	}

	
	public Integer deleteContexte(Contexte c) {
		gestRes.remove(c);
		return null;
	}

	
	public Boolean deleteNoteFromProject(Projet p, String note) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		projetSauv.removeNote(note);
		return true;
	}

	
	public void deleteProject(Projet p) {
		gestRes.remove(p);
	}

	
	public Boolean deleteTache(Tache t) {
		gestRes.remove(t);
		return true;
	}

	
	public Vector<Idee> getElements() {
		Vector<Idee> res = new Vector<Idee>();
		res.addAll(gestRes.getIdees());
		return res;
	}

	
	public Vector<Contact> getContacts() {
		Vector<Contact> res = new Vector<Contact>();
		res.addAll(gestRes.getContacts());
		return res;
	}
	
	
	public void deleteContact(Contact c) {
		gestRes.remove(c);		
	}
	
	
	public Vector<Contexte> getListeContexte() {
		Vector<Contexte> res = new Vector<Contexte>();
		res.addAll(gestRes.getContextes());
		return res;
	}

	
	public Vector<Projet> getListeProjet() {
		Vector<Projet> res = new Vector<Projet>();
		res.addAll(gestRes.getProjets());
		return res;
	}

	
	public Vector<Tache> getListeTache(Contexte c) {
		Vector<Tache> res = new Vector<Tache>();
		res.addAll(gestRes.getTaches());
		return res;
	}

	
	public Vector<Projet> getProjectSubProject(Projet p) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		Vector<Projet> res = new Vector<Projet>();
		res.addAll(projetSauv.getSousProjets());
		return res;
	}

	
	public Vector<Tache> getProjectTasks(Projet p) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		Vector<Tache> res = new Vector<Tache>();
		res.addAll(projetSauv.getTaches());
		return res;
	}

	
	public Object[] getProjetContacts(Projet p) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		return projetSauv.getContacts().toArray();
	}

	
	public Object[] getProjetNotes(Projet p) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		return projetSauv.getNotes().toArray();
	}

	
	public Tache getProjetUrgentTask(Projet p) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		return projetSauv.getUrgentTask();
	}

	
	public Projet getTaskProjet(Tache t) {
		Tache tacheSauv = gestRes.getTache(t.getId());
		return tacheSauv.getParent();
	}

	
	public Boolean setProjectContext(Projet p, String contexte) {
		Vector<Contexte> temp = this.getListeContexte();
		Contexte tempc = null;
		for(Contexte c : temp){
			if(c.getTitre().equals(contexte)){
				tempc = c;
			}
		}
		if(tempc == null){
			this.addContexte(contexte);
			temp = this.getListeContexte();
			for(Contexte c : temp){
				if(c.getTitre().equals(contexte)){
					tempc = c;
				}
			}
		}
		p.setContexte(tempc);
		return true;
	}

	
	public Boolean setProjetName(Projet p, String nom) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		gestRes.remove(projetSauv);
		projetSauv.setNom(nom);
		gestRes.add(projetSauv);
		return null;
	}
	
	public Integer updateBasket(Idee e, String contenu) {
		String titre = e.getNom();
		gestRes.remove(e);
		gestRes.add(new Idee(titre, contenu));
		return 0;
	}

	

}
