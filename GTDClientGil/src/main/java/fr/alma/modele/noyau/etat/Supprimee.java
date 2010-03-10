package fr.alma.modele.noyau.etat;

import fr.alma.modele.noyau.ITache;

/**
 * Classe Supprimee représentant l'etat d'une tache supprimee
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public class Supprimee extends Etat {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -2307092150026505001L;

	@Override
	public void aFaire(ITache t) {}

	@Override
	public void archiver(ITache t) {}

	@Override
	public void deleguer(ITache t) {}

	@Override
	public void enAttente(ITache t) {}

	@Override
	public void enCours(ITache t) {
		//t.setEtat(Etat.EN_COURS);
	}

	@Override
	public void supprimer(ITache t) {}

	@Override
	public void terminer(ITache t) {}
}
