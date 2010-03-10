package fr.alma.modele.noyau.etat;

import fr.alma.modele.noyau.ITache;

/**
 * Classe Archivee représentant l'etat d'archive d'une tache 
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public class Archivee extends Etat {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 4282972134760265065L;

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
	public void supprimer(ITache t) {
		//t.setEtat(Etat.SUPPRIMEE);
	}

	@Override
	public void terminer(ITache t) {}
}
