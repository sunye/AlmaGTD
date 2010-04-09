package fr.alma.gtd.entities;

//Start of user code ATraiter.import
import java.util.LinkedList;

import javax.persistence.*;
//End of user code

//Start of user code ATraiter.annotation
@Entity
@DiscriminatorValue("A_TRAITER")
//End of user code
/**
 * Classe ATraiter.
 */
public class ATraiter extends ChoseAFaire {

	/** 
	 * Constructeur par defaut.
	 */
	public ATraiter() {}
	
	
	/**
	 * Constructeur.
	 * @param description valeur de description dans le ChoseAFaire construit
	 */
	public ATraiter(String description) {
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