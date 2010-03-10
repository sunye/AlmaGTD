package fr.alma.gtd.donneesserveur;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import fr.alma.gtd.donneespartagees.AbstractContact;
import fr.alma.gtd.donneespartagees.IContact;

/**
 * Bean Entite representant un Participant et charge de gerer sa persistance.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Entity
@Table(name = "CONTACT")
public final class Contact extends AbstractContact {

	/**
	 * L'ID.
	 */
	private static final long serialVersionUID = -8232887211693546032L;

	/**
	 * Le constructeur par defaut.
	 */
	public Contact() {
		super();
	}
	
	/**
	 * Constructeur permettant l'initialisation du pseudonyme.
	 * @param pseudo Le pseudonyme choisi.
	 */
	public Contact(final String nom, final String email, final String adresse, final String numeroTelephone) {
		super(nom,email,adresse,numeroTelephone);
	}
	
	/**
	 * Constructeur de recopie d'un contexte.
	 * @param ctx Contexte a recopier.
	 */
	public Contact(final IContact contact) {
		super(contact);
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
	public String getAdresse() {
		return adresse;
	}

	@Basic
	@Override
	public String getEmail() {
		return email;
	}

	@Basic
	@Override
	public String getNom() {
		return nom;
	}

	@Basic
	@Override
	public String getTelephone() {
		return numeroTelephone;
	}

	public void copier(IContact contact) {
		// TODO Auto-generated method stub
		
	}

}
