package com.alma.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import com.alma.client.serializables.Project;
import com.alma.client.serializables.Task;
/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface IWebServerAsync {
	void greetServer(String input, AsyncCallback<String> callback);
	
	/*-----------------------ToConnectionManager-----------------------------*/
	void connect(String loginGTD, String passGTD, String loginTdoo,String passTDo, AsyncCallback<String> callback) throws Exception;

	void getProjects(String loginTdoo, AsyncCallback<List<Project>> asyncCallback) throws Exception;
	void getTasks(String loginTdoo, AsyncCallback<List<Task>> asyncCallback) throws Exception;
}
