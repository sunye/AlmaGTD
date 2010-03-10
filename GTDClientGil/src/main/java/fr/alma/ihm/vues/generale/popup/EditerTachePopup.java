package fr.alma.ihm.vues.generale.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.tree.TreePath;

import fr.alma.controleur.Controleur;
import fr.alma.controleur.IControleur.TypeAction;
import fr.alma.ihm.vues.ApplicationGTD;
import fr.alma.ihm.vues.generale.arbre.ArbreGTD;
import fr.alma.ihm.vues.generale.arbre.NoeudGTD;
import fr.alma.modele.noyau.Contexte;
import fr.alma.modele.noyau.Frequence;
import fr.alma.modele.noyau.IContexte;
import fr.alma.modele.noyau.ITache;

/**
 * Classe affichant une popup pour éditer une tâche existante.
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public class EditerTachePopup extends AbstractAjouterEditerTache {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 7775676698992035747L;

	/**
	 * instance de la classe (pattern singleton).
	 */
	private static EditerTachePopup instance = null;

	/**
	 * La tâche selectionnée
	 */
	private ITache tache;

	/**
	 * Constructeur.
	 */
	private EditerTachePopup() {
		super();
		setTitle("Edition tâche");

		titre.setText("Edition d'une tâche");
		soustitre.setText("Formulaire d'edition d'une tâche.");

		TreePath currentSelection = ArbreGTD.getInstance().getArbre().getSelectionPath();
		NoeudGTD currentNode = (NoeudGTD)(currentSelection.getLastPathComponent());
		this.tache = (ITache) currentNode.getUserObject();

		afficheCorps();
		doGTDLayout();
		initActionListeners();

		setLocationRelativeTo(null);
		pack();
		setVisible(true);
		textFieldNomTache.requestFocusInWindow();
	}

	/**
	 * affiche le corps de la fenêtre
	 */
	@Override
	public void afficheCorps() {	
		textFieldNomTache.setText(tache.getNom());

		if (tache.getDateDebut() != null) {
			textFieldDateDebut.setText(Controleur.df.format(tache.getDateDebut()));
		}

		if (tache.getDateEcheance() != null) {
			textFieldDateEcheance.setText(Controleur.df.format(tache.getDateEcheance()));
		}

		if (tache.getPeriodicite().getDateFinRepetition() != null) {
			textFieldDateFinRep.setText(Controleur.df.format(tache.getPeriodicite().getDateFinRepetition()));
		}

		comboBoxContexte.setSelectedItem(tache.getContexte());

		List<String> urls = tache.getUrls();
		String message="";
		for(String url : urls){
			message +=url+"";
		}
		textFieldLien.setText(message);

		List<String> tags = tache.getTags();
		message="";
		for(String tag : tags){
			message +=tag+"";
		}
		textFieldTags.setText(message);

		textAreaNotes.setText(tache.getNotes());


		//comboBoxFrequence.setSelectedItem(tache.getPeriodicite().getFrequence());

		comboBoxContexte.addItem("");
		for (IContexte c : Contexte.values()) {
			comboBoxContexte.addItem(c.toString());
		}

		comboBoxFrequence.addItem("");
		for (Frequence f : Frequence.values()) {
			comboBoxFrequence.addItem(f.toString());
		}

		if (tache.getTauxEffort() != null) {
			sliderTauxEffort.setValue(tache.getTauxEffort());
		} else {
			sliderTauxEffort.setValue(0);
		}

		afficherChampsSaisieTache();
		
		if (!tache.getPriorite().equals(-1)) {
			Integer priorite = tache.getPriorite();
			JRadioButton button = (JRadioButton) panBoutonRadios.getComponent(priorite - 1);
			button.setSelected(true);
		}
	}

	/**
	 * Initialisation des listeners.
	 */
	@Override
	public void initActionListeners() {
		jButtonOK.addActionListener(this);
		jButtonAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditerTachePopup.getInstance().dispose();
			}
		});
	}

	/**
	 * Méthode getInstance (pattern singleton).
	 * @return instance l'instance de la classe
	 */
	public final synchronized static EditerTachePopup getInstance() {
		if (instance == null){
			instance = new EditerTachePopup();
		}
		instance.toFront();
		return instance;
	}
	
	public final static boolean isNullInstance() {
		return instance == null;
	}

	/**
	 * Méthode dispose permettant de cacher la fenêtre tout en libérant l'instance en cours.
	 */
	@Override
	public void dispose(){
		super.dispose();
		instance=null;
	}

	/**
	 * Listener associé à la fenêtre.
	 */
	@Override 
	public void actionPerformed(ActionEvent arg0) {
		HashMap<Integer, Object> valeurs = new HashMap<Integer, Object>();

		//valeurs.put(Tache.PROJET_CONTENEUR, ajout.nomProjetSelectionne);
		valeurs.put(ITache.DATE_DEBUT, textFieldDateDebut.getText());
		valeurs.put(ITache.PRIORITE, getButtonValue());
		valeurs.put(ITache.NOTES, textAreaNotes.getText());
		valeurs.put(ITache.TAUX_EFFORT, sliderTauxEffort.getValue());
		valeurs.put(ITache.CONTEXTE, comboBoxContexte.getSelectedItem());
		valeurs.put(ITache.FREQUENCE_REP, comboBoxFrequence.getSelectedItem());
		valeurs.put(ITache.DATE_ARRET_FREQUENCE_REP, textFieldDateFinRep.getText());
		valeurs.put(ITache.TAGS, textFieldTags.getText());
		valeurs.put(ITache.URLS, textFieldLien.getText());
		valeurs.put(ITache.DATE_ECHEANCE, textFieldDateEcheance.getText());
		valeurs.put(ITache.NOM, textFieldNomTache.getText());

		ApplicationGTD.getInstance().getControler().ajouterEditerTache(valeurs, TypeAction.EDITER);
	}
	
	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************

	public ITache getTache() {
		return tache;
	}

	public void setTache(ITache tache) {
		this.tache = tache;
	}
}