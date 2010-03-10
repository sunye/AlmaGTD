package fr.alma.gtd.commande;

import java.util.Date;
import java.util.List;

import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.interfacedistante.CallBack;

public class CommandeTelechargeCalendrier implements Commande {

	public CommandeTelechargeCalendrier(CallBack<List<ITache>> callback) {
		
	}
	
	public CommandeTelechargeCalendrier(Date date, CallBack<List<ITache>> callback) {
		
	}

	@Override
	public void execute() {
		
	}

}
