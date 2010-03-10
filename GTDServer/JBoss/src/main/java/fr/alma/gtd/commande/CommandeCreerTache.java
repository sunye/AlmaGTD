package fr.alma.gtd.commande;

import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneesserveur.Utilisateur;
import fr.alma.gtd.interfacedistante.CallBack;
import fr.alma.gtd.isessions.ITacheServiceRemote;
import fr.alma.gtd.isessions.IUtilisateurServiceRemote;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Commande de creation d'une tache.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public final class CommandeCreerTache implements Commande {
	
	/**
	 * L'identifiant de l'utilisateur.
	 */
	private final String identification;
	
	/**
	 * La tache a creer.
	 */
	private final ITache tache;
	
	/**
	 * Le callback.
	 */
	private final CallBack<ITache> callback;
	
	/**
	 * Le service de gestion des taches.
	 */
	private ITacheServiceRemote tacheServiceRemote;
	
	/**
	 * Le service de gestion d'utilisateurs.
	 */
	private IUtilisateurServiceRemote utilisateurServiceRemote;
	
	/**
	 * Le constructeur.
	 * @param i L'identifiant de l'utilisateur.
	 * @param t La tache a creer.
	 * @param c Le callback.
	 */
	public CommandeCreerTache(final String i, final ITache t, final CallBack<ITache> c) {
		super();
		this.identification = i;
		this.tache = t;
		this.callback = c;
		
		final Properties env = new Properties();
		env.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		env.setProperty("java.naming.provider.url", "localhost:1099");
		env.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming"); 
		
		try {
			final Context context = new InitialContext(env);
			this.tacheServiceRemote = (ITacheServiceRemote) context.lookup("TacheService/local");
			this.utilisateurServiceRemote = (IUtilisateurServiceRemote) context.lookup("UtilisateurService/local");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void execute() throws RemoteException {
		final ITache tac = this.tacheServiceRemote.getTacheById(this.tache.getIdentifiantServeur());
		final Utilisateur uti = this.utilisateurServiceRemote.getUtilisateurById(this.tache.getCreateur().getIdentifiantServeur());
		
		if ((tac == null) && (uti != null) && (this.identification.equalsIgnoreCase(this.tache.getCreateur().getIdentifiantServeur()))) {
			this.callback.onSucces(this.tacheServiceRemote.creerTache(this.tache));
		} else {
			if (tac != null) {
				this.callback.onFailure(new Exception("Tache deja existante"));
			} else {
				this.callback.onFailure(new Exception("Identifiant utilisateur invalide"));
			}
		}
	}
}
