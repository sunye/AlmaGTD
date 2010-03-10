package fr.alma.gtd.isessions;

import java.util.Date;
import java.util.List;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IProjet;

public interface IProjetServiceRemote {

	IProjet creerProjet(IProjet i);

	List<IProjet> getAllProjet();

	IProjet getProjetById(String identifiantServeur);

	List<IProjet> getProjetByName(String nom);

	List<IProjet> getProjetByCreator(String idCreateur);
	
	List<IProjet> getProjetByCreator(String idCreateur, Date date);

	IProjet updateProjet(String identifiantServeur, IProjet Projet);

	void removeAll();

	void removeProjetById(String identifiantServeur);

	List<IProjet> getProjetByCtx(IContexte c);

}
