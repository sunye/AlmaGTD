package fr.alma.gtd.isessions;

import java.util.List;

import fr.alma.gtd.donneesserveur.Utilisateur;

/**
 * Interface du Bean Session charge de gerer la creation, modification, suppression et l'interrogation des Beans Entites Utilisateur.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface IUtilisateurServiceRemote {

		/**
		 * Cree un Bean entite (persistant) a partir de l'objet passe en parametre sous forme d'interface.
		 * @param p Utilisateur que l'on veut creer (persister).
		 * @return Le Utilisateur cree.
		 */
		Utilisateur creerUtilisateur(Utilisateur p);

		/**
		 * Retourne l'ensemble des Utilisateurs stockes par le serveur GTD.
		 * @return l'ensemble des Utilisateurs stockes par le serveur GTD.
		 */
		List<Utilisateur> getAllUtilisateur();

		/**
		 *	Retourne le Utilisateur identifie par l'identifiant passe en parametre.
		 *	@param identifiantServeur identifiant de l'objet recherche
		 *	@return Liste contenant l'objet recherche si celui-ci existe, vide sinon.
		 */
		Utilisateur getUtilisateurById(final String identifiantServeur);

		/**
		 * Retourne le Utilisateur dont le nom correspond au pseudonyme passe en parametre.
		 *	@param pseudonyme nom du Utilisateur recherche
		 *	@return liste contenant les Utilisateurs correspondant a la recherche.
		 */
		List<Utilisateur> getUtilisateurByLogin(final String pseudonyme);

		/**
		 * Supprime tous les Utilisateurs stockes sur le serveur - a utliser avec parcimonie.
		 */
		void removeAll();

		/**
		 * Supprime le Utilisateur dont l'identifiant correspond a celui passe en parametre.
		 * @param identifiantServeur L'identifiant du Utilisateur a supprimer. 
		 */
		void removeUtilisateurById(final String identifiantServeur);

		/**
		 * Met a jour l'utilisateur a partir de son identifiant.
		 * @param identifiantServeur identifiant de l'utilisateur a mettre a jour
		 * @param util nouvelle version de l'utilisateur
		 * @return nouvelle version de l'utilisateur
		 */
		Utilisateur updateUtilisateur(String identifiantServeur,
				Utilisateur util);

}
