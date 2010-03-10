package fr.alma.gtd.donneesserveur;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import fr.alma.gtd.donneespartagees.AbstractContexte;
import fr.alma.gtd.donneespartagees.IContexte;

/**
 * Bean Entite representant un contexte et charge de gerer sa persistance.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Entity
@Table(name = "CONTEXTE")
public final class Contexte extends AbstractContexte {
	
	/**
	 * L'ID.
	 */
	private static final long serialVersionUID = -8938444349832798401L;
	
	/**
	 * Le constructeur par defaut.
	 */
	public Contexte() {
		super();
	}
	
	/**
	 * Le constructeur initialisant le nom.
	 * @param n Le nom choisi.
	 */
	public Contexte(final String n) {
		super(n);
	}
	
	/**
	 * Constructeur de recopie d'un Participant.
	 * @param ctx Participant a recopier.
	 */
	public Contexte(final IContexte ctx) {
		super(ctx);
	}
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Override
	public String getIdentifiantServeur() {
		return this.identifiantServeur;
	}
	
	@Override
	@ManyToOne
	public Participant getCreateur() {
		return (Participant) this.createur;
	}
	
	@Override
	@Basic
	public Date getDateDeDerniereModification() {
		return dateDeDerniereModification;
	}

	@Override
	@Basic
	public String getNom() {
		return nom;
	}

	
}
