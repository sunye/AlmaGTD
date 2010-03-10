package fr.alma.gtd.isessions;

import java.util.Date;
import java.util.List;

import fr.alma.gtd.donneespartagees.ITag;

/**
 * Interface du Bean Session charge de gerer la creation, modification, suppression et l'interrogation des Beans Entites Tag.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface ITagServiceRemote {

		/**
		 * Cree un Bean entite (persistant) a partir de l'objet passe en parametre sous forme d'interface.
		 * @param tag Tag que l'on veut creer (persister).
		 * @return le tag cree.
		 */
		ITag creerTag(ITag tag);

		/**
		 * Retourne l'ensemble des tags stockes par le serveur GTD.
		 * @return l'ensemble des tags stockes par le serveur GTD.
		 */
		List<ITag> getAllTag();

		/**
		 *	Retourne le tag identifie par l'identifiant passe en parametre.
		 *	@param identifiantServeur identifiant de l'objet recherche.
		 *	@return Liste contenant l'objet recherche si celui-ci existe, vide sinon.
		 */
		ITag getTagById(final String identifiantServeur);

		/**
		 * Retourne le tag dont le nom correspond a la chaine de caracteres passee en parametre.
		 *	@param nom nom du participant recherche.
		 *	@return liste contenant les participants correspondant a la recherche.
		 */
		List<ITag> getTagByName(final String nom);

		/**
		 *	Retourne les tags cree par le participant identifie par l'identifiant passe en parametre.
		 *	@param idCreator identifiant du createur.
		 *	@return Liste contenant les objet recherches si celui-ci existe, vide sinon.
		 */
		List<ITag> getTagByCreator(final String idCreator);
		
		/**
		 *	Retourne les tags cree par le participant identifie par l'identifiant passe en parametre ayant ete modifie apres la date passe en parametre (utile pour se mettre a jour).
		 *	@param idCreator identifiant du createur.
		 *  @param dateModif tous les tags retournes auront ete modifie apres cette date.
		 *	@return Liste contenant les objet recherches si celui-ci existe, vide sinon.
		 */
		List<ITag> getTagByCreator(String idCreateur, Date dateModif);
		
		/**
		 * Supprime tous les tags stockes sur le serveur - a utliser avec parcimonie.
		 */
		void removeAll();

		/**
		 * Supprime le tag dont l'identifiant correspond a celui passe en parametre.
		 * @param identifiantServeur L'identifiant du tag a supprimer.
		 */
		void removeTagById(final String identifiantServeur);

		ITag updateTag(String identifiantServeur, ITag contexte);


}
