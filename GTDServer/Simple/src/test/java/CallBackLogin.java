import java.rmi.RemoteException;


public class CallBackLogin implements CallBack<String> {
	
	private String identification;
	
	@Override
	public void onFailure(Exception e) throws RemoteException {
		System.out.println("Exception : " + e.getMessage());
	}

	@Override
	public void onSucces(String result) throws RemoteException {
		System.out.println("Resultat du serveur : " + result);
		this.identification = result;
	}

	public String getIdentification() {
		return this.identification;
	}
	
}
