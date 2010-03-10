import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

//java -Djava.security.policy=mypolicy -Djava.rmi.server.codebase=file:///Users/Stephane/Documents/Workspace/Serveur/bin/ MainServeur

public class MainServeur {
	
	public static void main(String[] args) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager (new RMISecurityManager());
		}
		
		try {
			ServeurRMI serveur = new ServeurRMIImpl();
			Naming.rebind("ServeurRMI", serveur);
			System.out.println("Serveur RMI enregistre");
		} catch(RemoteException r) {
			r.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
