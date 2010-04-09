package fr.alma.modele;

import java.io.Serializable;

public enum Frequence implements Serializable {
	EVENEMENT_PONCTUEL,
	JOURNALIERE,
	HEBDOMADAIRE,
	MENSUELLE;
	
	@Override
	public String toString() {
            String retour = null;
            if (this == EVENEMENT_PONCTUEL) {
			retour = "Evénement ponctuel";
		} else if (this == JOURNALIERE) {
			retour = "Journalière";
		} else if (this == HEBDOMADAIRE) {
			retour = "Hebdomadaire";
		} else {
			retour = "Mensuelle";
		}

            return retour;
	}
}