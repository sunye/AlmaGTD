package fr.alma.gtd.commande;

import java.rmi.RemoteException;

/**
 * Interface des commandes.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface Commande {
	/**
	 * Execute la commande.
	 * @throws RemoteException 
	 */
	void execute() throws RemoteException;
}
