package fr.univnantes.alma.gtd.model.gestionnaireressources;

public class Date extends GTDEntity {
	private Integer annee;
	private Integer mois;
	private Integer jour;
	
	
	public Date() {

	}
	public Date(Integer annee, Integer mois, Integer jour) {
		super();
		this.annee = annee;
		this.mois = mois;
		this.jour = jour;
	}
	public Integer getAnnee() {
		return annee;
	}
	public void setAnnee(Integer annee) {
		this.annee = annee;
	}
	public Integer getMois() {
		return mois;
	}
	public void setMois(Integer mois) {
		this.mois = mois;
	}
	public Integer getJour() {
		return jour;
	}
	public void setJour(Integer jour) {
		this.jour = jour;
	}
	
	public String toString(){
		return String.valueOf(this.jour)+"/"+String.valueOf(this.mois)
			+"/"+String.valueOf(this.annee);
	}
	

}
