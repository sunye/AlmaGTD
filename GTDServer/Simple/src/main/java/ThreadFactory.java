
public final class ThreadFactory {
	
	private static ThreadFactory instance = new ThreadFactory();
	
	private ThreadFactory() {
		super();
	}
	
	public static ThreadFactory getInstance() {
		return instance;
	}
	
	public void executeCommande(final Commande c) {
		
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				c.execute();
			}
			
		});
		
		t.start();
	}
	
}
