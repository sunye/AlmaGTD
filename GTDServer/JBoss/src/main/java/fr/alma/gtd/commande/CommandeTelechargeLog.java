package fr.alma.gtd.commande;

import java.util.Date;
import java.util.List;

import fr.alma.gtd.interfacedistante.CallBack;

public class CommandeTelechargeLog implements Commande {

	public CommandeTelechargeLog(CallBack<List<String>> callback) {
		
	}

	public CommandeTelechargeLog(Date date, CallBack<List<String>> callback) {
		
	}

	public CommandeTelechargeLog(String username, CallBack<List<String>> callback) {
		
	}

	@Override
	public void execute() {
		
	}

}
