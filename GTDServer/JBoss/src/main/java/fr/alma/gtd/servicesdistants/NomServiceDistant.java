package fr.alma.gtd.servicesdistants;

/**
 * Identifiant du service distant appelle.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public enum NomServiceDistant {
	
	/**
	 * Creer un compte.
	 */
	CREER_COMPTE,
	
	/**
	 * Creer un contexte.
	 */
	CREER_CONTEXTE,
	
	/**
	 * Creer une idee.
	 */
	CREER_IDEE,
	
	/**
	 * Creer un proje.
	 */
	CREER_PROJET,
	
	/**
	 * Creer une tache.
	 */
	CREER_TACHE,
	
	/**
	 * Creer un tag.
	 */
	CREER_TAG,
	
	/**
	 * Se deconnecter.
	 */
	DISCONNECT,
	
	/**
	 * Envoyer un contexte.
	 */
	ENVOYER_CONTEXTE,
	
	/**
	 * Envoyer une idee.
	 */
	ENVOYER_IDEE,
	
	/**
	 * Envoyer un projet.
	 */
	ENVOYER_PROJET,
	
	/**
	 * Envoyer une tache.
	 */
	ENVOYER_TACHE,
	
	/**
	 * Envoyer un tag.
	 */
	ENVOYER_TAG,
	
	/**
	 * S'identifier.
	 */
	LOGIN,
	
	/**
	 * Modifier le mot de passe.
	 */
	MODIFIER_MOT_DE_PASSE,
	
	/**
	 * Modifier le pseudo.
	 */
	MODIFIER_PSEUDO,
	
	/**
	 * Supprimer le compte.
	 */
	SUPPRIMER_COMPTE,
	
	/**
	 * Supprimer le contexte.
	 */
	SUPPRIMER_CONTEXTE,
	
	/**
	 * Supprimer une idee.
	 */
	SUPPRIMER_IDEE,
	
	/**
	 * Supprimer un projet.
	 */
	SUPPRIMER_PROJET,
	
	/**
	 * Supprimer une tache.
	 */
	SUPPRIMER_TACHE,
	
	/**
	 * Supprimer un tag.
	 */
	SUPPRIMER_TAG,
	
	/**
	 * Telecharger le contenu des archives.
	 */
	TELECHARGER_ARCHIVE,
	
	/**
	 * Telecharger le contenu des archives depuis une date donnee.
	 */
	TELECHARGER_ARCHIVE_DATE,
	
	/**
	 * Telecharger le contenu du calendrier.
	 */
	TELECHARGER_CALENDRIER,
	
	/**
	 * Telecharger le contenu du calendrier depuis une date donnee.
	 */
	TELECHARGER_CALENDRIER_DATE,
	
	/**
	 * Telecharger tous les contextes.
	 */
	TELECHARGE_CONTEXTES,
	
	/**
	 * Telecharger toutes les idees.
	 */
	TELECHARGE_IDEES,
	
	/**
	 * Telecharger tout le contenu de l'inbox.
	 */
	TELECHARGE_INBOX,
	
	/**
	 * Telecharger tout le contenu de l'inbox depuis une date donnee.
	 */
	TELECHARGE_INBOX_DATE,
	
	/**
	 * Telecharger tous les participants.
	 */
	TELECHARGE_PARTICIPANTS,
	
	/**
	 * Telecharger tout le contenu de la poubelle.
	 */
	TELECHARGE_POUBELLE,
	
	/**
	 * Telecharger tout le contenu de la poubelle depuis uen date donnee.
	 */
	TELECHARGE_POUBELLE_DATE,
	
	/**
	 * Telecharger la liste des prochaines taches.
	 */
	TELECHARGE_PROCHAINES_TACHES,
	
	/**
	 * Telecharger la liste des prochaines taches depuis une date.
	 */
	TELECHARGE_PROCHAINES_TACHES_DATE,
	
	/**
	 * Telecharger la liste des prochaines taches pour un contexte donne.
	 */
	TELECHARGE_PROCHAINES_TACHES_PAR_CONTEXTE,
	
	/**
	 * Telecharger la liste des prochaines taches pour un contexte donne depuis une date donnee.
	 */
	TELECHARGE_PROCHAINES_TACHES_PAR_CONTEXTE_DATE,
	
	/**
	 * Telecharger tous les projets.
	 */
	TELECHARGE_PROJETS,
	
	/**
	 * Telecharger la liste des prochaines taches pour un tag donne.
	 */
	TELECHARGE_PROCHAINES_TACHES_PAR_TAG,
	
	/**
	 * Telecharger la liste des prochaines taches pour un tag donne depuis une date donnee.
	 */
	TELECHARGE_PROCHAINES_TACHES_PAR_TAG_DATE,
	
	/**
	 * Telecharger toutes les taches.
	 */
	TEKECHARGE_TACHES,
	
	/**
	 * Telecharger toutes les tags.
	 */
	TELECHARGE_TAGS,
	
	/**
	 * Telecharger toutes les contextes de l'utilisateur donne.
	 */
	TELECHARGE_CONTEXTES_ADMIN,
	
	/**
	 * Telecharger toutes les idees de l'utilisateur donne.
	 */
	TELECHARGE_IDEES_ADMIN,
	
	/**
	 * Telecharger tout le log.
	 */
	TELECHARGE_LOG,
	
	/**
	 * Telecharger tout le log depuis une date donnee.
	 */
	TELECHARGE_LOG_DATE,
	
	/**
	 * Telecharger tout le log de l'utilisateur donne.
	 */
	TELECHARGE_LOG_UTILISATEUR_ADMIN,
	
	/**
	 * Telecharger tout le log de l'admin.
	 */
	TELECHARGE_LOG_ADMIN,
	
	/**
	 * Telecharger toutes les projets de l'utilisateur donne.
	 */
	TELECHARGE_PROJETS_ADMIN,
	
	/**
	 * Telecharger toutes les taches de l'utilisateur donne.
	 */
	TELECHARGE_TACHES_ADMIN,
	
	/**
	 * Telecharger toutes les tags de l'utilisateur donne.
	 */
	TELECHARGE_TAGS_ADMIN
}
