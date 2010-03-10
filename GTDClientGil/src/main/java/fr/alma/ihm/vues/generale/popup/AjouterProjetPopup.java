package fr.alma.ihm.vues.generale.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import fr.alma.controleur.IControleur.TypeAction;
import fr.alma.ihm.vues.ApplicationGTD;
import fr.alma.modele.noyau.Contexte;
import fr.alma.modele.noyau.IContexte;
import fr.alma.modele.noyau.IProjet;

/**
 * Classe affichant une popup pour ajouter un nouveau projet.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class AjouterProjetPopup extends AbstractAjouterEditerProjet {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * instance de la classe (pattern singleton).
	 */
	private static AjouterProjetPopup instance = null;

	/**
	 * Constructeur.
	 */
	private AjouterProjetPopup() {
		super();
		setTitle("Nouveau projet");

		titre.setText("Créer un nouveau projet");
		soustitre.setText("Commencer à organiser ses tâches");

		afficheCorps();
		doGTDLayout();
		initActionListeners();

		setLocationRelativeTo(null);
		pack();
		setVisible(true);
		jTextFieldNomProjet.requestFocusInWindow();
	}

	/**
	 * affiche le corps de la fenêtre
	 */
	@Override
	public void afficheCorps() {
		jComboBoxContexte.addItem("");
		for (IContexte c : Contexte.values()) {
			jComboBoxContexte.addItem(c.toString());
		}
		afficherChampsSaisieProjet();
	}

	/**
	 * Initialisation des listeners.
	 */
	@Override
	public void initActionListeners() {
		jButtonOK.addActionListener(this);
		jButtonAnnuler.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationGTD.getInstance().gererMessage(4,"");
				dispose();
			}
		});
	}

	/**
	 * Listener associé à la fenêtre.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		HashMap<Integer, Object> valeurs = new HashMap<Integer, Object>();
		valeurs.put(IProjet.CONTEXTE_DEFAUT, jComboBoxContexte.getSelectedItem());
		valeurs.put(IProjet.NOTES, jTextAreaNotes.getText());
		valeurs.put(IProjet.NOM, jTextFieldNomProjet.getText());
		ApplicationGTD.getInstance().getControler().ajouterEditerProjet(valeurs, TypeAction.AJOUTER);
	}

	/**
	 * Méthode getInstance (pattern singleton).
	 * @return instance l'instance de la classe
	 */
	public static AjouterProjetPopup getInstance() {
		if (instance == null){
			instance = new AjouterProjetPopup();
		}
		instance.toFront();
		return instance;
	}

	/**
	 * Méthode dispose permettant de cacher la fenêtre tout en libérant l'instance en cours.
	 */
	@Override
	public void dispose(){
		super.dispose();
		instance=null;
	}
}