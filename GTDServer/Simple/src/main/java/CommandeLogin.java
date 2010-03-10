
public final class CommandeLogin implements Commande {
	
	private final String username;
	
	private final String password;
	
	private final CallBack<String> callback;
	
	public CommandeLogin(final String u, final String p, final CallBack<String> c) {
		super();
		this.username = u;
		this.password = p;
		this.callback = c;
	}
	
	@Override
	public void execute() {
		Acceptor.getInstance().login(username, password, callback);
	}
}
