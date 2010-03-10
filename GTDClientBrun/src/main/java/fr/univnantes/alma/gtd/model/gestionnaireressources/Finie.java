
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireressources;

import java.util.ArrayList;
import java.util.List;

// Start of user code for imports in Finie

// Start of user code for comments in Finie
/**
 * 
 */
// End of user code

// Start of user code for comments in Finie
/**
 * 
 */
// End of user code

public class Finie extends EtatTache { 

	
	
	// Start of user code for attributes in Finie

	// End of user code
	
	// Start of user code for constructors in Finie
	public Finie() {
		super();
	}
	
	public String toString() {
		return "Finie";
	}
	// End of user code
	
	@Override
	public List<EtatTache> etatsProchains() {
		List<EtatTache> res = new ArrayList<EtatTache>();
		res.add(FINIE);
		return res;
	}
	
	
}
