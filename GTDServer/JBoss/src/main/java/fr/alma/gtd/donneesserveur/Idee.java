package fr.alma.gtd.donneesserveur;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import fr.alma.gtd.donneespartagees.AbstractIdee;
import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IParticipant;

/**
 * Bean Entite representant une Idee et charge de gerer sa persistance.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Entity
@Table(name = "IDEE")
public final class Idee extends AbstractIdee {
	
	/**
	 * L'ID.
	 */
	private static final long serialVersionUID = -2348892725312472280L;

	/**
	 * Le constructeur par defaut (utilise par Hibernate).
	 */
	public Idee() {
		super();
	}
	
	/**
	 * Le constructeur permettant l'initialisation du nom et de la description.
	 * @param n Le nom choisi.
	 * @param desc Le description choisie.
	 * @param c Le createur de l'idee.
	 */
	public Idee(final String n, final String desc, final IParticipant c) {
		super(n, desc, c);
	}
	
	
	/**
	 * Constructeur de recopie d'une idee.
	 * @param idee Idee a recopier.
	 */
	public Idee(final IIdee idee) {
		super(idee);
	}
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Override
	public String getIdentifiantServeur() {
		return identifiantServeur;
	}
	
	@ManyToOne
	@Override
	public Participant getCreateur() {
		return (Participant) this.createur;
	}

	@Basic
	@Override
	public Date getDateDeDerniereModification() {
		return dateDeDerniereModification;
	}

	@Basic
	@Override
	public String getDescription() {
		return description;
	}

	@Basic
	@Override
	public String getNom() {
		return nom;
	}

	@Basic
	@Override
	public boolean isDansLaPoubelle() {
		return dansLaPoubelle;
	}
	
	@Override
	@Basic
	public boolean isDansArchive() {
		return this.dansArchive;
		
	}

	@Override
	public void mettreALaPoubelle() {
		// TODO Auto-generated method stub
	}

	@Override
	public void restaurer() {
		// TODO Auto-generated method stub
	}

}
