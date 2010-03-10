package fr.alma.gtd.commande;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneesserveur.Utilisateur;
import fr.alma.gtd.interfacedistante.CallBack;
import fr.alma.gtd.isessions.IContexteServiceRemote;
import fr.alma.gtd.isessions.IUtilisateurServiceRemote;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Commande de creation d'un contexte.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public final class CommandeCreerContexte implements Commande {
	
	/**
	 * L'identifiant de l'utilisateur.
	 */
	private final String identification;
	
	/**
	 * Le contexte.
	 */
	private final IContexte contexte;
	
	/**
	 * Le callback.
	 */
	private final CallBack<IContexte> callback;
	
	/**
	 * Le service de gestion des contextes.
	 */
	private IContexteServiceRemote contexteServiceRemote;
	
	/**
	 * Le service de gestion d'utilisateurs.
	 */
	private IUtilisateurServiceRemote utilisateurServiceRemote;
	
	/**
	 * Le constructeur.
	 * @param i L'identifiant de l'utilisateur.
	 * @param co Le contexte.
	 * @param ca Le callback.
	 */
	public CommandeCreerContexte(final String i, final IContexte co, final CallBack<IContexte> ca) {
		super();
		this.identification = i;
		this.contexte = co;
		this.callback = ca;
		
		final Properties env = new Properties();
		env.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		env.setProperty("java.naming.provider.url", "localhost:1099");
		env.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming"); 
		
		try {
			final Context context = new InitialContext(env);
			this.contexteServiceRemote = (IContexteServiceRemote) context.lookup("ContexteService/local");
			this.utilisateurServiceRemote = (IUtilisateurServiceRemote) context.lookup("UtilisateurService/local");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void execute() throws RemoteException {
		final IContexte ctx = this.contexteServiceRemote.getContexteById(this.contexte.getIdentifiantServeur()); 
		final Utilisateur uti = this.utilisateurServiceRemote.getUtilisateurById(this.contexte.getCreateur().getIdentifiantServeur());
		
		if ((ctx == null) && (uti != null) && (this.identification.equalsIgnoreCase(uti.getIdentifiantServeur()))) {
			this.callback.onSucces(this.contexteServiceRemote.creerContexte(this.contexte));
		} else {
			if (ctx != null) {
				this.callback.onFailure(new Exception("Contexte deja existant"));
			} else {
				this.callback.onFailure(new Exception("Identifiant utilisateur invalide"));
			}
		}
	}
}
