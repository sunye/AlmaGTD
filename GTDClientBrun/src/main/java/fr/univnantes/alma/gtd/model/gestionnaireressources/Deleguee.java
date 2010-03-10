
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireressources;

import java.util.ArrayList;
import java.util.List;

// Start of user code for imports in Deleguee

// Start of user code for comments in Deleguee
/**
 * 
 */
// End of user code

// Start of user code for comments in Deleguee
/**
 * 
 */
// End of user code

public class Deleguee extends EtatTache { 

	
	
	// Start of user code for attributes in Deleguee
	
	// End of user code
	
	// Start of user code for constructors in Deleguee
	public Deleguee() {
		super();
	}
	
	public String toString() {
		return "Deleguee";
	}
	// End of user code
	
	
	
	public void finie() {
		// Start of user code for finie method body
		this.setEtatTache(FINIE);
		// End of user code
	}
	
	@Override
	public List<EtatTache> etatsProchains() {
		List<EtatTache> res = new ArrayList<EtatTache>();
		res.add(FINIE);
		res.add(DELEGUEE);
		return res;
	}
	
}
