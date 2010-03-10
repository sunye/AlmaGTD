package fr.alma.modele.persistance.dao;

import java.util.List;

import fr.alma.modele.noyau.EntiteGTD;

/**
 * interface EntiteDao
 * @version 1.0
 * @author Université de Nantes
 */
public interface EntiteDao<EntiteGenerique extends EntiteGTD> {

	/**
	 * Créer une entité (un projet ou une tache)
	 * @param entite l'entité
	 * @return le bon déroulement de la création
	 */
    public Long creer(EntiteGenerique entite);

    /**
     * Récupère une entité (un projet ou une tache) via la BD
     * @param entiteId l'identifiant de l'entité
     * @return l'entité désirée
     */
    public EntiteGenerique recuperer(Long entiteId);

    /**
     * Met à jour une entité (un projet ou une tache)
     * @param entite l'entité
     */
    public void maj(EntiteGenerique entite);

    /**
     * Supprime une entité via son identifiant
     * @param entiteId l'identifiant de l'entité
     */
    public void supprimer(Long entiteId);

    /**
     * Supprime toute les entités 
     */
    public void supprimerTout();

    /**
     * Récupère toute les entités 
     * @return la liste de toutes les entités
     */
    public List<EntiteGenerique> recupererTout();

}
