package fr.univnantes.alma.gtd.persistance.bdlocale;

import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;
import fr.univnantes.alma.gtd.model.gestionnaireressources.GestionnaireRessources;

public interface GestionnaireRessourcesDAO {
public Integer create(final GTDEntity entity);
	
	public GestionnaireRessources find(final Integer id);
	
	public Boolean update(final GTDEntity entity);
	
	public Boolean delete(final GTDEntity entity);
	
	public Boolean deleteAll();
	
	public List<GestionnaireRessources> findAll();
}
