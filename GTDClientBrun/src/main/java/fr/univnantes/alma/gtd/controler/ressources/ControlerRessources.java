package fr.univnantes.alma.gtd.controler.ressources;

import java.util.ArrayList;
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



// Temporairement abstract pour ne pas avoir a implementer l'interface
// qui n'est pas finie
public class ControlerRessources implements IControlerRessources {
	
	private GestionnaireRessources gestRes = (GestionnaireRessources)ControlerConnexion.utilisateurConnecte.getIGestionnaireRessources();
	
	public ControlerRessources() {
		super();
	}

	@Override
	public Boolean addBasket(String nom, String element) {
		Idee idee = new Idee(nom, element);
		return (gestRes.add(idee) >= 0);
	}

	@Override
	public Boolean addContactToProject(Projet p, String text) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		projetSauv.addContact(new Contact(text));
		return true;
	}

	@Override
	public Boolean addContexte(String nom) {
		Contexte contexte = new Contexte(nom);
		return (gestRes.add(contexte) >= 0);
	}

	@Override
	public Boolean addNoteToProject(Projet p, String note) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		projetSauv.addNote(note);
		return true;
	}

	@Override
	public void addProjet(Projet p) {
		gestRes.add(p);
	}

	@Override
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

	@Override
	public Boolean addTache(String nom, Integer priorite, Integer effort,
			EtatTache etat, Date reveil, Date echeance, Projet projet) {
		
		Tache tache = new Tache(nom,priorite,effort,reveil,echeance,new ArrayList<String>(),new ArrayList<String>());
		if (etat.getId() != null) {
			tache.setEtatTache(etat);
			projet.addTache(tache);
			return true;
		}
		return false;
	}

	@Override
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

	@Override
	public Boolean addProject(String name) {
		Projet projet = new Projet(name);
		gestRes.add(projet);
		return true;
	}

	@Override
	public Integer deleteBasket(Idee e) {
		gestRes.remove(e);
		return null;
	}

	@Override
	public Boolean deleteContactFromProject(Projet p, Contact c) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		projetSauv.remove(c);
		return true;
	}

	@Override
	public Integer deleteContexte(Contexte c) {
		gestRes.remove(c);
		return null;
	}

	@Override
	public Boolean deleteNoteFromProject(Projet p, String note) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		projetSauv.removeNote(note);
		return true;
	}

	@Override
	public void deleteProject(Projet p) {
		gestRes.remove(p);
	}

	@Override
	public Boolean deleteTache(Tache t) {
		gestRes.remove(t);
		return true;
	}

	@Override
	public Vector<Idee> getElements() {
		Vector<Idee> res = new Vector<Idee>();
		res.addAll(gestRes.getIdees());
		return res;
	}

	@Override
	public Vector<Contact> getContacts() {
		Vector<Contact> res = new Vector<Contact>();
		res.addAll(gestRes.getContacts());
		return res;
	}
	
	@Override
	public void deleteContact(Contact c) {
		gestRes.remove(c);		
	}
	
	@Override
	public Vector<Contexte> getListeContexte() {
		Vector<Contexte> res = new Vector<Contexte>();
		res.addAll(gestRes.getContextes());
		return res;
	}

	@Override
	public Vector<Projet> getListeProjet() {
		Vector<Projet> res = new Vector<Projet>();
		res.addAll(gestRes.getProjets());
		return res;
	}

	@Override
	public Vector<Tache> getListeTache(Contexte c) {
		Vector<Tache> res = new Vector<Tache>();
		res.addAll(gestRes.getTaches());
		return res;
	}

	@Override
	public Vector<Projet> getProjectSubProject(Projet p) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		Vector<Projet> res = new Vector<Projet>();
		res.addAll(projetSauv.getSousProjets());
		return res;
	}

	@Override
	public Vector<Tache> getProjectTasks(Projet p) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		Vector<Tache> res = new Vector<Tache>();
		res.addAll(projetSauv.getTaches());
		return res;
	}

	@Override
	public Object[] getProjetContacts(Projet p) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		return projetSauv.getContacts().toArray();
	}

	@Override
	public Object[] getProjetNotes(Projet p) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		return projetSauv.getNotes().toArray();
	}

	@Override
	public Tache getProjetUrgentTask(Projet p) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		return projetSauv.getUrgentTask();
	}

	@Override
	public Projet getTaskProjet(Tache t) {
		Tache tacheSauv = gestRes.getTache(t.getId());
		return tacheSauv.getParent();
	}

	@Override
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

	@Override
	public Boolean setProjetName(Projet p, String nom) {
		Projet projetSauv = gestRes.getProjet(p.getId());
		gestRes.remove(projetSauv);
		projetSauv.setNom(nom);
		gestRes.add(projetSauv);
		return null;
	}
	@Override
	public Integer updateBasket(Idee e, String contenu) {
		String titre = e.getNom();
		gestRes.remove(e);
		gestRes.add(new Idee(titre, contenu));
		return 0;
	}

	

}
