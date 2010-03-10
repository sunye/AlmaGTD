package fr.alma.gtd.commande;

import java.util.Date;
import java.util.List;

import fr.alma.gtd.donneespartagees.IIdee;
import fr.alma.gtd.interfacedistante.CallBack;

public class CommandeTelechargeInbox implements Commande {

	public CommandeTelechargeInbox(CallBack<List<IIdee>> callback) {
		
	}

	public CommandeTelechargeInbox(Date date, CallBack<List<IIdee>> callback) {
		
	}
	
	@Override
	public void execute() {
		
	}

}
