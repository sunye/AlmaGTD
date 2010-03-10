package fr.alma.modele.noyau.etat;

import fr.alma.modele.noyau.ITache;

/**
 * La classe d'état de la tâche.
 * @author Université de Nantes
 * @version 1.0
 * @since 2009
 */

public abstract class Etat implements IEtat {

	//public static final IEtat A_FAIRE = new CustomerStateActive();
	public static final IEtat ARCHIVEE = new Archivee();
	//public static final IEtat DELEGUEE = new CustomerStateInactive();
	//public static final IEtat EN_ATTENTE = new CustomerStateInactive();
	public static final IEtat EN_COURS = new EnCours();
	public static final IEtat SUPPRIMEE = new Supprimee();
	//public static final IEtat TERMINEE = new CustomerStateInactive();


	@Override
	public abstract void aFaire(ITache t);

	@Override
	public abstract void archiver(ITache t);

	@Override
	public abstract void deleguer(ITache t);

	@Override
	public abstract void enAttente(ITache t);

	@Override
	public abstract void enCours(ITache t);

	@Override
	public abstract void supprimer(ITache t);

	@Override
	public abstract void terminer(ITache t);

	/**
	 * Returns true if the Object's class name matches this Object's class name.
	 */
	@Override
	public final boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		return getClass().getName().equals(obj.getClass().getName());
	}

	/**
	 * Returns the hashCode of the Object's Class name because all instances of this Class are equal.
	 */
	@Override
	public final int hashCode()
	{
		return getClass().getName().hashCode();
	}
}
