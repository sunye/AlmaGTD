import java.rmi.RemoteException;


public class CallBackDisconnect implements CallBack<String> {
	
	@Override
	public void onFailure(Exception e) throws RemoteException {
		System.out.println("Exception : " + e.getMessage());
	}

	@Override
	public void onSucces(String result) throws RemoteException {
		System.out.println("Resultat du serveur : " + result);
	}
}
