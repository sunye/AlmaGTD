package com.alma.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import com.alma.client.serializables.Project;
import com.alma.client.serializables.Task;
import com.alma.server.types.ProjetImpl;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("webserver")
public interface IWebServer extends RemoteService {
	String greetServer(String name);
	
	/*-----------------------ToConnectionManager-----------------------------*/
	String connect(String loginGTD, String passGTD, String loginTdoo,String passTDo) throws Exception;
	List<Project> getProjects(String loginTdoo)	throws Exception;
	List<Task> getTasks(String loginTdoo)	throws Exception;
}
