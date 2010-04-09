package fr.alma.gtd.dao;

import fr.alma.gtd.entities.Contact;
import fr.alma.gtd.entities.Contexte;
import fr.alma.gtd.entities.Element;
import fr.alma.gtd.entities.Projet;
import java.util.Collection;
import java.util.List;

public interface ElementDao extends GtdEntityDao<Element> {

    public List<Element> findAllOfProjet(Projet projet);

	public Collection<Element> findWithContext(Contexte contexte);

	public Collection<Contact> getContacts(Element tache);

}
