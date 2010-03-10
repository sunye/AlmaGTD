
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireressources;


// Start of user code for imports in Idee

// Start of user code for comments in Idee
/**
 * 
 */
// End of user code

// Start of user code for comments in Idee
/**
 * 
 */
// End of user code

public class Idee extends GTDEntity { 

	private String nom;
	private String description;
	
	
	// Start of user code for attributes in Idee

	// End of user code
	
	// Start of user code for constructors in Idee
	public Idee() {
		super();
	}
	
	public Idee(final String nom, final String description) {
		super();
		this.setNom(nom);
		this.setDescription(description);
	}
	
	@Override
	public String toString(){
		return this.nom;
		
	}
	// End of user code
	
	public String getNom() {
		// Start of user code for getter of nom
		return this.nom;
		// End of user code
	}
	
	public void setNom(String nom) {
		// Start of user code for setter of nom
		this.nom = nom;
		// End of user code
	}
	public String getDescription() {
		// Start of user code for getter of description
		return this.description;
		// End of user code
	}
	
	public void setDescription(String description) {
		// Start of user code for setter of description
		this.description = description;
		// End of user code
	}
	
	
	
	
	
}
