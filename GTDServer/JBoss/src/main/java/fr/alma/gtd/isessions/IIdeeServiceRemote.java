package fr.alma.gtd.isessions;

import java.util.Date;
import java.util.List;

import fr.alma.gtd.donneespartagees.IIdee;

public interface IIdeeServiceRemote {

	IIdee creerIdee(IIdee c);

	List<IIdee> getAllIdee();

	List<IIdee> getIdeeByName(String nom);

	IIdee getIdeeById(String identifiantServeur);

	List<IIdee> getIdeeByCreator(final String idCreateur);

	List<IIdee> getIdeeByCreator(String idCreateur, Date dateModif);

	IIdee updateIdee(String identifiantServeur, IIdee idee);
	
	void removeIdeeById(String identifiantServeur);

	void removeAll();


}
