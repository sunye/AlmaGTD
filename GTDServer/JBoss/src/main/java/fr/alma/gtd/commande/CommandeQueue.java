package fr.alma.gtd.commande;

import java.rmi.RemoteException;

/**
 * La classe de la queue de commandes synchronisee.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public final class CommandeQueue {
	
	/**
	 * L'instance de la queue de commandes.
	 */
	private static CommandeQueue instance = new CommandeQueue();
	
	/**
	 * Le constructeur.
	 */
	private CommandeQueue() {
		super();
	}
	
	/**
	 * Getter de l'instance.
	 * @return L'instance de la queue de commande.
	 */
	public static CommandeQueue getInstance() {
		return instance;
	}
	
	/**
	 * Lance l'execution de la commande.
	 * @param c La commande a executer.
	 */
	public synchronized void executionCommande(final Commande c) {
		try {
			c.execute();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
}
