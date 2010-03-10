package fr.alma.modele.persistance;

import fr.alma.modele.noyau.IContact;
import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.ITache;

/**
 * interface ICopieLocale
 * @version 1.0
 * @author Université de Nantes
 */
public interface ICopieLocale {

	/**
	 * Ajoute un contact en BD
	 * @param contact le contact à ajouter
	 * @return le bon déroulement de
	 */
	public Boolean ajouterContactBD(IContact contact);

	/**
	 * Ajoute un projet en BD
	 * @param projet le projet à ajouter
	 * @param idProjetPere l'identifiant du projet conteneur
	 * @return l'identifiant du projet crée
	 */
	public Long ajouterProjetBD(IProjet projet, Long idProjetPere);
	
	/**
	 * Ajoute une tache en BD
	 * @param tache la tache à ajouter
	 * @param projet le projet conteneur
	 * @return le bon déroulement de l'ajout de la tache en BD
	 */
	public Boolean ajouterTacheBD(ITache tache, IProjet projet);

	/**
	 * Modifie une tache déjà existant en BD
	 * @param tache la tache à modifier
	 * @return le bon déroulement de la modification de la tache en BD
	 */
	public Boolean modifierTache(ITache tache);
	
	/**
	 * Modifie un projet déjà existant en BD
	 * @param projet le projet à modifier 
	 * @return le bon déroulement de la modification du projet en BD
	 */
	public Boolean modifierProjet(IProjet projet);
	
	/**
	 * Supprime un contact déjà existant en BD
	 * @param contact le contact à supprimer
	 * @return le bon déroulement de la suppression du contact en BD
	 */
	public Boolean supprimerContact(IContact contact);

	/**
	 * Supprime une tâche déjà existante en BD
	 * @param tache la tâche à supprimer
	 * @return le bon déroulement de la suppression de la tâche en BD
	 */
	public Boolean supprimerTache(ITache tache);
	
	/**
	 * Supprime un projet deja en BD
	 * @param projet le projet à supprimer
	 * @return le bon déroulement de la suppression du projet en BD
	 */
	public Boolean supprimerProjet(IProjet projet);

	/**
	 * Transfert un projet en BD vers la corbeille en BD
	 * @param p le projet
	 */
	public void mettreDansCorbeille(IProjet p);
	
	/**
	 * Transfert un projet en BD vers la corbeille en BD
	 * @param t la tache
	 */
	public void mettreDansCorbeille(ITache t);
	
	/**
	 * Supprime de la BD tous les éléments présents dans la corbeille
	 * @param corbeille la corbeille
	 */
	public void viderCorbeille(IProjet corbeille);
}
