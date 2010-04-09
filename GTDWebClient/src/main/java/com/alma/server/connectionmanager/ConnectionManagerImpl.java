package com.alma.server.connectionmanager;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;

import com.alma.server.WebServerManager;
import com.alma.server.types.Session;

public class ConnectionManagerImpl implements ToConnectionManager {

	private static ConnectionManagerImpl instance = null;
	private final HashMap<String, Session> listSessions;
		
	private ConnectionManagerImpl() throws MalformedURLException, RemoteException, NotBoundException {		
		listSessions = new HashMap<String, Session>();
	}
	
	public static ConnectionManagerImpl getInstance() throws MalformedURLException, RemoteException, NotBoundException {		
		if(instance!=null){
			instance = new ConnectionManagerImpl();
		}			
		return instance;
	}
	
	@Override
	public String connect(final String loginGTD, final String passGTD, final String loginTdoo, final String passTDo) throws Exception {	
		final Session newSession = new Session(loginGTD, passGTD, loginTdoo, passTDo);
		listSessions.put(newSession.getIdSession(), newSession);		
		return WebServerManager.getConnectToServer().connect(newSession);		
	}

	@Override
	public String createAccount(final String login, final String password, final String nickname) throws Exception {
		return WebServerManager.getConnectToServer().createAccount(login, password, nickname);
	}

	@Override
	public String delAccount(String login, String password, Session session) throws Exception {
		return WebServerManager.getConnectToServer().deleteAccount(login, password, session);
	}

	@Override
	public String disconnect(Session session) throws Exception {		
		return WebServerManager.getConnectToServer().disConnect(session);		
	}

	@Override
	public String updateAccountPassword(String oldPassword, String newPassword, Session session) throws Exception {
		return WebServerManager.getConnectToServer().updateAccountPassword(oldPassword, newPassword, session);
	}

	public String updateAccountPseudo(String oldNickname, String newNickname, Session session) throws Exception {
		return WebServerManager.getConnectToServer().updateAccountPseudo(oldNickname, newNickname, session);
	}

	public HashMap<String, Session> getListSessions() {
		return listSessions;
	}
}
