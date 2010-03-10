package fr.alma.modele;

import java.util.Date;
import java.util.List;

import fr.alma.ihm.vues.generale.arbre.ArbreGTD;
import fr.alma.modele.gestionnaire.GestionnaireTaches;
import fr.alma.modele.gestionnaire.IGestionnaireTaches;
import fr.alma.modele.noyau.Contact;
import fr.alma.modele.noyau.Frequence;
import fr.alma.modele.noyau.IContact;
import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.ITache;
import fr.alma.modele.noyau.Projet;
import fr.alma.modele.noyau.Tache;

/**
 * Classe faisant office de façade.
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public class Modele extends ModeleAbstrait {

	/** Composant qui gère les tâches */
	private IGestionnaireTaches gestionnaireTaches;

	
	public Modele() {
		this.gestionnaireTaches = new GestionnaireTaches();
	}

	//**************************************************
	//				METHODES DE CLASSES
	//**************************************************

	@Override
	public Long existeUtilisateur(String login, char[] mdp) {
		return (gestionnaireTaches.getBd().existeUtilisateur(login, mdp));
	}

	@Override
	public void creerCompte(String login, char[] password, String email) {
		System.out.println("Création du compte...");
		gestionnaireTaches.getBd().creerCompte(login, password, email);
	}
	
	@Override
	public IContact creerContact(String nom, String email, String adresse, String tel) {
		IContact c = new Contact(nom, email, adresse, tel);
		((Contact) c).setIdUtilisateur(ModeleAbstrait.getIdUtilisateur());
		gestionnaireTaches.getBd().ajouterContactBD(c);
		return c;
	}

	@Override
	public Long creerProjet(String nom, String contexte, String notes, IProjet projetPere) {
		Projet p = new Projet(nom, contexte, notes);
		Long idProjet;
		if (projetPere == null) {
			idProjet = gestionnaireTaches.getBd().ajouterProjetBD(p, null);
		} else {
			projetPere.ajouterSousProjet(p);
			idProjet = gestionnaireTaches.getBd().ajouterProjetBD(p, projetPere.getId());
		}
		ArbreGTD.getInstance().addObject(p);
		notifyObserver(this);
		return idProjet;
	}

	@Override
	public void creerTache(String nom, IProjet projet, String contexte, String notes, Date dDebut, Date dEcheance, Integer priorite, 
			Integer tauxEffort, IContact[] contacts, Frequence frequence, Date dArretFrequenceRep, List<String> urls, List<String> tags) {
		Tache t = new Tache(nom, contexte, notes, dDebut, dEcheance, priorite, tauxEffort, contacts, frequence, dArretFrequenceRep, urls, tags);
		gestionnaireTaches.getBd().ajouterTacheBD(t, projet);
		ArbreGTD.getInstance().addObject(t);
		notifyObserver(this);
	}

	@Override
	public void editerTache(ITache t) {
		gestionnaireTaches.getBd().modifierTache(t);
	}

	@Override
	public void editerProjet(IProjet p) {
		gestionnaireTaches.getBd().modifierProjet(p);
	}


	@Override
	public void supprimerContact(IContact contact){
		gestionnaireTaches.getBd().supprimerContact(contact);
	}

	@Override
	public void commit() {
		System.out.println("Commit !!!");
	}

	@Override
	public void update() {
		System.out.println("Update !!!");
	}

	@Override
	public void synchro() {
		System.out.println("Synchronisation !!!");
	}

	@Override
	public void mettreDansCorbeille(Object o) {
		if (o instanceof IProjet) {
			gestionnaireTaches.getBd().mettreDansCorbeille((IProjet) o);
		} else if (o instanceof ITache) {
			gestionnaireTaches.getBd().mettreDansCorbeille((ITache) o);
		}
	}
	
	@Override
	public void viderCorbeille(IProjet corbeille) {
		gestionnaireTaches.getBd().viderCorbeille(corbeille);
	}

	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************

	public void setGestionnaireTaches(IGestionnaireTaches gestionnaireTaches) {
		this.gestionnaireTaches = gestionnaireTaches;
	}

	public IGestionnaireTaches getGestionnaireTaches() {
		return gestionnaireTaches;
	}
}
