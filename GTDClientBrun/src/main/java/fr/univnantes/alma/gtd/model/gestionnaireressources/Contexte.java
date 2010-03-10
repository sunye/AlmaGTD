
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireressources;


// Start of user code for imports in Contexte

// Start of user code for comments in Contexte
/**
 * 
 */
// End of user code

// Start of user code for comments in Contexte
/**
 * 
 */
// End of user code

public class Contexte extends GTDEntity { 

	private String description;
	private String titre;
	
	
	
	// Start of user code for attributes in Contexte

	// End of user code
	
	// Start of user code for constructors in Contexte
	public Contexte() {
		super();
	}
	
	public Contexte(final String titre) {
		super();
		this.titre = titre;
	}
	
	@Override
	public String toString() {
		return this.titre;
	}
	// End of user code
	
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
	public String getTitre() {
		// Start of user code for getter of titre
		return this.titre;
		// End of user code
	}
	
	public void setTitre(String titre) {
		// Start of user code for setter of titre
		this.titre = titre;
		// End of user code
	}
	
	
	
	
}
