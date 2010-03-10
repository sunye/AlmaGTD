package fr.alma.gtd.donneesserveur;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import fr.alma.gtd.donneespartagees.AbstractProjet;
import fr.alma.gtd.donneespartagees.Avancement;
import fr.alma.gtd.donneespartagees.IContact;
import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;

/**
 * Bean Entite representant un Projet et charge de gerer sa persistance.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Entity
@Table(name = "PROJET")
public final class Projet extends AbstractProjet {

	/**
	 * L'ID.
	 */
	private static final long serialVersionUID = 4820282979269181082L;

	/**
	 * Le constructeur par defaut.
	 */
	public Projet() {
		super();
	}

	/**
	 * Le constructeur avec initialisation du nom, du contexte par defaut et du createur.
	 * @param n Le nom choisi.
	 * @param contexte Le contexte choisi.
	 * @param c Le createur du projet.
	 */
	public  Projet(final String n, final IContexte contexte, 
			final IParticipant c) {
		super(n, contexte, c);
	}

	/**
	 * Constructeur de recopie d'un projet.
	 * @param p Projet a recopier
	 */
	public Projet(final IProjet p) {
		super(p);

		this.avancement = p.getAvancement();
		this.contacts = p.getListeContacts();
		this.contexteParDefaut = p.getContexteParDefaut();
		this.createur = p.getCreateur();
		this.dateDeDerniereModification = p.getDateDeDerniereModification();
		this.nom = p.getNom();
		this.dansArchive = p.isDansArchive();
		this.dansLaPoubelle = p.isDansLaPoubelle();
		this.listeDesParticipants = p.getListeDeParticipants();
		this.listeDesSousProjets = p.getListeDeSousProjets();
		this.listeDesTaches = p.getListeDeTaches();
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
		return this.dateDeDerniereModification;
	}

	@Override
	@Basic
	public String getNom() {
		return this.nom;
	}

	@Override
	@Basic
	public Avancement getAvancement() {
		return this.avancement;
	}

	@Override
	@ManyToOne
	public Contexte getContexteParDefaut() {
		return (Contexte) this.contexteParDefaut;
	}

	@Transient
	@Override
	public List<IParticipant> getListeDeParticipants() {
		return this.listeDesParticipants;
	}

	@Transient
	@Override
	public List<ITache> getListeDeTaches() {
		return this.listeDesTaches;
	}

	@Override
	@Basic
	public boolean isDansLaPoubelle() {
		return dansLaPoubelle;
	}
	
	@Override
	@Basic
	public boolean isDansArchive() {
		return this.dansArchive;
	}

	@Transient
	@Override
	public List<IContact> getListeContacts() {
		return contacts;
	}

	@Transient
	@Override
	public List<IProjet> getListeDeSousProjets() {
		return this.listeDesSousProjets;
	}

	
	/*
	 * Methodes privees appelees uniquement par Hibernate : 
	 * getters et setters permettant d'assurer la persistance des listes : 
	 * - de Sous-Projets
	 * - de Participants
	 * - de Tags
	 */	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Projet> getListeDesSousProjets() {
		Set<Projet> listeElem = new HashSet<Projet>();
		for(IProjet iElem : listeDesSousProjets){
			listeElem.add((Projet)iElem);
		}

		return listeElem;
	}

	private void setListeDesSousProjets(Set<Projet> ssprojets) {
		listeDesSousProjets.clear();
		for(IProjet t : ssprojets){
			listeDesSousProjets.add(t);
		}
	}


	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Participant> getListeDesParticipants() {
		Set<Participant> listeElem = new HashSet<Participant>();
		for(IParticipant iElem : listeDesParticipants){
			listeElem.add((Participant)iElem);
		}

		return listeElem;
	}

	private void setListeDesParticipants(Set<Participant> Participants) {
		listeDesParticipants.clear();
		for(Participant t : Participants){
			listeDesParticipants.add(t);
		}
	}


	@OneToMany(mappedBy = "projetConteneur", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Tache> getListeDesTaches() {
		Set<Tache> listeElem = new HashSet<Tache>();
		for(ITache iElem : listeDesTaches){
			listeElem.add((Tache)iElem);
		}
		return listeElem;
	}

	private void setListeDesTaches(Set<Tache> listedesTaches) {
		listeDesTaches.clear();
		for(Tache t : listedesTaches){
			listeDesTaches.add(t);
			t.setProjetConteneur(this);
		}
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Contact> getContacts() {
		Set<Contact> listeElem = new HashSet<Contact>();
		for(IContact iElem : contacts){
			listeElem.add((Contact)iElem);
		}		
		return listeElem;
	}

	private void setContacts(Set<Contact> listedescontacts) {
		contacts.clear();
		for(Contact t : listedescontacts){
			contacts.add(t);
		}
	}




}
