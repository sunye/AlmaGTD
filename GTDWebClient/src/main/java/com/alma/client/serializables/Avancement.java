package com.alma.client.serializables;

import java.io.Serializable;

/**
 * Enumeration representant les etats d'avancement possibles d'un projet ou d'une tache.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public enum Avancement implements Serializable{
	
	/**
	 * Indique que la tache ou le projet est a realiser.
	 */
	AFAIRE,
	
	/**
	 * Indique que la tache ou le projet est en attente.
	 */
	ENATTENTE,
	
	/**
	 * Indique que la tache ou le projet a ete delegue a un participant.
	 */
	DELEGUE,
	
	/**
	 * Indique que la tache ou le projet est termine.
	 */
	TERMINE
}
