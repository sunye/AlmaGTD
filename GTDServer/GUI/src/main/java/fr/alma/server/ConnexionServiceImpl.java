package fr.alma.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.alma.client.service.ConnexionService;

@SuppressWarnings("serial")
public class ConnexionServiceImpl extends RemoteServiceServlet implements
ConnexionService {

	@Override
	public boolean connectAdmin(String value, String value2) {
		if (value.equals("admin") && value2.equals("admin")) {
			return true;
		} else {
			return false;
		}
	}

}
