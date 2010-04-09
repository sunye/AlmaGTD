package com.alma.client;

import java.util.Date;
import java.util.List;

import com.alma.client.serializables.Task;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;



public class ViewPanel extends AbsolutePanel{
	private AlmaGTD agtdgui;
	private VerticalPanel listeTaches = new VerticalPanel();
	private  Label dateSelected = new Label("");
	public Date currentDate = new Date();


	public ViewPanel(final AlmaGTD agtdgui){
		super();
		this.agtdgui = agtdgui;

		this.setSize("100%", "99%");
		this.addStyleName("panel");

		//################ Date PIKCER ################
		final DatePicker datePicker = new DatePicker();
		add(datePicker, 16, 108);

		// Set the value in the text box when the user selects a date
		datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(final ValueChangeEvent<Date> event) {
				final Date date = event.getValue();
				final String dateString = DateTimeFormat.getMediumDateFormat().format(date);
				//currentDate = date;
				dateSelected.setText(dateString);
				loadTasks(date);


			}});


		// Set the default value
		datePicker.setValue(new Date(), true);

		loadTasks(new Date());
		// ajot de la liste
		final ListBox dropBox = new ListBox(false);
		add(dropBox, 323, 20);
		dropBox.addItem("Agenda");
		dropBox.addItem("Echéancier");
		dropBox.ensureDebugId("typeAffichage");

		final Label afficher = new Label("Afficher par :");
		add(afficher, 182, 26);
		afficher.setWidth("128px");

		final FlexTable flexTable = new FlexTable();
		add(flexTable, 205, 108);
		flexTable.setSize("483px", "187px");

		final HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setStyleName("textTask");
		flexTable.setWidget(0, 0, horizontalPanel);
		horizontalPanel.setSize("100px", "50px");

		// Put some text at the table's extremes.  This forces the table to be
		// 3 by 3.
		final Label textTasks = new Label("List of tasks to do at :");
		horizontalPanel.add(textTasks);
		textTasks.setStyleName("textTask");
		textTasks.setSize("149px", "100%\r\n");


		dateSelected.setStyleName("textTask");
		horizontalPanel.add(dateSelected);
		dateSelected.setSize("332px", "100%");


		flexTable.setWidget(1, 0, listeTaches);
		listeTaches.setStyleName("listTask");
		listeTaches.setSize("296px", "100px");
	}


	private void loadTasks(final Date current){

		listeTaches.clear();
		currentDate = current;
		final IWebServerAsync iws = ViewPanel.this.agtdgui.getWebserver();
		try {
			iws.getTasks(ViewPanel.this.agtdgui.getLogin(), new AsyncCallback<List<Task>>() {

				public void onSuccess(final List<Task> result) {
					int integer=1;
					listeTaches.add(new Label(integer+" - "+currentDate));
					for(Task task : result){
						//if(task.getDateDebut().before(current) && task.getDateFin().after(current)){
						listeTaches.add(new Label(integer+" - "+task.getNom()));
						integer++;
						//}
					}
				}

				public void onFailure(final Throwable caught) {
					caught.printStackTrace();
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


}