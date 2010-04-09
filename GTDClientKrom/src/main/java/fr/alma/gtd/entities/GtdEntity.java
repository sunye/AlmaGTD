package fr.alma.gtd.entities;

//Start of user code GtdEntity.import
import java.io.Serializable;
import java.util.Date;
//End of user code

//Start of user code GtdEntity.annotation

//End of user code
/**
 * Classe GtdEntity.
 */
public abstract class GtdEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Start of user code GtdEntity.dateModification.annotation

	//End of user code
	/** DateModification. */
	protected Date dateModification;

	//Start of user code GtdEntity.identifiantServeur.annotation
	
	//End of user code
	/** IdentifiantServeur. */
	protected String identifiantServeur;

	/**
	 * Getter de l'attribut dateModification.
	 * @return dateModification 
	 */
	public Date getDateModification() {
		return this.dateModification;
	}
	
	/**
	 * Setter de l'attribut dateModification.
	 * @param dateModification 
	 */
	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}
	
	/**
	 * Getter de l'attribut identifiantServeur.
	 * @return identifiantServeur 
	 */
	public String getIdentifiantServeur() {
		return this.identifiantServeur;
	}
	
	/**
	 * Setter de l'attribut identifiantServeur.
	 * @param identifiantServeur 
	 */
	public void setIdentifiantServeur(String identifiantServeur) {
		this.identifiantServeur = identifiantServeur;
	}
	
	/**
	 * Methode getId.
	 */
	public abstract Long getId();
	
}