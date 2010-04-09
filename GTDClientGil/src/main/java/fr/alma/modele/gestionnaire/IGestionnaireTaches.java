package fr.alma.modele.gestionnaire;

import java.util.Date;
import java.util.List;

import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.ITache;
import fr.alma.modele.persistance.BD;

//End of user code

/**
 * Interface IGestionnaireTaches faisant le lien entre l'IHm le noyau et la base de données
 * @version 1.0
 * @author Université de Nantes
 */
public interface IGestionnaireTaches {
	
	/**
	 * Creer une tache.
	 * @param tache la tache courante.
	 * @param projet le projet conteneur.
	 * @return le bon déroulement de la création.
	 */
	public Boolean creerTache(ITache tache, IProjet projet);
	
	/**
	 * Modifie une tache.
	 * @param tache la tache courante.
	 * @return le bon déroulement de la modification.
	 */
	public Boolean modifierTache(ITache tache);
	
	/**
	 * Supprime une tache.
	 * @param tache la tache courante.
	 * @return le bon déroulement de la suppression.
	 */
	public Boolean supprimerTache(ITache tache);
	
	/**
	 * Creer un projet.
	 * @param projet la projet courant.
	 * @return le bon déroulement de la création.
	 */
	public Boolean creerProjet(IProjet projet);
	
	/**
	 * Modifie un projet.
	 * @param projet le projet courant.
	 * @return le bon déroulement de la modification.
	 */
	public Boolean modifierProjet(IProjet projet);
	
	/**
	 * Supprime un projet.
	 * @param projet le projet courant.
	 * @return le bon déroulement de la suppression.
	 */
	public Boolean supprimerProjet(IProjet projet);
	
	/**
	 * Accède au projet racine
	 * @return le projet racine 
	 */
	public IProjet getProjetRacine();
	
	/**
	 * Récupère toute la liste de tâches de l'utilisateur.
	 * @return La liste de tâche de l'utilisateur.
	 */
	public List<ITache> getTaches();
	
	/**
	 * 
	 * @param date 
	 * @return La liste des projets depuis la dernière modification sur le serveur.
	 */
	public List<IProjet> getModifs(Date date);
	
	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	
	public BD getBd();
	public void setBd(BD dbase);
	
	
}
