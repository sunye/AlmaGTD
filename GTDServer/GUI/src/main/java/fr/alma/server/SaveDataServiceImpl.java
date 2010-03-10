package fr.alma.server;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.alma.client.service.SaveDataService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SaveDataServiceImpl extends RemoteServiceServlet implements
SaveDataService {

	@Override
	public void saveStock(String id, String type, String cle, String valeur) {
		System.out.println("donnees sauvegardees : ");
		System.out.println("id : "+id);
		System.out.println("type : "+type);
		System.out.println("cle : "+cle);
		System.out.println("valeur : "+valeur);		
	}

	@Override
	public void saveStock(String id, String type, String cle, Date valeur) {
		System.out.println("donnees sauvegardees : ");
		System.out.println("id : "+id);
		System.out.println("type : "+type);
		System.out.println("cle : "+cle);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		System.out.println("date : "+dateFormat.format(valeur));	
		
	}
	
}
