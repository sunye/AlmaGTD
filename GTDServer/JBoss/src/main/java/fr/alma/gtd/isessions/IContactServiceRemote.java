package fr.alma.gtd.isessions;

import java.util.List;

import fr.alma.gtd.donneespartagees.IContact;

public interface IContactServiceRemote {

	IContact creerContact(IContact c);

	List<IContact> getContactByName(String nom);

	IContact getContactById(String identifiantServeur);

	List<IContact> getAllContact();

	void removeAll();

	void removeContactById(String identifiantServeur);

	IContact updateContact(String identifiantServeur, IContact contact);

}
