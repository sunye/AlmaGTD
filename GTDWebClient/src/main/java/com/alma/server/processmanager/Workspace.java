package com.alma.server.processmanager;

import com.alma.server.WebServerManager;
import com.alma.server.types.DataManagerResult;
import com.alma.server.types.Session;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;


public class Workspace implements ProcessManager {
	
	public IContexte createContext(Session session, IContexte context) throws Exception {
		return WebServerManager.getToDataManager().createContext(session, context);
	}

	public IIdee createIdea(Session session, IIdee idea) throws Exception {
		return WebServerManager.getToDataManager().createIdea(session, idea);
	}

	public ITag createNote(Session session, ITag note) throws Exception {
		return WebServerManager.getToDataManager().createNote(session, note);
	}

	public IProjet createProject(Session session, IProjet project) throws Exception {
		return WebServerManager.getToDataManager().createProject(session, project);
	}

	public ITache createTask(Session session, ITache task) throws Exception {
		return WebServerManager.getToDataManager().createTask(session, task);
	}

	public String delContext(Session session, IContexte context) throws Exception {
		return WebServerManager.getToDataManager().delContext(session, context);
	}

	public String delIdea(Session session, IIdee idea) throws Exception {
		return WebServerManager.getToDataManager().delIdea(session, idea);
	}

	public String delNote(Session session, ITag note) throws Exception {
		return WebServerManager.getToDataManager().delNote(session, note);
	}

	public String delProject(Session session, IProjet project) throws Exception {
		return WebServerManager.getToDataManager().delProject(session, project);
	}

	public String delTask(Session session, ITache task) throws Exception {
		return WebServerManager.getToDataManager().delTask(session, task);
	}

	public DataManagerResult<IContexte> getContexts(Session session) throws Exception {
		return WebServerManager.getToDataManager().getContexts(session);
	}

	public DataManagerResult<IIdee> getIdeas(Session session) throws Exception {
		return WebServerManager.getToDataManager().getIdeas(session);
	}

	public DataManagerResult<ITag> getNotes(Session session) throws Exception {
		return WebServerManager.getToDataManager().getNotes(session);
	}

	public DataManagerResult<IProjet> getProjects(Session session) throws Exception {
		return WebServerManager.getToDataManager().getProjects(session);
	}

	public DataManagerResult<ITache> getTasks(Session session) throws Exception {
		return WebServerManager.getToDataManager().getTasks(session);
	}

	public IContexte updateContext(Session session, IContexte context) throws Exception {
		return WebServerManager.getToDataManager().updateContext(session, context);
	}

	public ITag updateNote(Session session, ITag note) throws Exception {
		return WebServerManager.getToDataManager().updateNote(session, note);
	}

	public IProjet updateProject(Session session, IProjet project) throws Exception {
		return WebServerManager.getToDataManager().updateProject(session, project);
	}

	public ITache updateTask(Session session, ITache task) throws Exception {
		return WebServerManager.getToDataManager().updateTask(session, task);
	}

	public IIdee updateIdea(Session session, IIdee idea) throws Exception {
		return WebServerManager.getToDataManager().updateIdea(session, idea);
	}
}
