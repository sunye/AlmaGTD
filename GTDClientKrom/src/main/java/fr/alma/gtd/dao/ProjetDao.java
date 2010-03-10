package fr.alma.gtd.dao;

import fr.alma.gtd.entities.Projet;

public interface ProjetDao extends GtdEntityDao<Projet> {

	public Projet findProjetRacine();

}
