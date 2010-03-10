package fr.alma.modele.serveur;

import java.util.Date;

public interface ISynchronisation {
	public Date getDerniereSynchro();
	//public ListeActionProjet updateActions();
	//public ListeActionProjet commitActions();
	public Boolean estSynchro();
	//public  fusionner(List<IProjet> listeServeur, List<IProjet> listeLocale);
}
