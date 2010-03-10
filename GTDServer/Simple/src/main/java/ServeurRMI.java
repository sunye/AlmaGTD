import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServeurRMI extends Remote {
	void login(final String username, final String password, final CallBack<String> callback) throws RemoteException;
	void disconnect(final String identification, final CallBack<String> callback) throws RemoteException;
	void telecharger(final String username, final String identification, final CallBack<Idee> callback) throws RemoteException;
}
