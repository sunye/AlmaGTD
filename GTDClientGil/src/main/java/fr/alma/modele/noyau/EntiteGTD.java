package fr.alma.modele.noyau;

import java.io.Serializable;

/**
 * Classe abstraite EntiteGTD, représentant chaque entité possédant un identifiant
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public abstract class EntiteGTD implements Serializable {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -1L;

	/** L'identifiant de l'objet */
	private Long id;

	/**
	 * Accesseur à l'identifiant
	 * @return l'identifiant
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Modificateur de l'identifiant
	 * @param id Le nouvel identifiant
	 */
	public void setId(Long id) {
		this.id = id;
	}
}
