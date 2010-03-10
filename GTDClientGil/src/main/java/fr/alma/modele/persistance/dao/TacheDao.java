package fr.alma.modele.persistance.dao;

import java.util.List;

import fr.alma.modele.noyau.Tache;

/**
 * interface TacheDao
 * @version 1.0
 * @author Université de Nantes
 */
public interface TacheDao  extends EntiteDao<Tache> {

	/**
	 * Récupère toutes les tâches d'un projet
	 * @param idProjet l'identifiant du projet conteneur
	 * @return la liste de tâche contenu dans le projet
	 */
	public List<Tache> recupererTaches(Long idProjet);

	/**
	 * Supprime toutes les tâches d'un projet
	 * @param idProjet l'identifiant du projet conteneur
	 */
	public void supprimerTaches(Long idProjet);
}
