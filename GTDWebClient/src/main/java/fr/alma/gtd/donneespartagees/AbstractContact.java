package fr.alma.gtd.donneespartagees;

public abstract class AbstractContact extends AbstractObjetServeur implements IContact {

	/** Classe representant les contacts lies a une tache ou a un projet.
	 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
	 * @version 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Adresse liee a ce contact.
	 */
	protected String adresse;
	
	/**
	 * Email liee a ce contact.
	 */
	protected String email;
	
	/**
	 * Nom liee a ce contact.
	 */
	protected String nom;
	
	/**
	 * Telephone liee a ce contact.
	 */
	protected String numeroTelephone;

	
	/**
	 * Constructeur par defaut.
	 */
	public AbstractContact() {
		super();
	}
	
	/**
	 * Constructeur standard d'un contact.
	 * @param nom nom du contact
	 * @param email email du contact
	 * @param adresse adresse du contact
	 * @param numeroTelephone numero de telephone du contact
	 */
	public AbstractContact(final String nom, final String email, final String adresse,final String numeroTelephone) {
		super();
		this.nom = nom;
		this.email = email;
		this.adresse = adresse;
		this.numeroTelephone = numeroTelephone;
	}
	
	/**
	 * Constructeur de recopier d'un contact.
	 * @param contact contact a copier.
	 */
	public AbstractContact(final IContact contact) {
		super();
	}
	
	/**
	 * Copie un contact.
	 * @param contact contact a copier.
	 */
	public void copier(final IContact contact) {
		this.identifiantServeur = contact.getIdentifiantServeur();
		this.dateDeDerniereModification = contact.getDateDeDerniereModification();
		this.nom = contact.getNom();
		this.email = contact.getEmail();
		this.adresse = contact.getAdresse();
		this.numeroTelephone = contact.getTelephone();
	}

	

	@Override
	public void setAdresse(final String adresse) {
		this.adresse = adresse;
	}

	@Override
	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public void setNom(final String nom) {
		this.nom = nom;
	}

	@Override
	public void setTelephone(final String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
	}
	

}
