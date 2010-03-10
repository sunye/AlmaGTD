package fr.alma.gtd.donneespartagees;


/**
 * Classe representant les contextes d'une tache ou le contexte par defaut d'un projet.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public abstract class AbstractContexte extends AbstractObjetServeur implements IContexte {
	
	/**
	 * Identifiant de serialisation.
	 */
	private static final long serialVersionUID = 2868136479716970129L;

	/**
	 * Le nom.
	 */
	protected String nom;
	
	/**
	 * Le createur.
	 */
	protected IParticipant createur;
	
	/**
	 * Le constructeur par defaut.
	 */
	public AbstractContexte() {
		super();
	}
	
	/**
	 * Le constructeur initialisant le nom.
	 * @param n Le nom choisi.
	 */
	public AbstractContexte(final String n) {
		super();
		this.nom = n;
	}

	/**
	 * Constructeur de recopie d'un contexte.
	 * @param ctx Contexte a recopier.
	 */
	public AbstractContexte(final IContexte ctx) {
		this.copier(ctx);
	}

	/**
	 * Copie un contexte.
	 * @param ctx Contexte a recopier.
	 */
	public void copier(final IContexte ctx) {
		this.nom = ctx.getNom();
		this.createur = ctx.getCreateur();
		this.dateDeDerniereModification = ctx.getDateDeDerniereModification();
		
	}

	@Override
	public final void setNom(final String n) {
		this.nom = n;
	}

	@Override
	public final void setCreateur(final IParticipant c) {
		this.createur = c;
	}
}
