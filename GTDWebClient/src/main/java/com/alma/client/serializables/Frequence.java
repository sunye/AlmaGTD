package com.alma.client.serializables;

import java.io.Serializable;

/**
 * Enumeration representant les differentes frequences de repetition d'une tache possibles.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public enum Frequence implements Serializable{
	
	/**
	 * Indique que la tache doit se repeter tous les jours. 
	 */
	JOURNALIER,
	
	/**
	 * Indique que la tache doit se repeter toutes les semaines.
	 */
	HEBDOMADAIRE,
	
	/**
	 * Indique que la tache doit se repeter tous les mois.
	 */
	MENSUEL,
	
	/**
	 * Indique que la tache doit se repeter tous les ans.
	 */
	ANNUEL
}
