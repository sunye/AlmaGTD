package fr.alma.gtd.interfacedistante;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import fr.alma.gtd.donneespartagees.AbstractParticipant;
import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IObjetServeur;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;

/**
 * Ensemble des methodes disponibles pour les clients utilisant CORBA.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface ServeurCORBA {
	
	// ################################ METHODE DE CREATION / ENVOI / SUPPRESSION ################################ 
	
	/**
	 * @param idee
	 * @param identification
	 * @param callback
	 */
	public void creerIdee(final IIdee idee, final String identification, final CallBack<String> callback);
	
	/**
	 * @param idee
	 * @param mode
	 * @param identification
	 * @param callback
	 */
	public void envoyerIdee(final IIdee idee, final ModeDeMiseAJour mode, final String identification, final CallBack<String> callback);
	
	/**
	 * @param idee
	 * @param identification
	 * @param callback
	 */
	public void supprimerIdee(final IIdee idee, final String identification, final CallBack<String> callback);
	
	/**
	 * @param idee
	 * @param tache
	 * @param identification
	 * @param callback
	 */
	public void creerTache(final IIdee idee, final ITache tache, final String identification, final CallBack<String> callback);
	
	/**
	 * @param tache
	 * @param mode
	 * @param identification
	 * @param callback
	 */
	public void envoyerTache(final ITache tache, final ModeDeMiseAJour mode, final String identification, final CallBack<String> callback);
	
	/**
	 * @param tache
	 * @param identification
	 * @param callback
	 */
	public void supprimerTache(final ITache tache, final String identification, final CallBack<String> callback);
	
	/**
	 * @param idee
	 * @param projet
	 * @param identification
	 * @param callback
	 */
	public void creerProjet(final IIdee idee, final IProjet projet, final String identification, final CallBack<String> callback);
	
	/**
	 * @param projet
	 * @param mode
	 * @param identification
	 * @param callback
	 */
	public void envoyerProjet(final IProjet projet, final ModeDeMiseAJour mode, final String identification, final CallBack<String> callback);
	
	/**
	 * @param projet
	 * @param identification
	 * @param callback
	 */
	public void supprimerProjet(final IProjet projet, final String identification, final CallBack<String> callback);
	
	/**
	 * @param contexte
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	public void creerContexte(final IObjetServeur contexte, final String identification, final CallBack<String> callback);
	
	/**
	 * @param contexte
	 * @param mode
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	public void envoyerContexte(final IObjetServeur contexte, final ModeDeMiseAJour mode, final String identification, final CallBack<String> callback);
	
	/**
	 * @param contexte
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	public void supprimerContexte(final IObjetServeur contexte, final String identification, final CallBack<String> callback);
	
	/**
	 * @param tag
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	public void creerTag(final ITag tag, final String identification, final CallBack<String> callback);
	
	/**
	 * @param tag
	 * @param mode
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	public void envoyerTag(final ITag tag, final ModeDeMiseAJour mode, final String identification, final CallBack<String> callback);
	
	/**
	 * @param tag
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	public void supprimerTag(final ITag tag, final String identification, final CallBack<String> callback);
	
	
	// ################################ METHODE DE MODIFICATION ################################
	
	
	public void ajouterTacheAProjet(final ITache tache, final IProjet projet, final String identification, CallBack<String> callback);
	public void retirerTacheDuProjet(final ITache tache, final IProjet projet, final String identification, CallBack<String> callback);

	public void ajouterParticipantAuProjet(final IProjet projet, final IParticipant nom, final String identification, final CallBack<String> callback);
	public void supprimerParticipantDuProjet(final IProjet projet, final IParticipant nom, final String identification, final CallBack<String> callback);

	public void ajouterParticipantDuProjetATache(final IProjet projet, final IParticipant nom, final ITache tache, final String identification, final CallBack<String> callback);
	public void retirerParticipantDeLaTache(final IParticipant nom, final ITache tache, final String identification, final CallBack<String> callback);
	
	
	// ################################ METHODE DE RECUPERATION ################################
	
	
	public void downloadInbox(final Date date, final String identification, final CallBack<String> callback);
	public void downloadInbox(final String identification, final CallBack<String> callback);


	public void downloadPoubelle(final Date date, final String identification, final CallBack<String> callback);
	public void downloadPoubelle(final String identification, final CallBack<String> callback);


	public void downloadArchive(final Date date, final String identification, final CallBack<String> callback);
	public void downloadArchive(final String identification, final CallBack<String> callback);


	public void downloadProchainesTaches(final Date date, final String identification, final CallBack<String> callback);
	public void downloadProchainesTaches(final String identification, final CallBack<String> callback);


	public void downloadProchainesTaches(final IObjetServeur contexte, final Date date, final String identification, final CallBack<String> callback);
	public void downloadProchainesTaches(final IObjetServeur contexte, final String identification, final CallBack<String> callback);


	public void downloadCalendrier(final Date date, final String identification, final CallBack<String> callback);
	public void downloadCalendrier(final String identification, final CallBack<String> callback);


	public void downloadTacheParTag(final Date date, final ITag tag, final String identification, final CallBack<String> callback);
	public void downloadTacheParTag(final ITag tag, final String identification, final CallBack<String> callback);
	
	
	// ################################ METHODE DE GESTION DE COMPTE ################################
	
	
	public void creerCompte(final String username, final String password, final String pseudo, final String identification, final CallBack<String> callback);
	public void supprimerCompte(final String username, final String password, final String identification, final CallBack<String> callback);

	public void modifierPseudo(final String pseudo, final String identification, final CallBack<String> callback);
	public void modifierUsername(final String username, final String identification, final CallBack<String> callback);
	public void modifierMotDePasse(final String oldpPassword, final String newPassword, final String identification, final CallBack<String> callback);

	public void login(final String username, final String password, final String identification, final CallBack<String> callback);
	public void disconnect(final String identification, final CallBack<String> callback);
	
	public void downloadListeParticipant(final String identification, final CallBack<List<AbstractParticipant>> callback);
	
	public void downloadLog(final Date date, final String identification, final CallBack<List<String>> callback);
	public void downloadLog(final String identification, final CallBack<List<String>> callback);
	
	
	// ################################ METHODE ADMINISTRATEUR ################################
	
	
	public void downloadLogAdmin(final String identification, final CallBack<List<String>> callback);
	public void downloadInbox(final String username, final String identification, final CallBack<List<String>> callback);
	public void downloadTaches(final String username, final String identification, final CallBack<List<String>> callback);
	public void downloadProjets(final String username, final String identification, final CallBack<List<String>> callback);
	public void downloadLog(final String username, final String identification, final CallBack<List<String>> callback);
}
