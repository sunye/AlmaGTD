package fr.alma.gtd.reactor;

import fr.alma.gtd.commande.Commande;
import fr.alma.gtd.commande.CommandeQueue;

/**
 * La classe de lancement des threads executant les commandes.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public class ThreadDeCommande implements Runnable {
	
	/**
	 * La commande a executer.
	 */
	private Commande commande;
	
	/**
	 * La queue de commandes.
	 */
	private final CommandeQueue commandeQueue;
	
	/**
	 * Le constructeur.
	 * @param c La commande a lancer.
	 */
	public ThreadDeCommande(final Commande c) {
		super();
		this.commande = c;
		this.commandeQueue = CommandeQueue.getInstance();
	}

	@Override
	public final void run() {
		this.commandeQueue.executionCommande(this.commande);
	}
	
	
}
