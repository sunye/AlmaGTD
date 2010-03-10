package fr.alma.gtd.commande;

import java.util.List;

import fr.alma.gtd.donneespartagees.IProjet;
import fr.alma.gtd.interfacedistante.CallBack;

public class CommandeTelechargeProjets implements Commande {

	public CommandeTelechargeProjets(CallBack<List<IProjet>> callback) {
		
	}

	public CommandeTelechargeProjets(String username, CallBack<List<IProjet>> callback) {
		
	}

	@Override
	public void execute() {
		
	}

}
