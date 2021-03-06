package donneespartagees;


/**
 * donneespartagees/serveurCORBAOperations.java .
 * Generated by the IDL-to-Java compiler (portable), version "3.2"
 * from C:/Users/Florian/workspace2/alma-gtd/idl/donneespartagees.idl
 * mercredi 6 janvier 2010 22 h 46 CET
 */


/**
 * Ensemble des methodes disponibles pour les clients utilisant CORBA.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface serveurCORBAOperations 
{

	/**
	 * @param idee
	 * @param identification
	 * @param callback
	 */
	void creerIdee (donneespartagees.IIdee idee, String identification, donneespartagees.Callback callback);

	/**
	 * @param idee
	 * @param mode
	 * @param identification
	 * @param callback
	 */
	void envoyerIdee (donneespartagees.IIdee idee, donneespartagees.ModeDeMiseAJour mode, String identification, donneespartagees.Callback callback);

	/**
	 * @param idee
	 * @param identification
	 * @param callback
	 */
	void supprimerIdee (donneespartagees.IIdee idee, String identification, donneespartagees.Callback callback);

	/**
	 * @param idee
	 * @param tache
	 * @param identification
	 * @param callback
	 */
	void creerTache (donneespartagees.IIdee idee, donneespartagees.ITache tache, String identification, donneespartagees.Callback callback);

	/**
	 * @param tache
	 * @param mode
	 * @param identification
	 * @param callback
	 */
	void envoyerTache (donneespartagees.ITache tache, donneespartagees.ModeDeMiseAJour mode, String identification, donneespartagees.Callback callback);

	/**
	 * @param tache
	 * @param identification
	 * @param callback
	 */
	void supprimerTache (donneespartagees.ITache tache, String identification, donneespartagees.Callback callback);

	/**
	 * @param idee
	 * @param projet
	 * @param identification
	 * @param callback
	 */
	void creerProjet (donneespartagees.IIdee idee, donneespartagees.IProjet projet, String identification, donneespartagees.Callback callback);

	/**
	 * @param projet
	 * @param mode
	 * @param identification
	 * @param callback
	 */
	void envoyerProjet (donneespartagees.IProjet projet, donneespartagees.ModeDeMiseAJour mode, String identification, donneespartagees.Callback callback);

	/**
	 * @param projet
	 * @param identification
	 * @param callback
	 */
	void supprimerProjet (donneespartagees.IProjet projet, String identification, donneespartagees.Callback callback);

	/**
	 * @param contexte
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	void creerContexte (donneespartagees.IObjetServeur contexte, String identification, donneespartagees.Callback callback);

	/**
	 * @param contexte
	 * @param mode
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	void envoyerContexte (donneespartagees.IObjetServeur contexte, donneespartagees.ModeDeMiseAJour mode, String identification, donneespartagees.Callback callback);

	/**
	 * @param contexte
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	void supprimerContexte (donneespartagees.IObjetServeur contexte, String identification, donneespartagees.Callback callback);

	/**
	 * @param tag
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	void creerTag (donneespartagees.ITag tag, String identification, donneespartagees.Callback callback);

	/**
	 * @param tag
	 * @param mode
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	void envoyerTag (donneespartagees.ITag tag, donneespartagees.ModeDeMiseAJour mode, String identification, donneespartagees.Callback callback);

	/**
	 * @param tag
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	void supprimerTag (donneespartagees.ITag tag, String identification, donneespartagees.Callback callback);

	/**
	 * @param contact
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	void creerContact (donneespartagees.IContact contact, String identification, donneespartagees.Callback callback);

	/**
	 * @param contact
	 * @param mode
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	void envoyerContact (donneespartagees.IContact contact, donneespartagees.ModeDeMiseAJour mode, String identification, donneespartagees.Callback callback);

	/**
	 * @param contact
	 * @param identification
	 * @param callback
	 * @throws RemoteException
	 */
	void supprimerContact (donneespartagees.IContact contact, String identification, donneespartagees.Callback callback);

	// ################################ METHODE DE MODIFICATION ################################
	void ajouterTacheAProjet (donneespartagees.ITache tache, donneespartagees.IProjet projet, String identification, donneespartagees.Callback callback);
	void retirerTacheDuProjet (donneespartagees.ITache tache, donneespartagees.IProjet projet, String identification, donneespartagees.Callback callback);
	void ajouterParticipantAuProjet (donneespartagees.IProjet projet, donneespartagees.IParticipant nom, String identification, donneespartagees.Callback callback);
	void supprimerParticipantDuProjet (donneespartagees.IProjet projet, donneespartagees.IParticipant nom, String identification, donneespartagees.Callback callback);
	void ajouterParticipantDuProjetATache (donneespartagees.IProjet projet, donneespartagees.IParticipant nom, donneespartagees.ITache tache, String identification, donneespartagees.Callback callback);
	void retirerParticipantDeLaTache (donneespartagees.IParticipant nom, donneespartagees.ITache tache, String identification, donneespartagees.Callback callback);

	// ################################ METHODE DE RECUPERATION ################################
	void downloadInbox (String date, String identification, donneespartagees.Callback callback);
	void downloadInbox2 (String identification, donneespartagees.Callback callback);
	void downloadPoubelle (String date, String identification, donneespartagees.Callback callback);
	void downloadPoubelle2 (String identification, donneespartagees.Callback callback);
	void downloadArchive (String date, String identification, donneespartagees.Callback callback);
	void downloadArchive2 (String identification, donneespartagees.Callback callback);
	void downloadProchainesTaches (String date, String identification, donneespartagees.Callback callback);
	void downloadProchainesTaches2 (String identification, donneespartagees.Callback callback);
	void downloadProchainesTaches3 (donneespartagees.IObjetServeur contexte, String date, String identification, donneespartagees.Callback callback);
	void downloadProchainesTaches4 (donneespartagees.IObjetServeur contexte, String identification, donneespartagees.Callback callback);
	void downloadCalendrier (String date, String identification, donneespartagees.Callback callback);
	void downloadCalendrier2 (String identification, donneespartagees.Callback callback);
	void downloadTacheParTag (String date, donneespartagees.ITag tag, String identification, donneespartagees.Callback callback);
	void downloadTacheParTag2 (donneespartagees.ITag tag, String identification, donneespartagees.Callback callback);

	// ################################ METHODE DE GESTION DE COMPTE ################################
	void creerCompte (String username, String password, String pseudo, String identification, donneespartagees.Callback callback);
	void supprimerCompte (String username, String password, String identification, donneespartagees.Callback callback);
	void modifierPseudo (String pseudo, String identification, donneespartagees.Callback callback);
	void modifierUsername (String username, String identification, donneespartagees.Callback callback);
	void modifierMotDePasse (String oldpPassword, String newPassword, String identification, donneespartagees.Callback callback);
	void login (String username, String password, String identification, donneespartagees.Callback callback);
	void disconnect (String identification, donneespartagees.Callback callback);
	void downloadListeParticipant (String identification, donneespartagees.Callback callback);
	void downloadLog (String date, String identification, donneespartagees.Callback callback);
	void downloadLog2 (String identification, donneespartagees.Callback callback);

	// ################################ METHODE ADMINISTRATEUR ################################
	void downloadLogAdmin (String identification, donneespartagees.Callback callback);
	void downloadInbox3 (String username, String identification, donneespartagees.Callback callback);
	void downloadTaches (String username, String identification, donneespartagees.Callback callback);
	void downloadProjets (String username, String identification, donneespartagees.Callback callback);
	void downloadLog3 (String username, String identification, donneespartagees.Callback callback);
} // interface serveurCORBAOperations
