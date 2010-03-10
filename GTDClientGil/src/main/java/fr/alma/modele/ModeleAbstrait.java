package fr.alma.modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.alma.modele.noyau.Frequence;
import fr.alma.modele.noyau.IContact;
import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.ITache;
import fr.alma.observer.*;

/**
 * Modèle abstrait.
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public abstract class ModeleAbstrait implements Observable {

	private static Long idUtilisateur;
	private static IProjet projetRacine;

	/**
	 * La liste d'observeurs (pattern observer)
	 */
	private ArrayList<Observer> observers = new ArrayList<Observer>();

	/**
	 * Vérifie si l'utilisateur réussi sa connexion
	 * @param login le login de l'utilisateur
	 * @param mdp le mot de passe de l'utilisateur
	 * @return le code de validation/erreur 
	 */
	public abstract Long existeUtilisateur(String login, char[] mdp);

	/**
	 * Créer un compte utilisateur
	 * @param login  le login de l'utilisateur
	 * @param password le mot de passe de l'utilisateur
	 * @param email l'adresse de l'utilisateur
	 */
	public abstract void creerCompte(String login, char[] password, String email);
	
	/**
	 * Créer un contact
	 * @param nom nom du contact
	 * @param email l'adresse email du contact
	 * @param adresse l'adresse du contact
	 * @param tel le téléphone du contact
	 * @return Le contact créé
	 */
	public abstract IContact creerContact(String nom, String email, String adresse, String tel);
	
	/**
	 * Créer une tâche
	 * @param nom nom de la tâche
	 * @param projet le projet dans lequel elle est contenu
	 * @param contexte le contexte de la tâche
	 * @param notesTache les notes de la tâche
	 * @param dateDedebut la date de commencement de la tâche
	 * @param echeance l'échéance de la tâche
	 * @param priorite la priorité de la tâche
	 * @param tauxEffort le taux d'effort demandé pour la tâche
	 * @param listeContact la liste de contacts associées à cette tâche
	 * @param frequence la fréquence d'utilisation de cette tâche
	 * @param arretFrequence la date d'arrêt de la fréquence de la tâche
	 * @param urls les urls de la tâche
	 * @param tags les références de la tâches
	 */
	public abstract void creerTache(String nom, IProjet projet, String contexte, String notesTache, Date dateDedebut,
			Date echeance, Integer priorite, Integer tauxEffort, IContact[] listeContact, Frequence frequence,
			Date arretFrequence, List<String> urls, List<String> tags);
	
	/**
	 * Créer un projet
	 * @param nom nom du projet
	 * @param contexte contexte du projet
	 * @param notes notes du projet
	 * @param projetPere projet qui contient le projet courant
	 * @return l'identifiant du projet
	 */
	public abstract Long creerProjet(String nom, String contexte, String notes, IProjet projetPere);

	/**
	 * Déplace un objet (tâche ou projet) dans la corbeille
	 * @param o
	 */
	public abstract void mettreDansCorbeille(Object o);

	/**
	 * Edite une tâche
	 * @param t la tâche
	 */
	public abstract void editerTache(ITache t); 
	
	/**
	 * Edite un projet
	 * @param p le projet
	 */
	public abstract void editerProjet(IProjet p); 

	/**
	 * Supprime un contact
	 * @param contact le nom du contact à supprimer
	 */
	public abstract void supprimerContact(IContact contact);
	
	/**
	 * vide la corbeille
	 * @param corbeille le projet corbeille
	 */
	public abstract void viderCorbeille(IProjet corbeille);

	// Fonctionnalités non-implémentées
	public abstract void commit();
	public abstract void update();
	public abstract void synchro();

	//**************************************************
	//		   IMPLEMENTATION PATTERN OBSERVER
	//**************************************************

	public void addObserver(Observer obs) {
		this.observers.add(obs);
	}

	public void notifyObserver(ModeleAbstrait modele) {
		for(Observer obs : observers)
			obs.update(modele);
	}

	public void removeObserver() {
		observers = new ArrayList<Observer>();
	}

	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************

	public static Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public static IProjet getProjetRacine() {
		return projetRacine;
	}

	public static void setIdUtilisateur(Long idUtilisateur) {
		ModeleAbstrait.idUtilisateur = idUtilisateur;
	}

	public static void setProjetRacine(IProjet projetRacine) {
		ModeleAbstrait.projetRacine = projetRacine;
	}
}
