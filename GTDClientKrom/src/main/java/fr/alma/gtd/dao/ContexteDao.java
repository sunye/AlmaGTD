package fr.alma.gtd.dao;

import fr.alma.gtd.entities.Contexte;

public interface ContexteDao extends GtdEntityDao<Contexte> {

	public Contexte findContexteNul();

	public boolean exist(String nom);

}
