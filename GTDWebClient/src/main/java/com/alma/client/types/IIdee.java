package com.alma.client.types;

/**
 * Interface representant les idees.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface IIdee extends IObjetServeur {

	/**
	 * @return Le nom.
	 */
	String getNom();

	/**
	 * @param n La nouvelle valeur du nom.
	 */
	void setNom(final String n);

	/**
	 * @return La description.
	 */
	String getDescription();

	/**
	 * @param desc La nouvelle valeur de la description.
	 */
	void setDescription(final String desc);

	/**
	 * Supprime l'idee, en la mettant a la poubelle.
	 */
	void mettreALaPoubelle();
	
	/**
	 * Restaurer la tache depuis la poubelle.
	 */
	void restaurer();
	
	/**
	 * @return Le boolean indiquant si l'idee est dans la poubelle.
	 */
	boolean isDansLaPoubelle();

	/**
	 * @param estDansPoubelle La nouvelle valeur du boolean indiquant si l'idee est dans la poubelle.
	 */
	void setDansLaPoubelle(final boolean estDansArchive);
	
	/**
	 * @return Le boolean indiquant si l'idee a ete archivee.
	 */
	boolean isDansArchive();

	/**
	 * @param estDansPoubelle La nouvelle valeur du boolean indiquant si l'idee a ete archivee.
	 */
	void setDansArchive(final boolean estDansArchive);

	/**
	 * @return Le createur de l'idee.
	 */
	IParticipant getCreateur();

	/**
	 * @param c La nouvelle valeur du createur.
	 */
	void setCreateur(final IParticipant c);

}
