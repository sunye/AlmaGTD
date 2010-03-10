package fr.alma.client;

import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.alma.client.model.IPageIHM;
import fr.alma.client.model.Navigation;

public class PortalIHM extends LayoutContainer {
	
	private IPageIHM page;

	public PortalIHM(IPageIHM page) {
		this.page=page;
	}

	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);
		setLayout(new BorderLayout());

		BorderLayoutData westData = new BorderLayoutData(LayoutRegion.WEST, 220, 0, 0);
		westData.setMargins(new Margins(5, 0, 5, 5));
		westData.setCollapsible(true);



		BorderLayoutData centerData = new BorderLayoutData(LayoutRegion.CENTER);
		centerData.setMargins(new Margins(5));



		BorderLayoutData northData = new BorderLayoutData(LayoutRegion.NORTH);
		northData.setSize(60);
		northData.setMargins(new Margins(5));

		add(new Navigation(), westData);
		
		VerticalPanel vp = new VerticalPanel();
		Button deco = new Button("Se déconnecter", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				History.newItem("0");
			}
		});
		deco.setIconStyle("deco");
		
		vp.add(deco);
		vp.add(page.getRecherchePanel());
		
		add(vp, northData);
		add(page.getPortalPanel(), centerData);
	}

	public void setPage(IPageIHM pageIHM) {
		this.page=pageIHM;		
	}

}
