package fr.alma.gtd.donneespartagees;

/**
 * Interface representant un participant.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface IParticipant extends IObjetServeur {

        /**
         * @return Le pseudonyme.
         */
        String getPseudonyme();

        /**
         * @param pseudo La nouvelle valeur du pseudonyme.
         */
        void setPseudonyme(final String pseudo);

}

