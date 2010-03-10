package fr.alma.gtd.isessions;

import java.util.List;

import fr.alma.gtd.donneespartagees.IParticipant;

/**
 * Interface du Bean Session charge de gerer la creation, modification, suppression et l'interrogation des Beans Entites Participant.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface IParticipantServiceRemote {

		/**
		 * Cree un Bean entite (persistant) a partir de l'objet passe en parametre sous forme d'interface.
		 * @param p Participant que l'on veut creer (persister).
		 * @return Le participant cree.
		 */
		IParticipant creerParticipant(IParticipant p);

		/**
		 * Retourne l'ensemble des participants stockes par le serveur GTD.
		 * @return l'ensemble des participants stockes par le serveur GTD.
		 */
		List<IParticipant> getAllParticipant();

		/**
		 *	Retourne le participant identifie par l'identifiant passe en parametre.
		 *	@param identifiantServeur identifiant de l'objet recherche
		 *	@return Liste contenant l'objet recherche si celui-ci existe, vide sinon.
		 */
		IParticipant getParticipantById(final String identifiantServeur);

		/**
		 * Retourne le participant dont le nom correspond au pseudonyme passe en parametre.
		 *	@param pseudonyme nom du participant recherche
		 *	@return liste contenant les participants correspondant a la recherche.
		 */
		List<IParticipant> getParticipantByPseudo(final String pseudonyme);

		/**
		 * Supprime tous les participants stockes sur le serveur - a utliser avec parcimonie.
		 */
		void removeAll();

		/**
		 * Supprime le participant dont l'identifiant correspond a celui passe en parametre.
		 * @param identifiantServeur L'identifiant du participant a supprimer. 
		 */
		void removeParticipantById(final String identifiantServeur);

		IParticipant updateParticipant(String identifiantServeur,
				IParticipant contexte);

}
