package fr.alma.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ConnexionServiceAsync {

	void connectAdmin(String value, String value2,
			AsyncCallback<Boolean> asyncCallback);

}
