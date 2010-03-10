package fr.alma.gtd.donneespartagees;

import java.util.Date;

/**
 * Classe representant les idees.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public abstract class AbstractIdee extends AbstractObjetServeur implements IIdee {

	/**
	 * Identifiant de serialisation.
	 */
	private static final long serialVersionUID = -3818784391851861129L;

	/**
	 * Le nom.
	 */
	protected String nom;

	/**
	 * La description.
	 */
	protected String description;


	/**
	 * Indique si l'idee est dans la poubelle.
	 */
	protected boolean dansLaPoubelle;

	/**
	 * La date de derniere modification.
	 */
	protected Date dateDeDerniereModification;

	/**
	 * Le createur.
	 */
	protected IParticipant createur;

	/**
	 * Initialisation des variables.
	 */
	{
		this.dansLaPoubelle = false;
		this.dateDeDerniereModification = new Date();
	}

	/**
	 * Le constructeur par defaut.
	 */
	public AbstractIdee() {
		super();
	}

	/**
	 * Le constructeur permettant l'initialisation du nom et de la description.
	 * @param n Le nom choisi.
	 * @param desc Le description choisie.
	 * @param c Le createur de l'idee.
	 */
	public AbstractIdee(final String n, final String desc, final IParticipant c) {
		super();
		this.nom = n;
		this.description = desc;
		this.createur = c;
	}


	/**
	 * Constructeur de recopie d'une idee.
	 * @param idee Idee a recopier.
	 */
	public AbstractIdee(final IIdee idee) {
		this.copier(idee);
	}

	public void copier(final IIdee idee) {
		this.nom = idee.getNom();
		this.createur = idee.getCreateur();
		this.dateDeDerniereModification = idee.getDateDeDerniereModification();
		this.identifiantServeur = idee.getIdentifiantServeur();
		this.description = idee.getDescription();
		this.dansLaPoubelle = idee.isDansLaPoubelle();
	}
	
	@Override
	public final void setNom(final String n) {
		this.nom = n;
	}

	@Override
	public final void setDescription(final String desc) {
		this.description = desc;
	}

	@Override
	public final void setDansLaPoubelle(final boolean estDansPoubelle) {
		this.dansLaPoubelle = estDansPoubelle;
	}

	@Override
	public final void setCreateur(final IParticipant c) {
		this.createur = c;
	}
}
