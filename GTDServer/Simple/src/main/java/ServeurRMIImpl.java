import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public final class ServeurRMIImpl extends UnicastRemoteObject implements ServeurRMI {
	
	private static final long serialVersionUID = 5661943902402108019L;
	
	private final Acceptor acceptor;
	
	private final CommandFactory commandFactory;
	
	private final ThreadFactory threadFactory;
	
	protected ServeurRMIImpl() throws RemoteException {
		super();
		this.acceptor = Acceptor.getInstance();
		this.commandFactory = CommandFactory.getInstance();
		this.threadFactory = ThreadFactory.getInstance();
	}

	@Override
	public void disconnect(final String identification, final CallBack<String> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			Commande c = this.commandFactory.getCommandeDeconnect(identification, callback);
			this.threadFactory.executeCommande(c);
		} else {
			callback.onFailure(new Exception(">>Not connected"));
		}
	}

	@Override
	public void login(final String username, final String password, final CallBack<String> callback) throws RemoteException {
		Commande c = this.commandFactory.getCommandeLogin(username, password, callback);
		this.threadFactory.executeCommande(c);
	}

	@Override
	public void telecharger(final String username, final String identification, final CallBack<Idee> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			Commande c = this.commandFactory.getCommandeTelecharger(username, identification, callback);
			this.threadFactory.executeCommande(c);
		} else {
			callback.onFailure(new Exception(">>Not connected"));
		}
	}
}
