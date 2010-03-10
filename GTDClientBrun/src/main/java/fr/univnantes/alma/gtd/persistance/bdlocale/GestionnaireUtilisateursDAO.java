package fr.univnantes.alma.gtd.persistance.bdlocale;

import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;
import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.GestionnaireUtilisateurs;

public interface GestionnaireUtilisateursDAO {
public GestionnaireUtilisateurs find(final Integer id);
public Integer create(final GTDEntity entity);
	public Boolean update(final GTDEntity entity);
	
	public Boolean delete(final GTDEntity entity);
	
	public Boolean deleteAll();
	
	public List<GestionnaireUtilisateurs> findAll();
}
