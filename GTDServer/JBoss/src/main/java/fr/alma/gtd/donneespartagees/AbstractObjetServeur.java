package fr.alma.gtd.donneespartagees;

import java.util.Date;

/**
 * Classe regroupant l'ensemble des comportements communs a tous les objets stockes sur le serveur.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public abstract class AbstractObjetServeur implements IObjetServeur {

	/**
	 * Identifiant de serialisation.
	 */
	private static final long serialVersionUID = -2449592198096455200L;
	
	/**
	 * L'identifiant serveur.
	 */
	protected String identifiantServeur;
	/**
	 * La date de derniere modification.
	 */
	protected Date dateDeDerniereModification;

	@Override
	public final void setIdentifiantServeur(final String idServeur) {
		this.identifiantServeur = idServeur;
	}

	@Override
	public final void setDateDeDerniereModification(final Date dateDerniereModification) {
		this.dateDeDerniereModification = dateDerniereModification;
	}

}
