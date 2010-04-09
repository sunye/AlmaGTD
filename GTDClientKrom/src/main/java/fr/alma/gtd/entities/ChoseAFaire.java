package fr.alma.gtd.entities;

import java.util.Collection;
//Start of user code ChoseAFaire.import
import javax.persistence.*;
//End of user code

//Start of user code ChoseAFaire.annotation
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
//End of user code
/**
 * Classe ChoseAFaire.
 */
public abstract class ChoseAFaire extends GtdEntity {

	//Start of user code ChoseAFaire.id.annotation
	@Id
	@GeneratedValue
	//End of user code
	/** Id. */
	protected Long id;

	//Start of user code ChoseAFaire.description.annotation
	@Basic
	//End of user code
	/** Description. */
	protected String description;

	//Start of user code ChoseAFaire.participants.annotation
	@ManyToMany(cascade = CascadeType.ALL)
	//End of user code
	/** Participants. */
	protected Collection<Participant> participants;

	
	/**
	 * Getter de l'attribut description.
	 * @return description 
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Setter de l'attribut description.
	 * @param description 
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * Methode toString.
	 */
	public String toString(){
		//Start of user code ChoseAFaire.toString
		return this.description;
		//End of user code
	}
	
}