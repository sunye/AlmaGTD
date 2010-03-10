package fr.alma.gtd.commande;

import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneesserveur.Utilisateur;
import fr.alma.gtd.interfacedistante.CallBack;
import fr.alma.gtd.isessions.IIdeeServiceRemote;
import fr.alma.gtd.isessions.IUtilisateurServiceRemote;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Commande de creation d'une idee.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public final class CommandeCreerIdee implements Commande {
	
	/**
	 * L'identifiant de l'utilisateur.
	 */
	private final String identification;
	
	/**
	 * L'idee.
	 */
	private final IIdee idee;
	
	/**
	 * Le callback.
	 */
	private final CallBack<IIdee> callback;
	
	/**
	 * Le service de gestion des idees.
	 */
	private IIdeeServiceRemote ideeServiceRemote;
	
	/**
	 * Le service de gestion d'utilisateurs.
	 */
	private IUtilisateurServiceRemote utilisateurServiceRemote;
	
	/**
	 * Le constructeur.
	 * @param i L'identifiant de l'utilisateur.
	 * @param id L'idee a creer.
	 * @param c Le callback.
	 */
	public CommandeCreerIdee(final String i, final IIdee id, final CallBack<IIdee> c) {
		super();
		this.identification = i;
		this.idee = id;
		this.callback = c;
		
		final Properties env = new Properties();
		env.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		env.setProperty("java.naming.provider.url", "localhost:1099");
		env.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming"); 
		
		try {
			final Context context = new InitialContext(env);
			this.ideeServiceRemote = (IIdeeServiceRemote) context.lookup("IdeeService/local");
			this.utilisateurServiceRemote = (IUtilisateurServiceRemote) context.lookup("UtilisateurService/local");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void execute() throws RemoteException {
		final IIdee ide = this.ideeServiceRemote.getIdeeById(this.idee.getIdentifiantServeur());
		final Utilisateur uti = this.utilisateurServiceRemote.getUtilisateurById(this.idee.getCreateur().getIdentifiantServeur());
		
		if ((ide == null) && (uti != null) && (this.identification.equalsIgnoreCase(this.idee.getCreateur().getIdentifiantServeur()))) {
			this.callback.onSucces(this.ideeServiceRemote.creerIdee(this.idee));
		} else {
			if (ide != null) {
				this.callback.onFailure(new Exception("Idee deja existante"));
			} else {
				this.callback.onFailure(new Exception("Identifiant utilisateur invalide"));
			}
		}
	}

}
