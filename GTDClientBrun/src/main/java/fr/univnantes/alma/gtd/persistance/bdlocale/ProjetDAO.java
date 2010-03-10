
/**
 * généré via interface.mt
 */

package fr.univnantes.alma.gtd.persistance.bdlocale;

// Start of user code for imports in ProjetDAO
import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Projet;

public interface ProjetDAO{
	public Integer create(final GTDEntity entity);
	
	public Projet find(final Integer id);
	
	public Boolean update(final GTDEntity entity);
	
	public Boolean delete(final GTDEntity entity);
	
	public Boolean deleteAll();
	
	public List<Projet> findAll();
	
}