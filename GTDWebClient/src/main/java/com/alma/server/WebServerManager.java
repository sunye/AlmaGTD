package com.alma.server;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import com.alma.server.connectionmanager.ConnectionManagerImpl;
import com.alma.server.connectionmanager.ToConnectionManager;
import com.alma.server.datamanager.ConnectToServer;
import com.alma.server.datamanager.DataManagerImpl;
import com.alma.server.datamanager.ToDataManager;
import com.alma.server.processmanager.ProcessManagerImpl;
import com.alma.server.processmanager.ToProcessManager;

/**
 * Permet d'obtenir les instances des différents composants.
 */
public class WebServerManager {
	
	private static ToDataManager toDataManager;
	
	private static ConnectToServer connectToServer;
	
	private static ToProcessManager toProcessManager;
	
	private static ToConnectionManager toConnectManager;
	
	static {
		try {
			WebServerManager.toDataManager = DataManagerImpl.getDataManagerInstance();
			WebServerManager.connectToServer = DataManagerImpl.getConnectToServerInstance();
			WebServerManager.toProcessManager = ProcessManagerImpl.getInstance();
			WebServerManager.toConnectManager = ConnectionManagerImpl.getInstance();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ToDataManager getToDataManager() {
		return WebServerManager.toDataManager;
	}
	
	public static ConnectToServer getConnectToServer() {
		return WebServerManager.connectToServer;
	}
	
	public static ToProcessManager getProcessManager() {
		return WebServerManager.toProcessManager;
	}
	
	public static ToConnectionManager getToConnectionManager() {
		return WebServerManager.toConnectManager;
	}
}
