package fr.alma.gtd.donneespartagees;

/**
 * Interface representant les tags.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface ITag extends IObjetServeur {

	/**
	 * @return Le nom.
	 */
	String getNom();

	/**
	 * @param n La nouvelle valeur du nom.
	 */
	void setNom(final String nom);

	/**
	 * @return Le createur de l'idee.
	 */
	IParticipant getCreateur();

	/**
	 * @param c La nouvelle valeur du createur.
	 */
	void setCreateur(final IParticipant createur);

}
