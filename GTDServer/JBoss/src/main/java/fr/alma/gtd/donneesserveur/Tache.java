package fr.alma.gtd.donneesserveur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.GenericGenerator;

import fr.alma.gtd.donneespartagees.AbstractTache;
import fr.alma.gtd.donneespartagees.Avancement;
import fr.alma.gtd.donneespartagees.Frequence;
import fr.alma.gtd.donneespartagees.IContact;
import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;

/**
 * Bean Entite representant une Tache et charge de gerer sa persistance.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Entity
@Table(name = "TACHE")
public final class Tache extends AbstractTache {

	/**
	 * Identifiant de serialisation.
	 */
	private static final long serialVersionUID = 5333316500244117082L;

	/**
	 * Le constructeur par defaut.
	 */
	public Tache() {
		super();
	}
	
	/**
	 * Le constructeur avec initialisation du nom, de la priorite, du taux d'effort, du contexte et du createur.
	 * @param n Le nom.
	 * @param p La priorite.
	 * @param effort Le taux d'effort.
	 * @param c Le contexte.
	 * @param cr Le createur de la tache.
	 */
	public Tache(final String n, final int p, final int effort, final IContexte c, final IParticipant cr) {
		super(n, p, effort, c, cr);
	}
	
	/**
	 * Constructeur de recopie d'un Participant.
	 * @param t Participant a recopier.
	 */
	public Tache(final ITache t) {
		super(t);
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

	@Basic
	@Override
	public Avancement getAvancement() {
		return this.avancement;
	}

	@ManyToOne
	@Override
	public Contexte getContexte() {
		return (Contexte) this.contexte;
	}

	@Basic
	@Override
	public Date getDateDebut() {
		return this.dateDebut;
	}

	@Basic
	@Override
	public Date getDateFin() {
		return this.dateFin;
	}

	@Basic
	@Override
	public Frequence getFrequence() {
		return this.frequence;
	}

	@Transient
	@Override
	public List<ITag> getListeDeTags() {
		return this.listeDesTags;
	}

	@Override
	@CollectionOfElements
	public List<String> getListeDesURLs() {
		return this.listeDesURLs;
	}

	@Override
	@OneToOne
	public Participant getParticipant() {
		return (Participant) this.participant;
	}

	@Override
	@Basic
	public int getPriorite() {
		return this.priorite;
	}

	@Override
	@ManyToOne
	public Projet getProjetConteneur() {
		return (Projet) this.projetConteneur;
	}

	@Override
	@Basic
	public int getTauxEffort() {
		return this.tauxEffort;
	}

	@Override
	@Basic
	public boolean isDansLaPoubelle() {
		return this.dansLaPoubelle;
	}
	
	@Override
	@Basic
	public boolean isDansArchive() {
		return this.dansArchive;
		
	}

	@Transient
	@Override
	public List<ITache> getListeTachesAnterieures() {
		return tachesAnterieures;
	}
	
	@Transient
	@Override
	public List<IContact> getListeContacts() {
		return contacts;
	}
	
	
	/*
	 * Methodes privees appelees uniquement par Hibernate : 
	 * getters et setters permettant d'assurer la persistance des listes : 
	 * - de Taches Anterieures
	 * - de Contacts
	 * - de Tags
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Tache> getTachesAnterieures() {
		HashSet<Tache> listeElem = new HashSet<Tache>();
		for(ITache iElem : tachesAnterieures){
			listeElem.add((Tache)iElem);
		}
		return listeElem;
	}
	
	private void setTachesAnterieures(Set<Tache> tachesPrec) {
		tachesAnterieures.clear();
		for(Tache t : tachesPrec){
			tachesAnterieures.add(t);
		}
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Tag> getListeDesTags() {
		HashSet<Tag> listeElem = new HashSet<Tag>();
		for(ITag iElem : listeDesTags){
			listeElem.add((Tag)iElem);
		}
		
		return listeElem;
	}
	
	private void setListeDesTags(Set<Tag> tags) {
		listeDesTags.clear();
		for(Tag t : tags){
			listeDesTags.add(t);
		}
	}
	
	@OneToMany(fetch = FetchType.EAGER)
	private Set<Contact> getContacts() {
		HashSet<Contact> listeElem = new HashSet<Contact>();
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
