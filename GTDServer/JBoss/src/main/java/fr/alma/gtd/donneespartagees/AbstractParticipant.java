package fr.alma.gtd.donneespartagees;

import java.util.Date;

/**
 * Classe representant un participant.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public abstract class AbstractParticipant extends AbstractObjetServeur implements IParticipant {
	
	/**
	 * Identifiant de serialisation.
	 */
	private static final long serialVersionUID = 8682719518429571480L;
	
	/**
	 * Le pseudonyme.
	 */
	protected String pseudonyme;
	
	{
		this.dateDeDerniereModification = new Date();
	}
	
	/**
	 * Le constructeur par defaut.
	 */
	public AbstractParticipant() {
		super();
	}
	
	/**
	 * Constructeur permettant l'initialisation du pseudonyme.
	 * @param pseudo Le pseudonyme choisi.
	 */
	public AbstractParticipant(final String pseudo) {
		super();
		this.pseudonyme = pseudo;
	}
	
	/**
	 * Constructeur de recopie d'un participant.
	 * @param p Participant a recopier.
	 */
	public AbstractParticipant(final IParticipant p) {
		this.copier(p);	
	}
	
	/**
	 * Copie d'un participant.
	 * @param p Participant a recopier.
	 */
	public void copier(final IParticipant p){
		this.pseudonyme = p.getPseudonyme();
		this.dateDeDerniereModification = p.getDateDeDerniereModification();
		this.identifiantServeur = p.getIdentifiantServeur();
	}

	@Override
	public final void setPseudonyme(final String pseudo) {
		this.pseudonyme = pseudo;
	}
	
}
