package com.alma.server.datamanager;

import java.io.IOException;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;

import com.alma.server.types.Session;

/**
 * Permet de realiser les operations basiques sur les differents type de donnees (Create, Update, Delete).
 */
public interface DataManager {

	/**
	 * Creer une tache.
	 * @param session - l'utilisateur auquel appartient la tache.
	 * @param task - la tache a creer.
	 * @return La tache avec un identifiant affecte par le serveur.
	 * @throws Exception - Erreur RMI, erreur session.
	 */
	public ITache createTask(final Session session, final ITache task) throws IOException;
	
	/**
	 * Modifier une tache.
	 * @param session - l'utilisateur auquel appartient la tache.
	 * @param task - la tache a modifier.
	 * @return La tache modifiee.
	 * @throws Exception - Erreur RMI, erreur session, tache invalide.
	 */
	public ITache updateTask(final Session session, final ITache task) throws IOException;

	/**
	 * Supprimer une tache
	 * @param session - l'utilisateur auquel appartient la tache.
	 * @param task - la tache a supprimer.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur session, tache invalide.
	 */
	public String delTask(Session session, ITache task) throws IOException;

	/**
	 * Creer une note.
	 * @param session - l'utilisateur auquel appartient la note.
	 * @param note - la note a creer.
	 * @return La note avec un identifiant affecte par le serveur.
	 * @throws Exception - Erreur RMI, erreur session.
	 */
	public ITag createNote(Session session, ITag note) throws IOException;
	
	/**
	 * Modifier une note.
	 * @param session - l'utilisateur auquel appartient la note.
	 * @param note - la note a modifier.
	 * @return La note modifiee.
	 * @throws Exception - Erreur RMI, erreur session, note invalide.
	 */
	public ITag updateNote(final Session session, ITag note) throws IOException;
	
	/**
	 * Supprimer une note.
	 * @param session - l'utilisateur auquel appartient la note.
	 * @param note - la note a supprimer.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur session, note invalide.
	 */
	public String delNote(Session session, ITag note) throws IOException;
	
	/**
	 * Creer un projet.
	 * @param session - l'utilisateur auquel appartient le projet.
	 * @param project - le projet a creer.
	 * @return Le projet avec un identifiant affecte par le serveur.
	 * @throws Exception - Erreur RMI, erreur session.
	 */
	public IProjet createProject(Session session, IProjet project) throws IOException;

	/**
	 * Modifier un projet.
	 * @param session - l'utilisateur auquel appartient le projet.
	 * @param project - le projet a modifier.
	 * @return Le projet modifie.
	 * @throws Exception - Erreur RMI, erreur session, projet invalide.
	 */
	public IProjet updateProject(Session session, IProjet project) throws IOException;

	/**
	 * Supprimer un projet.
	 * @param session - l'utilisateur auquel appartient le projet.
	 * @param project - le projet a supprimer.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur session, projet invalide.
	 */
	public String delProject(Session session, IProjet project) throws IOException;

	/**
	 * Creer un contexte.
	 * @param session - l'utilisateur auquel appartient le contexte.
	 * @param context - le contexte a creer.
	 * @return Le contexte avec un identifiant affecte par le serveur.
	 * @throws Exception - Erreur RMI, erreur session.
	 */
	public IContexte createContext(Session session, IContexte context) throws IOException;
	
	/**
	 * Modifier un contexte.
	 * @param session - l'utilisateur auquel appartient le contexte.
	 * @param context - le contexte a modifier.
	 * @return Le contexte modifie.
	 * @throws Exception - Erreur RMI, erreur session, contexte invalide.
	 */
	public IContexte updateContext(Session session, IContexte context) throws IOException;
	
	/**
	 * Supprimer un contexte.
	 * @param session - l'utilisateur auquel appartient le contexte.
	 * @param context - le contexte a supprimer.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur session, contexte invalide.
	 */
	public String delContext(Session session, IContexte context) throws IOException; 
	
	/**
	 * Creer une idee.
	 * @param session - l'utilisateur auquel appartient l'idee.
	 * @param idea - l'idee a creer.
	 * @return L'idee avec un identifiant affecte par le serveur.
	 * @throws Exception - Erreur RMI, erreur session.
	 */
	public IIdee createIdea(Session session, IIdee idea) throws IOException;
	
	/**
	 * Modifier une idee.
	 * @param session - l'utilisateur auquel appartient l'idee.
	 * @param idea - l'idee a modifier.
	 * @return L'idee modifiee.
	 * @throws Exception - Erreur RMI, erreur session, l'idee invalide.
	 */
	public IIdee updateIdea(Session session, IIdee idea) throws IOException;

	/**
	 * Supprimer une idee.
	 * @param session - l'utilisateur auquel appartient l'idee.
	 * @param idea - l'idee a supprimer.
	 * @return Message de confirmation du serveur.
	 * @throws Exception - Erreur RMI, erreur session, l'idee invalide.
	 */
	public String delIdea(Session session, IIdee idea) throws IOException;
}
