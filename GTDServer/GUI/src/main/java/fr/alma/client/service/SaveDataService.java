package fr.alma.client.service;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("saveData")
public interface SaveDataService extends RemoteService {
	void saveStock(String id, String type, String cle, String valeur);
	
	void saveStock(String id, String type, String cle, Date valeur);
}
