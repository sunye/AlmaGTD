
/**
 * généré via interface.mt
 */

package fr.univnantes.alma.gtd.persistance.bdlocale;

// Start of user code for imports in IdeeDAO
import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Idee;

public interface IdeeDAO{
	public Integer create(final GTDEntity entity);
	
	public Idee find(final Integer id);
	
	public Boolean update(final GTDEntity entity);
	
	public Boolean delete(final GTDEntity entity);
	
	public Boolean deleteAll();
	
	public List<Idee> findAll();
	
}