package com.alma.server.datamanager;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;

import com.alma.server.types.Session;

import fr.alma.gtd.interfacedistante.CallBack;
import fr.alma.gtd.interfacedistante.ModeDeMiseAJour;
import fr.alma.gtd.interfacedistante.ServeurRMI;

public class ServerGTDImpl implements Server {
	
	/**
	 * CallBack permettant de passer d'une communication asynchrone a une communication synchrone.
	 *
	 * @param <T>
	 */
	private class CallBackSync<T> extends UnicastRemoteObject implements CallBack<T>, Remote {
		
		private static final long serialVersionUID = -5869946370897293578L;

		public CallBackSync() throws RemoteException {
			super();
		}

		private T value;
		private Exception exception;

		public void onFailure(Exception e) {
			this.exception = e;
			ServerGTDImpl.this.sync();
		}

		public void onSucces(T t) {
			this.value  = t;
			ServerGTDImpl.this.sync();
		}

		public Exception getException() {
			return exception;
		}

		public T getValue() {
			return value;
		}
	}
	
	private ModeDeMiseAJour maj;
	
	private ServeurRMI serverGTDRMIAsync;
	
	public ServerGTDImpl(ModeDeMiseAJour maj) throws MalformedURLException, RemoteException, NotBoundException {
		this.serverGTDRMIAsync = (ServeurRMI) Naming.lookup("rmi://" + /*host +*/ "/ServeurGTD_RMI") ;
		this.maj = maj;
	}
	
	/**
	 * Methode permettant de reveiller le thread attendant le retour du CallBack.
	 */
	private synchronized void sync() {
		this.notify();
	}

	public synchronized String connect(Session session) throws Exception {
		CallBackSync<String> cb = new CallBackSync<String>();
		this.serverGTDRMIAsync.login(session.getLoginGTD(), session.getPasswordGTD(), cb);
		/* On attend le retour du CallBack */
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		session.setKeyGTD(cb.getValue());
		return cb.getValue();
	}

	public synchronized String createAccount(String login, String password, String nickname) throws Exception {
		CallBackSync<String> cb = new CallBackSync<String>();
		this.serverGTDRMIAsync.creerCompte(login, password, nickname, cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}
	public synchronized String deleteAccount(String login, String password, Session session) throws Exception {	
		CallBackSync<String> cb = new CallBackSync<String>();
		this.serverGTDRMIAsync.supprimerCompte(login, password, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized String updateAccountPassword(String oldPassword, String newPassword, Session session) throws Exception {	
		CallBackSync<String> cb = new CallBackSync<String>();
		this.serverGTDRMIAsync.modifierMotDePasse(oldPassword, newPassword, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized String updateAccountPseudo(String oldNickname, String newNickname, Session session) throws Exception {	
		CallBackSync<String> cb = new CallBackSync<String>();
		this.serverGTDRMIAsync.modifierPseudo(newNickname, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized IContexte createContext(Session session, IContexte context) throws Exception {
		CallBackSync<IContexte> cb = new CallBackSync<IContexte>();
		this.serverGTDRMIAsync.creerContexte(context, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized IIdee createIdea(Session session, IIdee idea) throws Exception {
		CallBackSync<IIdee> cb = new CallBackSync<IIdee>();
		this.serverGTDRMIAsync.creerIdee(idea, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized List<IProjet> getProjects(Session session) throws Exception {
		CallBackSync<List<IProjet>> cb = new CallBackSync<List<IProjet>>();
		this.serverGTDRMIAsync.telechargeProjets(session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized List<ITache> getTasks(Session session) throws Exception {
		CallBackSync<List<ITache>> cb = new CallBackSync<List<ITache>>();
		this.serverGTDRMIAsync.telechargeTaches(session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized String disConnect(Session session) throws Exception {
		CallBackSync<String> cb = new CallBackSync<String>();
		this.serverGTDRMIAsync.disconnect(session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized ITag createNote(Session session, ITag note) throws Exception {
		CallBackSync<ITag> cb = new CallBackSync<ITag>();
		this.serverGTDRMIAsync.creerTag(note, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized IProjet createProject(Session session, IProjet project) throws Exception {
		CallBackSync<IProjet> cb = new CallBackSync<IProjet>();
		this.serverGTDRMIAsync.creerProjet(project, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized ITache createTask(Session session, ITache task) throws Exception {
		CallBackSync<ITache> cb = new CallBackSync<ITache>();
		this.serverGTDRMIAsync.creerTache(task, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized String delContext(Session session, IContexte context) throws Exception {
		CallBackSync<String> cb = new CallBackSync<String>();
		this.serverGTDRMIAsync.supprimerContexte(context, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized String delIdea(Session session, IIdee idea) throws Exception {
		CallBackSync<String> cb = new CallBackSync<String>();
		this.serverGTDRMIAsync.supprimerIdee(idea, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized String delNote(Session session, ITag note) throws Exception {
		CallBackSync<String> cb = new CallBackSync<String>();
		this.serverGTDRMIAsync.supprimerTag(note, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized String delProject(Session session, IProjet project)	throws Exception {
		CallBackSync<String> cb = new CallBackSync<String>();
		this.serverGTDRMIAsync.supprimerProjet(project, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public synchronized String delTask(Session session, ITache task) throws Exception {
		CallBackSync<String> cb = new CallBackSync<String>();
		this.serverGTDRMIAsync.supprimerTache(task, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public IContexte updateContext(Session session, IContexte context) throws Exception {
		CallBackSync<IContexte> cb = new CallBackSync<IContexte>();
		this.serverGTDRMIAsync.envoyerContexte(context, this.maj, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public IIdee updateIdea(Session session, IIdee idea) throws Exception {
		CallBackSync<IIdee> cb = new CallBackSync<IIdee>();
		this.serverGTDRMIAsync.envoyerIdee(idea, this.maj, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public ITag updateNote(Session session, ITag note) throws Exception {
		CallBackSync<ITag> cb = new CallBackSync<ITag>();
		this.serverGTDRMIAsync.envoyerTag(note, this.maj, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public IProjet updateProject(Session session, IProjet project) throws Exception {
		CallBackSync<IProjet> cb = new CallBackSync<IProjet>();
		this.serverGTDRMIAsync.envoyerProjet(project, this.maj, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public ITache updateTask(Session session, ITache task) throws Exception {
		CallBackSync<ITache> cb = new CallBackSync<ITache>();
		this.serverGTDRMIAsync.envoyerTache(task, this.maj, session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public List<IContexte> getContexts(Session session) throws Exception {
		CallBackSync<List<IContexte>> cb = new CallBackSync<List<IContexte>>();
		this.serverGTDRMIAsync.telechargeContextes(session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public List<IIdee> getIdeas(Session session) throws Exception {
		CallBackSync<List<IIdee>> cb = new CallBackSync<List<IIdee>>();
		this.serverGTDRMIAsync.telechargeIdees(session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}

	public List<ITag> getNotes(Session session) throws Exception {
		CallBackSync<List<ITag>> cb = new CallBackSync<List<ITag>>();
		this.serverGTDRMIAsync.telechargeTags(session.getKeyGTD(), cb);
		this.wait();
		if (cb.getException() != null) {
			throw cb.getException();
		}
		return cb.getValue();
	}
}

