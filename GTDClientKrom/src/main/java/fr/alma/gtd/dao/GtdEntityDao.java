package fr.alma.gtd.dao;

import java.util.List;

import fr.alma.gtd.entities.GtdEntity;

public interface GtdEntityDao<AnyEntity extends GtdEntity> {
	
	public Long create(AnyEntity entity);
	
	public void delete(Long id);
	
	public void deleteAll();
	
	public AnyEntity find(Long id);
	
	public List<AnyEntity> findAll();
	
	public void update(AnyEntity entity);

}
