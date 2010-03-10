package fr.alma.gtd.commande;

import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.interfacedistante.CallBack;

import java.util.List;

public class CommandeTelechargeIdees implements Commande {

	public CommandeTelechargeIdees(CallBack<List<IIdee>> callback) {
		
	}

	public CommandeTelechargeIdees(String username, CallBack<List<IIdee>> callback) {
		
	}

	@Override
	public void execute() {
		
	}

}
