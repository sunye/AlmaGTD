package fr.alma.modele.noyau.etat;

import fr.alma.modele.noyau.ITache;

/**
 * interface IEtat
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public interface IEtat {

	/**
	 * Change l'etat de la tache dans l'etat "à faire"
	 * @param t la tache
	 */
	public void aFaire(ITache t);
	
	/**
	 * Change l'etat de la tache dans l'etat "archivé"
	 * @param t la tache
	 */
	public void archiver(ITache t);
	
	/**
	 * Change l'etat de la tache dans l'etat "delegué"
	 * @param t la tache
	 */
	public void deleguer(ITache t);
	
	/**
	 * Change l'etat de la tache dans l'etat "en attente"
	 * @param t la tache
	 */
	public void enAttente(ITache t);
	
	/**
	 * Change l'etat de la tache dans l'etat "en cours"
	 * @param t la tache
	 */
	public void enCours(ITache t);
	
	/**
	 * Change l'etat de la tache dans l'etat "supprimé"
	 * @param t la tache
	 */
	public void supprimer(ITache t);
	
	/**
	 * Change l'etat de la tache dans l'etat "terminé"
	 * @param t la tache
	 */
	public void terminer(ITache t);
}
