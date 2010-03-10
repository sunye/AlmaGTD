
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireressources;


// Start of user code for imports in Contact

// Start of user code for comments in Contact
/**
 * 
 */
// End of user code

// Start of user code for comments in Contact
/**
 * 
 */
// End of user code

public class Contact extends GTDEntity { 

	private String nom;
	private String prenom;
	private String coordonnees;
	
	
	
	
	// Start of user code for attributes in Contact

	// End of user code
	
	// Start of user code for constructors in Contact
	public Contact() {
		super();
	}
	
	public Contact(final String nom) {
		super();
		this.setNom(nom);
	}

	public String toString() {
		return nom;
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
	public String getPrenom() {
		// Start of user code for getter of prenom
		return this.prenom;
		// End of user code
	}
	
	public void setPrenom(String prenom) {
		// Start of user code for setter of prenom
		this.prenom = prenom;
		// End of user code
	}
	public String getCoordonnees() {
		// Start of user code for getter of coordonnees
		return this.coordonnees;
		// End of user code
	}
	
	public void setCoordonnees(String coordonnees) {
		// Start of user code for setter of coordonnees
		this.coordonnees = coordonnees;
		// End of user code
	}
	
	
	
	
	
}
