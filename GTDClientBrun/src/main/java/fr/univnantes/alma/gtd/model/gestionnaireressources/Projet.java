
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireressources;

// Start of user code for imports in Projet
import java.util.ArrayList;
import java.util.List;

import fr.univnantes.alma.gtd.persistance.IPersistance;

// Start of user code for comments in Projet
/**
 * 
 */
// End of user code

// Start of user code for comments in Projet
/**
 * 
 */
// End of user code

public class Projet extends GTDEntity { 

	private List<Tache> taches = new ArrayList<Tache>();
	private List<Projet> sousProjets = new ArrayList<Projet>();
	private Contexte contexte;
	private String nom;
	
	// Start of user code for attributes in Projet
	private Projet parent;
	private List<Contact> contacts = new ArrayList<Contact>();
	private List<String> notes = new ArrayList<String>();
	private IPersistance persistance = GTDEntity.PERSISTANCE;
	// End of user code

	// Start of user code for constructors in Projet
	public Projet() {
		super();	
	}
	
	public Projet(String nom) {
		super();
		this.nom = nom;
	}
	
	public Projet(String nom, Contexte c) {
		super();
		this.nom = nom;
		this.contexte = c;
	}
	
	public Projet(String nom, Projet p, Contexte c) {
		super();
		this.nom = nom;
		this.contexte = c;
		this.parent = p;
	}
	
	public Projet(String nom, Contexte c, Projet p) {
		super();
		this.nom = nom;
		this.contexte = c;
		this.parent = p;
	}

	public void addTache(Tache t){
		this.taches.add(t);
		persistance.update(this);
	}
	public void remove(Tache t){
		taches.remove(t);
		persistance.delete(t);
		persistance.update(this);
	}

	public String toString() {
		return nom;
	}

	public void addSousProjet(Projet p){
		sousProjets.add(p);
		persistance.update(this);
	}

	public void addNote(String text) {
		notes.add(text);	
		persistance.update(this);
	}

	public void removeNote(String note) {
		notes.remove(note);
		persistance.update(this);
	}

	public void addContact(Contact contact) {
		contacts.add(contact);	
		persistance.update(this);
	}

	public void remove(Contact c) {
		contacts.remove(c);	
		persistance.update(this);
	}

	public Tache getUrgentTask() {
		if (taches.size()>0){
			int tacheref = 0;
			for (int i =0; i<taches.size();i++){
				if (((Date) taches.get(i).getEcheance()).getAnnee().compareTo(((Date) taches.get(tacheref).getEcheance()).getAnnee())<0){
					tacheref = i;
				}else if((((Date) taches.get(i).getEcheance()).getAnnee().compareTo(((Date) taches.get(tacheref).getEcheance()).getAnnee())==0)
						&&(((Date) taches.get(i).getEcheance()).getMois().compareTo(((Date) taches.get(tacheref).getEcheance()).getMois())<0)){
					tacheref = i;
				}else if ((((Date) taches.get(i).getEcheance()).getJour().compareTo(((Date) taches.get(tacheref).getEcheance()).getJour())<0)
						&&(((Date) taches.get(i).getEcheance()).getAnnee().compareTo(((Date) taches.get(tacheref).getEcheance()).getAnnee())==0)
						&&(((Date) taches.get(i).getEcheance()).getAnnee().compareTo(((Date) taches.get(tacheref).getEcheance()).getAnnee())==0)){
					tacheref = i;
				}


			}
			return taches.get(tacheref);

		}

		return null;

	}
	
	public Projet getParent() {
		return parent;
	}

	public void setParent(Projet parent) {
		this.parent = parent;
		//persistance.update(this);
	}

	public void remove(Projet p) {
		sousProjets.remove(p);	
		persistance.delete(p);
		persistance.update(this);
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public List<String> getNotes() {
		return notes;
	}
	// End of user code
	
	public List<Tache> getTaches() {
		// Start of user code for getter of taches
		return this.taches;
		// End of user code
	}
	public List<Projet> getSousProjets() {
		// Start of user code for getter of sousProjets
		return this.sousProjets;
		// End of user code
	}
	public Contexte getContexte() {
		// Start of user code for getter of contexte
		return this.contexte;
		// End of user code
	}
	
	public void setContexte(Contexte contexte) {
		// Start of user code for setter of contexte
		this.contexte = contexte;
		// End of user code
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}
