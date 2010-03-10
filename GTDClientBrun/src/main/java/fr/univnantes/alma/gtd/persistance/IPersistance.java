
/**
 * généré via interface.mt
 */

package fr.univnantes.alma.gtd.persistance;

// Start of user code for imports in IPersistance
import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireressources.AFaire;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Contact;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Contexte;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Deleguee;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EnAttente;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EtatTache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Finie;
import fr.univnantes.alma.gtd.model.gestionnaireressources.GestionnaireRessources;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Idee;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Projet;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Tache;
import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.GestionnaireUtilisateurs;
import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;

public interface IPersistance {
	public Boolean update(final GestionnaireUtilisateurs entity);
	
	public Integer create(final GestionnaireRessources entity);
	public List<GestionnaireRessources> findAllGestionnaireRessources();
	public GestionnaireRessources findGestionnaireRessources(final Integer id);
	public Boolean update(final GestionnaireRessources entity);
	public Boolean deleteAllGestionnaireRessources();
	public Boolean delete(final GestionnaireRessources entity);
	
	public List<Utilisateur> findAllUtilisateurs();
	public Utilisateur findUtilisateur(final Integer id);
	public Boolean update(final Utilisateur entity);
	public Boolean deleteAllUtilisateurs();
	public Boolean delete(final Utilisateur entity);
	
	public List<Idee> findAllIdees();
	public Idee findIdee(final Integer id);
	public Boolean update(final Idee entity);
	public Boolean deleteAllIdees();
	public Boolean delete(final Idee entity);
	
	public List<Contact> findAllContacts();
	public Contact findContact(final Integer id);
	public Boolean update(final Contact entity);
	public Boolean deleteAllContacts();
	public Boolean delete(final Contact entity);
	
	public List<Contexte> findAllContextes();
	public Contexte findContexte(final Integer id);
	public Boolean update(final Contexte entity);
	public Boolean deleteAllContextes();
	public Boolean delete(final Contexte entity);
	
	public List<Projet> findAllProjets();
	public Projet findProjet(final Integer id);
	public Boolean update(final Projet entity);
	public Boolean deleteAllProjets();
	public Boolean delete(final Projet entity);
	
	public List<Tache> findAllTaches();
	public Tache findTache(final Integer id);
	public Boolean update(final Tache entity);
	public Boolean deleteAllTache();
	public Boolean delete(final Tache entity);

	public void create(Projet projet);
	
	public void valide();

	public Integer create(GestionnaireUtilisateurs gestionnaireUtilisateurs);
	public GestionnaireUtilisateurs findGestionnaireUtilisateurs(Integer id);

	public void create(EtatTache entity);
	public AFaire findAFaire(Integer id);
	public Deleguee findDeleguee(Integer id);
	public EnAttente findEnAttente(Integer id);
	public Finie findFinie(Integer id);
}