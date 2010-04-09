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
	protected String identServeur;
	/**
	 * La date de derniere modification.
	 */
	protected Date dateDeDerModif;
	public String getIdentServeur() {
		return identServeur;
	}
	public void setIdentServeur(final String identServeur) {
		this.identServeur = identServeur;
	}
	public Date getDateDeDerModif() {
		return dateDeDerModif;
	}
	public void setDateDeDerModif(final Date dateDeDerModif) {
		this.dateDeDerModif = dateDeDerModif;
	}



}
