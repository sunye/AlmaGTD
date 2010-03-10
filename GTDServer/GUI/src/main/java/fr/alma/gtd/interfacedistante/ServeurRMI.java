package fr.alma.gtd.interfacedistante;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.donneespartagees.IObjetServeur;
import fr.alma.gtd.donneespartagees.IParticipant;
import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;

/**
 * Ensemble des methodes disponibles pour les clients utilisant RMI.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface ServeurRMI extends Remote {
       
        // ################################ METHODE DE CREATION / ENVOI / SUPPRESSION ################################
       
        /**
         * Permet de creer une idee sur le serveur.
         * L'idee passee en parametre doit posseder un identifiant serveur vide
         * ("".equalsIgnoreCase(idee.getIdServeur()) == true)
         * Si l'identifiant serveur n'est pas vide, alors cette idee est consideree
         * comme existant deja sur le serveur, la methode onFailure du CallBack sera
         * donc appelee et l'idee ne sera pas creee sur le serveur.
         * @param idee L'idee a creer.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void creerIdee(final IIdee idee, final String identification, final CallBack<IIdee> callback) throws RemoteException;
       
        /**
         * Permet d'envoyer une idee sur le serveur qu'il la sauvegarde.
         * L'idee passee en parametre doit posseder un identifiant serveur non vide
         * ("".equalsIgnoreCase(idee.getIdServeur()) == false)
         * Cet identifiant aura ete fourni par le CallBack de la methode creerIdee.
         * Si l'identifiant serveur est vide, la methode onFailure du CallBack sera
         * appelee et l'idee ne sera pas mise a jour. Il faudra utiliser la methode
         * creerIdee auparavant.
         * Si l'identifiant serveur n'est pas vide mais qu'il ne correspond pas a une
         * idee possedee par l'utilisateur auquel le jeton d'identification correspond
         * alors la methode onFailure du CallBack sera appelee.
         * @param idee L'idee a mettre a jour.
         * @param mode Le mode de mise a jour.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void envoyerIdee(final IIdee idee, final ModeDeMiseAJour mode, final String identification, final CallBack<IIdee> callback) throws RemoteException;
       
        /**
         * Permet de supprimer une idee sur le serveur.
         * L'idee passee en parametre doit posseder un identifiant serveur non vide
         * ("".equalsIgnoreCase(idee.getIdServeur()) == false)
         * Cet identifiant aura ete fourni par le CallBack de la methode creerIdee.
         * Si l'identifiant serveur est vide, la methode onFailure du CallBack sera
         * appelee et l'idee ne sera pas mise a jour. Il faudra utiliser la methode
         * creerIdee auparavant.
         * Si l'identifiant serveur n'est pas vide mais qu'il ne correspond pas a une
         * idee possedee par l'utilisateur auquel le jeton d'identification correspond
         * alors la methode onFailure du CallBack sera appelee.
         * @param idee L'idee a supprimer.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void supprimerIdee(final IIdee idee, final String identification, final CallBack<String> callback) throws RemoteException;
       
        /**
         * Permet de creer une tache sur le serveur.
         * La tache passee en parametre doit posseder un identifiant serveur vide
         * ("".equalsIgnoreCase(tache.getIdServeur()) == true)
         * Si l'identifiant serveur n'est pas vide, alors cette tache est consideree
         * comme existant deja sur le serveur, la methode onFailure du CallBack sera
         * donc appelee et la tache ne sera pas creee sur le serveur.
         * @param tache La tache a creer.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void creerTache(final ITache tache, final String identification, final CallBack<ITache> callback) throws RemoteException;
       
        /**
         * Permet d'envoyer une tache sur le serveur pour qu'il la sauvegarde.
         * La tache passee en parametre doit posseder un identifiant serveur non vide
         * ("".equalsIgnoreCase(tache.getIdServeur()) == false)
         * Cet identifiant aura ete fourni par le CallBack de la methode creerTache.
         * Si l'identifiant serveur est vide, la methode onFailure du CallBack sera
         * appelee et la tache ne sera pas mise a jour. Il faudra utiliser la methode
         * creerTache auparavant.
         * Si l'identifiant serveur n'est pas vide mais qu'il ne correspond pas a une
         * tache possedee par l'utilisateur auquel le jeton d'identification correspond
         * alors la methode onFailure du CallBack sera appelee.
         * @param tache La tache a mettre a jour.
         * @param mode Le mode de mise a jour.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void envoyerTache(final ITache tache, final ModeDeMiseAJour mode, final String identification, final CallBack<ITache> callback) throws RemoteException;
       
        /**
         * Permet de supprimer une tache sur le serveur.
         * La tache passee en parametre doit posseder un identifiant serveur non vide
         * ("".equalsIgnoreCase(tache.getIdServeur()) == false)
         * Cet identifiant aura ete fourni par le CallBack de la methode creerTache.
         * Si l'identifiant serveur est vide, la methode onFailure du CallBack sera
         * appelee et la tache ne sera pas mise a jour. Il faudra utiliser la methode
         * creerTache auparavant.
         * Si l'identifiant serveur n'est pas vide mais qu'il ne correspond pas a une
         * tache possedee par l'utilisateur auquel le jeton d'identification correspond
         * alors la methode onFailure du CallBack sera appelee.
         * @param tache La tache a supprimer.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void supprimerTache(final ITache tache, final String identification, final CallBack<String> callback) throws RemoteException;
       
        /**
         * Permet de creer un projet sur le serveur.
         * Le projet passe en parametre doit posseder un identifiant serveur vide
         * ("".equalsIgnoreCase(projet.getIdServeur()) == true)
         * Si l'identifiant serveur n'est pas vide, alors ce projet est considere
         * comme existant deja sur le serveur, la methode onFailure du CallBack sera
         * donc appelee et le projet ne sera pas cree sur le serveur.
         * @param projet Le projet a creer.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void creerProjet(final IProjet projet, final String identification, final CallBack<IProjet> callback) throws RemoteException;
       
        /**
         * Permet d'envoyer un projet sur le serveur pour qu'il le sauvegarde.
         * Le projet passe en parametre doit posseder un identifiant serveur non vide
         * ("".equalsIgnoreCase(projet.getIdServeur()) == false)
         * Cet identifiant aura ete fourni par le CallBack de la methode creerProjet.
         * Si l'identifiant serveur est vide, la methode onFailure du CallBack sera
         * appelee et le projet ne sera pas mise a jour. Il faudra utiliser la methode
         * creerProjet auparavant.
         * Si l'identifiant serveur n'est pas vide mais qu'il ne correspond pas a un
         * projet possede par l'utilisateur auquel le jeton d'identification correspond
         * alors la methode onFailure du CallBack sera appelee.
         * @param projet Le projet a mettre a jour.
         * @param mode Le mode de mise a jour.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void envoyerProjet(final IProjet projet, final ModeDeMiseAJour mode, final String identification, final CallBack<IProjet> callback) throws RemoteException;
       
        /**
         * Permet de supprimer un projet sur le serveur.
         * Le projet passee en parametre doit posseder un identifiant serveur non vide
         * ("".equalsIgnoreCase(projet.getIdServeur()) == false)
         * Cet identifiant aura ete fourni par le CallBack de la methode creerProjet.
         * Si l'identifiant serveur est vide, la methode onFailure du CallBack sera
         * appelee et le projet ne sera pas mise a jour. Il faudra utiliser la methode
         * creerProjet auparavant.
         * Si l'identifiant serveur n'est pas vide mais qu'il ne correspond pas a un
         * projet possede par l'utilisateur auquel le jeton d'identification correspond
         * alors la methode onFailure du CallBack sera appelee.
         * @param projet Le projet a supprimer.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void supprimerProjet(final IProjet projet, final String identification, final CallBack<String> callback) throws RemoteException;
       
        /**
         * Permet de creer un contexte sur le serveur.
         * Le contexte passe en parametre doit posseder un identifiant serveur vide
         * ("".equalsIgnoreCase(contexte.getIdServeur()) == true)
         * Si l'identifiant serveur n'est pas vide, alors ce contexte est considere
         * comme existant deja sur le serveur, la methode onFailure du CallBack sera
         * donc appelee et le contexte ne sera pas cree sur le contexte.
         * @param contexte Le contexte a creer.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void creerContexte(final IContexte contexte, final String identification, final CallBack<IContexte> callback) throws RemoteException;
       
        /**
         * Permet d'envoyer un contexte sur le serveur pour qu'il le sauvegarde.
         * Le contexte passe en parametre doit posseder un identifiant serveur non vide
         * ("".equalsIgnoreCase(contexte.getIdServeur()) == false)
         * Cet identifiant aura ete fourni par le CallBack de la methode creerContexte.
         * Si l'identifiant serveur est vide, la methode onFailure du CallBack sera
         * appelee et le contexte ne sera pas mise a jour. Il faudra utiliser la methode
         * creerProjet auparavant.
         * Si l'identifiant serveur n'est pas vide mais qu'il ne correspond pas a un
         * contexte possede par l'utilisateur auquel le jeton d'identification correspond
         * alors la methode onFailure du CallBack sera appelee.
         * @param contexte Le contexte a mettre a jour.
         * @param mode Le mode de mise a jour.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void envoyerContexte(final IContexte contexte, final ModeDeMiseAJour mode, final String identification, final CallBack<IContexte> callback) throws RemoteException;
       
        /**
         * Permet de supprimer un contexte sur le serveur.
         * Le contexte passee en parametre doit posseder un identifiant serveur non vide
         * ("".equalsIgnoreCase(contexte.getIdServeur()) == false)
         * Cet identifiant aura ete fourni par le CallBack de la methode creerContexte.
         * Si l'identifiant serveur est vide, la methode onFailure du CallBack sera
         * appelee et le contexte ne sera pas mise a jour. Il faudra utiliser la methode
         * creerContexte auparavant.
         * Si l'identifiant serveur n'est pas vide mais qu'il ne correspond pas a un
         * contexte possede par l'utilisateur auquel le jeton d'identification correspond
         * alors la methode onFailure du CallBack sera appelee.
         * @param contexte Le contexte a supprimer.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void supprimerContexte(final IContexte contexte, final String identification, final CallBack<String> callback) throws RemoteException;
       
        /**
         * Permet de creer un tag sur le serveur.
         * Le tag passe en parametre doit posseder un identifiant serveur vide
         * ("".equalsIgnoreCase(tag.getIdServeur()) == true)
         * Si l'identifiant serveur n'est pas vide, alors ce tag est considere
         * comme existant deja sur le serveur, la methode onFailure du CallBack sera
         * donc appelee et le tag ne sera pas cree sur le contexte.
         * @param tag Le tag a creer.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void creerTag(final ITag tag, final String identification, final CallBack<ITag> callback) throws RemoteException;
       
        /**
         * Permet d'envoyer un tag sur le serveur pour qu'il le sauvegarde.
         * Le tag passe en parametre doit posseder un identifiant serveur non vide
         * ("".equalsIgnoreCase(tag.getIdServeur()) == false)
         * Cet identifiant aura ete fourni par le CallBack de la methode creerTag.
         * Si l'identifiant serveur est vide, la methode onFailure du CallBack sera
         * appelee et le tag ne sera pas mise a jour. Il faudra utiliser la methode
         * creerTag auparavant.
         * Si l'identifiant serveur n'est pas vide mais qu'il ne correspond pas a un
         * tag possede par l'utilisateur auquel le jeton d'identification correspond
         * alors la methode onFailure du CallBack sera appelee.
         * @param tag Le tag a mettre a jour.
         * @param mode Le mode de mise a jour.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void envoyerTag(final ITag tag, final ModeDeMiseAJour mode, final String identification, final CallBack<ITag> callback) throws RemoteException;
       
        /**
         * Permet de supprimer un tag sur le serveur.
         * Le tag passee en parametre doit posseder un identifiant serveur non vide
         * ("".equalsIgnoreCase(tag.getIdServeur()) == false)
         * Cet identifiant aura ete fourni par le CallBack de la methode creerTag.
         * Si l'identifiant serveur est vide, la methode onFailure du CallBack sera
         * appelee et le tag ne sera pas mise a jour. Il faudra utiliser la methode
         * creerTag auparavant.
         * Si l'identifiant serveur n'est pas vide mais qu'il ne correspond pas a un
         * tag possede par l'utilisateur auquel le jeton d'identification correspond
         * alors la methode onFailure du CallBack sera appelee.
         * @param tag Le tag a mettre a jour.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void supprimerTag(final ITag tag, final String identification, final CallBack<String> callback) throws RemoteException;
       
        // ################################ METHODE DE RECUPERATION ################################
       
        /**
         * Permet de telecharger tout le contenu de l'inbox.
         * Le callback de cette methode permettra de recuperer la liste des idees
         * n'etant pas a la poubelle triee par nom.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeInbox(final String identification, final CallBack<List<IIdee>> callback) throws RemoteException;
       
        /**
         * Permet de telecharger tout le contenu de l'inbox.
         * Le callback de cette methode permettra de recuperer la liste des idees
         * n'etant pas a la poubelle triee par nom.
         * La date permet de choisir a partir de quelle date de derniere modification
         * le serveur doit il commencer a renvoyer les idees.
         * @param date La date de derniere modification desiree.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeInbox(final Date date, final String identification, final CallBack<List<IIdee>> callback) throws RemoteException;
       
        /**
         * Permet de telecharger tout le contenu de la poubelle.
         * La poubelle pouvant contenir des taches, des idees ou des projets
         * le callback permet de recuperer une liste d'objet serveur.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargePoubelle(final String identification, final CallBack<List<IObjetServeur>> callback) throws RemoteException;
       
        /**
         * Permet de telecharger tout le contenu de la poubelle.
         * La poubelle pouvant contenir des taches, des idees ou des projets
         * le callback permet de recuperer une liste d'objet serveur.
         * La date permet de choisir a partir de quelle date de derniere modification
         * le serveur doit il commencer a renvoyer le contenu de la poubelle.
         * @param date La date de derniere modification desiree.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargePoubelle(final Date date, final String identification, final CallBack<List<IObjetServeur>> callback) throws RemoteException;
       
        /**
         * Permet de telecharger tout le contenu des archives.
         * Les archives pouvant contenir des taches ou des projets, le callback permet
         * de recuperer une liste d'objet serveur.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeArchive(final String identification, final CallBack<List<IObjetServeur>> callback) throws RemoteException;
       
        /**
         * Permet de telecharger tout le contenu des archives.
         * Les archives pouvant contenir des taches ou des projets, le callback permet
         * de recuperer une liste d'objet serveur.
         * La date permet de choisir a partir de quelle date de derniere modification
         * le serveur doit il commencer a renvoyer le contenu des archives.
         * @param date La date de derniere modification desiree.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeArchive(final Date date, final String identification, final CallBack<List<IObjetServeur>> callback) throws RemoteException;
       
        /**
         * Permet de telecharger la liste des prochaines taches a realiser.
         * La liste des prochaines taches a realiser sera retournee grace
         * au callback. Ce dernier fournira une liste des taches triee par
         * priorite, echeance, et taux d'effort.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeProchainesTaches(final String identification, final CallBack<List<ITache>> callback) throws RemoteException;
       
        /**
         * Permet de telecharger la liste des prochaines taches a realiser.
         * La liste des prochaines taches a realiser sera retournee grace
         * au callback. Ce dernier fournira une liste des taches triee par
         * priorite, echeance, et taux d'effort.
         * La date permet de choisir a partir de quelle date de derniere modification
         * le serveur doit il commencer a renvoyer les prochaines taches.
         * @param date La date de derniere modification desiree.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeProchainesTaches(final Date date, final String identification, final CallBack<List<ITache>> callback) throws RemoteException;
       
        /**
         * Permet de telecharger la liste des prochaines taches a realiser pour un contexte fourni.
         * La liste des prochaines taches a realiser sera retournee grace
         * au callback. Ce dernier fournira une liste des taches triee par
         * priorite, echeance, et taux d'effort.
         * @param contexte Le contexte desire.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeProchainesTachesParContexte(final IContexte contexte, final String identification, final CallBack<List<ITache>> callback) throws RemoteException;
       
        /**
         * Permet de telecharger la liste des prochaines taches a realiser pour un contexte fourni.
         * La liste des prochaines taches a realiser sera retournee grace
         * au callback. Ce dernier fournira une liste des taches triee par
         * priorite, echeance, et taux d'effort.
         * @param contexte Le contexte desire.
         * @param date La date de derniere modification desiree.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeProchainesTachesParContexte(final Date date, final IContexte contexte, final String identification, final CallBack<List<ITache>> callback) throws RemoteException;
       

        /**
         * Permet de telecharger la liste des prochaines taches dans le calendrier.
         * La liste retournee contiendra les prochaines tache triee par date d'echeance
         * priorite et taux d'effort. Seules les taches qui ont une date d'echeance
         * pourront etre retournees.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeCalendrier(final String identification, final CallBack<List<ITache>> callback) throws RemoteException;
       
        /**
         * Permet de telecharger la liste des prochaines taches dans le calendrier.
         * La liste retournee contiendra les prochaines tache triee par date d'echeance
         * priorite et taux d'effort. Seules les taches qui ont une date d'echeance
         * pourront etre retournees.
         * @param date La date de derniere modification desiree.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeCalendrier(final Date date, final String identification, final CallBack<List<ITache>> callback) throws RemoteException;
       
        /**
         * Permet de telecharger la liste des prochaines taches a realiser pour un tag fourni.
         * La liste des prochaines taches a realiser sera retournee grace
         * au callback. Ce dernier fournira une liste des taches triee par
         * priorite, echeance, et taux d'effort.
         * @param tag Le tag desire.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeTacheParTag(final ITag tag, final String identification, final CallBack<List<ITache>> callback) throws RemoteException;
       
        /**
         * Permet de telecharger la liste des prochaines taches a realiser pour un tag fourni.
         * La liste des prochaines taches a realiser sera retournee grace
         * au callback. Ce dernier fournira une liste des taches triee par
         * priorite, echeance, et taux d'effort.
         * @param date La date de derniere modification desiree.
         * @param tag Le tag desire.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeTacheParTag(final Date date, final ITag tag, final String identification, final CallBack<List<ITache>> callback) throws RemoteException;
       
       
        // ################################ METHODE DE RECUPERATION COMPLETE ################################
       
       
        /**
         * Permet de recuperer tous les tags.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeTags(final String identification, final CallBack<List<ITag>> callback) throws RemoteException;
       
        /**
         * Permet de recuperer tous les contextes.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeContextes(final String identification, final CallBack<List<IContexte>> callback) throws RemoteException;
       
        /**
         * Permet de recuperer tous les participants.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeParticipants(final String identification, final CallBack<List<IParticipant>> callback) throws RemoteException;
       
        /**
         * Permet de recuperer toutes les taches.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeTaches(final String identification, final CallBack<List<ITache>> callback) throws RemoteException;
       
        /**
         * Permet de recuperer tous les projets.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeProjets(final String identification, final CallBack<List<IProjet>> callback) throws RemoteException;
       
        /**
         * Permet de recuperer toutes les idees.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargeIdees(final String identification, final CallBack<List<IIdee>> callback) throws RemoteException;
       
       
        // ################################ METHODE DE GESTION DE COMPTE ################################
       
       
        /**
         * Permet de creer un compte.
         * La methode onFailure du callback sera appelee si le nom d'utilisateur
         * est deja pris, si le pseudo est deja pris, si le mot de passe
         * est invalide, ou si le nouveau mot de passe ne respecte pas les
         * criteres de validite d'un mot de passe.
         * @param username Le nom d'utilisateur desire.
         * @param password Le mot de passe desire.
         * @param pseudo Le pseudonyme desire.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void creerCompte(final String username, final String password, final String pseudo, final CallBack<String> callback) throws RemoteException;
       
        /**
         * Permet de supprimer un compte.
         * La methode onFailure du callback sera appelee si le nom d'utilisateur
         * et le mot de passe ne correspondent pas au compte auquel correspond le
         * jeton d'identification. On ne peut supprimer un compte qui n'est pas
         * le sien.
         * @param username Le nom d'utilisateur du compte.
         * @param password Le mot de passe du compte.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void supprimerCompte(final String username, final String password, final String identification, final CallBack<String> callback) throws RemoteException;
       
        /**
         * Permet de modifier le pseudonyme de son compte.
         * @param pseudo Le nouveau pseudonyme desire.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void modifierPseudo(final String pseudo, final String identification, final CallBack<String> callback) throws RemoteException;
       
        /**
         * Permet de modifier le mot de passe de son compte.
         * La methode onFailure du callback sera appelee si le mot de passe
         * est invalide, ou si le nouveau mot de passe ne respecte pas les
         * criteres de validite d'un mot de passe.
         * @param oldpPassword L'ancien mot de passe.
         * @param newPassword Le nouveau mot de passe.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void modifierMotDePasse(final String oldpPassword, final String newPassword, final String identification, final CallBack<String> callback) throws RemoteException;
       
        /**
         * Permet de s'identifier sur le serveur.
         * Cette methode renvoie avec la methode onSuccess du callback
         * le jeton d'identification.
         * @param username Le nom d'utilisateur du compte.
         * @param password Le mot de passe
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void login(final String username, final String password, final CallBack<String> callback) throws RemoteException;
       
        /**
         * Permet de fermer sa session sur le serveur.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void disconnect(final String identification, final CallBack<String> callback) throws RemoteException;
       
        /**
         * Permet de recuperer le log des actions realisees sur le compte.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargerLog(final String identification, final CallBack<List<String>> callback) throws RemoteException;
       
        /**
         * Permet de recuperer le log des actions realisees sur le compte depuis la date demandee.
         * @param date La date a partir de laquelle le log retourne doit commencer.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargerLog(final Date date, final String identification, final CallBack<List<String>> callback) throws RemoteException;
       
       
        // ################################ METHODE ADMINISTRATEUR ################################
       
       
        /**
         * Permet de recuperer le log de l'administrateur.
         * Cette methode n'est accessible qu'avec un jeton d'identification
         * correspondant a un administrateur.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargerLogAdmin(final String identification, final CallBack<List<String>> callback) throws RemoteException;
       
        /**
         * Permet de recuperer la liste des idees d'un participant.
         * Cette methode n'est accessible qu'avec un jeton d'identification
         * correspondant a un administrateur.
         * @param username Le nom d'utilisateur du participant.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargerIdees(final String username, final String identification, final CallBack<List<IIdee>> callback) throws RemoteException;
       
        /**
         * Permet de recuperer la liste des taches d'un participant.
         * Cette methode n'est accessible qu'avec un jeton d'identification
         * correspondant a un administrateur.
         * @param username Le nom d'utilisateur du participant.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargerTaches(final String username, final String identification, final CallBack<List<ITache>> callback) throws RemoteException;
       
        /**
         * Permet de recuperer la liste des projets d'un participant.
         * Cette methode n'est accessible qu'avec un jeton d'identification
         * correspondant a un administrateur.
         * @param username Le nom d'utilisateur du participant.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargerProjets(final String username, final String identification, final CallBack<List<IProjet>> callback) throws RemoteException;
       
        /**
         * Permet de recuperer la liste des tags d'un participant.
         * Cette methode n'est accessible qu'avec un jeton d'identification
         * correspondant a un administrateur.
         * @param username Le nom d'utilisateur du participant.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargerTags(final String username, final String identification, final CallBack<List<ITag>> callback) throws RemoteException;
       
        /**
         * Permet de recuperer la liste des contextes d'un participant.
         * Cette methode n'est accessible qu'avec un jeton d'identification
         * correspondant a un administrateur.
         * @param username Le nom d'utilisateur du participant.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargerContextes(final String username, final String identification, final CallBack<List<IContexte>> callback) throws RemoteException;
       
        /**
         * Permet de recuperer le log d'un participant.
         * Cette methode n'est accessible qu'avec un jeton d'identification
         * correspondant a un administrateur.
         * @param username Le nom d'utilisateur du participant.
         * @param identification Le jeton d'identification.
         * @param callback Le callback.
         * @throws RemoteException En cas de probleme avec RMI.
         */
        void telechargerLog(final String username, final String identification, final CallBack<List<String>> callback) throws RemoteException;
}
