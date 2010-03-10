package fr.alma.client;

import java.util.List;

import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

import fr.alma.client.model.Error;
import fr.alma.client.model.Idee;
import fr.alma.client.model.PieChartData;
import fr.alma.client.model.Projet;
import fr.alma.client.model.ServerData;
import fr.alma.client.model.Stock;
import fr.alma.client.model.Tache;
import fr.alma.client.page.ConnexionErrorPage;
import fr.alma.client.page.ConnexionPage;
import fr.alma.client.page.FAQPage;
import fr.alma.client.page.GeneralHistoriquePage;
import fr.alma.client.page.IdeeHistoriquePage;
import fr.alma.client.page.IdeeStatistiquePage;
import fr.alma.client.page.ProjetHistoriquePage;
import fr.alma.client.page.ProjetStatistiquePage;
import fr.alma.client.page.TacheHistoriquePage;
import fr.alma.client.page.TacheStatistiquePage;
import fr.alma.client.page.UtilisateurStatistiquePage;
import fr.alma.client.service.LoadDataService;
import fr.alma.client.service.LoadDataServiceAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
@SuppressWarnings("deprecation")
public class IHMGtd implements EntryPoint, HistoryListener {

	private LoadDataServiceAsync stockDataService = GWT
	.create(LoadDataService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {		

		RootPanel.get("portalIHM").setHeight("400px");
		RootPanel.get("portalIHM").add(new ConnexionPage());
		
		History.addHistoryListener(this);
		
	}

	public void onHistoryChanged(String historyToken) {

		int numPage = Integer.parseInt(historyToken);

		switch (numPage) {
		
		case -1 :
			RootPanel.get("portalIHM").clear();	
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new ConnexionErrorPage());
			break;
			
		case 0 :
			RootPanel.get("portalIHM").clear();	
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new ConnexionErrorPage());
			break;
		
		case 1 :
			stockDataService.getStocks(new AsyncCallback<List<Stock>>() {

				@Override
				public void onSuccess(List<Stock> result) {
					//Ajoute le portail IHM à la page
					RootPanel.get("portalIHM").clear();	
					RootPanel.get("portalIHM").setHeight("400px");
					RootPanel.get("portalIHM").add(new PortalIHM(new GeneralHistoriquePage(result)));
				}

				@Override
				public void onFailure(Throwable caught) {
					Error.showLoadError();
				}
			});

			//Chargement
			RootPanel.get("portalIHM").clear();	
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new HTML("Chargement..."));
			break;
			
			
		case 2 :
			stockDataService.getIdee(new AsyncCallback<List<Idee>>() {
				
				@Override
				public void onSuccess(List<Idee> result) {
					//Ajoute le portail IHM à la page
					RootPanel.get("portalIHM").clear();
					RootPanel.get("portalIHM").setHeight("400px");
					RootPanel.get("portalIHM").add(new PortalIHM(new IdeeHistoriquePage(result)));
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					MessageBox.alert("Erreur", "Impossible de récupérer vos données", null);									
				}
			});

			//Chargement
			RootPanel.get("portalIHM").clear();	
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new HTML("Chargement..."));			
			break;

			
			
		case 3 :
			
			stockDataService.getTache(new AsyncCallback<List<Tache>>() {
				
				@Override
				public void onSuccess(List<Tache> result) {
					RootPanel.get("portalIHM").clear();
					RootPanel.get("portalIHM").setHeight("400px");
					RootPanel.get("portalIHM").add(new PortalIHM(new TacheHistoriquePage(result)));
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Error.showLoadError();					
				}
			});
			//Chargement
			RootPanel.get("portalIHM").clear();	
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new HTML("Chargement..."));			
			break;

			
			
		case 4 :
			stockDataService.getProjet(new AsyncCallback<List<Projet>>() {
				
				@Override
				public void onSuccess(List<Projet> result) {
					RootPanel.get("portalIHM").clear();
					RootPanel.get("portalIHM").setHeight("400px");
					RootPanel.get("portalIHM").add(new PortalIHM(new ProjetHistoriquePage(result)));
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Error.showLoadError();					
				}
			});			
			//Chargement
			RootPanel.get("portalIHM").clear();	
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new HTML("Chargement..."));	
			break;

			
			
		case 5 :
			stockDataService.getUserStats(new AsyncCallback<List<PieChartData>>() {
				
				@Override
				public void onSuccess(List<PieChartData> result) {
					RootPanel.get("portalIHM").clear();
					RootPanel.get("portalIHM").setHeight("400px");
					RootPanel.get("portalIHM").add(new PortalIHM(new UtilisateurStatistiquePage(result)));					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Error.showLoadError();
					
				}
			});
			//Chargement
			RootPanel.get("portalIHM").clear();	
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new HTML("Chargement..."));	
			break;

		case 6 :
			stockDataService.getIdeeStats(new AsyncCallback<List<Integer>>() {
				
				@Override
				public void onSuccess(List<Integer> result) {
					RootPanel.get("portalIHM").clear();
					RootPanel.get("portalIHM").setHeight("400px");
					RootPanel.get("portalIHM").add(new PortalIHM(new IdeeStatistiquePage(result)));
					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Error.showLoadError();
					
				}
			});
			//Chargement
			RootPanel.get("portalIHM").clear();
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new HTML("Chargement..."));
			break;

		case 7 :
			stockDataService.getTacheStats(new AsyncCallback<List<Integer>>() {

				@Override
				public void onFailure(Throwable caught) {
					Error.showLoadError();
					
				}

				@Override
				public void onSuccess(List<Integer> result) {
					RootPanel.get("portalIHM").clear();
					RootPanel.get("portalIHM").setHeight("400px");
					RootPanel.get("portalIHM").add(new PortalIHM(new TacheStatistiquePage(result)));
					
				}
			});
			//Chargement
			RootPanel.get("portalIHM").clear();	
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new HTML("Chargement..."));
			break;

		case 8 :
			stockDataService.getProjetStats(new AsyncCallback<List<Integer>>() {
				
				@Override
				public void onSuccess(List<Integer> result) {
					RootPanel.get("portalIHM").clear();
					RootPanel.get("portalIHM").setHeight("400px");
					RootPanel.get("portalIHM").add(new PortalIHM(new ProjetStatistiquePage(result)));					
				}
				
				@Override
				public void onFailure(Throwable caught) {
					Error.showLoadError();
					
				}
			});
			//Chargement
			RootPanel.get("portalIHM").clear();	
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new HTML("Chargement..."));
			break;
			
			
		case 9 :
			RootPanel.get("portalIHM").clear();
			RootPanel.get("portalIHM").setHeight("900px");
			RootPanel.get("portalIHM").add(new PortalIHM(new FAQPage()));
			break;

		case 98 :
			RootPanel.get("portalIHM").clear();
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new PortalIHM(new GeneralHistoriquePage(ServerData.getSeekStocks())));
			break;
			
		case 99 :
			RootPanel.get("portalIHM").clear();
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new PortalIHM(new GeneralHistoriquePage(ServerData.getSeekStocks())));
			break;
			
		case 100 :
			RootPanel.get("portalIHM").clear();
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new PortalIHM(new IdeeHistoriquePage(ServerData.getDataIdee())));
			break;
			
		case 101 :
			RootPanel.get("portalIHM").clear();
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new PortalIHM(new IdeeHistoriquePage(ServerData.getDataIdee())));
			break;
			
		case 102 :
			RootPanel.get("portalIHM").clear();
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new PortalIHM(new TacheHistoriquePage(ServerData.getDataTache())));
			break;
			
		case 103 :
			RootPanel.get("portalIHM").clear();
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new PortalIHM(new TacheHistoriquePage(ServerData.getDataTache())));
			break;

		case 104 :
			RootPanel.get("portalIHM").clear();
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new PortalIHM(new ProjetHistoriquePage(ServerData.getDataProjet())));
			break;
			
		case 105 :
			RootPanel.get("portalIHM").clear();
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new PortalIHM(new ProjetHistoriquePage(ServerData.getDataProjet())));
			break;
			
		default :
			stockDataService.getStocks(new AsyncCallback<List<Stock>>() {

				@Override
				public void onSuccess(List<Stock> result) {
					//Ajoute le portail IHM à la page
					RootPanel.get("portalIHM").clear();
					RootPanel.get("portalIHM").setHeight("400px");
					RootPanel.get("portalIHM").add(new PortalIHM(new GeneralHistoriquePage(result)));
				}

				@Override
				public void onFailure(Throwable caught) {
					MessageBox.alert("Erreur", "Impossible de récupérer vos données", null);				
				}
			});

			//Chargement
			RootPanel.get("portalIHM").clear();	
			RootPanel.get("portalIHM").setHeight("400px");
			RootPanel.get("portalIHM").add(new HTML("Chargement..."));
			break;
		}
	}



}
