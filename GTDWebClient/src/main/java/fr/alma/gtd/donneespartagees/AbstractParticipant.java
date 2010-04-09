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
	
	/**
	 * Le constructeur par defaut.
	 */
	public AbstractParticipant() {
		super();
		this.dateDeDerModif = new Date();
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
	public AbstractParticipant(final IParticipant particip) {
		this.copier(particip);	
	}
	
	/**
	 * Copie d'un participant.
	 * @param p Participant a recopier.
	 */
	public void copier(final IParticipant particip){
		this.pseudonyme = particip.getPseudonyme();
		this.dateDeDerModif = particip.getDateDeDerModif();
		this.identServeur = particip.getIdentifiantServeur();
	}

	@Override
	public final void setPseudonyme(final String pseudo) {
		this.pseudonyme = pseudo;
	}

	public String getPseudonyme() {
		return pseudonyme;
	}
	
}
