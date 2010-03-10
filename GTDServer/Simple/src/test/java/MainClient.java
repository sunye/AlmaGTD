import java.rmi.RMISecurityManager;

//java -Djava.security.policy=mypolicy -Djava.rmi.server.codebase=file:///Users/Stephane/Documents/Workspace/Serveur/bin/ MainClient

public class MainClient {
	
	public static void main(String args[]) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		
		ClientRMI c = new ClientRMI();
		c.connect();
		
		c.login("stephane", "stephanepassword");
		c.telecharger();
		c.disconnect();
		
		c.login("alex", "alexpassword");
		c.telecharger();
		c.disconnect();
	}
	
}
