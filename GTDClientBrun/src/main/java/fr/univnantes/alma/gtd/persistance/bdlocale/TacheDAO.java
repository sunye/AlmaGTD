
/**
 * généré via interface.mt
 */

package fr.univnantes.alma.gtd.persistance.bdlocale;

// Start of user code for imports in TacheDAO
import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Tache;

public interface TacheDAO{
	public Integer create(final GTDEntity entity);
	
	public Tache find(final Integer id);
	
	public Boolean update(final GTDEntity entity);
	
	public Boolean delete(final GTDEntity entity);
	
	public Boolean deleteAll();
	
	public List<Tache> findAll();
	
}