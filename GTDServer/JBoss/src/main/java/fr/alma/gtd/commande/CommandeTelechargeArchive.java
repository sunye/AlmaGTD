package fr.alma.gtd.commande;

import java.util.Date;
import java.util.List;

import fr.alma.gtd.donneespartagees.IObjetServeur;
import fr.alma.gtd.interfacedistante.CallBack;

public class CommandeTelechargeArchive implements Commande {

	public CommandeTelechargeArchive(Date date, CallBack<List<IObjetServeur>> callback) {
		
	}

	public CommandeTelechargeArchive(CallBack<List<IObjetServeur>> callback) {
		
	}

	@Override
	public void execute() {
		
	}

}
