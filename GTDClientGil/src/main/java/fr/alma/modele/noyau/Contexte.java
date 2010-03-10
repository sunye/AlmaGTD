package fr.alma.modele.noyau;

import java.io.Serializable;

/**
 * Enumération Contexte, non encore totalement implémentée
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public enum Contexte implements Serializable, IContexte {
	TRAVAIL,
	MAISON,
	TEL;
	
	/* (non-Javadoc)
	 * @see fr.alma.modele.noyau.IContexte#toString()
	 */
	@Override
	public String toString() {
		if (this == TRAVAIL) {
			return "Travail";
		} else if (this == MAISON) {
			return "Maison";
		} else {
			return "Tél.";
		}
	}
}
