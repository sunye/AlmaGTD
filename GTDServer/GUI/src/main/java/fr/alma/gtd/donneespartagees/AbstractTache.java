package fr.alma.gtd.donneespartagees;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe representant les taches.
 * @author Stephane Begaudeau, Benjamin Gosset, Alex Lagarde, Christophe Renaudineau.
 * @version 1.0.0
 */
public abstract class AbstractTache extends AbstractObjetServeur implements ITache {
       
        /**
         * Identifiant de serialisation.
         */
        private static final long serialVersionUID = 1305177706132419383L;

        /**
         * Le nom.
         */
        protected String nom;
       
        /**
         * La priorite.
         */
        protected int priorite;
       
        /**
         * Le taux d'effort.
         */
        protected int tauxEffort;
       
        /**
         * L'avancement.
         */
        protected Avancement avancement;
       
        /**
         * La frequence.
         */
        protected Frequence frequence;
       
        /**
         * La date de debut.
         */
        protected Date dateDebut;
       
        /**
         * La date de fin.
         */
        protected Date dateFin;
       
        /**
         * Indique si la tache est dans la poubelle.
         */
        protected boolean dansLaPoubelle;
       
        /**
         * Indique si la tache est archivee.
         */
        protected boolean dansArchive;
       
        /**
         * Le createur.
         */
        protected IParticipant createur;
       
        /**
         * Le participant.
         */
        protected IParticipant participant;
       
        /**
         * Le contexte.
         */
        protected IObjetServeur contexte;
       
        /**
         * La liste des tags.
         */
        protected List<ITag> listeDesTags;
       
        /**
         * La liste des URLs.
         */
        protected List<String> listeDesURLs;
       
        /**
         * Le projet contenant la tache.
         */
        protected IProjet projetConteneur;

        /**
         * Liste des contacts associes a cette tache.
         */
        protected List<IContact> contacts;
       
        /**
         * Liste des taches devant etre executee avant la tache consideree.
         */
        protected List<ITache> tachesAnterieures;

       
        /**
         * Initialisation des variables.
         */
        {
                this.dansLaPoubelle = false;
                this.dansArchive = false;
                this.priorite = 0;
                this.tauxEffort = 0;
                this.dateDebut = new Date();
                this.dateFin = new Date();
                this.dansLaPoubelle = false;
                this.dateDeDerniereModification = new Date();
                this.listeDesTags = new ArrayList<ITag>();
                this.listeDesURLs = new ArrayList<String>();
                this.tachesAnterieures = new ArrayList<ITache>();
                this.contacts = new ArrayList<IContact>();
        }
       
        /**
         * Le constructeur par defaut.
         */
        public AbstractTache() {
                super();
        }
       
        /**
         * Le constructeur avec initialisation du nom, de la priorite, du taux d'effort, du contexte et du createur.
         * @param n Le nom.
         * @param p La priorite.
         * @param effort Le taux d'effort.
         * @param c Le contexte.
         * @param cr Le createur de la tache.
         */
        public AbstractTache(final String n, final int p, final int effort, final IObjetServeur c, final IParticipant cr) {
                this.nom = n;
                this.priorite = p;
                this.tauxEffort = effort;
                this.contexte = c;
                this.createur = cr;
        }
       
        /**
         * Constructeur de recopie d'une tache.
         * @param t Tache a recopier
         */
        public AbstractTache(final ITache t) {
                this.copier(t);
        }

        public void copier(final ITache t){
                this.nom = t.getNom();
                this.priorite =  t.getPriorite();
                this.tauxEffort = t.getTauxEffort();
                this.dateDebut = t.getDateDebut();
                this.dateFin = t.getDateFin();
                this.dansLaPoubelle = t.isDansLaPoubelle();
                this.identifiantServeur = t.getIdentifiantServeur();
                this.dateDeDerniereModification = t.getDateDeDerniereModification();
                this.createur = t.getCreateur();
                this.participant = t.getParticipant();
                this.contexte = t.getContexte();
                this.listeDesTags = t.getListeDeTags();
                this.listeDesURLs = t.getListeDesURLs();
                this.projetConteneur = t.getProjetConteneur();
        }
       
        @Override
        public final void archiver() {
                //TODO
        }
       
        @Override
        public final void mettreALaPoubelle() {
                //TODO
        }
       
        @Override
        public final  void restaurer() {
                //TODO
        }

        @Override
        public abstract String getNom();

        @Override
        public final void setNom(final String n) {
                this.nom = n;
        }

        @Override
        public abstract int getPriorite();

        @Override
        public final void setPriorite(final int p) {
                this.priorite = p;
        }


        @Override
        public abstract int getTauxEffort();

        @Override
        public final void setTauxEffort(final int effort) {
                this.tauxEffort = effort;
        }

        @Override
        public final void setAvancement(final Avancement a) {
                this.avancement = a;
        }

        @Override
        public final void setFrequence(final Frequence f) {
                this.frequence = f;
        }

        @Override
        public final void setDateDebut(final Date debut) {
                this.dateDebut = debut;
        }

        @Override
        public final void setDateFin(final Date fin) {
                this.dateFin = fin;
        }

        @Override
        public final void setDansLaPoubelle(final boolean dansPoubelle) {
                this.dansLaPoubelle = dansPoubelle;
        }
       
        @Override
        public final void setDansArchive(final boolean dansArchive) {
                this.dansArchive = dansArchive;
        }

        @Override
        public final void setCreateur(final IParticipant c) {
                this.createur = c;
        }

        @Override
        public final void setParticipant(final IParticipant p) {
                this.participant = p;
        }

        @Override
        public final void setContexte(final IContexte c) {
                this.contexte = c;
        }

        @Override
        public final void setListeDeTags(final List<ITag> listeTags) {
                this.listeDesTags = listeTags;
        }

        @Override
        public final void setListeDesURLs(final List<String> listeURLs) {
                this.listeDesURLs = listeURLs;
        }
       
        @Override
        public final void setProjetConteneur(final IProjet projet) {
                this.projetConteneur = projet;
        }
       
        @Override
        public final void setListeTachesAnterieures(final List<ITache> tachesPrecedentes){
                this.tachesAnterieures = tachesPrecedentes;
        }
       
        @Override
        public final void setListeContacts(final List<IContact> contacts){
                this.contacts = contacts;
        }
}

