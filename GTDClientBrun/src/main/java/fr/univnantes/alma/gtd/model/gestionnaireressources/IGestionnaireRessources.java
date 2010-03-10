
/**
 * généré via interface.mt
 */

package fr.univnantes.alma.gtd.model.gestionnaireressources;

// Start of user code for imports in IGestionnaireRessources
import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;

public interface IGestionnaireRessources {
	
	public Integer getId();
	
	public void setId(Integer id);
	
	public Utilisateur getUtilisateur();
	
	public void setUtilisateur(Utilisateur utilisateur);
	
	public Boolean remove(final Projet project);
	
	public Projet getProjet(final Integer id);
	
	public Integer add(final Contact contact);
	
	public Boolean remove(final Tache task);
	
	public Tache getTache(final Integer id);
	
	public Boolean remove(final Contact contact);
	
	public Contact getContact(final Integer id);
	
	public Integer add(final Contexte context);
	
	public List<Projet> getProjets();
	
	public List<Tache> getTaches();
	
	public List<Contact> getContacts();
	
	public Integer add(final Projet projet);
	
	public Contexte getContexte(final Integer id);
	
	public Boolean remove(final Contexte context);
	
	public List<Contexte> getContextes();
	
	public Integer add(final Idee idee);
	
	public Idee getIdee(final Integer id);
	
	public Boolean remove(final Idee idea);
	
	public List<Idee> getIdees();
}