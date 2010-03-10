package fr.univnantes.alma.gtd.persistance.bdlocale;

import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireressources.AFaire;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Deleguee;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EnAttente;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EtatTache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Finie;
import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;

public interface EtatTacheDAO {
public Integer create(final GTDEntity entity);
	
	public AFaire findAFaire(final Integer id);
	
	public Deleguee findDeleguee(final Integer id);
	
	public EnAttente findEnAttente(final Integer id);
	
	public Finie findFinie(final Integer id);
	
	public Boolean update(final GTDEntity entity);
	
	public Boolean delete(final GTDEntity entity);
	
	public Boolean deleteAll();
	
	public List<EtatTache> findAll();
}
