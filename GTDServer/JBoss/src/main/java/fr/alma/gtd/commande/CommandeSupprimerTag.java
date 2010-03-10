package fr.alma.gtd.commande;

import fr.alma.gtd.donneespartagees.ITag;
import fr.alma.gtd.donneesserveur.Utilisateur;
import fr.alma.gtd.interfacedistante.CallBack;
import fr.alma.gtd.isessions.ITagServiceRemote;
import fr.alma.gtd.isessions.IUtilisateurServiceRemote;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Commande de suppression d'un tag.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public final class CommandeSupprimerTag implements Commande {
	
	/**
	 * L'identifiant de l'utilisateur.
	 */
	private final String identification;
	
	/**
	 * Le tag a supprimer.
	 */
	private final ITag tag;
	
	/**
	 * Le callback.
	 */
	private final CallBack<String> callback;
	
	/**
	 * Le service de gestion de tags.
	 */
	private ITagServiceRemote tagServiceRemote;
	
	/**
	 * Le service de gestion d'utilisateurs.
	 */
	private IUtilisateurServiceRemote utilisateurServiceRemote;
	
	/**
	 * Le constructeur.
	 * @param i L'identifiant de l'utilisateur.
	 * @param t Le tag a supprimer.
	 * @param c Le callback.
	 */
	public CommandeSupprimerTag(final String i, final ITag t, final CallBack<String> c) {
		super();
		this.identification = i;
		this.tag = t;
		this.callback = c;
		
		final Properties env = new Properties();
		env.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		env.setProperty("java.naming.provider.url", "localhost:1099");
		env.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming"); 
		
		try {
			final Context context = new InitialContext(env);
			this.tagServiceRemote = (ITagServiceRemote) context.lookup("TagService/local");
			this.utilisateurServiceRemote = (IUtilisateurServiceRemote) context.lookup("UtilisateurService/local");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void execute() throws RemoteException {
		final ITag ta = this.tagServiceRemote.getTagById(this.tag.getIdentifiantServeur());
		final Utilisateur uti = this.utilisateurServiceRemote.getUtilisateurById(this.tag.getCreateur().getIdentifiantServeur());
		
		if ((ta != null) && (uti != null) && (this.identification.equalsIgnoreCase(this.tag.getCreateur().getIdentifiantServeur()))) {
			this.tagServiceRemote.removeTagById(ta.getIdentifiantServeur());
			this.callback.onSucces("Tag supprime");
		} else {
			if (ta == null) {
				this.callback.onFailure(new Exception("Tag inexistant"));
			} else {
				this.callback.onFailure(new Exception("Identifiant utilisateur invalide"));
			}
		}
	}

}
