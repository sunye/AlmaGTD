package fr.alma.gtd.donneesserveur;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import fr.alma.gtd.donneespartagees.AbstractTag;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.ITag;

/**
 * Bean Entite representant un Tag et charge de gerer sa persistance.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Entity
@Table(name = "TAG")
public final class Tag extends AbstractTag {

	/**
	 * Identifiant de serialisation.
	 */
	private static final long serialVersionUID = 636415771384651280L;
	
	/**
	 * Le constructeur par defaut.
	 */
	public Tag() {
		super();
	}
	
	/**
	 * Constructeur d'un Tag (prend simplement le nom correspondant au Tag).
	 * @param name Nom correspondant au Tag
	 * @param p Le createur du tag.
	 */
	public Tag(final String name, final IParticipant p) {
		super(name, p);
	}
	
	/**
	 * Constructeur de recopie d'un Tag.
	 * @param tag Tag a recopier
	 */
	public Tag(final ITag tag) {
		super(tag);
	}
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Override
	public String getIdentifiantServeur() {
		return this.identifiantServeur;
	}
	
	@ManyToOne
	@Override
	public Participant getCreateur() {
		return (Participant) this.createur;
	}

	@Basic
	@Override
	public Date getDateDeDerniereModification() {
		return this.dateDeDerniereModification;
	}
	
	@Basic
	@Override
	public String getNom() {
		return this.nom;
	}

	

}
