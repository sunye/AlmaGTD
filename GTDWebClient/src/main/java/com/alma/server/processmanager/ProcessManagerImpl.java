package com.alma.server.processmanager;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;

import com.alma.server.types.DataManagerResult;
import com.alma.server.types.Session;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;

public class ProcessManagerImpl implements ToProcessManager {
	
	private static ToProcessManager instance = null;
	
	public static ToProcessManager getInstance() throws MalformedURLException, RemoteException, NotBoundException{
		if(instance==null) {
			instance = new ProcessManagerImpl();
		}
		return instance;
	}
	
	private HashMap<String, ProcessManager> workspaces;
	
	private ProcessManagerImpl() {
		this.workspaces = new HashMap<String, ProcessManager>();
	}
	
	public boolean createWorkspace(Session session) {
		if (this.workspaces.containsKey(session.getIdSession())) {
			return false;
		} else {
			this.workspaces.put(session.getIdSession(), new Workspace());
			return true;
		}
	}
	
	public boolean deleteWorkspace(Session session) {
		if (this.workspaces.containsKey(session)) {
			this.workspaces.remove(session);
			return true;
		} else {
			return false;
		}
	}

	public IContexte createContext(Session session, IContexte context) throws Exception {
		return this.workspaces.get(session.getIdSession()).createContext(session, context);
	}

	public IIdee createIdea(Session session, IIdee idea) throws Exception {
		return this.workspaces.get(session.getIdSession()).createIdea(session, idea);
	}

	public ITag createNote(Session session, ITag note) throws Exception {
		return this.workspaces.get(session.getIdSession()).createNote(session, note);
	}

	public IProjet createProject(Session session, IProjet project) throws Exception {
		return this.workspaces.get(session.getIdSession()).createProject(session, project);
	}

	public ITache createTask(Session session, ITache task) throws Exception {
		return this.workspaces.get(session.getIdSession()).createTask(session, task);
	}

	public String delContext(Session session, IContexte context) throws Exception {
		return this.workspaces.get(session.getIdSession()).delContext(session, context);
	}

	public String delIdea(Session session, IIdee idea) throws Exception {
		return this.workspaces.get(session.getIdSession()).delIdea(session, idea);
	}

	public String delNote(Session session, ITag note) throws Exception {
		return this.workspaces.get(session.getIdSession()).delNote(session, note);
	}

	public String delProject(Session session, IProjet project) throws Exception {
		return this.workspaces.get(session.getIdSession()).delProject(session, project);
	}

	public String delTask(Session session, ITache task) throws Exception {
		return this.workspaces.get(session.getIdSession()).delTask(session, task);
	}

	public DataManagerResult<IContexte> getContexts(Session session) throws Exception {
		return this.workspaces.get(session.getIdSession()).getContexts(session);
	}

	public DataManagerResult<IIdee> getIdeas(Session session) throws Exception {
		return this.workspaces.get(session.getIdSession()).getIdeas(session);
	}

	public DataManagerResult<ITag> getNotes(Session session) throws Exception {
		return this.workspaces.get(session.getIdSession()).getNotes(session);
	}

	public DataManagerResult<IProjet> getProjects(Session session) throws Exception {
		return this.workspaces.get(session.getIdSession()).getProjects(session);
	}

	public DataManagerResult<ITache> getTasks(Session session) throws Exception {
		return this.workspaces.get(session.getIdSession()).getTasks(session);
	}
	
	public IContexte updateContext(Session session, IContexte context) throws Exception {
		return this.workspaces.get(session.getIdSession()).updateContext(session, context);
	}

	public ITag updateNote(Session session, ITag note) throws Exception {
		return this.workspaces.get(session.getIdSession()).updateNote(session, note);
	}

	public IProjet updateProject(Session session, IProjet project) throws Exception {
		return this.workspaces.get(session.getIdSession()).updateProject(session, project);
	}

	public ITache updateTask(Session session, ITache task) throws Exception {
		return this.workspaces.get(session.getIdSession()).updateTask(session, task);
	}

	public IIdee updateIdea(Session session, IIdee idea) throws Exception {
		return this.workspaces.get(session.getIdSession()).updateIdea(session, idea);
	}
}
