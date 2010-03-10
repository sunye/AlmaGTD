
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireressources;

import java.util.ArrayList;
import java.util.List;

// Start of user code for imports in EnAttente

// Start of user code for comments in EnAttente
/**
 * 
 */
// End of user code

// Start of user code for comments in EnAttente
/**
 * 
 */
// End of user code

public class EnAttente extends EtatTache { 

	
	
	// Start of user code for attributes in EnAttente

	// End of user code
	
	// Start of user code for constructors in EnAttente
	public EnAttente() {
		super();
	}
	
	public String toString() {
		return "En attente";
	}
	// End of user code
	
	
	
	public void aFaire() {
		// Start of user code for aFaire method body
		this.setEtatTache(AFAIRE);
		// End of user code
	}
	public void deleguee() {
		// Start of user code for deleguee method body
		this.setEtatTache(DELEGUEE);
		// End of user code
	}
	@Override
	public List<EtatTache> etatsProchains() {
		List<EtatTache> res = new ArrayList<EtatTache>();
		res.add(AFAIRE);
		res.add(DELEGUEE);
		res.add(ENATTENTE);
		return res;
	}
	
}
