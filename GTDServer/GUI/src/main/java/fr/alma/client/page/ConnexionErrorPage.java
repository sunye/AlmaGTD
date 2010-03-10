package fr.alma.client.page;

import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

import fr.alma.client.model.Error;
import fr.alma.client.service.ConnexionService;
import fr.alma.client.service.ConnexionServiceAsync;

public class ConnexionErrorPage extends LayoutContainer  {
	
	private VerticalPanel vp;
	
	private FormData formData;
	
	private ConnexionServiceAsync connexionService = GWT
	.create(ConnexionService.class);
	
	private static boolean rafraichir = true;
	
	@Override
	protected void onRender(Element parent, int index) {
		super.onRender(parent, index);  
		formData = new FormData("-20"); 
		vp = new VerticalPanel();  
	    vp.setHorizontalAlign(HorizontalAlignment.CENTER); 
	    vp.setPagePosition(800,800);
	    vp.setStyleName("connexion");
	    createForm1(); 
	    add(vp);
	}

	private void createForm1() {
		FormPanel simple = new FormPanel(); 
		simple.setLabelWidth(100);
	    simple.setHeading("Connexion");  
	    simple.setFrame(true);  
	    simple.setWidth(350);  
	    
	    HTML error = new HTML("<p style='color:red;'>Identifiant ou mot de passe incorrect</p>");
	    simple.add(error);
	  
	    final TextField<String> firstName = new TextField<String>();  
	    firstName.setFieldLabel("Login");  
	    firstName.setAllowBlank(false);  
	    simple.add(firstName, formData);  
	    
	    final TextField<String> password = new TextField<String>();  
	    password.setFieldLabel("Mot de passe");  
	    password.setPassword(true);
	    password.setAllowBlank(false);  
	    simple.add(password, formData); 
	    
	    Button b = new Button("Se connecter", new SelectionListener<ButtonEvent>() {

			@Override
			public void componentSelected(ButtonEvent ce) {
				
				connexionService.connectAdmin(firstName.getValue(), password.getValue(),
						new AsyncCallback<Boolean>() {
					public void onFailure(Throwable caught) {
						Error.showConnectError();
					}

					public void onSuccess(Boolean result) {
						
						if (result) {
							History.newItem("1");
						} else {
							if (rafraichir) {
								rafraichir=false;
								History.newItem("-1");
							} else {
								rafraichir=true;
								History.newItem("0");
							}
						}
					}
				});
				
			}
	    });
	    
	    

	    simple.addButton(b);  
	  
	    simple.setButtonAlign(HorizontalAlignment.CENTER);  
	    
	  
	    vp.add(simple);  
		
	}

}
