package fr.alma.gtd.donneespartagees;

import java.util.Date;

/**
 * Enumeration representant les tags d'une tache.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public abstract class AbstractTag implements ITag {
	
	/**
	 * Identifiant de serialisation.
	 */
	private static final long serialVersionUID = 769066350989797895L;

	/**
	 * Le nom.
	 */
	protected String nom;
	
	/**
	 * L'identifiant.
	 */
	protected String identifiant;
	
	/**
	 * L'identifiant serveur.
	 */
	protected String identifiantServeur;
	
	/**
	 * Le createur.
	 */
	protected IParticipant createur;
	
	/**
	 * La date de derniere modification.
	 */
	protected Date dateDeDerniereModification;
	
	/**
	 * Le constructeur par defaut.
	 */
	public AbstractTag() {
		super();
	}
	
	/**
	 * Constructeur d'un Tag (a partir du nom correspondant au Tag et du participant l'ayant cree).
	 * @param name Nom correspondant au Tag
	 * @param p Participant ayant creer la tache
	 */
	public AbstractTag(final String name, final IParticipant p) {
		this.nom = name;
		this.createur = p;
	}
	
	/**
	 * Constructeur de recopie d'un Tag.
	 * @param tag Tag a recopier
	 */
	public AbstractTag(final ITag tag) {
		this.copier(tag);
	}
	
	public void copier(final ITag tag){
		this.nom = tag.getNom();
		this.identifiantServeur = tag.getIdentifiantServeur();
		this.createur = tag.getCreateur();
		this.dateDeDerniereModification = tag.getDateDeDerniereModification();
	}
	
	@Override
	public final void setNom(final String n) {
		this.nom = n;
	}

	@Override
	public final void setIdentifiantServeur(final String idServeur) {
		this.identifiantServeur = idServeur;
	}
	
	@Override
	public final void setCreateur(final IParticipant c) {
		this.createur = c;
	}
	
	@Override
	public final void setDateDeDerniereModification(final Date dateDerniereModification) {
		this.dateDeDerniereModification = dateDerniereModification;
	}
}
