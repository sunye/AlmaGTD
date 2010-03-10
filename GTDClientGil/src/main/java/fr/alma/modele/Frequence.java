package fr.alma.modele;

import java.io.Serializable;

public enum Frequence implements Serializable {
	EVENEMENT_PONCTUEL,
	JOURNALIERE,
	HEBDOMADAIRE,
	MENSUELLE;
	
	@Override
	public String toString() {
		if (this == EVENEMENT_PONCTUEL) {
			return "Evénement ponctuel";
		} else if (this == JOURNALIERE) {
			return "Journalière";
		} else if (this == HEBDOMADAIRE) {
			return "Hebdomadaire";
		} else {
			return "Mensuelle";
		}
	}
}