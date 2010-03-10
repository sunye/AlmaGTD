package com.alma.server.datamanager;

import java.util.List;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;

import com.alma.server.types.Session;

/**
 * Permet la lecture des donnees utilisateurs sans tenir compte de la synchronisation entre les deux serveurs.
 */
public interface DataManagerNoSync {
	
	/**
	 * @param session - Session de l'utilisateur.
	 * @return La liste de contextes.
	 * @throws Exception - Erreur RMI, erreur session.
	 */
	public List<IContexte> getContexts(Session session) throws Exception;

    /**
     * @param session - Session de l'utilisateur.
     * @return La liste d'idees.
     * @throws Exception - Erreur RMI, erreur session.
     */
    public List<IIdee> getIdeas(Session session) throws Exception;

    /**
     * @param session - Session de l'utilisateur.
     * @return La liste de notes.
     * @throws Exception - Erreur RMI, erreur session.
     */
    public List<ITag> getNotes(Session session) throws Exception;

    /**
     * @param session - Session de l'utilisateur.
     * @return La liste de projets.
     * @throws Exception - Erreur RMI, erreur session.
     */
    public List<IProjet> getProjects(Session session) throws Exception;

    /**
     * @param session - Session de l'utilisateur.
     * @return La liste de taches.
     * @throws Exception - Erreur RMI, erreur session.
     */
    public List<ITache> getTasks(Session session) throws Exception;
}
