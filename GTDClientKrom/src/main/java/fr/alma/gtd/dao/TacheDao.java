package fr.alma.gtd.dao;

import java.util.Collection;
import java.util.List;

import fr.alma.gtd.entities.Tache;

public interface TacheDao extends GtdEntityDao<Tache> {

	public Collection<Tache> findATraiterWithContexts(List<String> noms);

}
