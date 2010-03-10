package fr.alma.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.alma.client.model.Idee;
import fr.alma.client.model.PieChartData;
import fr.alma.client.model.Projet;
import fr.alma.client.model.Stock;
import fr.alma.client.model.Tache;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("loadData")
public interface LoadDataService extends RemoteService {
	
	List<Stock> getStocks();
	
	List<Idee> getIdee();
	
	List<Tache> getTache();
	
	List<Projet> getProjet();

	List<Stock> getSeekStocks();

	List<PieChartData> getUserStats();

	List<Integer> getIdeeStats();

	List<Integer> getTacheStats();

	List<Integer> getProjetStats();
	
	List<Stock> getDataStock(String input, String type);

	List<Idee> getDataIdee(String text, String type);

	List<Tache> getDataTache(String text, String type);

	List<Projet> getDataProjet(String text, String type);
}
