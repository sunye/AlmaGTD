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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe faisant office de façade.
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public class Modele extends AbstractModele {

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
		Logger.getAnonymousLogger().log(Level.INFO, "Création du compte...");
		gestionnaireTaches.getBd().creerCompte(login, password, email);
	}
	
	@Override
	public IContact creerContact(String nom, String email, String adresse, String tel) {
		IContact contact = new Contact(nom, email, adresse, tel);
		((Contact) contact).setIdUtilisateur(AbstractModele.getIdUtilisateur());
		gestionnaireTaches.getBd().ajouterContactBD(contact);
		return contact;
	}

	@Override
	public Long creerProjet(String nom, String contexte, String notes, IProjet projetPere) {
		Projet projet = new Projet(nom, contexte, notes);
		Long idProjet;
		if (projetPere == null) {
			idProjet = gestionnaireTaches.getBd().ajouterProjetBD(projet, null);
		} else {
			projetPere.ajouterSousProjet(projet);
			idProjet = gestionnaireTaches.getBd().ajouterProjetBD(projet, projetPere.getId());
		}
		ArbreGTD.getInstance().addObject(projet);
		notifyObserver(this);
		return idProjet;
	}

	@Override
	public void creerTache(String nom, IProjet projet, String contexte, String notes, Date dDebut, Date dEcheance, Integer priorite, 
			Integer tauxEffort, IContact[] contacts, Frequence frequence, Date dArretFrequenceRep, List<String> urls, List<String> tags) {
		Tache tache = new Tache(nom, contexte, notes, dDebut, dEcheance, priorite, tauxEffort, contacts, frequence, dArretFrequenceRep, urls, tags);
		gestionnaireTaches.getBd().ajouterTacheBD(tache, projet);
		ArbreGTD.getInstance().addObject(tache);
		notifyObserver(this);
	}

	@Override
	public void editerTache(ITache tache) {
		gestionnaireTaches.getBd().modifierTache(tache);
	}

	@Override
	public void editerProjet(IProjet projet) {
		gestionnaireTaches.getBd().modifierProjet(projet);
	}


	@Override
	public void supprimerContact(IContact contact){
		gestionnaireTaches.getBd().supprimerContact(contact);
	}

	@Override
	public void commit() {
                Logger.getAnonymousLogger().log(Level.INFO, "Commit !!!");
	}

	@Override
	public void update() {
                Logger.getAnonymousLogger().log(Level.INFO, "Update !!!");

	}

	@Override
	public void synchro() {
		Logger.getAnonymousLogger().log(Level.INFO, "Synchronisation !!!");
	}

	@Override
	public void mettreDansCorbeille(Object object) {
		if (object instanceof IProjet) {
			gestionnaireTaches.getBd().mettreDansCorbeille((IProjet) object);
		} else if (object instanceof ITache) {
			gestionnaireTaches.getBd().mettreDansCorbeille((ITache) object);
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
