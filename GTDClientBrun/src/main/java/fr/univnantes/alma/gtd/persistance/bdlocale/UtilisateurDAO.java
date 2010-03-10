package fr.univnantes.alma.gtd.persistance.bdlocale;

import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;
import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;

public interface UtilisateurDAO {
public Integer create(final GTDEntity entity);
	
	public Utilisateur find(final Integer id);
	
	public Boolean update(final GTDEntity entity);
	
	public Boolean delete(final GTDEntity entity);
	
	public Boolean deleteAll();
	
	public List<Utilisateur> findAll();
}
