package fr.alma.modele.noyau.etat;

import fr.alma.modele.noyau.ITache;

/**
 * Classe Deleguee représentant l'etat de delegation d'une tache 
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */
public class Deleguee extends Etat {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 6217974664204259422L;

	@Override
	public void aFaire(ITache t) {}

	@Override
	public void archiver(ITache t) {}

	@Override
	public void deleguer(ITache t) {}

	@Override
	public void enAttente(ITache t) {}

	@Override
	public void enCours(ITache t) {}

	@Override
	public void supprimer(ITache t) {}

	@Override
	public void terminer(ITache t) {}
}
