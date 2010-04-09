
package fr.alma.gtd.ihm.renseignement;

import java.util.Date;

public class InfosEcheancier {

	private Boolean estActive;
	private Date dateDebut;
	private Date dateFin;
	private String repetition;

	private  InfosEcheancier() {
	}

	public InfosEcheancier(Boolean estActive, Date dateDebut, Date dateFin, String repetition) {
		this.estActive = estActive;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.repetition = repetition;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public Boolean getEstActive() {
		return estActive;
	}

	public String getRepetition() {
		return repetition;
	}

}
