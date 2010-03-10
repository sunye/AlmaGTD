/*
 * Ext GWT - Ext for GWT
 * Copyright(c) 2007-2009, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package fr.alma.client.model;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.AccordionLayout;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class Navigation extends LayoutContainer {

  public Navigation() {

    ContentPanel panel = new ContentPanel();
    panel.setHeading("Menu");
    panel.setBodyBorder(false);

    panel.setLayout(new AccordionLayout());

    ContentPanel cp = new ContentPanel();

    cp.setBodyStyleName("pad-text");
    
    //HISTORIQUE
    cp.setHeading("Historique");
    
    //Lien general
    Hyperlink link0 = new Hyperlink("General", "1");
    link0.addClickListener(new ClickListener() {
		
		@Override
		public void onClick(Widget sender) {
			History.newItem("1");			
		}
	});
    cp.add(link0);
    
    //Idee
    Hyperlink link1 = new Hyperlink("Idee", "2");
    link1.addClickListener(new ClickListener() {
		
		@Override
		public void onClick(Widget sender) {
			History.newItem("2");			
		}
	});
    cp.add(link1);
    
  //Tache
    Hyperlink link2 = new Hyperlink("Tache", "3");
    link2.addClickListener(new ClickListener() {
		
		@Override
		public void onClick(Widget sender) {
			History.newItem("3");			
		}
	});
    cp.add(link2);
    
  //Projet
    Hyperlink link3 = new Hyperlink("Projet", "4");
    link3.addClickListener(new ClickListener() {
		
		@Override
		public void onClick(Widget sender) {
			History.newItem("4");			
		}
	});
    cp.add(link3);

    cp.addStyleName("listeNavigation");
    panel.add(cp);

   
    
    //STATISTIQUE
    cp = new ContentPanel();

    cp.setBodyStyleName("pad-text");
    cp.setHeading("Statistique");
    
    //Utilisateur
    Hyperlink linkutil = new Hyperlink("Utilisateur", "5");
    linkutil.addClickListener(new ClickListener() {
		
		@Override
		public void onClick(Widget sender) {
			History.newItem("5");			
		}
	});
    cp.add(linkutil); 
    
  //Idee
    Hyperlink linkidee = new Hyperlink("Idee", "6");
    linkidee.addClickListener(new ClickListener() {
		
		@Override
		public void onClick(Widget sender) {
			History.newItem("6");			
		}
	});
    cp.add(linkidee);
    
  //tache
    Hyperlink linktache = new Hyperlink("Tache", "7");
    linktache.addClickListener(new ClickListener() {
		
		@Override
		public void onClick(Widget sender) {
			History.newItem("7");			
		}
	});
    cp.add(linktache);
    
    //Projet
    Hyperlink linkproj = new Hyperlink("Projet", "8");
    linkproj.addClickListener(new ClickListener() {
		
		@Override
		public void onClick(Widget sender) {
			History.newItem("8");			
		}
	});
    cp.add(linkproj);    
    
    cp.addStyleName("listeNavigation");
    panel.add(cp);
    
    
    //Help
    cp = new ContentPanel();

    cp.setBodyStyleName("pad-text");
    cp.setHeading("Aide");
    
    //help
    Hyperlink linkhelp = new Hyperlink("FAQ", "9");
    linkutil.addClickListener(new ClickListener() {
		
		@Override
		public void onClick(Widget sender) {
			History.newItem("9");			
		}
	});
    cp.add(linkhelp); 
    
    cp.addStyleName("listeNavigation");
    panel.add(cp);

    panel.setSize(200, 200);

    add(panel);
        
  }

}
