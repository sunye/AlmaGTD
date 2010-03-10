package fr.alma.modele.noyau;

import java.util.List;

/**
 * Interface IProjet
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public interface IProjet {

	/**** TEST ****/
	public static final int CONTEXTE_DEFAUT = 0;
	public static final int NOTES = 1;
	public static final int NOM = 2;
	public static final int PROJET_CONTENEUR = 2;

	/**
	 * Ajoute une tâche au projet.
	 * @param t la tâche à ajouter.
	 */
	public void ajouterTache(ITache t);

	/**
	 *  Ajoute un sous projet au projet.
	 * @param p le sous projet.
	 */
	public void ajouterSousProjet(IProjet p);

	/**
	 * Récupére la tâche d'identifiant id.
	 * @param id l'identifiant de la tâche.
	 * @return la tâche correspondante à l'identifiant.
	 */
	public ITache getTache(Long id);

	/**
	 * Supprime la tâche d'identidiant id .
	 * @param id l'identifiant de la tâche.
	 * @return le bon déroulement de la suppression.
	 */
	public Boolean supprimerTache(Long id);

	/**
	 * Récupère toutes les tâches du projet courant.
	 * @param l toute la liste de tâches existantes.
	 * @return la liste de tâche
	 */
	public List<ITache> getAllTaches(List<ITache> l);

	/**
	 * Récupère le sous projet d'identifiant id.
	 * @param id identifiant du projet d'identifiant id.
	 * @return le projet d'identifiant id.
	 */
	public IProjet getSousProjet(Long id);

	/**
	 * Récupère tous les sous projets du projet courant.
	 * @param projets toute la liste de projets existants.
	 */
	public void getSousProjets(List<IProjet> projets);

	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	
	public List<ITache> getTaches();

	public String getNom();

	public String getNotes();

	public Long getId();
	
	public Long getIdPere();

	public String getContexte();

	public List<IProjet> getSousProjets();

	public Long getIdUtilisateur();

	public void setTaches(List<ITache> taches);

	public void setNom(String nom);

	public void setNotes(String notes);

	public void setContexte(String contexte);

	public void setId(Long id);

	public void setSousProjets(List<IProjet> sousProjets);

	public void setIdPere(Long idPere);

	public void setIdUtilisateur(Long idUtilisateur);
}