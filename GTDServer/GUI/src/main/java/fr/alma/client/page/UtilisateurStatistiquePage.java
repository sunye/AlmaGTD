package fr.alma.client.page;

import java.util.List;

import com.extjs.gxt.charts.client.Chart;
import com.extjs.gxt.charts.client.model.ChartModel;
import com.extjs.gxt.charts.client.model.Legend;
import com.extjs.gxt.charts.client.model.Legend.Position;
import com.extjs.gxt.charts.client.model.charts.PieChart;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.custom.Portal;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

import fr.alma.client.model.Examples;
import fr.alma.client.model.IPageIHM;
import fr.alma.client.model.PieChartData;
import fr.alma.client.model.ServerData;

public class UtilisateurStatistiquePage implements IPageIHM  {
	
	private List<PieChartData> data;
	
	public UtilisateurStatistiquePage(List<PieChartData> userStats) {
		data=userStats;
	}


	@Override
	public Portal getPortalPanel() {
			      
	    String url = !Examples.isExplorer() ? "../../" : "";    
	    url += "resources/chart/open-flash-chart.swf";  
	      
	    final Chart chart = new Chart(url);  
	    chart.setBorders(true);  
	    chart.setChartModel(getPieChartData());    
	    
		Portal portal = new Portal(2);
		portal.setBorders(true);
		portal.setStyleAttribute("backgroundColor", "white");
		portal.setColumnWidth(0, .75);
		portal.setColumnWidth(1, .25);

		Portlet portlet = new Portlet();
		portlet.setHeading("Statistique - Utilisateur");
		configPanel(portlet);
		portlet.setLayout(new FitLayout());
		portlet.add(chart);
		
		portlet.setHeight(250);

		portal.add(portlet, 0);


		portlet = new Portlet();
		portlet.setHeading("Aide 1");
		configPanel(portlet);
		portlet.addText(getAide1Text());
		portal.add(portlet, 1);

		return portal;
	}
	

	private void configPanel(final ContentPanel panel) {
		panel.setCollapsible(true);
		panel.setAnimCollapse(true);
	}

	private String getAide1Text() {
		return "<div class=text style='padding: 5px'>Passer votre souris sur les statistiques pour obtenir des informations supplémentaires</div>";
	}

	@Override
	public HorizontalPanel getRecherchePanel() {
		GeneralHistoriquePage recherche = new GeneralHistoriquePage(ServerData.getStocks());
		return recherche.getRecherchePanel();
	}
	
	private ChartModel getPieChartData() {  
	    ChartModel cm = new ChartModel("Nombre d'actions par utilisateur",  
	        "font-size: 14px; font-family: Verdana; text-align: center;");  
	    cm.setBackgroundColour("#fffff5");  
	    Legend lg = new Legend(Position.RIGHT, true);  
	    lg.setPadding(10);  
	    lg.setMargin(10);
	    cm.setLegend(lg);  
	      
	    PieChart pie = new PieChart();  
	    pie.setAlpha(0.5f);  
	    pie.setNoLabels(true);  
	    pie.setTooltip("#label# : #val# actions<br>#percent#");  
	    pie.setColours("#ff0000", "#00aa00", "#0000ff", "#ff9900", "#ff00ff");
	    
	    for (PieChartData slice : data) {
	    	 pie.addSlices(new PieChart.Slice(slice.getValeur(), slice.getLabel(), slice.getText()));  
	    }
	  
	    cm.addChartConfig(pie);  
	    return cm;  
	  }

}
