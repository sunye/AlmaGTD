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
	protected String identServeur;
	
	/**
	 * Le createur.
	 */
	protected IParticipant createur;
	
	/**
	 * La date de derniere modification.
	 */
	protected Date dateDeDerModif;
	
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
	public AbstractTag(final String name, final IParticipant createur) {
		this.nom = name;
		this.createur = createur;
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
		this.identServeur = tag.getIdentifiantServeur();
		this.createur = tag.getCreateur();
		this.dateDeDerModif = tag.getDateDeDerModif();
	}
	
	@Override
	public final void setNom(final String nom) {
		this.nom = nom;
	}
	
	@Override
	public final void setCreateur(final IParticipant createur) {
		this.createur = createur;
	}

	public String getNom() {
		return nom;
	}

	public String getIdentServeur() {
		return identServeur;
	}

	public void setIdentServeur(final String identServeur) {
		this.identServeur = identServeur;
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
