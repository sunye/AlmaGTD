package fr.alma.gtd.donneespartagees;

/**
 * Enumeration representant les differentes frequences de repetition d'une tache possibles.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public enum Frequence {
        
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
