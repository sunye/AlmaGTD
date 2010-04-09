package com.alma.server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


import com.alma.client.IWebServer;
import com.alma.server.connectionmanager.ToConnectionManager;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.alma.server.types.*;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class IWebServerImpl extends RemoteServiceServlet implements
IWebServer, ToConnectionManager {
	
	private final HashMap<String,Session> activeSession = new HashMap<String,Session>();
	
	
	public String greetServer(final String input) {
		final String serverInfo = getServletContext().getServerInfo();
		final String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/*-----------------------------------ToConnectionManager----------------------------------*/
	
	@Override
	public String connect(final String loginGTD, final String passGTD, final String loginTdoo,
			final String passTDo) throws Exception {
				
		final Session session1 = new Session(loginGTD,passGTD,loginTdoo,passTDo);
		
		activeSession.put(loginTdoo, session1);
				
		return WebServerManager.getConnectToServer().connect(session1);
	}

	@Override
	public String createAccount(final String login, final String password, final String nickname)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delAccount(final String login, final String password, final Session idSession)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disconnect(final Session session) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateAccountPassword(final String oldPassword, final String newPassword,
			final Session session) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateAccountPseudo(final String oldNickname, final String newNickname,
			final Session session) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*-----------------------------------------Task and projects--------------------------------------*/
	public final List<com.alma.client.serializables.Project> getProjects(String loginTdoo) throws Exception{
					
		DataManagerResult<fr.alma.gtd.donneespartagees.IProjet> dataResult;
		
		dataResult = WebServerManager.getToDataManager().getProjects(activeSession.get(loginTdoo));
				
		final LinkedList<com.alma.client.serializables.Project> result = new LinkedList<com.alma.client.serializables.Project> ();
					
		for(fr.alma.gtd.donneespartagees.IProjet proj : dataResult.getToodleDoList()){
			System.out.println(proj.getNom()+":"+proj.getListeDeTaches().size()+" taches.");
			result.add(Converter.getServerType(proj));
		}
			
		return result;
		
	}
	
	public final  List<com.alma.client.serializables.Task> getTasks(String loginTdoo) throws Exception{
		
		DataManagerResult<fr.alma.gtd.donneespartagees.ITache> dataResult = null;
		
		final  LinkedList<com.alma.client.serializables.Task> result = new LinkedList<com.alma.client.serializables.Task> ();
				
		dataResult = WebServerManager.getToDataManager().getTasks(activeSession.get(loginTdoo));
								
		for(fr.alma.gtd.donneespartagees.ITache task : dataResult.getToodleDoList()){
			result.add(Converter.getServerType(task));
		}		
		
		return result;
		
	}
	
	
	public final  fr.alma.gtd.donneespartagees.ITache  updateTasks(String loginTdoo,com.alma.client.serializables.Task task) throws Exception{
		
		return WebServerManager.getToDataManager().updateTask(activeSession.get(loginTdoo),Converter.getClientType(task));	
	}
	
	
}
