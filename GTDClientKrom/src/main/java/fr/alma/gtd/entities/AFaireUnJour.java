package fr.alma.gtd.entities;

//Start of user code AFaireUnJour.import
import java.util.LinkedList;

import javax.persistence.*;
//End of user code

//Start of user code AFaireUnJour.annotation
@Entity
@DiscriminatorValue("A_FAIRE")
//End of user code
/**
 * Classe AFaireUnJour.
 */
public class AFaireUnJour extends ChoseAFaire {

	/** 
	 * Constructeur par defaut.
	 */
	public AFaireUnJour() {}
	
	
	/**
	 * Constructeur.
	 * @param description valeur de description dans le ChoseAFaire construit
	 */
	public AFaireUnJour(String description) {
		this.participants = new LinkedList<Participant>();
		this.description = description;
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