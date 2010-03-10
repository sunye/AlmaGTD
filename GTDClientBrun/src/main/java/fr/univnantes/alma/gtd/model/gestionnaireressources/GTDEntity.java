
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireressources;

import fr.univnantes.alma.gtd.persistance.IPersistance;
import fr.univnantes.alma.gtd.persistance.bdlocale.PersistanceLocale;

// Start of user code for imports in GTDEntity

// Start of user code for comments in GTDEntity
/**
 * 
 */
// End of user code

// Start of user code for comments in GTDEntity
/**
 * 
 */
// End of user code

public abstract class GTDEntity { 

	private Integer id;
	public final static IPersistance PERSISTANCE = new PersistanceLocale();
	
	
	// Start of user code for attributes in GTDEntity
	
	// End of user code
	
	// Start of user code for constructors in GTDEntity
	public GTDEntity() {
		super();
	}
	
	/* // contructeur généré
	public GTDEntity(final Integer id) {
		super();
		this.setId(id);
	}
	*/
	// End of user code
	
	public Integer getId() {
		// Start of user code for getter of id
		return this.id;
		// End of user code
	}
	
	public void setId(Integer id) {
		// Start of user code for setter of id
		this.id = id;
		// End of user code
	}
	
	
	
}
