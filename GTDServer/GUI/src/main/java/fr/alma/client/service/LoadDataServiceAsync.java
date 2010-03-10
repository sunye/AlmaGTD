package fr.alma.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fr.alma.client.model.Idee;
import fr.alma.client.model.PieChartData;
import fr.alma.client.model.Projet;
import fr.alma.client.model.Stock;
import fr.alma.client.model.Tache;

public interface LoadDataServiceAsync {

	void getStocks(AsyncCallback<List<Stock>> callback);

	void getIdee(AsyncCallback<List<Idee>> callback);

	void getProjet(AsyncCallback<List<Projet>> callback);

	void getTache(AsyncCallback<List<Tache>> callback);

	void getUserStats(AsyncCallback<List<PieChartData>> callback);

	void getIdeeStats(AsyncCallback<List<Integer>> callback);

	void getProjetStats(AsyncCallback<List<Integer>> callback);

	void getSeekStocks(AsyncCallback<List<Stock>> callback);

	void getTacheStats(AsyncCallback<List<Integer>> callback);

	void getDataStock(String input, String type,
			AsyncCallback<List<Stock>> callback);

	void getDataIdee(String text, String type,
			AsyncCallback<List<Idee>> asyncCallback);

	void getDataTache(String text, String type,
			AsyncCallback<List<Tache>> asyncCallback);

	void getDataProjet(String text, String type,
			AsyncCallback<List<Projet>> asyncCallback);
	
	

}
