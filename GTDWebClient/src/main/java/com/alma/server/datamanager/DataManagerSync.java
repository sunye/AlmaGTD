package com.alma.server.datamanager;

import com.alma.server.types.DataManagerResult;
import com.alma.server.types.Session;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;

/**
 * Permet la lecture des donnees utilisateurs en tenant compte de la synchronisation entre les deux serveurs.
 */
public interface DataManagerSync {
	
	/**
	 * @param session - Session de l'utilisateur
	 * @return la liste de taches de l'utilisateur (serveur GTD & ToodleDo).
	 * @throws Exception - Erreur RMI, erreur session.
	 */
	public DataManagerResult<ITache> getTasks(Session session) throws Exception;
	
	/**
	 * @param session - Session de l'utilisateur
	 * @return la liste de notes de l'utilisateur (serveur GTD & ToodleDo).
	 * @throws Exception - Erreur RMI, erreur session.
	 */
	public DataManagerResult<ITag> getNotes(Session session) throws Exception;

	/**
	 * @param session - Session de l'utilisateur
	 * @return la liste de projets de l'utilisateur (serveur GTD & ToodleDo).
	 * @throws Exception - Erreur RMI, erreur session.
	 */
	public DataManagerResult<IProjet> getProjects(Session session) throws Exception;

	/**
	 * @param session - Session de l'utilisateur
	 * @return la liste de contextes de l'utilisateur (serveur GTD & ToodleDo).
	 * @throws Exception - Erreur RMI, erreur session.
	 */
	public DataManagerResult<IContexte> getContexts(Session session) throws Exception;

	/**
	 * @param session - Session de l'utilisateur
	 * @return la liste d'idees de l'utilisateur (serveur GTD & ToodleDo).
	 * @throws Exception - Erreur RMI, erreur session.
	 */
	public DataManagerResult<IIdee> getIdeas(Session session) throws Exception;
}
