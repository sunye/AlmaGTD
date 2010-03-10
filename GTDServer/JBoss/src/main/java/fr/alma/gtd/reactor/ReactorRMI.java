package fr.alma.gtd.reactor;

import fr.alma.gtd.commande.CommandeFactory;
import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IObjetServeur;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;
import fr.alma.gtd.interfacedistante.CallBack;
import fr.alma.gtd.interfacedistante.ModeDeMiseAJour;
import fr.alma.gtd.interfacedistante.ServeurRMI;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Bean session RMI.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
@Stateless
@Remote(ServeurRMI.class)
public final class ReactorRMI implements ServeurRMI {
	
	/**
	 * L'acceptor.
	 */
	private Acceptor acceptor;
	
	/**
	 * La fabrique de commande.
	 */
	private CommandeFactory commandeFactory;
	
	/**
	 * Message d'erreur d'identification.
	 */
	private final String identificationInvalide = "IdentificationInvalide";
	
	/**
	 * Le constructeur par defaut.
	 */
	public ReactorRMI() {
		super();
		this.acceptor = Acceptor.getInstance();
		this.commandeFactory = CommandeFactory.getInstance();
	}

	@Override
	public void creerCompte(final String username, final String password, final String pseudo, final CallBack<String> callback) throws RemoteException {
		final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeCreerCompte(username, password, pseudo, callback));
		final Thread t = new Thread(tdc);
		t.start();
	}

	@Override
	public void creerContexte(final IContexte contexte, final String identification, final CallBack<IContexte> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeCreerContexte(identification, contexte, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void creerIdee(final IIdee idee, final String identification, final CallBack<IIdee> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeCreerIdee(identification, idee, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void creerProjet(final IProjet projet, final String identification, final CallBack<IProjet> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeCreerProjet(identification, projet, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void creerTache(final ITache tache, final String identification, final CallBack<ITache> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeCreerTache(identification, tache, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void creerTag(final ITag tag, final String identification, final CallBack<ITag> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeCreerTag(identification, tag, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void disconnect(final String identification, final CallBack<String> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			this.acceptor.retire(identification);
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void envoyerContexte(final IContexte contexte, final ModeDeMiseAJour mode, final String identification, final CallBack<IContexte> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeEnvoyerContexte(identification, contexte, mode, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void envoyerIdee(final IIdee idee, final ModeDeMiseAJour mode, final String identification, final CallBack<IIdee> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeEnvoyerIdee(identification, idee, mode, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void envoyerProjet(final IProjet projet, final ModeDeMiseAJour mode, final String identification, final CallBack<IProjet> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeEnvoyerProjet(identification, projet, mode, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void envoyerTache(final ITache tache, final ModeDeMiseAJour mode, final String identification, final CallBack<ITache> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeEnvoyerTache(identification, tache, mode, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void envoyerTag(final ITag tag, final ModeDeMiseAJour mode, final String identification, final CallBack<ITag> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeEnvoyerTag(identification, tag, mode, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void login(final String username, final String password, final CallBack<String> callback) throws RemoteException {
		final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeLogin(username, password, callback));
		final Thread t = new Thread(tdc);
		t.start();
	}

	@Override
	public void modifierMotDePasse(final String oldpPassword, final String newPassword, final String identification, final CallBack<String> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeModifierMotDePasse(oldpPassword, newPassword, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void modifierPseudo(final String pseudo, final String identification, final CallBack<String> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeModifierPseudo(pseudo, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void supprimerCompte(final String username, final String password, final String identification, final CallBack<String> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeSupprimerCompte(username, password, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void supprimerContexte(final IContexte contexte, final String identification, final CallBack<String> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeSupprimerContexte(identification, contexte, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void supprimerIdee(final IIdee idee, final String identification, final CallBack<String> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeSupprimerIdee(identification, idee, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void supprimerProjet(final IProjet projet, final String identification, final CallBack<String> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeSupprimerProjet(identification, projet, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void supprimerTache(final ITache tache, final String identification, final CallBack<String> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeSupprimerTache(identification, tache, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void supprimerTag(final ITag tag, final String identification, final CallBack<String> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeSupprimerTag(identification, tag, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeArchive(final String identification, final CallBack<List<IObjetServeur>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeArchive(callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeArchive(final Date date, final String identification, final CallBack<List<IObjetServeur>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeArchive(date, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeCalendrier(final String identification, final CallBack<List<ITache>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeCalendrier(callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeCalendrier(final Date date, final String identification, final CallBack<List<ITache>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeCalendrier(date, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeContextes(final String identification, final CallBack<List<IContexte>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeContextes(identification, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeIdees(final String identification, final CallBack<List<IIdee>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeIdees(callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeInbox(final String identification, final CallBack<List<IIdee>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeInbox(callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeInbox(final Date date, final String identification, final CallBack<List<IIdee>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeInbox(date, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeParticipants(final String identification, final CallBack<List<IParticipant>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeParticipants(callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargePoubelle(final String identification, final CallBack<List<IObjetServeur>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargePoubelle(callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargePoubelle(final Date date, final String identification, final CallBack<List<IObjetServeur>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargePoubelle(date, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeProchainesTaches(final String identification, final CallBack<List<ITache>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeProchainesTaches(callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeProchainesTaches(final Date date, final String identification, final CallBack<List<ITache>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeProchainesTaches(date, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeProchainesTachesParContexte(final IContexte contexte, final String identification, final CallBack<List<ITache>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeProchainesTachesParContexte(contexte, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeProchainesTachesParContexte(final Date date, final IContexte contexte, final String identification, final CallBack<List<ITache>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeProchainesTachesParContexte(date, contexte, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeProjets(final String identification, final CallBack<List<IProjet>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeProjets(callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeTacheParTag(final ITag tag, final String identification, final CallBack<List<ITache>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeParTag(tag, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeTacheParTag(final Date date, final ITag tag, final String identification, final CallBack<List<ITache>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeParTag(date, tag, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeTaches(final String identification, final CallBack<List<ITache>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeTaches(callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargeTags(final String identification, final CallBack<List<ITag>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeTags(callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargerContextes(final String username, final String identification, final CallBack<List<IContexte>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeContextes(identification, username, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargerIdees(final String username, final String identification, final CallBack<List<IIdee>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeIdees(callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargerLog(final String identification, final CallBack<List<String>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeLog(callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargerLog(final Date date, final String identification, final CallBack<List<String>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeLog(date, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargerLog(final String username, final String identification, final CallBack<List<String>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeLog(username, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargerLogAdmin(final String identification, final CallBack<List<String>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeLogAdmin(callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargerProjets(final String username, final String identification, final CallBack<List<IProjet>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeProjets(username, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargerTaches(final String username, final String identification, final CallBack<List<ITache>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeTaches(username, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

	@Override
	public void telechargerTags(final String username, final String identification, final CallBack<List<ITag>> callback) throws RemoteException {
		if (this.acceptor.accept(identification)) {
			final ThreadDeCommande tdc = new ThreadDeCommande(this.commandeFactory.getCommandeTelechargeTags(username, callback));
			final Thread t = new Thread(tdc);
			t.start();
		} else {
			callback.onFailure(new Exception(this.identificationInvalide));
		}
	}

}
