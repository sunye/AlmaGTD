package fr.alma.gtd.commande;

import java.util.List;

import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.interfacedistante.CallBack;

public class CommandeTelechargeTaches implements Commande {

	public CommandeTelechargeTaches(CallBack<List<ITache>> callback) {
		
	}

	public CommandeTelechargeTaches(String username, CallBack<List<ITache>> callback) {
		
	}

	@Override
	public void execute() {
		
	}

}
