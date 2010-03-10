package fr.alma.gtd.commande;

import fr.alma.gtd.donneespartagees.ITag;
import fr.alma.gtd.donneesserveur.Utilisateur;
import fr.alma.gtd.interfacedistante.CallBack;
import fr.alma.gtd.interfacedistante.ModeDeMiseAJour;
import fr.alma.gtd.isessions.ITagServiceRemote;
import fr.alma.gtd.isessions.IUtilisateurServiceRemote;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Commande d'envoi d'un tag.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public final class CommandeEnvoyerTag implements Commande {
	
	/**
	 * L'identifiant de l'utilisateur.
	 */
	private final String identification;
	
	/**
	 * Le tag a envoyer.
	 */
	private final ITag tag;
	
	/**
	 * Le mode de mise a jour.
	 */
	private final ModeDeMiseAJour mode;
	
	/**
	 * Le callback.
	 */
	private final CallBack<ITag> callback;
	
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
	 * @param t Le tag a envoyer.
	 * @param m Le mode de mise a jour.
	 * @param c Le callback.
	 */
	public CommandeEnvoyerTag(final String i, final ITag t, final ModeDeMiseAJour m, final CallBack<ITag> c) {
		super();
		this.identification = i;
		this.tag = t;
		this.mode = m;
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
			if (this.mode == ModeDeMiseAJour.FORCE) {
				this.callback.onSucces(this.tagServiceRemote.updateTag(this.tag.getIdentifiantServeur(), this.tag));
			} else if (this.mode == ModeDeMiseAJour.WARNING) {
				
				if (this.tag.getDateDeDerniereModification().before(
						this.tagServiceRemote.getTagById(this.tag.getIdentifiantServeur()).getDateDeDerniereModification())) {
					this.callback.onFailure(new Exception("Tag existant mais version plus recente en ligne"));
				} else {
					this.callback.onSucces(this.tagServiceRemote.updateTag(this.tag.getIdentifiantServeur(), this.tag));
				}
				
			}
		} else {
			if (ta == null) {
				this.callback.onFailure(new Exception("Contexte inexistant"));
			} else {
				this.callback.onFailure(new Exception("Identifiant utilisateur invalide"));
			}
		}
	}

}
