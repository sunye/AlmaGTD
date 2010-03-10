package com.alma.server;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.loststone.toodledo.util.AuthToken;

import com.alma.client.IWebServer;
import com.alma.server.connectionmanager.ToConnectionManager;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import com.alma.server.types.*;

import fr.alma.gtd.donneespartagees.ITache;


/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class IWebServerImpl extends RemoteServiceServlet implements
IWebServer, ToConnectionManager {
	
	private HashMap<String,Session> activeSession = new HashMap<String,Session>();
	
	
	public String greetServer(String input) {
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");
		return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
	}

	/*-----------------------------------ToConnectionManager----------------------------------*/
	
	@Override
	public String connect(String loginGTD, String passGTD, String loginTdoo,
			String passTDo) throws Exception {
				
		Session s1 = new Session(loginGTD,passGTD,loginTdoo,passTDo);
		
		activeSession.put(loginTdoo, s1);
				
		return WebServerManager.getConnectToServer().connect(s1);
	}

	@Override
	public String createAccount(String login, String password, String nickname)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delAccount(String login, String password, Session idSession)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String disconnect(Session session) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateAccountPassword(String oldPassword, String newPassword,
			Session session) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateAccountPseudo(String oldNickname, String newNickname,
			Session session) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/*-----------------------------------------Task and projects--------------------------------------*/
	public List<com.alma.client.serializables.Project> getProjects(String loginTdoo) throws Exception{
					
		DataManagerResult<fr.alma.gtd.donneespartagees.IProjet> dataResult;
		
		dataResult = WebServerManager.getToDataManager().getProjects(activeSession.get(loginTdoo));
				
		LinkedList<com.alma.client.serializables.Project> result = new LinkedList<com.alma.client.serializables.Project> ();
					
		for(fr.alma.gtd.donneespartagees.IProjet proj : dataResult.getToodleDoList()){
			System.out.println(proj.getNom()+":"+proj.getListeDeTaches().size()+" taches.");
			result.add(Converter.getServerType(proj));
		}
			
		return result;
		
	}
	
	public List<com.alma.client.serializables.Task> getTasks(String loginTdoo) throws Exception{
		
		DataManagerResult<fr.alma.gtd.donneespartagees.ITache> dataResult = null;
		
		LinkedList<com.alma.client.serializables.Task> result = new LinkedList<com.alma.client.serializables.Task> ();
				
		dataResult = WebServerManager.getToDataManager().getTasks(activeSession.get(loginTdoo));
								
		for(fr.alma.gtd.donneespartagees.ITache task : dataResult.getToodleDoList()){
			result.add(Converter.getServerType(task));
		}		
		
		return result;
		
	}
	
	
	public fr.alma.gtd.donneespartagees.ITache  updateTasks(String loginTdoo,com.alma.client.serializables.Task task) throws Exception{
		
		return WebServerManager.getToDataManager().updateTask(activeSession.get(loginTdoo),Converter.getClientType(task));	
	}
	
	
}
