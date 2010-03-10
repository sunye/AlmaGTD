package fr.alma.gtd.commande;

import java.util.List;

import fr.alma.gtd.donneespartagees.ITag;
import fr.alma.gtd.interfacedistante.CallBack;

public class CommandeTelechargeTags implements Commande {

	public CommandeTelechargeTags(CallBack<List<ITag>> callback) {
		
	}

	public CommandeTelechargeTags(String username, CallBack<List<ITag>> callback) {
		
	}

	@Override
	public void execute() {
		
	}

}
