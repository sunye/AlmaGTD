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
	
	public String connect(final Session  session) throws Exception {
		//TODO GTD Connect commenté
		return /*serverGTD.connect(session) + "\n" +*/ serverToodleDo.connect(session);
	}

	public String createAccount(final String login, final String password, final String nickname)	throws Exception {
		return serverGTD.createAccount(login, password, nickname) + "\n" + serverToodleDo.createAccount(login, password, nickname);
	}

	public String deleteAccount(final String login, final String password, final Session session)	throws Exception {
		return serverGTD.deleteAccount(login, password, session) + "\n" + serverToodleDo.deleteAccount(login, password, session);
	}

	public String disConnect(final Session session) throws Exception {
		return this.serverGTD.disConnect(session) + "\n" + this.serverToodleDo.disConnect(session);
	}

	public String updateAccountPassword(final String oldPassword, final String newPassword, final Session session) throws Exception {	
		this.serverToodleDo.updateAccountPassword(oldPassword, newPassword, session);
		return this.serverGTD.updateAccountPassword(oldPassword, newPassword, session);
	}

	public String updateAccountPseudo(final String oldNickname, final String newNickname, final Session session) throws Exception {
		this.serverToodleDo.updateAccountPseudo(oldNickname, newNickname, session);
		return this.serverGTD.updateAccountPseudo(oldNickname, newNickname, session);
	}

	public IContexte createContext(final Session session, final IContexte context) throws Exception {
		this.serverToodleDo.createContext(session, context);
		return this.serverGTD.createContext(session, context);
	}

	public IIdee createIdea(final Session session, final IIdee idea) throws Exception {
		this.serverToodleDo.createIdea(session, idea);
		return this.serverGTD.createIdea(session, idea);
	}

	public ITag createNote(final Session session, final ITag note) throws Exception {
		this.serverToodleDo.createNote(session, note);
		return this.serverGTD.createNote(session, note);
	}

	public IProjet createProject(final Session session, final IProjet project) throws Exception {
		this.serverToodleDo.createProject(session, project);		
		return  this.serverGTD.createProject(session, project);
	}

	public ITache createTask(final Session session, final ITache task) throws Exception {
		this.serverToodleDo.createTask(session, task);
		return this.serverGTD.createTask(session, task);
	}

	public String delContext(final Session session, final IContexte context) throws Exception {
		this.serverToodleDo.delContext(session, context);
		return this.serverGTD.delContext(session, context);
	}

	public String delIdea(final Session session, final IIdee idea) throws Exception {
		this.serverToodleDo.delIdea(session, idea);
		return this.serverGTD.delIdea(session, idea);
	}

	public String delNote(final Session session, final ITag note) throws Exception {
		this.serverToodleDo.delNote(session, note);
		return this.serverGTD.delNote(session, note);
	}

	public String delProject(final Session session, final IProjet project) throws Exception {
		this.serverToodleDo.delProject(session, project);
		return this.serverGTD.delProject(session, project);
	}

	public String delTask(final Session session, final ITache task) throws Exception {
		this.serverToodleDo.delTask(session, task);
		return  this.serverGTD.delTask(session, task);	
	}

	public IContexte updateContext(final Session session, final IContexte context) throws Exception {
		this.serverToodleDo.updateContext(session, context);
		return this.serverGTD.updateContext(session, context);
	}

	public IIdee updateIdea(final Session session, final IIdee idea) throws Exception {
		this.serverToodleDo.updateIdea(session, idea);
		return this.serverGTD.updateIdea(session, idea);
	}

	public ITag updateNote(final Session session, final ITag note) throws Exception {
		this.serverToodleDo.updateNote(session, note);
		return this.serverGTD.updateNote(session, note);
	}

	public IProjet updateProject(final Session session, final IProjet project) throws Exception {
		this.serverToodleDo.updateProject(session, project);
		return this.serverGTD.updateProject(session, project);
	}

	public ITache updateTask(final Session session, final ITache task) throws Exception {
		this.serverToodleDo.updateTask(session, task);
		return this.serverGTD.updateTask(session, task);
	}

	public DataManagerResult<IContexte> getContexts(final Session session) throws Exception {
		final DataManagerResult<IContexte> dmr = new DataManagerResult<IContexte>();
		dmr.setToodleDoList(this.serverToodleDo.getContexts(session));
		dmr.setGTDList(this.serverGTD.getContexts(session));
		return dmr;
	}

	public DataManagerResult<IIdee> getIdeas(final Session session) throws Exception {
		final DataManagerResult<IIdee> dmr = new DataManagerResult<IIdee>();
		dmr.setToodleDoList(this.serverToodleDo.getIdeas(session));
		dmr.setGTDList(this.serverGTD.getIdeas(session));
		return dmr;
	}

	public DataManagerResult<ITag> getNotes(final Session session) throws Exception {
		final DataManagerResult<ITag> dmr = new DataManagerResult<ITag>();
		dmr.setToodleDoList(this.serverToodleDo.getNotes(session));
		dmr.setGTDList(this.serverGTD.getNotes(session));
		return dmr;
	}

	public DataManagerResult<IProjet> getProjects(final Session session) throws Exception {
		final DataManagerResult<IProjet> dmr = new DataManagerResult<IProjet>();
		dmr.setToodleDoList(this.serverToodleDo.getProjects(session));
		//dmr.setGTDList(this.serverGTD.getProjects(session));
		return dmr;
	}

	public DataManagerResult<ITache> getTasks(final Session session) throws Exception {
		final DataManagerResult<ITache> dmr = new DataManagerResult<ITache>();
		dmr.setToodleDoList(this.serverToodleDo.getTasks(session));
		//dmr.setGTDList(this.serverGTD.getTasks(session));
		return dmr;
	}


	public Server getServerGTD() {
		return serverGTD;
	}


	public void setServerGTD(final Server serverGTD) {
		this.serverGTD = serverGTD;
	}


	public Server getServerToodleDo() {
		return serverToodleDo;
	}


	public void setServerToodleDo(final Server serverToodleDo) {
		this.serverToodleDo = serverToodleDo;
	}
}
