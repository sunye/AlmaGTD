
/**
 * généré via interface.mt
 */

package fr.univnantes.alma.gtd.persistance.bdlocale;

// Start of user code for imports in ContactDAO
import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Contact;
import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;

public interface ContactDAO{
	public Integer create(final GTDEntity entity);
	
	public Contact find(final Integer id);
	
	public Boolean update(final GTDEntity entity);
	
	public Boolean delete(final GTDEntity entity);
	
	public Boolean deleteAll();
	
	public List<Contact> findAll();
	
}