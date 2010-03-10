package fr.univnantes.alma.gtd.persistance.bdlocale;

import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireressources.Date;
import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;

public interface DateDAO {
public Integer create(final GTDEntity entity);
	
	public Date find(final Integer id);
	
	public Boolean update(final GTDEntity entity);
	
	public Boolean delete(final GTDEntity entity);
	
	public Boolean deleteAll();
	
	public List<Date> findAll();
}
