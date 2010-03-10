package fr.alma.gtd.donneespartagees;

import java.util.Date;
import java.util.List;
/**
 * Interface representant les taches.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public interface ITache extends IObjetServeur {

        /**
         * Archive la tache.
         */
        void archiver();

        /**
         * Supprime la tache, en la mettant a la poubelle.
         */
        void mettreALaPoubelle();

        /**
         * Restaurer la tache depuis la poubelle.
         */
        void restaurer();

        /**
         * @return Le nom.
         */
        String getNom();

        /**
         * @param n La nouvelle valeur du nom.
         */
        void setNom(final String n);

        /**
         * @return La priorite.
         */
        int getPriorite();

        /**
         * @param p La nouvelle valeur de la priorite.
         */
        void setPriorite(final int p);

        /**
         * @return Le tauxEffort.
         */
        int getTauxEffort();

        /**
         * @param effort La nouvelle valeur du taux d'effort.
         */
        void setTauxEffort(final int effort);

        /**
         * @return L'avancement.
         */
        Avancement getAvancement();

        /**
         * @param a La nouvelle valeur de l'avancement.
         */
        void setAvancement(final Avancement a);

        /**
         * @return La frequence.
         */
        Frequence getFrequence();

        /**
         * @param f La nouvelle valeur de la frequence.
         */
        void setFrequence(final Frequence f);

        /**
         * @return La dateDebut.
         */
        Date getDateDebut();

        /**
         * @param debut La nouvelle valeur de la date de debut.
         */
        void setDateDebut(final Date debut);

        /**
         * @return La dateFin.
         */
        Date getDateFin();

        /**
         * @param fin La nouvelle valeur de la date de fin.
         */
        void setDateFin(final Date fin);

        /**
         * @return Un boolean indiquant si la tache est dans la poubelle.
         */
        boolean isDansLaPoubelle();

        /**
         * @param dansPoubelle La nouvelle valeur du boolean indiquant si la tache est dans la poubelle.
         */
        void setDansLaPoubelle(final boolean dansPoubelle);


        /**
         * @return Un boolean indiquant si la tache est dans les archives.
         */
        boolean isDansArchive();
       
        /**
         * @param dansArchive La nouvelle valeur du boolean indiquant si la tache est dans les archives.
         */
        void setDansArchive(boolean dansArchive);
       
        /**
         * @return Le createur du projet.
         */
        IParticipant getCreateur();

        /**
         * @param c La nouvelle valeur du createur.
         */
        void setCreateur(final IParticipant c);

        /**
         * @return Le participant.
         */
        IParticipant getParticipant();

        /**
         * @param p La nouvelle valeur du participant.
         */
        void setParticipant(final IParticipant p);

        /**
         * @return Le contexte.
         */
        IContexte getContexte();

        /**
         * @param c La nouvelle valeur du contexte.
         */
        void setContexte(final IContexte c);

        /**
         * @return La listeDesTags.
         */
        List<ITag> getListeDeTags();

        /**
         * @param listeTags La nouvelle valeur de la liste des tags.
         */
        void setListeDeTags(final List<ITag> listeTags);

        /**
         * @return La listeDesURLs.
         */
        List<String> getListeDesURLs();

        /**
         * @param listeURLs La nouvelle valeur de la liste des URLs.
         */
        void setListeDesURLs(final List<String> listeURLs);

        /**
         * @return Le projet conteneur.
         */
        IProjet getProjetConteneur();

        /**
         * @param projet La nouvelle valeur du projet.
         */
        void setProjetConteneur(final IProjet projet);
       
       
        /**
         * @return Taches devant etre executees avant cette tache.
         */
        List<ITache> getListeTachesAnterieures();
       
        /**
         * @param tachesPrecedentes Taches devant etre executees avant cette tache.
         */
        void setListeTachesAnterieures(final List<ITache> tachesPrecedentes);
       
        /**
         * @return Personnes a contacter a propos de cette tache.
         */
        List<IContact> getListeContacts();
       
        /**
         * @param contacts Personnes a contacter a propos de cette tache.
         */
        void setListeContacts(final List<IContact> contacts);

}

