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
	private BD bd;

	/**  
	 * la liste de projets en cours
	 */
	private List<IProjet> projets;

	/**
	 * Constructeur.
	 */
	public GestionnaireTaches () {
		this.bd = new BD();
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
		//Start of user code for modifierTache method body
		for(IProjet p : projets){
			ITache t = p.getTache(tache.getId());
			if(t != null){
				t = tache;
				return true;
			}
		}
		return false;
		//End of user code
	}

	@Override
	public Boolean supprimerTache(ITache tache) {
		//Start of user code for supprimerTache method body
		for(IProjet p : projets){
			if(p.supprimerTache(tache.getId())){
				return true;
			}
		}
		return false;
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
		//Start of user code for modifierProjet method body
		for(IProjet p : projets){
			if(p.getId() == projet.getId()){
				p = projet;
				return true;
			}
		}
		return false;
		//End of user code
	}

	@Override
	public Boolean supprimerProjet(IProjet projet) {
		//Start of user code for supprimerProjet method body
		for(IProjet p : projets){
			if(p.getId() == projet.getId()){
				projets.remove(projet);
				return true;
			}
		}
		return false;
		//End of user code
	}

	@Override
	public List<ITache> getTaches() {
		//Start of user code for getTaches method body
		List<ITache> l = new ArrayList<ITache>();
		for(IProjet p : projets){
			l = p.getAllTaches(l);
		}
		return l;
		//End of user code
	}

	public List<IProjet> getModifs(Date date) {
		//Start of user code for getModifs method body
		//TODO
		return null;
		//End of user code
	}
	
	@Override
	public IProjet getProjetRacine() {
		return bd.recupererProjetRacine();
	}

	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	
	@Override
	public BD getBd() {
		return bd;
	}

	@Override
	public void setBd(BD bd) {
		this.bd = bd;
	}
}