package fr.alma.gtd.donneespartagees;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface representant les projets.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface IProjet extends IObjetServeur {

	/**
	 * Ajoute une tache au projet.
	 * @param t La tache a ajouter.
	 */
	 void ajoutTache(final ITache t);

	/**
	 * Supprime la tache du projet.
	 * @param t La tache a supprimer.
	 */
	 void supprimeTache(final ITache t);

	/**
	 * Supprime toutes les taches du projet.
	 */
	 void supprimerToutesTaches();

	/**
	 * Archive le projet.
	 */
	 void archiver();

	/**
	 * Supprime le projet, en le mettant a la poubelle.
	 */
	 void mettreALaPoubelle();

	/**
	 * Restaurer le projet depuis la poubelle.
	 */
	 void restaurer();

	/**
	 * @return Le nom.
	 */
	 String getNom();

	/**
	 * @param n La nouvelle valeur du nom.
	 */
	 void setNom(final String n);

	/**
	 * @return Le boolean indiquant si l'idee est dans la poubelle.
	 */
	 boolean isDansLaPoubelle();

	/**
	 * @param estDansPoubelle La nouvelle valeur du boolean indiquant si l'idee est dans la poubelle.
	 */
	 void setDansLaPoubelle(final boolean estDansPoubelle);

	/**
	 * @return L'avancement.
	 */
	 Avancement getAvancement();

	/**
	 * @param a La nouvelle valeur de l'avancement.
	 */
	 void setAvancement(final Avancement a);

	/**
	 * @return Le contexteParDefaut.
	 */
	 IContexte getContexteParDefaut();

	/**
	 * @param contexteDefaut La nouvelle valeur du contexte par defaut.
	 */
	 void setContexteParDefaut(final IContexte contexteDefaut);

	/**
	 * @return La listeDesTaches.
	 */
	 List<ITache> getListeDeTaches();

	/**
	 * @param listeTaches La liste des taches.
	 */
	 void setListeDeTaches(final ArrayList<ITache> listeTaches);
	 
	 /**
	 * @return La liste des sous-projets associes a ce projet.
	 */
	 List<IProjet> getListeDeSousProjets();

	 /**
	  * @param La liste des sous-projets associes a ce projet.
	  */
	 void setListeDeSousProjets(final List<IProjet> listeProjets);

	/**
	 * @return La listeDesParticipants.
	 */
	 List<IParticipant> getListeDeParticipants();

	/**
	 * @param listeParticipants La nouvelle valeur de la liste des participants.
	 */
	 void setListeDeParticipants(final List<IParticipant> listeParticipants);

	/**
	 * @return Le createur du projet.
	 */
	 IParticipant getCreateur();

	/**
	 * @param c La nouvelle valeur du createur.
	 */
	 void setCreateur(final IParticipant c);
	 
	 /**
	 * @return Personnes a contacter a propos de cette projet.
	 */
	 List<IContact> getListeContacts();
		
	 /**
	 * @param contacts Personnes a contacter a propos de ce projet.
	 */
	 void setListeContacts(final List<IContact> contacts);

	 /**
	  * @return vrai si le projet est dans les archive, faux sinon.
	  */
	 boolean isDansArchive();
	 
	 /**
	  * @param dansArchive indique si le projet doit etre place ou non dans les archives. 
	  */
	 void setDansArchive(final boolean dansArchive);
}
