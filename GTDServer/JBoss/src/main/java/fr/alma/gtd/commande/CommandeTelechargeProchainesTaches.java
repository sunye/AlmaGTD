package fr.alma.gtd.commande;

import java.util.Date;
import java.util.List;

import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.interfacedistante.CallBack;

public class CommandeTelechargeProchainesTaches implements Commande {

	public CommandeTelechargeProchainesTaches(CallBack<List<ITache>> callback) {
		
	}

	public CommandeTelechargeProchainesTaches(Date date, CallBack<List<ITache>> callback) {
		
	}

	@Override
	public void execute() {
		
	}

}
