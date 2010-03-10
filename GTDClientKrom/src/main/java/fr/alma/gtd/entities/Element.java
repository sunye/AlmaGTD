package fr.alma.gtd.entities;

import java.util.Collection;
//Start of user code Element.import
import javax.persistence.*;

import fr.alma.gtd.util.Visitor;
//End of user code

//Start of user code Element.annotation
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//End of user code
/**
 * Classe Element.
 */
public abstract class Element extends GtdEntity {

	private static final long serialVersionUID = -849915506634923544L;

	//Start of user code Element.id.annotation
	@Id
	@GeneratedValue
	//End of user code
	/** Id. */
	protected Long id;

	//Start of user code Element.nom.annotation
	@Basic
	//End of user code
	/** Nom. */
	protected String nom;

	//Start of user code Element.notes.annotation
	@Basic
	//End of user code
	/** Notes. */
	protected String notes;

	//Start of user code Element.prioriteDynamique.annotation
	@Basic
	//End of user code
	/** PrioriteDynamique. */
	protected Integer prioriteDynamique;

	//Start of user code Element.echeancier.annotation
	@OneToOne(cascade = CascadeType.ALL)
	//End of user code
	/** Echeancier. */
	protected Echeancier echeancier;

	//Start of user code Element.contacts.annotation
	@ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
	//End of user code
	/** Contacts. */
	protected Collection<Contact> contacts;

	//Start of user code Element.projetConteneur.annotation
	@ManyToOne
	//End of user code
	/** ProjetConteneur. */
	protected Projet projetConteneur;

	//Start of user code Element.participants.annotation
	@ManyToMany(cascade = CascadeType.ALL)
	//End of user code
	/** Participants. */
	protected Collection<Participant> participants;

	//Start of user code Element.contexte.annotation
	@ManyToOne
	//End of user code
	/** Contexte. */
	protected Contexte contexte;

	
	/**
	 * Getter de l'attribut nom.
	 * @return nom 
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Setter de l'attribut nom.
	 * @param nom 
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Getter de l'attribut notes.
	 * @return notes 
	 */
	public String getNotes() {
		return this.notes;
	}
	
	/**
	 * Setter de l'attribut notes.
	 * @param notes 
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	/**
	 * Getter de l'attribut prioriteDynamique.
	 * @return prioriteDynamique 
	 */
	public Integer getPrioriteDynamique() {
		return this.prioriteDynamique;
	}
	
	/**
	 * Setter de l'attribut prioriteDynamique.
	 * @param prioriteDynamique 
	 */
	public void setPrioriteDynamique(Integer prioriteDynamique) {
		this.prioriteDynamique = prioriteDynamique;
	}
	
	/**
	 * Getter de l'attribut echeancier.
	 * @return echeancier 
	 */
	public Echeancier getEcheancier() {
		return this.echeancier;
	}
	
	/**
	 * Setter de l'attribut echeancier.
	 * @param echeancier 
	 */
	public void setEcheancier(Echeancier echeancier) {
		this.echeancier = echeancier;
	}
	
	/**
	 * Getter de l'attribut contacts.
	 * @return contacts 
	 */
	public Collection<Contact> getContacts() {
		return this.contacts;
	}
	
	/**
	 * Setter de l'attribut contacts.
	 * @param contacts 
	 */
	public void setContacts(Collection<Contact> contacts) {
		this.contacts = contacts;
	}
	
	/**
	 * Getter de l'attribut projetConteneur.
	 * @return projetConteneur 
	 */
	public Projet getProjetConteneur() {
		return this.projetConteneur;
	}
	
	/**
	 * Setter de l'attribut projetConteneur.
	 * @param projetConteneur 
	 */
	public void setProjetConteneur(Projet projetConteneur) {
		this.projetConteneur = projetConteneur;
	}
	
	/**
	 * Getter de l'attribut participants.
	 * @return participants 
	 */
	public Collection<Participant> getParticipants() {
		return this.participants;
	}
	
	/**
	 * Setter de l'attribut participants.
	 * @param participants 
	 */
	public void setParticipants(Collection<Participant> participants) {
		this.participants = participants;
	}
	
	/**
	 * Getter de l'attribut contexte.
	 * @return contexte 
	 */
	public Contexte getContexte() {
		return this.contexte;
	}
	
	/**
	 * Setter de l'attribut contexte.
	 * @param contexte 
	 */
	public void setContexte(Contexte contexte) {
		this.contexte = contexte;
	}
	
	/**
	 * Methode permettant d'ajouter des contacts.
	 */
	public void addContacts(Contact contact){
		this.contacts.add(contact);
	}
	
	/**
	 * Methode permettant de supprimer des contacts.
	 */
	public void removeContacts(Contact contact){
		this.contacts.remove(contact);
	}	
	
	/**
	 * Methode permettant d'ajouter des participants.
	 */
	public void addParticipants(Participant participant){
		this.participants.add(participant);
	}
	
	/**
	 * Methode permettant de supprimer des participants.
	 */
	public void removeParticipants(Participant participant){
		this.participants.remove(participant);
	}	
	
	/**
	 * Methode accept.
	 */
	public abstract void accept(Visitor v);
	
	/**
	 * Methode estTache.
	 */
	public abstract Boolean estTache();

	/**
	 * Methode toString.
	 */
	public String toString(){
		//Start of user code Element.toString
		return this.nom;
		//End of user code
	}
	
}