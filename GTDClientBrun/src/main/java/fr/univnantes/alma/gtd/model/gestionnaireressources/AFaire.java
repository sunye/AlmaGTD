
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireressources;

import java.util.ArrayList;
import java.util.List;

// Start of user code for imports in AFaire

// Start of user code for comments in AFaire
/**
 * 
 */
// End of user code

// Start of user code for comments in AFaire
/**
 * 
 */
// End of user code

public class AFaire extends EtatTache { 

	
	
	// Start of user code for attributes in AFaire

	// End of user code
	
	// Start of user code for constructors in AFaire
	public AFaire() {
		super();
	}
	
	public String toString() {
		return "A faire";
	}
	// End of user code
	
	
	public void deleguee() {
		// Start of user code for deleguee method body
		this.setEtatTache(DELEGUEE);
		// End of user code
	}
	public void enAttente() {
		// Start of user code for enAttente method body
		this.setEtatTache(ENATTENTE);
		// End of user code
	}
	public void finie() {
		// Start of user code for finie method body
		this.setEtatTache(FINIE);
		// End of user code
	}

	@Override
	public List<EtatTache> etatsProchains() {
		List<EtatTache> res = new ArrayList<EtatTache>();
		res.add(DELEGUEE);
		res.add(ENATTENTE);
		res.add(FINIE);
		res.add(AFAIRE);
		return res;
	}

	
}
