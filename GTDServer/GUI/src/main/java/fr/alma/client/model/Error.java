package fr.alma.client.model;

import com.extjs.gxt.ui.client.widget.MessageBox;

public class Error {
	
	public static void showLoadError() {
		MessageBox.alert("Erreur", "Impossible de récupérer vos données", null);				

	}
	
	public static void showSaveError() {
		MessageBox.alert("Erreur", "Impossible de sauvegarder vos données", null);				
	}

	public static void showConnectError() {
		MessageBox.alert("Erreur", "Impossible de se connecter", null);
	}

}
