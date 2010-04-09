package fr.alma.gtd.dao;

import fr.alma.gtd.entities.ChoseAFaire;
import java.util.Collection;

public interface ChoseAFaireDao extends GtdEntityDao<ChoseAFaire> {

	public Collection<ChoseAFaire> findAFaireUnJour();

	public Collection<ChoseAFaire> findATraiter();

}
