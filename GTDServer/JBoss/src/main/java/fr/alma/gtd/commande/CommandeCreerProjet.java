package fr.alma.gtd.commande;

import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneesserveur.Utilisateur;
import fr.alma.gtd.interfacedistante.CallBack;
import fr.alma.gtd.isessions.IProjetServiceRemote;
import fr.alma.gtd.isessions.IUtilisateurServiceRemote;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Commande de creation d'un projet.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public final class CommandeCreerProjet implements Commande {
	
	/**
	 * L'identifiant de l'utilisateur.
	 */
	private final String identification;
	
	/**
	 * Le projet.
	 */
	private final IProjet projet;
	
	/**
	 * Le callback.
	 */
	private final CallBack<IProjet> callback;
	
	/**
	 * Le service de gestion des projets.
	 */
	private IProjetServiceRemote projetServiceRemote;
	
	/**
	 * Le service de gestion d'utilisateurs.
	 */
	private IUtilisateurServiceRemote utilisateurServiceRemote;
	
	/**
	 * Le constructeur.
	 * @param i L'identifiant de l'utilisateur.
	 * @param p Le projet a creer.
	 * @param c Le callback.
	 */
	public CommandeCreerProjet(final String i, final IProjet p, final CallBack<IProjet> c) {
		super();
		this.identification = i;
		this.projet = p;
		this.callback = c;
		
		final Properties env = new Properties();
		env.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		env.setProperty("java.naming.provider.url", "localhost:1099");
		env.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming"); 
		
		try {
			final Context context = new InitialContext(env);
			this.projetServiceRemote = (IProjetServiceRemote) context.lookup("ProjetService/local");
			this.utilisateurServiceRemote = (IUtilisateurServiceRemote) context.lookup("UtilisateurService/local");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute() throws RemoteException {
		final IProjet proj = this.projetServiceRemote.getProjetById(this.projet.getIdentifiantServeur());
		final Utilisateur uti = this.utilisateurServiceRemote.getUtilisateurById(this.projet.getCreateur().getIdentifiantServeur());
		
		if ((proj == null) && (uti != null) && (this.identification.equalsIgnoreCase(this.projet.getCreateur().getIdentifiantServeur()))) {
			this.callback.onSucces(this.projetServiceRemote.creerProjet(this.projet));
		} else {
			this.callback.onFailure(new Exception("Projet deja existante"));
		}
	}

}
