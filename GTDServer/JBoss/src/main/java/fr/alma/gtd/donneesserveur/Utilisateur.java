package fr.alma.gtd.donneesserveur;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


/**
 * Bean Entite representant un contexte et charge de gerer sa persistance.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */

@Entity
@Table(name = "UTILISATEUR")
public final class Utilisateur {
	
	/**
	 * Identifiant de serialisation.
	 */
	private static final long serialVersionUID = -8938444349832798401L;
	
	/**
	 * L'identifiant serveur.
	 */
	protected String identifiantServeur;
	
	/**
	 * Chaine de caractere correspondant au login (pseudonyme) dont l'utilisateur aura besoin pour s'identifier.
	 */
	private String login;
	
	/**
	 * Chaine de caractere correspondant au password (mot de passe) dont l'utilisateur aura besoin pour s'identifier.
	 */
	private String password;

	/**
	 * Participant associe a cet utilisateur. 
	 */
	private Participant participantAssocie;
	
	/**
	 * Sete des idees emises par l'utilisateur (stockee dans le but d'optimiser les requetes).
	 */
	private Set<Idee> ideesAssociees;
	
	/**
	 * Sete des taches creees par l'utilisateur (stockee dans le but d'optimiser les requetes).
	 */
	private Set<Tache> tachesAssociees;
	
	/**
	 * Sete des projets crees par l'utilisateur (stockee dans le but d'optimiser les requetes).
	 */
	private Set<Projet> projetsAssocies;
	
	/**
	 * Le constructeur par defaut.
	 */
	public Utilisateur() {
		super();
	}
	
	/**
	 * Constructeur par recopie d'un utilisateur.
	 * @param u utilisateur a copier.
	 */
	public Utilisateur(final Utilisateur u) {
		super();
		this.copier(u);
	}

	/**
	 * recopie d'un utilisateur.
	 * @param u utilisateur a copier.
	 */
	public void copier(final Utilisateur u){
		this.ideesAssociees = u.getIdeesAssociees();
		this.tachesAssociees = u.getTachesAssociees();
		this.projetsAssocies = u.getProjetsAssocies();
		this.participantAssocie = u.getParticipantAssocie();
		this.password = u.getPassword();
		this.login = u.getLogin();
	}
	/**
	 * @return L'identifiantServeur.
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getIdentifiantServeur(){
		return this.identifiantServeur;
	}

	/**
	 * @param idServeur La nouvelle valeur de l'identifiant serveur.
	 */
	public void setIdentifiantServeur(final String idServeur){
		this.identifiantServeur = idServeur;
	}
	
	/**
	 * @return login dont l'utilisateur aura besoin pour s'identifier.
	 */
	@Basic
	public String getLogin() {
		return login;
	}

	/**
	 * @param login dont l'utilisateur aura besoin pour s'identifier.
	 */
	public void setLogin(final String login) {
		this.login = login;
	}

	@Basic
	/**
	 * @return password dont l'utilisateur aura besoin pour s'identifier.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password dont l'utilisateur aura besoin pour s'identifier.
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	@OneToMany
	/**
	 * @return Sete des idees emises par l'utilisateur.
	 */
	public Set<Idee> getIdeesAssociees() {
		return ideesAssociees;
	}

	/**
	 * @param ideesAssociees Sete des idees emises par l'utilisateur.
	 */
	public void setIdeesAssociees(final Set<Idee> ideesAssociees) {
		this.ideesAssociees = ideesAssociees;
	}

	/**
	 * @return Sete des taches emises par l'utilisateur.
	 */
	@OneToMany
	public Set<Tache> getTachesAssociees() {
		return tachesAssociees;
	}

	/**
	 * @param tachesAssociees Sete des taches emises par l'utilisateur.
	 */
	public void setTachesAssociees(final Set<Tache> tachesAssociees) {
		this.tachesAssociees = tachesAssociees;
	}

	@OneToMany
	/**
	 * @return Sete des projets crees par l'utilisateur.
	 */
	public Set<Projet> getProjetsAssocies() {
		return projetsAssocies;
	}

	/**
	 * @param projetsAssociees Sete des projets crees par l'utilisateur.
	 */
	public void setProjetsAssocies(final Set<Projet> projetsAssocies) {
		this.projetsAssocies = projetsAssocies;
	}
	

	/**
	 * @return Participant associe a cet utilisateur.
	 */
	@OneToOne
	public Participant getParticipantAssocie() {
		return participantAssocie;
	}
	
	/**
	 * @param participantAssocie Participant a associer a cet utilisateur.
	 */
	public void setParticipantAssocie(final Participant participantAssocie) {
		this.participantAssocie = participantAssocie;
	}

	
	/**
	 * Retourne vrai si le mot de passe passe en parametre est identique a celui socke en attribut.
	 * @param mdp mot de passe passe a tester
	 * @return vrai si les mots de passe sont identiques, faux sinon.
	 */
	public boolean estPasswordCorrect(final String mdp){
		return this.password.equals(mdp);
	}



}
