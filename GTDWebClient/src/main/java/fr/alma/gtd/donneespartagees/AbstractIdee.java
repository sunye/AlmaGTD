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
	protected Date dateDeDerModif;

	/**
	 * Le createur.
	 */
	protected IParticipant createur;


	/**
	 * Le constructeur par defaut.
	 */
	public AbstractIdee() {
		super();
		this.dansLaPoubelle = false;
		this.dateDeDerModif = new Date();
	}

	/**
	 * Le constructeur permettant l'initialisation du nom et de la description.
	 * @param n Le nom choisi.
	 * @param desc Le description choisie.
	 * @param c Le createur de l'idee.
	 */
	public AbstractIdee(final String nom, final String desc, final IParticipant createur) {
		super();
		this.nom = nom;
		this.description = desc;
		this.createur = createur;
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
		this.dateDeDerModif = idee.getDateDeDerModif();
		this.identServeur = idee.getIdentifiantServeur();
		this.description = idee.getDescription();
		this.dansLaPoubelle = idee.isDansLaPoubelle();
	}
	
	@Override
	public final void setNom(final String nom) {
		this.nom = nom;
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
	public final void setCreateur(final IParticipant createur) {
		this.createur = createur;
	}

	public String getNom() {
		return nom;
	}

	public String getDescription() {
		return description;
	}

	public boolean isDansLaPoubelle() {
		return dansLaPoubelle;
	}

	public IParticipant getCreateur() {
		return createur;
	}

	public Date getDateDeDerModif() {
		return dateDeDerModif;
	}

	public void setDateDeDerModif(final Date dateDeDerModif) {
		this.dateDeDerModif = dateDeDerModif;
	}
}
