import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public final class ClientRMI {
	
	private ServeurRMI serveur;

	private String identification;
	
	private String username;
	
	public void connect() {
		try {
			System.out.println("Connexion au serveur");
			this.serveur = (ServeurRMI) Naming.lookup("rmi://" + "localhost" + "/ServeurRMI");
		} catch(RemoteException r) {
			r.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public void login(final String username, final String password) {
		this.username = username;
		CallBackLogin callback = new CallBackLogin();
		this.identification = callback.getIdentification();
		
		try {
			UnicastRemoteObject.exportObject(callback);
			this.serveur.login(username, password, callback);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		
	}
	
	public void disconnect() {
		CallBackDisconnect callback = new CallBackDisconnect();
		
		try {
			UnicastRemoteObject.exportObject(callback);
			this.serveur.disconnect(this.identification, callback);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
	public void telecharger() {
		CallBackTelecharger callback = new CallBackTelecharger();
		
		try {
			UnicastRemoteObject.exportObject(callback);
			this.serveur.telecharger(username, identification, callback);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
}
