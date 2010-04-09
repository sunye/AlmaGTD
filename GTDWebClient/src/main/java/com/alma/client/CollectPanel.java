package com.alma.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DragAppearance;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.util.EventHandler;  
import com.smartgwt.client.widgets.events.*;

public class CollectPanel extends AbsolutePanel{
	
	private final DialogBox newIdeaDialogBox;
	private final Button newIdeaValidButton,newIdeaCancelButton;
	private final TextBox ideaTitle;
	private final TextArea ideaContent ;
	private final Canvas canvas;
	private final AlmaGTD agtdgui;
	private static int curAdd=50;
	
	public CollectPanel(final AlmaGTD agtdgui){
		super();
		this.agtdgui=agtdgui;
		this.setSize("99%", "100%");
		
		
		final Button btnNew = new Button("<img src='img/plus.png' width='25' height='25'/>");
        btnNew.addClickHandler(new ClickHandler() {
        	public void onClick(final ClickEvent event) {
        		newIdeaDialogBox.center();
        		canvas.setVisible(false);
				newIdeaValidButton.setFocus(true);
        	}
        });
        btnNew.setSize("43px", "35px");
        	
        //Canva creation
        canvas = new Canvas();        
       
        canvas.setSize("100%","500px");        
        canvas.addStyleName("panel");
        canvas.setZIndex(2);
       
             
        
        final Label trash = new Label("Drop Here to delete note.");  
        
        trash.setTop("15px");  
        trash.setSize("300px","400px");
        trash.setShowEdges(true);  
        trash.setAlign(Alignment.CENTER);  
        trash.setCanAcceptDrop(true);  
        trash.addDropOverHandler(new DropOverHandler() {  
            public void onDropOver(final DropOverEvent event) {  
                trash.setBackgroundColor("#FFFF88");                
            }  
        });  
          
        trash.addDropOutHandler(new DropOutHandler() {  
            public void onDropOut(final DropOutEvent event) {  
                trash.setBackgroundColor("white");  
            }             
        });  
          
        trash.addDropHandler(new DropHandler() {  
            public void onDrop(final DropEvent event) {  
            	final DragLabel target = ((DragLabel)EventHandler.getDragTarget());               
                     
                String title = target.getTitle();
                title=title.substring(4, title.indexOf("</h3>"));
               // SC.say("You dropped the " +  title);
                agtdgui.getListIdeas().remove(title);
                target.destroy();
            }             
        });
        trash.setLeft("70%");
        
        canvas.addChild(trash);
        
        canvas.draw();
        this.add(canvas, 0, 50);
        
        
        this.add(btnNew, 6, 6);
        
	    //Create the popup newIdeaDialogBox box
		newIdeaDialogBox = new DialogBox();
		newIdeaValidButton = new Button("Create note");
		newIdeaCancelButton = new Button("Cancel");
		newIdeaDialogBox.setText("New Note");
		newIdeaDialogBox.setAnimationEnabled(true);
		// We can set the id of a widget by accessing its Element
		newIdeaValidButton.getElement().setId("closeButton");
		newIdeaCancelButton.getElement().setId("closeButton");
		final VerticalPanel newIdeaBoxVPanel = new VerticalPanel();
		newIdeaBoxVPanel.addStyleName("dialogVPanel");
		newIdeaBoxVPanel.add(new HTML("<b>Idea title:</b>"));
		ideaTitle = new TextBox();
		ideaTitle.setWidth("350px");
		newIdeaBoxVPanel.add(ideaTitle);
		newIdeaBoxVPanel.add(new HTML("<br><b>Idea note:</b>"));
		ideaContent = new TextArea();
		ideaContent.setSize("350px","150px");
		
		newIdeaBoxVPanel.add(ideaContent);
		newIdeaBoxVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		//Command buttons
		final HorizontalPanel cmdPanel = new HorizontalPanel();
		cmdPanel.setSpacing(5);
		cmdPanel.add(newIdeaValidButton);
		cmdPanel.add(newIdeaCancelButton);		
		newIdeaBoxVPanel.add(cmdPanel);
		
		newIdeaDialogBox.setWidget(newIdeaBoxVPanel);
		
		// Add a handler to close the newIdeaDialogBox
		newIdeaValidButton.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				newIdeaDialogBox.hide();
				btnNew.setEnabled(true);
				btnNew.setFocus(true);
				addNote(ideaTitle.getText(),ideaContent.getText());
				canvas.setVisible(true);
				ideaTitle.setText("");
				ideaContent.setText("");
			}
		});		
		// Add a handler to close the newIdeaDialogBox doing nothing
		newIdeaCancelButton.addClickHandler(new ClickHandler() {
			public void onClick(final ClickEvent event) {
				newIdeaDialogBox.hide();
				btnNew.setEnabled(true);
				btnNew.setFocus(true);
				canvas.setVisible(true);
				ideaTitle.setText("");
				ideaContent.setText("");
			}
		});
			
		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML srvRespLabel = new HTML();
		final VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(srvRespLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		
		
	}
	
	public DialogBox getNewIdeaDialogBox() {
		return newIdeaDialogBox;
	}
	
	public TextBox getIdeaTitle() {
		return ideaTitle;
	}
	
	public TextArea getIdeaContent() {
		return ideaContent;
	}
	
	public AlmaGTD getAgtdgui() {
		return agtdgui;
	}
	
	public static int getCurAdd() {
		return curAdd;
	}

	public static void setCurAdd(final int curAdd) {
		CollectPanel.curAdd = curAdd;
	}

	public Button getNewIdeaValidButton() {
		return newIdeaValidButton;
	}

	public Button getNewIdeaCancelButton() {
		return newIdeaCancelButton;
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	@Override
	public void setVisible(final boolean visible){
		super.setVisible(visible);
		init();
	}
	
	public void init(){
		for(Canvas child : canvas.getChildren()){
			System.out.println(child.getTitle());
			if((child.getTitle()!=null)&&(child.getTitle().startsWith("<h3>"))){
					canvas.removeChild(child);
			}
		}
		
		final DragLabel dragTranslucent = new DragLabel("Translucent", 50);
		for(String title : agtdgui.getListIdeas().keySet()){
	        dragTranslucent.setDragAppearance(DragAppearance.OUTLINE);
	        dragTranslucent.setDragOpacity(60);
	        dragTranslucent.setContents("<h3>"+title+"</h3>"+"<br><p style='text-align:left;'>"+ agtdgui.getListIdeas().get(title)+"</p>");
	        dragTranslucent.setSize("180px","200px");
	        dragTranslucent.setBaseStyle("postit");
	        canvas.addChild(dragTranslucent);
	        curAdd=curAdd+20;
		}
	}
	
	public void addNote(final String title, final String note){
		
		final DragLabel dragTranslucent = new DragLabel("Translucent", 50);
        dragTranslucent.setDragAppearance(DragAppearance.OUTLINE);
        dragTranslucent.setDragOpacity(60);
        dragTranslucent.setContents("<h3>"+title+"</h3>"+"<br><p style='text-align:left;'>"+note+"</p>");
        dragTranslucent.setSize("180px","200px");
        dragTranslucent.setBaseStyle("postit");
        canvas.addChild(dragTranslucent);
        
        agtdgui.getListIdeas().put(title,note);
        curAdd=curAdd+10;
	}
	
    public static class DragLabel extends Label {
        public DragLabel(final String contents, final int left) {        	
            super();
        	setTop(50);
            setLeft(curAdd);
            setContents(contents);
            setAlign(Alignment.CENTER);
            setPadding(15);
            setShowEdges(false);
            setKeepInParentRect(true);
            setCanDragReposition(true);
            setCanDrop(true);  
            setCanDrag(true);
        }
    }
}
