package fr.alma.modele.noyau;

import java.util.ArrayList;
import java.util.List;

import fr.alma.modele.Modele;

//End of user code

/**
 * Classe représentant un projet.
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public class Projet extends EntiteGTD implements IProjet {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 8783090617992243326L;

	/** le nom du projet  */
	private String nom;

	/** les notes  */
	private String notes;

	/** la liste de tâches associée à ce projet  */
	private List<ITache> taches = new ArrayList<ITache>();

	/** le contexte du projet  */
	private String contexte;

	/** la liste de sou-projets associée  */
	private List<IProjet> sousProjets = new ArrayList<IProjet>();

	/** l'identifiant du projet père  */
	private Long idPere;

	/** l'identifiant utilisateur associé à ce projet  */
	private Long idUtilisateur;

	/**
	 * Constructeur.
	 */
	public Projet () {
		super();
	}

	/**
	 * Constructeur avec paramètre.
	 * @param nom nom du projet
	 * @param contexte contexte du projet
	 * @param notes notes du projet
	 */
	public Projet(String nom, String contexte, String notes) {
		this.nom = nom;
		this.contexte = contexte;
		this.notes = notes;
		this.setIdUtilisateur(Modele.getIdUtilisateur());
	}

	@Override
	public void ajouterTache(ITache t) {
		taches.add(t);
	}

	@Override
	public void ajouterSousProjet(IProjet p) {
		sousProjets.add(p);
	}

	@Override
	public ITache getTache(Long id){
		for(ITache t : taches){
			if(t.getId() == id){
				return t;
			}
		}
		if(sousProjets.size() == 0){
			return null;
		}
		else{
			for(IProjet p : sousProjets){
				return p.getTache(id);
			}
			return null;
		}
	}

	@Override
	public Boolean supprimerTache(Long id){
		for (ITache t : taches){
			if (t.getId() == id){
				this.taches.remove(t);
				return true;
			}
		}
		if (sousProjets.size() == 0) {
			return false;
		} else {
			for(IProjet p : sousProjets) {
				return p.supprimerTache(id);
			}
			return false;
		}
	}

	@Override
	public List<ITache> getAllTaches(List<ITache> l) {
		l.addAll(this.getTaches());
		if (sousProjets.size() == 0) {
			return l;
		} else {
			for(IProjet p : sousProjets) {
				return p.getAllTaches(l);
			}
			return l;
		}
	}

	@Override
	public IProjet getSousProjet(Long id){
		if (this.getId()==id) {
			return this;
		}
		for (IProjet p : sousProjets) {
			if (p.getId() == id) {
				return p;
			}
		}
		return null;
	}

	@Override
	public void getSousProjets(List<IProjet> projets){
		projets.addAll(sousProjets);
		for (IProjet p : sousProjets) {
			p.getSousProjets(projets);
		}
	}

	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	
	@Override
	public List<ITache> getTaches() {
		return taches;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public String getNotes() {
		return notes;
	}

	@Override
	public String getContexte() {
		return contexte;
	}

	@Override
	public List<IProjet> getSousProjets() {
		return sousProjets;
	}
	
	@Override
	public Long getIdPere() {
		return idPere;
	}
	
	@Override
	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	@Override
	public void setTaches(List<ITache> taches) {
		this.taches = taches;
	}

	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public void setContexte(String contexte) {
		this.contexte = contexte;
	}

	@Override
	public void setSousProjets(List<IProjet> sousProjets) {
		this.sousProjets = sousProjets;
	}

	@Override
	public void setIdPere(Long idPere) {
		this.idPere = idPere;
	}

	@Override
	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
}