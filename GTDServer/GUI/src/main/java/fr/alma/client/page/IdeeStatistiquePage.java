package fr.alma.client.page;

import java.util.List;

import com.extjs.gxt.charts.client.Chart;
import com.extjs.gxt.charts.client.model.ChartModel;
import com.extjs.gxt.charts.client.model.axis.XAxis;
import com.extjs.gxt.charts.client.model.axis.YAxis;
import com.extjs.gxt.charts.client.model.charts.CylinderBarChart;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.custom.Portal;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;

import fr.alma.client.model.Examples;
import fr.alma.client.model.IPageIHM;
import fr.alma.client.model.ServerData;

public class IdeeStatistiquePage implements IPageIHM  {
	
	private List<Integer> data;

	public IdeeStatistiquePage(List<Integer> ideeStats) {
		data=ideeStats;
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
		portlet.setHeading("Statistique - Idee");
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
		return "<div class=text style='padding: 5px'>Passer votre souris sur les histogrammes pour obtenir des informations supplémentaires</div>";
	}

	@Override
	public HorizontalPanel getRecherchePanel() {
		GeneralHistoriquePage recherche = new GeneralHistoriquePage(ServerData.getStocks());
		return recherche.getRecherchePanel();
	}

	private ChartModel getPieChartData() {  
		ChartModel cm = new ChartModel("Nombre d'idées par mois", "font-size: 14px; font-family: Verdana; text-align: center;padding:10px;");  
		cm.setBackgroundColour("#ffffff");  
		XAxis xa = new XAxis();  
		xa.setLabels("JAN", "FEB", "MAR", "AVR", "MAI", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC");  
		xa.setZDepth3D(5);  
		xa.setTickHeight(4);  
		xa.setOffset(true);  
		xa.setColour("#909090");  
		cm.setXAxis(xa);  
		YAxis ya = new YAxis();  
		ya.setSteps(1);  
		ya.setMax(10);  
		cm.setYAxis(ya);  
		CylinderBarChart bchart = new CylinderBarChart();  
		bchart.setColour("#440088");  
		bchart.setAlpha(.8f);  
		bchart.setTooltip("#val# idées");  
		
		for (Integer i : data) {
			bchart.addValues(i); 
		}

		cm.addChartConfig(bchart);        
		return cm;  
	}

}
