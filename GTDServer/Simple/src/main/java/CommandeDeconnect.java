import java.rmi.RemoteException;


public final class CommandeDeconnect implements Commande {
	
	private final String identification;
	
	private final CallBack<String> callback;
	
	public CommandeDeconnect(String i, CallBack<String> c) {
		super();
		this.identification = i;
		this.callback = c;
	}

	@Override
	public void execute() {
		try {
			if (Acceptor.getInstance().retire(identification)) {
				this.callback.onSucces("Deconnected");
			} else {
				this.callback.onFailure(new Exception(">>Not connected"));
			}
		} catch(RemoteException r) {
			r.printStackTrace();
		}
	}
}
