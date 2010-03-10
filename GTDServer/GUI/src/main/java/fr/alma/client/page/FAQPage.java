package fr.alma.client.page;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.HorizontalPanel;
import com.extjs.gxt.ui.client.widget.custom.Portal;
import com.extjs.gxt.ui.client.widget.custom.Portlet;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.ui.HTML;

import fr.alma.client.model.IPageIHM;
import fr.alma.client.model.ServerData;

public class FAQPage implements IPageIHM {

	public FAQPage() {}

	@Override
	public HorizontalPanel getRecherchePanel() {
		GeneralHistoriquePage recherche = new GeneralHistoriquePage(ServerData.getStocks());
		return recherche.getRecherchePanel();
	}

	@Override
	public Portal getPortalPanel() {
		Portal portal = new Portal(2);
		portal.setBorders(true);
		portal.setStyleAttribute("backgroundColor", "white");
		portal.setColumnWidth(0, .70);
		portal.setColumnWidth(1, .30);

		Portlet portlet = new Portlet();
		portlet.setHeading("Aide - FAQ");
		configPanel(portlet);
		portlet.setLayout(new FitLayout());
		portlet.add(createFAQ());
		portlet.setHeight(800);

		portal.add(portlet, 0);


		portlet = new Portlet();
		portlet.setHeading("Sommaire");
		configPanel(portlet);
		portlet.add(getSommaire());
		portal.add(portlet, 1);

		return portal;
	}

	private HTML getSommaire() {
		String text = "";
		text+="<a href='#9'>What are the system requirements for GWT?</a><br/>";
		text+="<a href='#9'>How do I install GWT?</a><br/>";
		text+="<a href='#9'>Is GWT available in my country? Does it work for my language?</a><br/>";
		text+="<a href='#9'>Does GWT cost anything?</a><br/>";
		text+="<a href='#9'>Why is Google giving this away?</a><br/>";
		text+="<a href='#9'>Will I have to upgrade my application when a new version of GWT is released?</a><br/>";
		text+="<a href='#9'>How do I get my project to run again after upgrading GWT?</a><br/>";
		HTML sommaire = new HTML(text);
		return sommaire;
	}


	/**
	 * 
	 * @return
	 */
	private HTML createFAQ() {


		String text = "";

		text+="<br/><h2>What are the system requirements for GWT?</h2>";
		text+="Google Web Toolkit is designed to run on systems that meet the following requirements:";
		text+=" - Java: Sun Java 2 Runtime Environment 1.5<br/>";
		text+=" - Operating system: Windows Vista/XP/2000, Mac OS X 10.4+ (Tiger or Leopard), or Linux with GTK+ 2.2.1+<br/>";
		text+=" - Hardware: ~100MB of free disk space, 512MB RAM<br/><br/>";
		text+="If you have trouble running GWT, and your system meets the requirements above, let us know on the GWT developer discussion group.";
		text+="<br/><br/><br/><h2>How do I install GWT?</h2>";
		text+="For step-by-step instructions, see Getting Started: Quick Start Installing Google Web Toolkit.";
		text+="<br/><br/><br/><h2>Is GWT available in my country? Does it work for my language?</h2>";
		text+="GWT is available in all countries and should work for most languages, though documentation is currently only available in U.S. English.";
		text+="<br/><br/><br/><h2>Does GWT cost anything?</h2>";
		text+="No, Google Web Toolkit is completely free. In fact, all of GWT's source code is available under the Apache 2.0 open source license.";
		text+="<br/><br/><br/><h2>Why is Google giving this away?</h2>";
		text+="We just have a thing for web-based apps. We've gained quite a lot of experience developing AJAX applications with products like Google Maps and Google Calendar, and GWT is our way of helping developers leverage that expertise in their own AJAX apps.";
		text+="<br/><br/><br/><h2>Will I have to upgrade my application when a new version of GWT is released?</h2>";
		text+="We'll release new versions of GWT on the GWT web site. Barring any unforeseen circumstances, we expect to keep all previous versions of GWT available on the web site as well. As releases become outdated, we'll no longer actively support them on the GWT discussion group, but we'll do our best to keep old releases and documentation around as long as possible.";
		text+="<br/><br/><br/><h2>How do I get my project to run again after upgrading GWT?</h2>";
		text+="If you are working on a project, you may notice problems compiling and running your project if you upgrade the GWT library from one version to another. Sometimes you may see GWT compiler internal errors, or you may see odd runtime errors you've never seen before.";
		text+="Before you file a bug, try stopping hosted mode and erasing your compiler output (usually in the directory www/<package name>. Then, restart hosted mode. If you're still having problems, though, we definitely want to hear about it!";

		return new HTML(text);
	}

	private void configPanel(final ContentPanel panel) {
		panel.setCollapsible(true);
		panel.setAnimCollapse(true);
	}

}
