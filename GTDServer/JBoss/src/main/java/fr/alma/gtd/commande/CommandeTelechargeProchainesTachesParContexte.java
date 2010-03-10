package fr.alma.gtd.commande;

import java.util.Date;
import java.util.List;

import fr.alma.gtd.donneespartagees.IContexte;
import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.interfacedistante.CallBack;

public class CommandeTelechargeProchainesTachesParContexte implements Commande {

	public CommandeTelechargeProchainesTachesParContexte(IContexte contexte, CallBack<List<ITache>> callback) {
		
	}

	public CommandeTelechargeProchainesTachesParContexte(Date date, IContexte contexte, CallBack<List<ITache>> callback) {
		
	}

	@Override
	public void execute() {
		
	}

}
