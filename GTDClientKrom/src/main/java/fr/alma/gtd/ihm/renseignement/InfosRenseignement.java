
package fr.alma.gtd.ihm.renseignement;

import fr.alma.gtd.entities.Contact;
import fr.alma.gtd.entities.Contexte;
import fr.alma.gtd.entities.Projet;
import java.util.List;

public class InfosRenseignement {

	private String nom;
	private String description;
	private Contexte contexte;
	private Projet projetParent;
	private InfosEcheancier echeancier;
	private List<Contact> contacts;
	private Boolean estTache;

	private Boolean estProjetOrdonne;

	private Integer priorite;
	private Integer tauxEffort;

	private InfosRenseignement() {
	}

	public InfosRenseignement(String nom, String description, Contexte contexte, Projet projetParent, InfosEcheancier echeancier, List<Contact> contacts, Boolean estTache, Boolean estProjetOrdonne) {
		this.nom = nom;
		this.description = description;
		this.contexte = contexte;
		this.projetParent = projetParent;
		this.echeancier = echeancier;
		this.contacts = contacts;
		this.estTache = estTache;
		this.estProjetOrdonne = estProjetOrdonne;
	}

	public InfosRenseignement(String nom, String description, Contexte contexte, Projet projetParent, InfosEcheancier echeancier, List<Contact> contacts, Boolean estTache, Integer priorite, Integer tauxEffort) {
		this.nom = nom;
		this.description = description;
		this.contexte = contexte;
		this.projetParent = projetParent;
		this.echeancier = echeancier;
		this.contacts = contacts;
		this.estTache = estTache;
		this.priorite = priorite;
		this.tauxEffort = tauxEffort;
	}

	public Contexte getContexte() {
		return contexte;
	}

	public String getDescription() {
		return description;
	}

	public InfosEcheancier getEcheancier() {
		return echeancier;
	}

	public Boolean getEstProjetOrdonne() {
		return estProjetOrdonne;
	}

	public Boolean getEstTache() {
		return estTache;
	}

	public String getNom() {
		return nom;
	}

	public Integer getPriorite() {
		return priorite;
	}

	public Projet getProjetParent() {
		return projetParent;
	}

	public Integer getTauxEffort() {
		return tauxEffort;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

}
