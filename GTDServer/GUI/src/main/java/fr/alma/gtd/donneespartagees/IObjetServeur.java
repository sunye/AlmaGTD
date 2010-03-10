package fr.alma.gtd.donneespartagees;

import java.io.Serializable;
import java.util.Date;

/**
 * Interface regroupant l'ensemble des comportements communs a tous les objets stockes sur le serveur.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface IObjetServeur extends Serializable {

        /**
         * @return L'identifiantServeur.
         */
        String getIdentifiantServeur();

        /**
         * @param idServeur La nouvelle valeur de l'identifiant serveur.
         */
        void setIdentifiantServeur(final String idServeur);

        /**
         * @return La date de derniere modification.
         */
        Date getDateDeDerniereModification();

        /**
         * @param dateDerniereModification La nouvelle date de derniere modification.
         */
        void setDateDeDerniereModification(final Date dateDerniereModification);

}
