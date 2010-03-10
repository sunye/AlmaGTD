package fr.alma.gtd.entities;

import java.util.Collection;
import java.util.LinkedList;
//Start of user code Tache.import
import javax.persistence.*;

import org.hibernate.annotations.CollectionOfElements;

import fr.alma.gtd.util.Visitor;
//End of user code

//Start of user code Tache.annotation
@Entity
//End of user code
/**
 * Classe Tache.
 */
public class Tache extends Element {

	private static final long serialVersionUID = 7384200270682265403L;

	//Start of user code Tache.priorite.annotation
	@Basic
	//End of user code
	/** Priorite. */
	private Integer priorite;

	//Start of user code Tache.tauxEffort.annotation
	@Basic
	//End of user code
	/** TauxEffort. */
	private Integer tauxEffort;

	//Start of user code Tache.liens.annotation
	@CollectionOfElements(fetch = FetchType.EAGER)
	//End of user code
	/** Liens. */
	private Collection<String> liens;

	//Start of user code Tache.avancement.annotation
	@Enumerated(EnumType.STRING)
	//End of user code
	/** Avancement. */
	private Avancement avancement;

	//Start of user code Tache.tags.annotation
	@ManyToMany(cascade = CascadeType.ALL)
	//End of user code
	/** Tags. */
	private Collection<Tag> tags;

	/** 
	 * Constructeur par defaut.
	 */
	public Tache() {}
	
	
	/**
	 * Constructeur.
	 * @param priorite valeur de priorite dans le Tache construit
	 * @param tauxEffort valeur de tauxEffort dans le Tache construit
	 * @param avancement valeur de avancement dans le Tache construit
	 * @param nom valeur de nom dans le Element construit
	 * @param contexte valeur de contexte dans le Element construit
	 */
	public Tache(Integer priorite, Integer tauxEffort, Avancement avancement, String nom, Contexte contexte) {
		this.liens = new LinkedList<String>();
		this.tags = new LinkedList<Tag>();
		this.contacts = new LinkedList<Contact>();
		this.participants = new LinkedList<Participant>();
		this.priorite = priorite;
		this.tauxEffort = tauxEffort;
		this.avancement = avancement;
		this.nom = nom;
		this.contexte = contexte;
	}
			
	/**
	 * Getter de l'attribut priorite.
	 * @return priorite 
	 */
	public Integer getPriorite() {
		return this.priorite;
	}
	
	/**
	 * Setter de l'attribut priorite.
	 * @param priorite 
	 */
	public void setPriorite(Integer priorite) {
		this.priorite = priorite;
	}
	
	/**
	 * Getter de l'attribut tauxEffort.
	 * @return tauxEffort 
	 */
	public Integer getTauxEffort() {
		return this.tauxEffort;
	}
	
	/**
	 * Setter de l'attribut tauxEffort.
	 * @param tauxEffort 
	 */
	public void setTauxEffort(Integer tauxEffort) {
		this.tauxEffort = tauxEffort;
	}
	
	/**
	 * Getter de l'attribut liens.
	 * @return liens 
	 */
	public Collection<String> getLiens() {
		return this.liens;
	}
	
	/**
	 * Setter de l'attribut liens.
	 * @param liens 
	 */
	public void setLiens(Collection<String> liens) {
		this.liens = liens;
	}
	
	/**
	 * Getter de l'attribut avancement.
	 * @return avancement 
	 */
	public Avancement getAvancement() {
		return this.avancement;
	}
	
	/**
	 * Setter de l'attribut avancement.
	 * @param avancement 
	 */
	public void setAvancement(Avancement avancement) {
		this.avancement = avancement;
	}
	
	/**
	 * Getter de l'attribut contexte.
	 * @return contexte 
	 */
	@Override
	public Contexte getContexte() {
		return this.contexte;
	}
	
	/**
	 * Setter de l'attribut contexte.
	 * @param contexte 
	 */
	@Override
	public void setContexte(Contexte contexte) {
		this.contexte = contexte;
	}
	
	/**
	 * Getter de l'attribut tags.
	 * @return tags 
	 */
	public Collection<Tag> getTags() {
		return this.tags;
	}
	
	/**
	 * Setter de l'attribut tags.
	 * @param tags 
	 */
	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}
	
	/**
	 * Methode permettant d'ajouter des liens.
	 */
	public void addLiens(String string){
		this.liens.add(string);
	}
	
	/**
	 * Methode permettant de supprimer des liens.
	 */
	public void removeLiens(String string){
		this.liens.remove(string);
	}	
	
	/**
	 * Methode permettant d'ajouter des tags.
	 */
	public void addTags(Tag tag){
		this.tags.add(tag);
	}
	
	/**
	 * Methode permettant de supprimer des tags.
	 */
	public void removeTags(Tag tag){
		this.tags.remove(tag);
	}	
	
	/**
 	 * Methode accept.
 	 */
 	@Override
	public void accept(Visitor v){
 	//Start of user code accept
		v.visitTache(this);
	//End of user code
 	}
 	
	/**
 	 * Methode estTache.
 	 */
 	@Override
	public Boolean estTache(){
 	//Start of user code estTache
		return true;
	//End of user code
 	}
 	
	/**
 	 * Methode getId.
 	 */
 	@Override
	public Long getId(){
 	//Start of user code getId
		return this.id;
	//End of user code
 	}
 	
}