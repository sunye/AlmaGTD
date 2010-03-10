package fr.alma.gtd.commande;

import fr.alma.gtd.donneesserveur.Participant;
import fr.alma.gtd.donneesserveur.Utilisateur;
import fr.alma.gtd.interfacedistante.CallBack;
import fr.alma.gtd.isessions.IUtilisateurServiceRemote;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Commande de creation du compte.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public final class CommandeCreerCompte implements Commande {
	
	/**
	 * Le nom d'utilisateur.
	 */
	private final String username;
	
	/**
	 * Le mot de passe.
	 */
	private final String password;
	
	/**
	 * Le pseudo.
	 */
	private final String pseudo;
	
	/**
	 * Le callback.
	 */
	private final CallBack<String> callback;
	
	/**
	 * Le service de gestion d'utilisateurs.
	 */
	private IUtilisateurServiceRemote utilisateurServiceRemote;
	
	/**
	 * Le constructeur?
	 * @param u Le nom d'utilisateur.
	 * @param p Le mot de passe.
	 * @param ps Le pseudo.
	 * @param c Le callback.
	 */
	public CommandeCreerCompte(final String u, final String p, final String ps, final CallBack<String> c) {
		super();
		this.username = u;
		this.password = p;
		this.pseudo = ps;
		this.callback = c;
		
		final Properties env = new Properties();
		env.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		env.setProperty("java.naming.provider.url", "localhost:1099");
		env.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming"); 
		
		try {
			final Context context = new InitialContext(env);
			this.utilisateurServiceRemote = (IUtilisateurServiceRemote) context.lookup("UtilisateurService/local");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void execute() throws RemoteException {
		final Utilisateur util = new Utilisateur();
		util.setLogin(this.username);
		util.setPassword(this.password);
		
		final Participant par = new Participant();
		par.setDateDeDerniereModification(new Date());
		par.setPseudonyme(this.pseudo);
		
		util.setParticipantAssocie(par);
		
		if (this.utilisateurServiceRemote.getUtilisateurByLogin(this.username) == null) {
			this.utilisateurServiceRemote.creerUtilisateur(new Utilisateur());
			this.callback.onSucces("Utilisateur enregistre");
		} else {
			this.callback.onFailure(new Exception("Utilisateur existant"));
		}
		
	}

}
