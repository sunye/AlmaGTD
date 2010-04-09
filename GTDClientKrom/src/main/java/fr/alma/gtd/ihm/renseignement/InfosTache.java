
package fr.alma.gtd.ihm.renseignement;

import java.util.LinkedList;

public class InfosTache {

	private Integer priorite;
	private Integer tauxEffort;
	private LinkedList<String> listeLiens;
	private LinkedList<String> listeTag;

	private  InfosTache() {
	}

	public InfosTache(Integer priorite, Integer tauxEffort, LinkedList<String> listeLiens, LinkedList<String> listeTags) {
		this.priorite = priorite;
		this.tauxEffort = tauxEffort;
		this.listeLiens = listeLiens;
		this.listeTag = listeTags;
	}

	public Integer getPriorite() {
		return priorite;
	}

	public Integer getTauxEffort() {
		return tauxEffort;
	}

	public LinkedList<String> getListeLiens() {
		return listeLiens;
	}

	public LinkedList<String> getListeTag() {
		return listeTag;
	}

}
