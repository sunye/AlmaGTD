package fr.alma.client.service;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SaveDataServiceAsync {

	void saveStock(String id, String type, String cle, String valeur,
			AsyncCallback<Void> callback);

	void saveStock(String id, String type, String cle, Date valeur,
			AsyncCallback<Void> callback);

}
