package fr.alma.gtd.commande;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IObjetServeur;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;
import fr.alma.gtd.interfacedistante.CallBack;
import fr.alma.gtd.interfacedistante.ModeDeMiseAJour;
import fr.alma.gtd.reactor.Acceptor;

import java.util.Date;
import java.util.List;

/**
 * Fabrique de commandes.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public final class CommandeFactory {
	
	/**
	 * L'instance de la fabrique de commandes.
	 */
	private static CommandeFactory instance = new CommandeFactory();
	
	/**
	 * Le constructeur par defaut.
	 */
	private CommandeFactory() {
		super();
	}
	
	/**
	 * @return L'instance de la fabrique de commandes.
	 */
	public static CommandeFactory getInstance() {
		return instance;
	}
	
	/**
	 * Cree la commande de creation de compte.
	 * @param username Le nom d'utilisateur desire.
	 * @param password Le mot de passe desire.
	 * @param pseudo Le pseudonyme desire.
	 * @param callback Le callback.
	 * @return La commande de creation de compte.
	 */
	public Commande getCommandeCreerCompte(final String username, final String password, final String pseudo, final CallBack<String> callback) {
		return new CommandeCreerCompte(pseudo, pseudo, pseudo, callback);
	}
	
	/**
	 * Cree la commande de creation de compte.
	 * @param identification L'identifiant de l'utilisateur.
	 * @param contexte Le contexte a creer.
	 * @param callback Le callback.
	 * @return La commande de creation de contexte.
	 */
	public Commande getCommandeCreerContexte(final String identification, final IContexte contexte, final CallBack<IContexte> callback) {
		return new CommandeCreerContexte(Acceptor.getInstance().decode(identification), contexte, callback);
	}
	
	/**
	 * Cree la commande de creation d'idee.
	 * @param identification L'identifiant de l'utilisateur.
	 * @param idee L'idee a creer.
	 * @param callback Le callback.
	 * @return La commande de creation d'idee.
	 */
	public Commande getCommandeCreerIdee(final String identification, final IIdee idee, final CallBack<IIdee> callback) {
		return new CommandeCreerIdee(Acceptor.getInstance().decode(identification), idee, callback);
	}
	
	/**
	 * Cree la commande de creation de projet.
	 * @param identification L'identifiant de l'utilisateur.
	 * @param projet Le projet a creer.
	 * @param callback Le callback.
	 * @return La commande de creation de projet.
	 */
	public Commande getCommandeCreerProjet(final String identification, final IProjet projet, final CallBack<IProjet> callback) {
		return new CommandeCreerProjet(Acceptor.getInstance().decode(identification), projet, callback);
	}
	
	/**
	 * Cree la commande de creation de tache.
	 * @param identification L'identifiant de l'utilisateur.
	 * @param tache La tache a creer.
	 * @param callback Le callback.
	 * @return La commande de creation de tache.
	 */
	public Commande getCommandeCreerTache(final String identification, final ITache tache, final CallBack<ITache> callback) {
		return new CommandeCreerTache(Acceptor.getInstance().decode(identification), tache, callback);
	}
	
	/**
	 * Cree la commande de creation de tag.
	 * @param identification L'identifiant de l'utilisateur.
	 * @param tag Le tag a creer.
	 * @param callback Le callback.
	 * @return La commande de creation de tag.
	 */
	public Commande getCommandeCreerTag(final String identification, final ITag tag, final CallBack<ITag> callback) {
		return new CommandeCreerTag(Acceptor.getInstance().decode(identification), tag, callback);
	}
	
	/**
	 * Cree la commande d'envoi de contexte.
	 * @param identification L'identifiant de l'utilisateur.
	 * @param contexte Le context a envoyer.
	 * @param mode Le mode d'envoi.
	 * @param callback Le callback.
	 * @return La commande d'envoi de contexte.
	 */
	public Commande getCommandeEnvoyerContexte(final String identification, final IContexte contexte, final ModeDeMiseAJour mode, final CallBack<IContexte> callback) {
		return new CommandeEnvoyerContexte(Acceptor.getInstance().decode(identification), contexte, mode, callback);
	}
	
	/**
	 * Cree la commande d'envoi d'idee. 
	 * @param identification L'identifiant de l'utilisateur.
	 * @param idee L'idee a envoyer.
	 * @param mode Le mode d'envoi.
	 * @param callback Le callback.
	 * @return La commande d'envoi d'idee.
	 */
	public Commande getCommandeEnvoyerIdee(final String identification, final IIdee idee, final ModeDeMiseAJour mode, final CallBack<IIdee> callback) {
		return new CommandeEnvoyerIdee(Acceptor.getInstance().decode(identification), idee, mode, callback);
	}
	
	/**
	 * Cree le commande d'envoi de projet.
	 * @param identification L'identifiant de l'utilisateur.
	 * @param projet Le projet a envoyer.
	 * @param mode Le mode d'envoi.
	 * @param callback Le callback.
	 * @return La commande d'envoi de projet.
	 */
	public Commande getCommandeEnvoyerProjet(final String identification, final IProjet projet, final ModeDeMiseAJour mode, final CallBack<IProjet> callback) {
		return new CommandeEnvoyerProjet(Acceptor.getInstance().decode(identification), projet, mode, callback);
	}
	
	/**
	 * Cree le commande d'envoi de tache.
	 * @param identification L'identifiant de l'utilisateur.
	 * @param tache La tache a envoyer.
	 * @param mode Le mode d'envoi.
	 * @param callback Le callback.
	 * @return La commande d'envoi de tache.
	 */
	public Commande getCommandeEnvoyerTache(final String identification, final ITache tache, final ModeDeMiseAJour mode, final CallBack<ITache> callback) {
		return new CommandeEnvoyerTache(Acceptor.getInstance().decode(identification), tache, mode, callback);
	}
	
	/**
	 * Cree la commande d'envoi de tag.
	 * @param identification L'identifiant de l'utilisateur.
	 * @param tag Le tag a envoyer.
	 * @param mode Le mode d'envoi.
	 * @param callback Le callback.
	 * @return La commande d'envoi de tag.
	 */
	public Commande getCommandeEnvoyerTag(final String identification, final ITag tag, final ModeDeMiseAJour mode, final CallBack<ITag> callback) {
		return new CommandeEnvoyerTag(Acceptor.getInstance().decode(identification), tag, mode, callback);
	}
	
	/**
	 * Cree la commande de login.
	 * @param username Le nom d'utilisateur.
	 * @param password Le mot de passe.
	 * @param callback Le callback.
	 * @return La commande de login.
	 */
	public Commande getCommandeLogin(final String username, final String password, final CallBack<String> callback) {
		return new CommandeLogin(username, password, callback);
	}
	
	/**
	 * Cree la commande de modification de mot de passe.
	 * @param oldPassword L'ancien mot de passe.
	 * @param newPassword Le nouveau de mot de passe.
	 * @param callback Le callback.
	 * @return La commande de modification de mot de passe.
	 */
	public Commande getCommandeModifierMotDePasse(final String oldPassword, final String newPassword, final CallBack<String> callback) {
		return new CommandeModifierMotDePasse(oldPassword, newPassword, callback);
	}
	
	/**
	 * Cree la commande de modification du pseudo.
	 * @param pseudo Le pseudo.
	 * @param callback Le callback.
	 * @return La commande de modification du pseudo.
	 */
	public Commande getCommandeModifierPseudo(final String pseudo, final CallBack<String> callback) {
		return new CommandeModifierPseudo(pseudo, callback);
	}
	
	/**
	 * Cree la commande de suppression de compte.
	 * @param username Le nom d'utilisateur.
	 * @param password Le mot de passe.
	 * @param callback Le callback.
	 * @return La commande de suppression de compte.
	 */
	public Commande getCommandeSupprimerCompte(final String username, final String password, final CallBack<String> callback) {
		return new CommandeSupprimerCompte(username, password, callback);
	}
	
	/**
	 * Cree la commande de suppression de contexte.
	 * @param identification L'identifiant de l'utilisateur.
	 * @param contexte Le contexte a supprimer.
	 * @param callback Le callback.
	 * @return La commande de suppression de contexte.
	 */
	public Commande getCommandeSupprimerContexte(final String identification, final IContexte contexte, final CallBack<String> callback) {
		return new CommandeSupprimerContexte(Acceptor.getInstance().decode(identification), contexte, callback);
	}
	
	/**
	 * Cree la commande de suppression d'idee.
	 * @param identification L'identifiant de l'utilisateur.
	 * @param idee L'idee a supprimer.
	 * @param callback Le callback.
	 * @return La commande de suppression d'idee.
	 */
	public Commande getCommandeSupprimerIdee(final String identification, final IIdee idee, final CallBack<String> callback) {
		return new CommandeSupprimerIdee(Acceptor.getInstance().decode(identification), idee, callback);
	}
	
	/**
	 * Cree la commande de suppression de projet. 
	 * @param identification L'identifiant de l'utilisateur. 
	 * @param projet Le projet a supprimer.
	 * @param callback Le callback.
	 * @return La commande de suppression de projet.
	 */
	public Commande getCommandeSupprimerProjet(final String identification, final IProjet projet, final CallBack<String> callback) {
		return new CommandeSupprimerProjet(Acceptor.getInstance().decode(identification), projet, callback);
	}
	
	/**
	 * Cree la commande de suppression de tache.
	 * @param identification L'identifiant de l'utilisateur.  
	 * @param tache La tache a supprimer.
	 * @param callback Le callback.
	 * @return La commande de suppression de tache.
	 */
	public Commande getCommandeSupprimerTache(final String identification, final ITache tache, final CallBack<String> callback) {
		return new CommandeSupprimerTache(Acceptor.getInstance().decode(identification), tache, callback);
	}
	
	/**
	 * Cree la commande de suppression de tag.
	 * @param identification L'identifiant de l'utilisateur.  
	 * @param tag Le tag a supprimer.
	 * @param callback Le callback.
	 * @return La commande de suppression de tag.
	 */
	public Commande getCommandeSupprimerTag(final String identification, final ITag tag, final CallBack<String> callback) {
		return new CommandeSupprimerTag(Acceptor.getInstance().decode(identification), tag, callback);
	}
	
	/**
	 * Cree la commande de telechargement de l'archive.
	 * @param date La date de debut des elements des archives a recuperer.
	 * @param callback Le callback.
	 * @return La commande de telechargement de l'archive.
	 */
	public Commande getCommandeTelechargeArchive(final Date date, final CallBack<List<IObjetServeur>> callback) {
		return new CommandeTelechargeArchive(date, callback);
	}
	
	/**
	 * Cree la commande de telechargement de l'archive.
	 * @param callback Le callback.
	 * @return La commande de telechargement de l'archive.
	 */
	public Commande getCommandeTelechargeArchive(final CallBack<List<IObjetServeur>> callback) {
		return new CommandeTelechargeArchive(callback);
	}
	
	/**
	 * Cree la commande de telechargement du calendrier.
	 * @param callback Le callback.
	 * @return La commande de telechargement du calendrier.
	 */
	public Commande getCommandeTelechargeCalendrier(final CallBack<List<ITache>> callback) {
		return new CommandeTelechargeCalendrier(callback);
	}
	
	/**
	 * Cree la commande de telechargement du calendrier.
	 * @param date La date de debut des elements du calendrier a recuperer.
	 * @param callback Le callback.
	 * @return La commande de telechargement du calendrier.
	 */
	public Commande getCommandeTelechargeCalendrier(final Date date, final CallBack<List<ITache>> callback) {
		return new CommandeTelechargeCalendrier(date, callback);
	}
	
	/**
	 * Cree la commande de telechargement de contextes.
	 * @param identification L'identifiant de l'utilisateur. 
	 * @param callback Le callback.
	 * @return La commande de telechargement de contextes.
	 */
	public Commande getCommandeTelechargeContextes(final String identification, final CallBack<List<IContexte>> callback) {
		return new CommandeTelechargeContextes(Acceptor.getInstance().decode(identification), callback);
	}
	
	/**
	 * Cree la commande de telechargement des idees.
	 * @param callback Le callback.
	 * @return La commande de telechargement des idees.
	 */
	public Commande getCommandeTelechargeIdees(final CallBack<List<IIdee>> callback) {
		return new CommandeTelechargeIdees(callback);
	}
	
	/**
	 * Cree la commande de telechargement de l'inbox.
	 * @param callback Le callback.
	 * @return La commande de telechargement de l'inbox.
	 */
	public Commande getCommandeTelechargeInbox(final CallBack<List<IIdee>> callback) {
		return new CommandeTelechargeInbox(callback);
	}
	
	/**
	 * Cree la commande de telechargement de l'inbox.
	 * @param date La date de debut des elements de l'inbox a recuperer.
	 * @param callback Le callback.
	 * @return La commande de telechargement de l'inbox.
	 */
	public Commande getCommandeTelechargeInbox(final Date date, final CallBack<List<IIdee>> callback) {
		return new CommandeTelechargeInbox(date, callback);
	}
	
	/**
	 * Cree la commande de telechargement des participants.
	 * @param callback Le callback.
	 * @return La commande de telechargement des participants.
	 */
	public Commande getCommandeTelechargeParticipants(final CallBack<List<IParticipant>> callback) {
		return new CommandeTelechargeParticipants(callback);
	}
	
	/**
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargePoubelle(final CallBack<List<IObjetServeur>> callback) {
		return new CommandeTelechargePoubelle(callback);
	}
	
	/**
	 * @param date
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargePoubelle(final Date date, final CallBack<List<IObjetServeur>> callback) {
		return new CommandeTelechargePoubelle(date, callback);
	}
	
	/**
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeProchainesTaches(final CallBack<List<ITache>> callback) {
		return new CommandeTelechargeProchainesTaches(callback);
	}
	
	/**
	 * @param date
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeProchainesTaches(final Date date, final CallBack<List<ITache>> callback) {
		return new CommandeTelechargeProchainesTaches(date, callback);
	}
	
	/**
	 * @param contexte
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeProchainesTachesParContexte(final IContexte contexte, final CallBack<List<ITache>> callback) {
		return new CommandeTelechargeProchainesTachesParContexte(contexte, callback);
	}
	
	/**
	 * @param date
	 * @param contexte
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeProchainesTachesParContexte(final Date date, final IContexte contexte, final CallBack<List<ITache>> callback) {
		return new CommandeTelechargeProchainesTachesParContexte(date, contexte, callback);
	}
	
	/**
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeProjets(final CallBack<List<IProjet>> callback) {
		return new CommandeTelechargeProjets(callback);
	}
	
	/**
	 * @param tag
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeParTag(final ITag tag, final CallBack<List<ITache>> callback) {
		return new CommandeTelechargeParTag(tag, callback);
	}
	
	/**
	 * @param date
	 * @param tag
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeParTag(final Date date, final ITag tag, final CallBack<List<ITache>> callback) {
		return new CommandeTelechargeParTag(date, tag, callback);
	}
	
	/**
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeTaches(final CallBack<List<ITache>> callback) {
		return new CommandeTelechargeTaches(callback);
	}
	
	/**
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeTags(final CallBack<List<ITag>> callback) {
		return new CommandeTelechargeTags(callback);
	}
	
	/**
	 * @param identification L'identifiant de l'utilisateur. 
	 * @param username
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeContextes(final String identification, final String username, final CallBack<List<IContexte>> callback) {
		return new CommandeTelechargeContextes(identification, username, callback);
	}
	
	/**
	 * @param username
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeIdees(final String username, final CallBack<List<IIdee>> callback) {
		return new CommandeTelechargeIdees(username, callback);
	}
	
	/**
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeLog(final CallBack<List<String>> callback) {
		return new CommandeTelechargeLog(callback);
	}
	
	/**
	 * @param date
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeLog(final Date date, final CallBack<List<String>> callback) {
		return new CommandeTelechargeLog(date, callback);
	}
	
	/**
	 * @param username
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeLog(final String username, final CallBack<List<String>> callback) {
		return new CommandeTelechargeLog(username, callback);
	}
	
	/**
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeLogAdmin(final CallBack<List<String>> callback) {
		return new CommandeTelechargeLogAdmin(callback);
	}
	
	/**
	 * @param username
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeProjets(final String username, final CallBack<List<IProjet>> callback) {
		return new CommandeTelechargeProjets(username, callback);
	}
	
	/**
	 * @param username
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeTaches(final String username, final CallBack<List<ITache>> callback) {
		return new CommandeTelechargeTaches(username, callback);
	}
	
	/**
	 * @param username
	 * @param callback
	 * @return
	 */
	public Commande getCommandeTelechargeTags(final String username, final CallBack<List<ITag>> callback) {
		return new CommandeTelechargeTags(username, callback);
	}
	
}
