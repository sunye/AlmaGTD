package fr.alma.gtd.entities;

import java.util.Collection;
import java.util.LinkedList;
//Start of user code Projet.import
import javax.persistence.*;

import fr.alma.gtd.util.Visitor;
//End of user code

//Start of user code Projet.annotation
@Entity
//End of user code
/**
 * Classe Projet.
 */
public class Projet extends Element {

	//Start of user code Projet.elements.annotation
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "projetConteneur")
	//End of user code
	/** Elements. */
	private Collection<Element> elements;

	//Start of user code Projet.ordonne.annotation
	
	//End of user code
	/** Ordonne. */
	private Boolean ordonne;

	/** 
	 * Constructeur par defaut.
	 */
	public Projet() {}
	
	
	/**
	 * Constructeur.
	 * @param ordonne valeur de ordonne dans le Projet construit
	 * @param nom valeur de nom dans le Element construit
	 * @param contexte valeur de contexte dans le Element construit
	 */
	public Projet(Boolean ordonne, String nom, Contexte contexte) {
		this.elements = new LinkedList<Element>();
		this.contacts = new LinkedList<Contact>();
		this.participants = new LinkedList<Participant>();
		this.ordonne = ordonne;
		this.nom = nom;
		this.contexte = contexte;
	}
			
	/**
	 * Getter de l'attribut elements.
	 * @return elements 
	 */
	public Collection<Element> getElements() {
		return this.elements;
	}
	
	/**
	 * Setter de l'attribut elements.
	 * @param elements 
	 */
	public void setElements(Collection<Element> elements) {
		this.elements = elements;
	}
	
	/**
	 * Getter de l'attribut ordonne.
	 * @return ordonne 
	 */
	public Boolean getOrdonne() {
		return this.ordonne;
	}
	
	/**
	 * Setter de l'attribut ordonne.
	 * @param ordonne 
	 */
	public void setOrdonne(Boolean ordonne) {
		this.ordonne = ordonne;
	}
	
	/**
	 * Methode permettant d'ajouter des elements.
	 */
	public void addElements(Element element){
		this.elements.add(element);
	}
	
	/**
	 * Methode permettant de supprimer des elements.
	 */
	public void removeElements(Element element){
		this.elements.remove(element);
	}	
	
	/**
	 * Methode ajoutTache.
	 */
	public void ajoutTache(Tache tache){
		//Start of user code Projet.ajoutTache
		tache.setProjetConteneur(this);
		this.addElements(tache);
		//End of user code
	}
	
	/**
	 * Methode ajoutSousProjet.
	 */
	public void ajoutSousProjet(Projet projet){
		//Start of user code Projet.ajoutSousProjet
		projet.setProjetConteneur(this);
		this.addElements(projet);
		//End of user code
	}
	
	/**
	 * Methode ajoutElementIndex.
	 */
	public void ajoutElementIndex(Element element, Integer index){
		//Start of user code Projet.ajoutElementIndex
		LinkedList<Element> liste = new LinkedList<Element>(this.elements);
		element.setProjetConteneur(this);
		liste.add(index, element);
		this.elements = liste;
		//End of user code
	}
	
	/**
 	 * Methode accept.
 	 */
 	@Override
	public void accept(Visitor v){
 	//Start of user code accept
		v.visitProjet(this);
	//End of user code
 	}
 	
	/**
 	 * Methode estTache.
 	 */
 	@Override
	public Boolean estTache(){
 	//Start of user code estTache
		return false;
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