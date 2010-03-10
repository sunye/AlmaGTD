package fr.alma.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("connexion")
public interface ConnexionService extends RemoteService {

	boolean connectAdmin(String value, String value2);
	

}
