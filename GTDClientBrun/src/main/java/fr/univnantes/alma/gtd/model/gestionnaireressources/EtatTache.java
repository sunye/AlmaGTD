
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireressources;

import java.util.List;

// Start of user code for imports in EtatTache

// Start of user code for comments in EtatTache
/**
 * 
 */
// End of user code

// Start of user code for comments in EtatTache
/**
 * 
 */
// End of user code

public abstract class EtatTache extends GTDEntity{ 

	
		private EtatTache etatTache;

	
	// Start of user code for attributes in EtatTache
		public static EtatTache AFAIRE;
		public static EtatTache DELEGUEE;
		public static EtatTache ENATTENTE;
		public static EtatTache FINIE;

	// End of user code
	
	// Start of user code for constructors in EtatTache
	public EtatTache() {
		super();
	}
	// End of user code
	
	
	public EtatTache getEtatTache() {
		return this.etatTache;
	}

	public void setEtatTache(EtatTache etatTache) {
		this.etatTache = etatTache;
	}

	
	public void aFaire() {
		// Start of user code for aFaire method body
		
		// End of user code
	}
	public void deleguee() {
		// Start of user code for deleguee method body
		
		// End of user code
	}
	public void enAttente() {
		// Start of user code for enAttente method body
		
		// End of user code
	}
	public void finie() {
		// Start of user code for finie method body
		
		// End of user code
	}
	
	public abstract List<EtatTache> etatsProchains();
	
}
