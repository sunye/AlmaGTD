package fr.alma.gtd.donneespartagees;

/**
 * Interface representant les contextes d'une tache ou le contexte par defaut d'un projet.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface IContexte extends IObjetServeur {

	/**
	 * @return Le nom.
	 */
	String getNom();

	/**
	 * @param n La nouvelle valeur du nom.
	 */
	void setNom(final String n);

	/**
	 * @return Le createur de l'idee.
	 */
	IParticipant getCreateur();

	/**
	 * @param c La nouvelle valeur du createur.
	 */
	void setCreateur(final IParticipant c);

}
