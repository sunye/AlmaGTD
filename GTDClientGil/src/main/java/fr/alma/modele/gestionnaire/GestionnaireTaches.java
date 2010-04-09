package fr.alma.modele.gestionnaire;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.ITache;
import fr.alma.modele.persistance.BD;

/**
 * Classe GestionnaireTaches faisant le lien entre l'IHm le noyau et la base de données
 * @version 1.0
 * @author Université de Nantes
 */
public class GestionnaireTaches implements IGestionnaireTaches {

    /**
     * la base de données
     */
    private BD database;
    /**
     * la liste de projets en cours
     */
    private List<IProjet> projets;

    /**
     * Constructeur.
     */
    public GestionnaireTaches() {
        this.database = new BD();
        this.projets = new ArrayList<IProjet>();
    }

    @Override
    public Boolean creerTache(ITache tache, IProjet projet) {
        //Start of user code for creerTache method body
        projet.getTaches().add(tache);
        return null;
        //End of user code
    }

    @Override
    public Boolean modifierTache(ITache tache) {

        Boolean etat = false;
        //Start of user code for modifierTache method body
        for (IProjet p : projets) {
            ITache tche = p.getTache(tache.getId());
            if (tche != null) {
                tche = tache;
                etat = true;
            }
        }
        return etat;
        //End of user code
    }

    @Override
    public Boolean supprimerTache(ITache tache) {
        Boolean etat = false;
        //Start of user code for supprimerTache method body
        for (IProjet p : projets) {
            if (p.supprimerTache(tache.getId())) {
                etat = true;
            }
        }
        return etat;
        //End of user code
    }

    @Override
    public Boolean creerProjet(IProjet projet) {
        //Start of user code for creerProjet method body
        this.projets.add(projet);
        return true;
        //End of user code
    }

    @Override
    public Boolean modifierProjet(IProjet projet) {
        Boolean etat = false;
        //Start of user code for modifierProjet method body
        for (IProjet p : projets) {
            if (p.getId() == projet.getId()) {
                p = projet;
                etat = true;
            }
        }
        return etat;
        //End of user code
    }

    @Override
    public Boolean supprimerProjet(IProjet projet) {
        Boolean etat = false;
        //Start of user code for supprimerProjet method body
        for (IProjet p : projets) {
            if (p.getId() == projet.getId()) {
                projets.remove(projet);
                etat = true;
            }
        }
        return etat;
        //End of user code
    }

    @Override
    public List<ITache> getTaches() {
        //Start of user code for getTaches method body
        List<ITache> tacheList = new ArrayList<ITache>();
        for (IProjet p : projets) {
            tacheList = p.getAllTaches(tacheList);
        }
        return tacheList;
        //End of user code
    }

    @Override
    public List<IProjet> getModifs(Date date) {
        //Start of user code for getModifs method body
        //TODO
        return null;
        //End of user code
    }

    @Override
    public IProjet getProjetRacine() {
        return database.recupererProjetRacine();
    }

    //**************************************************
    //		         GETTERS AND SETTERS
    //**************************************************
    @Override
    public BD getBd() {
        return database;
    }

    @Override
    public void setBd(BD dbase) {
        this.database = dbase;
    }
}
