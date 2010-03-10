
/**
 * généré via interface.mt
 */

package fr.univnantes.alma.gtd.persistance.bdlocale;

// Start of user code for imports in ContextDAO
import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Contexte;
import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;

public interface ContextDAO{
	public Integer create(final GTDEntity entity);
	
	public Contexte find(final Integer id);
	
	public Boolean update(final GTDEntity entity);
	
	public Boolean delete(final GTDEntity entity);
	
	public Boolean deleteAll();
	
	public List<Contexte> findAll();
	
}