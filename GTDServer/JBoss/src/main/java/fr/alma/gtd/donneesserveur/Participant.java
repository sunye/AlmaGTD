package fr.alma.gtd.donneesserveur;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import fr.alma.gtd.donneespartagees.AbstractParticipant;
import fr.alma.gtd.donneespartagees.IParticipant;

/**
 * Bean Entite representant un Participant et charge de gerer sa persistance.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Entity
@Table(name = "PARTICIPANT")
public final class Participant extends AbstractParticipant {

	/**
	 * L'ID.
	 */
	private static final long serialVersionUID = -8232887211693446032L;

	/**
	 * Le constructeur par defaut.
	 */
	public Participant() {
		super();
	}
	
	/**
	 * Constructeur permettant l'initialisation du pseudonyme.
	 * @param pseudo Le pseudonyme choisi.
	 */
	public Participant(final String pseudo) {
		super();
		this.pseudonyme = pseudo;
	}
	
	/**
	 * Constructeur de recopie d'un contexte.
	 * @param ctx Contexte a recopier.
	 */
	public Participant(final IParticipant ctx) {
		super(ctx);
	}
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Override
	public String getIdentifiantServeur() {
		return identifiantServeur;
	}
	
	@Basic
	@Override
	public Date getDateDeDerniereModification() {
		return dateDeDerniereModification;
	}

	@Basic
	@Override
	public String getPseudonyme() {
		return pseudonyme;
	}
	
	@Override
	public boolean equals(final Object obj) {
		boolean result = false;
		
		if (obj instanceof Participant) {
			final Participant p = (Participant) obj;
			if (p.identifiantServeur.equals(this.identifiantServeur)) {
				result = true;
			}
		}
		
		return result;
	}
	
	@Override
	public int hashCode() {
		return this.identifiantServeur.hashCode();
	}
}
