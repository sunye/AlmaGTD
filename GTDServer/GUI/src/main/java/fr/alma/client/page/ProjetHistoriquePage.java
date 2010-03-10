package fr.alma.client.page;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
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
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
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

import fr.alma.client.model.ChampComboBox;
import fr.alma.client.model.Error;
import fr.alma.client.model.IPageIHM;
import fr.alma.client.model.Projet;
import fr.alma.client.model.ServerData;
import fr.alma.client.model.Stock;
import fr.alma.client.model.Tache;
import fr.alma.client.model.TypeChamp;
import fr.alma.client.service.LoadDataService;
import fr.alma.client.service.LoadDataServiceAsync;

public class ProjetHistoriquePage implements IPageIHM {

private static boolean rafrachir = false;
	
	private List<Projet> data;
	
	private LoadDataServiceAsync dataService = GWT.create(LoadDataService.class);

	
	public ProjetHistoriquePage(List<Projet> projet) {
		data=projet;
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
		combo.setEmptyText("Selectionnez un type...");  
		combo.setDisplayField("name");  
		combo.setWidth(100);  
		combo.setStore(ServerData.getProjetChamp()); 
		combo.setValue(new TypeChamp("Tous"));
		combo.setTypeAhead(true);  
		combo.setTriggerAction(TriggerAction.ALL);  
		cp.add(combo);

		Button rechercher = new Button("Rechercher", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {

				dataService.getDataProjet(textBox.getText(), combo.getValue().getType(),
						new AsyncCallback<List<Projet>>() {
					public void onFailure(Throwable caught) {
						Error.showLoadError();
					}

					public void onSuccess(List<Projet> result) {
						ServerData.setDataProjet(result);
						if (rafrachir) {
							rafrachir=false;
							History.newItem("104");							
						} else {
							rafrachir=true;
							History.newItem("105");
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
		portlet.setHeading("Historique - Projet");
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
	@SuppressWarnings("unchecked")
	private Grid<Projet> createGrid() {

		List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

		ColumnConfig column = new ColumnConfig();
		column.setId("name");
		column.setHeader("Nom");
		column.setWidth(200);
		
		TextField text = new TextField<String>();  
		text.setAllowBlank(false);  
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig();
		column.setId("contexte");
		column.setHeader("Contexte");
		column.setWidth(75);
		
		text = new TextField<String>();  
		text.setAllowBlank(false);  
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig();
		column.setId("createur");
		column.setHeader("Créateur");
		column.setWidth(75);
		
		text = new TextField<String>();  
		text.setAllowBlank(false);  
		column.setEditor(new CellEditor(text));
		configs.add(column);

		column = new ColumnConfig("date", "Date", 100);  
		column.setDateTimeFormat(DateTimeFormat.getMediumDateFormat());  
		
		DateField dateField = new DateField(); 
		dateField.setAllowBlank(false);
		dateField.getPropertyEditor().setFormat(DateTimeFormat.getFormat("MM/dd/y"));
		column.setEditor(new CellEditor(dateField));
		configs.add(column);  
		
		column = new ColumnConfig();
		column.setId("avancement");
		column.setHeader("Avancement");
		column.setWidth(100);
		
		final SimpleComboBox<String> combo = new SimpleComboBox<String>();  
	    combo.setForceSelection(true);  
	    combo.setTriggerAction(TriggerAction.ALL);
	    for (String avancement : ChampComboBox.getAvancements()) {
	    	combo.add(avancement);  
	    }
	  
	    CellEditor editor = new CellEditor(combo) {  
	      @Override  
	      public Object preProcessValue(Object value) {  
	        if (value == null) {  
	          return value;  
	        }  
	        return combo.findModel(value.toString());  
	      }  
	  
	      @Override  
	      public Object postProcessValue(Object value) {  
	        if (value == null) {  
	          return ChampComboBox.getAvancements().get(0);  
	        }  
	        return ((ModelData) value).get("value");  
	      }  
	    };  
	
		column.setEditor(editor);
		configs.add(column);

		ListStore<Projet> store = new ListStore<Projet>();
		store.add(data);

		ColumnModel cm = new ColumnModel(configs);

		RowEditor<Stock> re = new RowEditor<Stock>();
		Grid<Projet> g = new Grid<Projet>(store, cm);
		g.addPlugin(re);
		g.setAutoExpandColumn("name");
		g.setBorders(true);
		return g;
	}

}
