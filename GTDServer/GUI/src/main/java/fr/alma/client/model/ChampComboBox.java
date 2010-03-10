package fr.alma.client.model;

import java.util.ArrayList;

public class ChampComboBox {

	public static ArrayList<String> getAvancements() {
		ArrayList<String> avancements = new ArrayList<String>();
		avancements.add("A faire");
		avancements.add("En attente");
		avancements.add("Delegue");
		avancements.add("Termine");
		return avancements;
	}

}
