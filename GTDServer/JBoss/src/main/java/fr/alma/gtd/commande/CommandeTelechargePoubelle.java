package fr.alma.gtd.commande;

import java.util.Date;
import java.util.List;

import fr.alma.gtd.donneespartagees.IObjetServeur;
import fr.alma.gtd.interfacedistante.CallBack;

public class CommandeTelechargePoubelle implements Commande {

	public CommandeTelechargePoubelle(CallBack<List<IObjetServeur>> callback) {
		
	}

	public CommandeTelechargePoubelle(Date date, CallBack<List<IObjetServeur>> callback) {
		
	}

	@Override
	public void execute() {
		
	}

}
