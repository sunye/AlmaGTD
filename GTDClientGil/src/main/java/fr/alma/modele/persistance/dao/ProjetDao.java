package fr.alma.modele.persistance.dao;

import java.util.List;

import fr.alma.modele.noyau.Projet;

/**
 * interface ProjetDao
 * @version 1.0
 * @author Université de Nantes
 */
public interface ProjetDao extends EntiteDao<Projet> {
	
	/**
	 * Récupère toutes les tâches et les projets d'un utilisateurs connecté
	 * @return le projet racine (celui qui contient tout)
	 */
	public Projet recupererProjetRacine();
	
	/**
	 * Récupère toutes les tâches du panier
	 * @return le projet panier (qui contient toutes les tâches)
	 */
	public Projet recupererPanier();

	/**
	 *  Récupère tous les sous-projets d'un projet
	 * @param idPere l'identifiant du projet père
	 * @return la liste de sous-projets
	 */
	public List<Projet> recupererSousProjets(Long idPere);

	/**
	 * Récupère toutes les tâches et les projets de la corbeille
	 * @return le projet corbeille qui contient toutes les tâches et les projet de la corbeille)
	 */
	public Projet recupererCorbeille();
}
