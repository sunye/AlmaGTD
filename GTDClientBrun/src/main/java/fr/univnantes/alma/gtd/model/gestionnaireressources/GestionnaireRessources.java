
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireressources;

// Start of user code for imports in GestionnaireRessources
import java.util.ArrayList;
import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;
import fr.univnantes.alma.gtd.persistance.IPersistance;

// Start of user code for comments in GestionnaireRessources
/**
 * 
 */
// End of user code

// Start of user code for comments in GestionnaireRessources
/**
 * 
 */
// End of user code

public class GestionnaireRessources extends GTDEntity implements IGestionnaireRessources { 

	private List<Projet> projets = new ArrayList<Projet>();
	private List<Contact> contacts = new ArrayList<Contact>();
	private List<Contexte> contextes = new ArrayList<Contexte>();
	private List<Idee> idees = new ArrayList<Idee>();
	private Utilisateur utilisateur;

	// Start of user code for attributes in GestionnaireRessources
	private IPersistance persistance = GTDEntity.PERSISTANCE;
	// End of user code
	
	// Start of user code for constructors in GestionnaireRessources
	public GestionnaireRessources() {
		super();
	}
	
	/* // contructeur généré
	public GestionnaireRessources(final Projet projets, final Contact contacts, final Contexte contextes, final Idee idees) {
		super();
		this.setProjets(projets);
		this.setContacts(contacts);
		this.setContextes(contextes);
		this.setIdees(idees);
	}
	*/
	// End of user code
	
	public List<Projet> getProjets() {
		// Start of user code for getter of projets
		return this.projets;
		// End of user code
	}
	
	
	public List<Contact> getContacts() {
		// Start of user code for getter of contacts
		return this.contacts;
		// End of user code
	}
	
	public List<Contexte> getContextes() {
		// Start of user code for getter of contextes
		return this.contextes;
		// End of user code
	}
	
	public List<Idee> getIdees() {
		// Start of user code for getter of idees
		return this.idees;
		// End of user code
	}
	
	/**
	 * @see IGestionnaireRessources#delete
	 */
	public Boolean remove(final Projet project) {
		// Start of user code for delete method body
		Boolean res = this.projets.remove(project);
		persistance.update(this);
		return res;		
		// End of user code
	}
	/**
	 * @see IGestionnaireRessources#getProject
	 */
	public Projet getProjet(final Integer id) {
		// Start of user code for getProject method body
		return persistance.findProjet(id);		
		// End of user code
	}
	/**
	 * @see IGestionnaireRessources#create
	 */
	public Integer add(final Contact contact) {
		// Start of user code for create method body
		//persistance.create(contact);
		this.contacts.add(contact);
		persistance.update(this);
		return contact.getId();		
		// End of user code
	}
	
	/**
	 * @see IGestionnaireRessources#delete
	 */
	public Boolean remove(final Tache task) {
		// Start of user code for delete method body
		return persistance.delete(task);		
		// End of user code
	}
	/**
	 * @see IGestionnaireRessources#getTask
	 */
	public Tache getTache(final Integer id) {
		// Start of user code for getTask method body
		return persistance.findTache(id);		
		// End of user code
	}
	
	/**
	 * @see IGestionnaireRessources#delete
	 */
	public Boolean remove(final Contact contact) {
		// Start of user code for delete method body
		return persistance.delete(contact);		
		// End of user code
	}
	/**
	 * @see IGestionnaireRessources#getContact
	 */
	public Contact getContact(final Integer id) {
		// Start of user code for getContact method body
		return persistance.findContact(id);		
		// End of user code
	}
	/**
	 * @see IGestionnaireRessources#create
	 */
	public Integer add(final Contexte context) {
		// Start of user code for create method body
		this.contextes.add(context);
		persistance.update(this);
		return context.getId();		
		// End of user code
	}

	/**
	 * @see IGestionnaireRessources#findAllTasks
	 */
	public List<Tache> getTaches() {
		// Start of user code for findAllTasks method body
		List<Tache> res = new ArrayList<Tache>();
		for (Projet p : this.projets)
			res.addAll(p.getTaches());
		return res;		
		// End of user code
	}

	/**
	 * @see IGestionnaireRessources#create
	 */
	public Integer add(final Projet projet) {
		// Start of user code for create method body
		this.projets.add(projet);
		persistance.update(this);
		return projet.getId();		
		// End of user code
	}
	/**
	 * @see IGestionnaireRessources#getContext
	 */
	public Contexte getContexte(final Integer id) {
		// Start of user code for getContext method body
		return persistance.findContexte(id);		
		// End of user code
	}
	/**
	 * @see IGestionnaireRessources#delete
	 */
	public Boolean remove(final Contexte context) {
		// Start of user code for delete method body
		this.contextes.remove(context);
		return persistance.delete(context);		
		// End of user code
	}
	/**
	 * @see IGestionnaireRessources#create
	 */
	public Integer add(final Idee idee) {
		// Start of user code for create method body
		this.idees.add(idee);
		persistance.update(this);
		return idee.getId();		
		// End of user code
	}
	/**
	 * @see IGestionnaireRessources#getIdea
	 */
	public Idee getIdee(final Integer id) {
		// Start of user code for getIdea method body
		return persistance.findIdee(id);		
		// End of user code
	}
	/**
	 * @see IGestionnaireRessources#delete
	 */
	public Boolean remove(final Idee idea) {
		// Start of user code for delete method body
		this.idees.remove(idea);
		persistance.update(this);
		return persistance.delete(idea);		
		// End of user code
	}

	@Override
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	@Override
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
		
	}
}
