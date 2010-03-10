import java.rmi.RemoteException;


public final class CommandeTelecharger implements Commande {
	
	private final String username;
	
	private final CallBack<Idee> callback;
	
	public CommandeTelecharger(String u, CallBack<Idee> c) {
		super();
		this.username = u;
		this.callback = c;
	}

	@Override
	public void execute() {
		try {
			if ("stephane".equalsIgnoreCase(username)) {
				this.callback.onSucces(new IdeeImpl("idee", "stephane"));
			} else {
				if ("alex".equalsIgnoreCase(username)) {
					this.callback.onSucces(new IdeeImpl("idee", "alex"));
				} else {
					this.callback.onFailure(new Exception(">>Invalid username"));
				}
			}
		} catch (RemoteException r) {
			r.printStackTrace();
		}
	}

}
