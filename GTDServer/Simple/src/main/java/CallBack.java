import java.rmi.Remote;
import java.rmi.RemoteException;


public interface CallBack<T> extends Remote {
	void onSucces(T result) throws RemoteException ;
	void onFailure(Exception e) throws RemoteException ;
}
