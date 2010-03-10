package fr.alma.client.page;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.RowEditorEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.custom.Portal;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.TextBox;

import fr.alma.client.model.Error;
import fr.alma.client.model.IPageIHM;
import fr.alma.client.model.ServerData;
import fr.alma.client.model.Stock;
import fr.alma.client.model.TypeChamp;
import fr.alma.client.service.LoadDataService;
import fr.alma.client.service.LoadDataServiceAsync;
import fr.alma.client.service.SaveDataService;
import fr.alma.client.service.SaveDataServiceAsync;

public class GeneralHistoriquePage implements IPageIHM {
	
	private static boolean rafrachir = false;

	private List<Stock> data;
	
	private ListStore<Stock> store = new ListStore<Stock>();
	
	private SaveDataServiceAsync saveDataService = GWT
	.create(SaveDataService.class);

	/**
	 * Créer un service distant pour obtenir les résultats de la recherche
	 */
	private LoadDataServiceAsync dataService = GWT.create(LoadDataService.class);

	public GeneralHistoriquePage(List<Stock> stocks) {
		data=stocks;
	}

	@Override
	public HorizontalPanel getRecherchePanel() {
		HorizontalPanel cp = new HorizontalPanel();

		VerticalPanel blank = new VerticalPanel();
		blank.setWidth(250);
		cp.add(blank);

		final TextBox textBox = new TextBox();
		textBox.setWidth("200px");
		cp.add(textBox);

		//ComboBox pour choisir idee, tache ou projet
		final ComboBox<TypeChamp> combo = new ComboBox<TypeChamp>();  

		combo.setValue(new TypeChamp("Tous")); 
		combo.setDisplayField("name");  
		combo.setWidth(100);  
		combo.setStore(ServerData.getTypeChamp());  
		combo.setTypeAhead(true);  
		combo.setTriggerAction(TriggerAction.ALL);  
		cp.add(combo);

		Button rechercher = new Button("Rechercher", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				dataService.getDataStock(textBox.getText(), combo.getValue().getType(),
						new AsyncCallback<List<Stock>>() {
					public void onFailure(Throwable caught) {
						Error.showLoadError();
					}

					public void onSuccess(List<Stock> result) {
						ServerData.setDataStock(result);
						if (rafrachir) {
							rafrachir=false;
							History.newItem("98");							
						} else {
							rafrachir=true;
							History.newItem("99");
						}
					}
				});
			}

		});


		cp.add(rechercher);
		return cp;
	}

	@Override
	public Portal getPortalPanel() {
		Portal portal = new Portal(2);
		portal.setBorders(true);
		portal.setStyleAttribute("backgroundColor", "white");
		portal.setColumnWidth(0, .75);
		portal.setColumnWidth(1, .25);

		Portlet portlet = new Portlet();
		portlet.setHeading("Historique - General");
		configPanel(portlet);
		portlet.setLayout(new FitLayout());
		portlet.add(createGrid());
		portlet.setHeight(250);

		portal.add(portlet, 0);


		portlet = new Portlet();
		portlet.setHeading("Aide 1");
		configPanel(portlet);
		portlet.addText(getAide1Text());
		portal.add(portlet, 1);

		portlet = new Portlet();
		portlet.setHeading("Aide 2");
		configPanel(portlet);
		portlet.addText(getAide2Text());
		portal.add(portlet, 1);
		return portal;
	}

	private String getAide1Text() {
		return "<div class=text style='padding: 5px'>Pour trier vos résultats, cliquez sur les labels des colonnes.</div>";
	}

	private String getAide2Text() {
		return "<div class=text style='padding: 5px'>Effectuer une recherche pour obtenir plus rapidement le résultat souhaité.</div>";
	}

	private void configPanel(final ContentPanel panel) {
		panel.setCollapsible(true);
		panel.setAnimCollapse(true);
	}

	/**
	 * 
	 * @return
	 */
	private Grid<Stock> createGrid() {

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("name");
		column.setHeader("Nom");
		column.setWidth(200);

		TextField<String> text = new TextField<String>();  
		text.setAllowBlank(false);  
		column.setEditor(new CellEditor(text)); 
		configs.add(column);

		column = new ColumnConfig();
		column.setId("type");
		column.setHeader("Type");
		column.setWidth(100);
		configs.add(column);

		column = new ColumnConfig();
		column.setId("createur");
		column.setHeader("Créateur");
		column.setWidth(150);

		text = new TextField<String>();  
		text.setAllowBlank(false);  
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig("date", "Date", 150);  
		column.setDateTimeFormat(DateTimeFormat.getMediumDateFormat());  

		DateField dateField = new DateField(); 
		dateField.setAllowBlank(false);
		dateField.getPropertyEditor().setFormat(DateTimeFormat.getFormat("MM/dd/y"));
		column.setEditor(new CellEditor(dateField)); 
		configs.add(column);  

		store.add(data);

		ColumnModel cm = new ColumnModel(configs);

		RowEditor<Stock> re = new RowEditor<Stock>();
		re.addListener(Events.AfterEdit, new Listener<RowEditorEvent>() {

			public void handleEvent(RowEditorEvent be) {
				
				String id = "0";
				String type = "";
				
				store.commitChanges();
				for (Stock r : store.getModels()) {
					if (be.getRowIndex()==r.getPos()) {
						type= r.get("type");
						id=r.getId();
					}
					
				}
				
				Map<String, Object> changes = be.getChanges();
				Set<String> cles = changes.keySet();
				Iterator<?> it = cles.iterator();
				while (it.hasNext()){
					String cle = (String) it.next(); 
					String valeur;
					if (cle.equals("date")) {
						Date date = (Date) changes.get(cle);
						saveDataService.saveStock(id, type, cle, date, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Error.showSaveError();
								
							}

							@Override
							public void onSuccess(Void result) {
								
							}
							
						});
						
					} else {
						valeur = (String) changes.get(cle);
						saveDataService.saveStock(id, type, cle, valeur, new AsyncCallback<Void>() {

							@Override
							public void onFailure(Throwable caught) {
								Error.showSaveError();
								
							}

							@Override
							public void onSuccess(Void result) {
								
							}
							
						});
					}
					
					
				}
			}

		});
		Grid<Stock> g = new Grid<Stock>(store, cm);
		g.addPlugin(re);
		g.setAutoExpandColumn("name");
		g.setBorders(true);
		return g;
	}

}
