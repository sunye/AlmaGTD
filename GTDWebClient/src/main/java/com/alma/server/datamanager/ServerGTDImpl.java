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

		public void onFailure(final Exception exception) {
			this.exception = exception;
			ServerGTDImpl.this.sync();
		}

		public void onSucces(final T value) {
			this.value  = value;
			ServerGTDImpl.this.sync();
		}

		public Exception getException() {
			return exception;
		}

		public T getValue() {
			return value;
		}

		public void setValue(final T value) {
			this.value = value;
		}

		public void setException(final Exception exception) {
			this.exception = exception;
		}
	} 
	
	private ModeDeMiseAJour maj;
	
	private ServeurRMI serverGTDRMIAsync;
	
	public ServerGTDImpl(final ModeDeMiseAJour maj) throws MalformedURLException, RemoteException, NotBoundException {
		this.serverGTDRMIAsync = (ServeurRMI) Naming.lookup("rmi://" + /*host +*/ "/ServeurGTD_RMI") ;
		this.maj = maj;
	}
	
	/**
	 * Methode permettant de reveiller le thread attendant le retour du CallBack.
	 */
	private synchronized void sync() {
		this.notify();
	}

	public synchronized String connect(final Session session) throws Exception {
		final CallBackSync<String> callb = new CallBackSync<String>();
		this.serverGTDRMIAsync.login(session.getLoginGTD(), session.getPasswordGTD(), callb);
		/* On attend le retour du CallBack */
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		session.setKeyGTD(callb.getValue());
		return callb.getValue();
	}

	public synchronized String createAccount(final String login, final String password, final String nickname) throws Exception {
		final CallBackSync<String> callb = new CallBackSync<String>();
		this.serverGTDRMIAsync.creerCompte(login, password, nickname, callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}
	public synchronized String deleteAccount(final String login, final String password, final Session session) throws Exception {	
		final CallBackSync<String> callb = new CallBackSync<String>();
		this.serverGTDRMIAsync.supprimerCompte(login, password, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized String updateAccountPassword(final String oldPassword, final String newPassword, final Session session) throws Exception {	
		final CallBackSync<String> callb = new CallBackSync<String>();
		this.serverGTDRMIAsync.modifierMotDePasse(oldPassword, newPassword, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized String updateAccountPseudo(final String oldNickname, final String newNickname, final Session session) throws Exception {	
		final CallBackSync<String> callb = new CallBackSync<String>();
		this.serverGTDRMIAsync.modifierPseudo(newNickname, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized IContexte createContext(final Session session, final IContexte context) throws Exception {
		final CallBackSync<IContexte> callb = new CallBackSync<IContexte>();
		this.serverGTDRMIAsync.creerContexte(context, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized IIdee createIdea(final Session session, final IIdee idea) throws Exception {
		final CallBackSync<IIdee> callb = new CallBackSync<IIdee>();
		this.serverGTDRMIAsync.creerIdee(idea, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized List<IProjet> getProjects(final Session session) throws Exception {
		final CallBackSync<List<IProjet>> callb = new CallBackSync<List<IProjet>>();
		this.serverGTDRMIAsync.telechargeProjets(session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized List<ITache> getTasks(final Session session) throws Exception {
		final CallBackSync<List<ITache>> callb = new CallBackSync<List<ITache>>();
		this.serverGTDRMIAsync.telechargeTaches(session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized String disConnect(final Session session) throws Exception {
		final CallBackSync<String> callb = new CallBackSync<String>();
		this.serverGTDRMIAsync.disconnect(session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized ITag createNote(final Session session, final ITag note) throws Exception {
		final CallBackSync<ITag> callb = new CallBackSync<ITag>();
		this.serverGTDRMIAsync.creerTag(note, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized IProjet createProject(final Session session, final IProjet project) throws Exception {
		final CallBackSync<IProjet> callb = new CallBackSync<IProjet>();
		this.serverGTDRMIAsync.creerProjet(project, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized ITache createTask(final Session session, final ITache task) throws Exception {
		final CallBackSync<ITache> callb = new CallBackSync<ITache>();
		this.serverGTDRMIAsync.creerTache(task, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized String delContext(final Session session, final IContexte context) throws Exception {
		final CallBackSync<String> callb = new CallBackSync<String>();
		this.serverGTDRMIAsync.supprimerContexte(context, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized String delIdea(final Session session, final IIdee idea) throws Exception {
		final CallBackSync<String> callb = new CallBackSync<String>();
		this.serverGTDRMIAsync.supprimerIdee(idea, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized String delNote(final Session session, final ITag note) throws Exception {
		final CallBackSync<String> callb = new CallBackSync<String>();
		this.serverGTDRMIAsync.supprimerTag(note, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized String delProject(final Session session, final IProjet project)	throws Exception {
		final CallBackSync<String> callb = new CallBackSync<String>();
		this.serverGTDRMIAsync.supprimerProjet(project, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public synchronized String delTask(final Session session, final ITache task) throws Exception {
		final CallBackSync<String> callb = new CallBackSync<String>();
		this.serverGTDRMIAsync.supprimerTache(task, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public IContexte updateContext(final Session session, final IContexte context) throws Exception {
		final CallBackSync<IContexte> callb = new CallBackSync<IContexte>();
		this.serverGTDRMIAsync.envoyerContexte(context, this.maj, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public IIdee updateIdea(final Session session, final IIdee idea) throws Exception {
		final CallBackSync<IIdee> callb = new CallBackSync<IIdee>();
		this.serverGTDRMIAsync.envoyerIdee(idea, this.maj, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public ITag updateNote(final Session session, final ITag note) throws Exception {
		final CallBackSync<ITag> callb = new CallBackSync<ITag>();
		this.serverGTDRMIAsync.envoyerTag(note, this.maj, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public IProjet updateProject(final Session session, final IProjet project) throws Exception {
		final CallBackSync<IProjet> callb = new CallBackSync<IProjet>();
		this.serverGTDRMIAsync.envoyerProjet(project, this.maj, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public ITache updateTask(final Session session, final ITache task) throws Exception {
		final CallBackSync<ITache> callb = new CallBackSync<ITache>();
		this.serverGTDRMIAsync.envoyerTache(task, this.maj, session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public List<IContexte> getContexts(final Session session) throws Exception {
		final CallBackSync<List<IContexte>> callb = new CallBackSync<List<IContexte>>();
		this.serverGTDRMIAsync.telechargeContextes(session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public List<IIdee> getIdeas(final Session session) throws Exception {
		final CallBackSync<List<IIdee>> callb = new CallBackSync<List<IIdee>>();
		this.serverGTDRMIAsync.telechargeIdees(session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public List<ITag> getNotes(final Session session) throws Exception {
		final CallBackSync<List<ITag>> callb = new CallBackSync<List<ITag>>();
		this.serverGTDRMIAsync.telechargeTags(session.getKeyGTD(), callb);
		this.wait();
		if (callb.getException() != null) {
			throw callb.getException();
		}
		return callb.getValue();
	}

	public ModeDeMiseAJour getMaj() {
		return maj;
	}

	public void setMaj(final ModeDeMiseAJour maj) {
		this.maj = maj;
	}

	public ServeurRMI getServerGTDRMIAsync() {
		return serverGTDRMIAsync;
	}

	public void setServerGTDRMIAsync(ServeurRMI serverGTDRMIAsync) {
		this.serverGTDRMIAsync = serverGTDRMIAsync;
	}
}

