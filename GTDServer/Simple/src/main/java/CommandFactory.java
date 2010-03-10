

public final class CommandFactory {
	
	private static CommandFactory instance = new CommandFactory();
	
	private CommandFactory() {
		super();
	}
	
	public static CommandFactory getInstance() {
		return instance;
	}
	
	public Commande getCommandeLogin(final String username, final String password, final CallBack<String> callback) {
		return new CommandeLogin(username, password, callback);
	}
	
	public Commande getCommandeDeconnect(final String identification, final CallBack<String> callback) {
		return new CommandeDeconnect(identification, callback);
	}
	
	public Commande getCommandeTelecharger(final String username, final String identification, final CallBack<Idee> callback) {
		return new CommandeTelecharger(username, callback);
	}
}
