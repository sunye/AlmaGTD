package fr.alma.gtd.commande;

import java.util.Date;
import java.util.List;

import fr.alma.gtd.donneespartagees.ITache;
import fr.alma.gtd.donneespartagees.ITag;
import fr.alma.gtd.interfacedistante.CallBack;

public class CommandeTelechargeParTag implements Commande {

	public CommandeTelechargeParTag(ITag tag, CallBack<List<ITache>> callback) {
		
	}

	public CommandeTelechargeParTag(Date date, ITag tag, CallBack<List<ITache>> callback) {
		
	}

	@Override
	public void execute() {
		
	}

}
