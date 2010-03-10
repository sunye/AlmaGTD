package fr.alma.modele.noyau.etat;

import fr.alma.modele.noyau.ITache;

/**
 * Classe EnCours représentant l'etat initial d'une tache 
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public class EnCours extends Etat {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -2905102426751876579L;

	@Override
	public void aFaire(ITache t) {}

	@Override
	public void archiver(ITache t) {
		//t.setEtat(Etat.ARCHIVEE);
	}

	@Override
	public void deleguer(ITache t) {}

	@Override
	public void enAttente(ITache t) {}

	@Override
	public void enCours(ITache t) {}

	@Override
	public void supprimer(ITache t) {
		//t.setEtat(Etat.SUPPRIMEE);
	}

	@Override
	public void terminer(ITache t) {}

}
