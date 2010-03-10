import java.rmi.RemoteException;


public class CallBackTelecharger implements CallBack<Idee> {
	
	@Override
	public void onFailure(Exception e) throws RemoteException {
		System.out.println("Exception : " + e.getMessage());
	}

	@Override
	public void onSucces(Idee result) throws RemoteException {
		System.out.println("Resultat du serveur : " + result);
	}
}
