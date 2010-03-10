package fr.alma.gtd.isessions;

import java.util.Date;
import java.util.List;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.ITache;

public interface ITacheServiceRemote {

	ITache creerTache(ITache i);

	List<ITache> getAllTache();

	ITache getTacheById(String identifiantServeur);

	List<ITache> getTacheByName(String nom);

	List<ITache> getTacheByCreator(String idCreateur);

	List<ITache> getTacheByCreator(String idCreateur, Date dateModif);
	
	List<ITache> getTacheByCtx(IContexte c);
	
	List<ITache> getTacheByCtx(IContexte c, Date dateModif);
	
	List<ITache> getTacheByCtxAndCreator(IContexte ctx, IParticipant createur);
	
	List<ITache> getTacheByCtxAndCreator(IContexte ctx, IParticipant createur, Date dateModif);

	ITache updateTache(String identifiantServeur, ITache Tache);

	void removeAll();

	void removeTacheById(String identifiantServeur);





}
