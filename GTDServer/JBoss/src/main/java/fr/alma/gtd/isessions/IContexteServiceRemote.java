package fr.alma.gtd.isessions;

import java.util.List;

import fr.alma.gtd.donneespartagees.IContexte;

public interface IContexteServiceRemote {

	IContexte creerContexte(IContexte c);

	List<IContexte> getAllContexte();

	IContexte getContexteById(String identifiantServeur);

	List<IContexte> getContexteByName(String nom);

	List<IContexte> getContexteByCreateur(String idCreateur);

	IContexte updateContexte(String identifiantServeur, IContexte Contexte);

	void removeAll();

	void removeContexteById(String identifiantServeur) throws Exception;

}
