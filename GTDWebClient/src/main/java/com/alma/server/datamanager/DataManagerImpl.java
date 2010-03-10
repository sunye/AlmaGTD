package com.alma.server.datamanager;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.alma.server.types.DataManagerResult;
import com.alma.server.types.Session;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;



public class DataManagerImpl implements ConnectToServer, ToDataManager{
	
	private static DataManagerImpl instance = null;
	
	private Server serverGTD;
	private Server serverToodleDo;
	
	private DataManagerImpl() throws MalformedURLException, RemoteException, NotBoundException {
		//serverGTD = new ServerGTDImpl(ModeDeMiseAJour.FORCE);
		serverToodleDo  = new ServerToodleDoImp();
	}
	
	
	public static ConnectToServer getConnectToServerInstance() throws MalformedURLException, RemoteException, NotBoundException{
		if(instance == null) {
			instance = new DataManagerImpl();
		}
		return instance;
	}
	
	public static ToDataManager getDataManagerInstance() throws MalformedURLException, RemoteException, NotBoundException{
		if(instance == null) {
			instance = new DataManagerImpl();
		}
		return instance;
	}
	
	public String connect(Session  session) throws Exception {
		//TODO GTD Connect commenté
		return /*serverGTD.connect(session) + "\n" +*/ serverToodleDo.connect(session);
	}

	public String createAccount(String login, String password, String nickname)	throws Exception {
		return serverGTD.createAccount(login, password, nickname) + "\n" + serverToodleDo.createAccount(login, password, nickname);
	}

	public String deleteAccount(String login, String password, Session session)	throws Exception {
		return serverGTD.deleteAccount(login, password, session) + "\n" + serverToodleDo.deleteAccount(login, password, session);
	}

	public String disConnect(Session session) throws Exception {
		return this.serverGTD.disConnect(session) + "\n" + this.serverToodleDo.disConnect(session);
	}

	public String updateAccountPassword(String oldPassword, String newPassword, Session session) throws Exception {	
		this.serverToodleDo.updateAccountPassword(oldPassword, newPassword, session);
		return this.serverGTD.updateAccountPassword(oldPassword, newPassword, session);
	}

	public String updateAccountPseudo(String oldNickname, String newNickname, Session session) throws Exception {
		this.serverToodleDo.updateAccountPseudo(oldNickname, newNickname, session);
		return this.serverGTD.updateAccountPseudo(oldNickname, newNickname, session);
	}

	public IContexte createContext(Session session, IContexte context) throws Exception {
		this.serverToodleDo.createContext(session, context);
		return this.serverGTD.createContext(session, context);
	}

	public IIdee createIdea(Session session, IIdee idea) throws Exception {
		this.serverToodleDo.createIdea(session, idea);
		return this.serverGTD.createIdea(session, idea);
	}

	public ITag createNote(Session session, ITag note) throws Exception {
		this.serverToodleDo.createNote(session, note);
		return this.serverGTD.createNote(session, note);
	}

	public IProjet createProject(Session session, IProjet project) throws Exception {
		this.serverToodleDo.createProject(session, project);		
		return  this.serverGTD.createProject(session, project);
	}

	public ITache createTask(Session session, ITache task) throws Exception {
		this.serverToodleDo.createTask(session, task);
		return this.serverGTD.createTask(session, task);
	}

	public String delContext(Session session, IContexte context) throws Exception {
		this.serverToodleDo.delContext(session, context);
		return this.serverGTD.delContext(session, context);
	}

	public String delIdea(Session session, IIdee idea) throws Exception {
		this.serverToodleDo.delIdea(session, idea);
		return this.serverGTD.delIdea(session, idea);
	}

	public String delNote(Session session, ITag note) throws Exception {
		this.serverToodleDo.delNote(session, note);
		return this.serverGTD.delNote(session, note);
	}

	public String delProject(Session session, IProjet project) throws Exception {
		this.serverToodleDo.delProject(session, project);
		return this.serverGTD.delProject(session, project);
	}

	public String delTask(Session session, ITache task) throws Exception {
		this.serverToodleDo.delTask(session, task);
		return  this.serverGTD.delTask(session, task);	
	}

	public IContexte updateContext(Session session, IContexte context) throws Exception {
		this.serverToodleDo.updateContext(session, context);
		return this.serverGTD.updateContext(session, context);
	}

	public IIdee updateIdea(Session session, IIdee idea) throws Exception {
		this.serverToodleDo.updateIdea(session, idea);
		return this.serverGTD.updateIdea(session, idea);
	}

	public ITag updateNote(Session session, ITag note) throws Exception {
		this.serverToodleDo.updateNote(session, note);
		return this.serverGTD.updateNote(session, note);
	}

	public IProjet updateProject(Session session, IProjet project) throws Exception {
		this.serverToodleDo.updateProject(session, project);
		return this.serverGTD.updateProject(session, project);
	}

	public ITache updateTask(Session session, ITache task) throws Exception {
		this.serverToodleDo.updateTask(session, task);
		return this.serverGTD.updateTask(session, task);
	}

	public DataManagerResult<IContexte> getContexts(Session session) throws Exception {
		DataManagerResult<IContexte> dmr = new DataManagerResult<IContexte>();
		dmr.setToodleDoList(this.serverToodleDo.getContexts(session));
		dmr.setGTDList(this.serverGTD.getContexts(session));
		return dmr;
	}

	public DataManagerResult<IIdee> getIdeas(Session session) throws Exception {
		DataManagerResult<IIdee> dmr = new DataManagerResult<IIdee>();
		dmr.setToodleDoList(this.serverToodleDo.getIdeas(session));
		dmr.setGTDList(this.serverGTD.getIdeas(session));
		return dmr;
	}

	public DataManagerResult<ITag> getNotes(Session session) throws Exception {
		DataManagerResult<ITag> dmr = new DataManagerResult<ITag>();
		dmr.setToodleDoList(this.serverToodleDo.getNotes(session));
		dmr.setGTDList(this.serverGTD.getNotes(session));
		return dmr;
	}

	public DataManagerResult<IProjet> getProjects(Session session) throws Exception {
		DataManagerResult<IProjet> dmr = new DataManagerResult<IProjet>();
		dmr.setToodleDoList(this.serverToodleDo.getProjects(session));
		//dmr.setGTDList(this.serverGTD.getProjects(session));
		return dmr;
	}

	public DataManagerResult<ITache> getTasks(Session session) throws Exception {
		DataManagerResult<ITache> dmr = new DataManagerResult<ITache>();
		dmr.setToodleDoList(this.serverToodleDo.getTasks(session));
		//dmr.setGTDList(this.serverGTD.getTasks(session));
		return dmr;
	}
}
