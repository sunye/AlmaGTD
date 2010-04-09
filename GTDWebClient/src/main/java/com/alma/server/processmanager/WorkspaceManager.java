package com.alma.server.processmanager;

import com.alma.server.types.Session;

/**
 * Cette interface permet de creer et supprimer des workspaces utilisateurs. Chaque utilisateur connecte possede un workspace.
 */
public interface WorkspaceManager {
	
	/**
	 * Creer un workspace.
	 * @param session - l'utilisateur pour lequel on cree un workspace.
	 * @return 
	 */
	public boolean createWorkspace(Session session);
	
	/**
	 * Supprime un workspace.
	 * @param session - l'utilisateur dont le workspace doit etre supprimer.
	 * @return
	 */
	public boolean deleteWorkspace(Session session);
}
