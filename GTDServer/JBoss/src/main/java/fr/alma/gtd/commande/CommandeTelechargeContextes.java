package fr.alma.gtd.commande;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneesserveur.Utilisateur;
import fr.alma.gtd.interfacedistante.CallBack;
import fr.alma.gtd.isessions.IContexteServiceRemote;
import fr.alma.gtd.isessions.IUtilisateurServiceRemote;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Commande de telechargement d'un contexte.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public final class CommandeTelechargeContextes implements Commande {
	
	/**
	 * L'identifiant de l'utilisateur.
	 */
	private final String identification;
	
	/**
	 * Le nom d'utilisateur.
	 */
	private final String username;
	
	/**
	 * Le callback.
	 */
	private final CallBack<List<IContexte>> callback;
	
	/**
	 * Le service de gestion de contexte.
	 */
	private IContexteServiceRemote contexteServiceRemote;
	
	/**
	 * Le service de gestion d'utilisateurs.
	 */
	private IUtilisateurServiceRemote utilisateurServiceRemote;
	
	/**
	 * Le constructeur.
	 * @param i L'identifiant de l'utilisateur.
	 * @param c Le callback.
	 */
	public CommandeTelechargeContextes(final String i, final CallBack<List<IContexte>> c) {
		super();
		this.identification = i;
		this.callback = c;
		this.username = "";
	}

	/**
	 * Le constructeur.
	 * @param i L'identifiant de l'utilisateur.
	 * @param u Le nom d'utilisateur.
	 * @param c Le callback.
	 */
	public CommandeTelechargeContextes(final String i, final String u, final CallBack<List<IContexte>> c) {
		super();
		this.identification = i;
		this.username = u;
		this.callback = c;
	}

	@Override
	public void execute() throws RemoteException {
		if ("".equalsIgnoreCase(this.username)) {
			//TODO
		} else {
			final Utilisateur uti = this.utilisateurServiceRemote.getUtilisateurById(this.identification);
			
			if (uti != null) {
				this.callback.onSucces(this.contexteServiceRemote.getContexteByCreateur(uti.getIdentifiantServeur()));
			} else {
				this.callback.onFailure(new Exception("Identifiant utilisateur invalide"));
			}
		}
	}

}
