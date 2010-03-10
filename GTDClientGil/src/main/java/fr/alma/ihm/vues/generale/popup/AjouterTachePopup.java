package fr.alma.ihm.vues.generale.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import fr.alma.controleur.IControleur.TypeAction;
import fr.alma.ihm.vues.ApplicationGTD;
import fr.alma.ihm.vues.generale.arbre.ArbreGTD;
import fr.alma.ihm.vues.generale.arbre.NoeudGTD;
import fr.alma.modele.noyau.Contexte;
import fr.alma.modele.noyau.Frequence;
import fr.alma.modele.noyau.IContexte;
import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.Tache;

/**
 * Classe affichant une popup pour ajouter une nouvelle tâche.
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public class AjouterTachePopup extends AbstractAjouterEditerTache {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 7775676698992035747L;

	/**
	 * instance de la classe (pattern singleton).
	 */
	private static AjouterTachePopup instance = null;

	/**
	 * Constructeur.
	 */
	private AjouterTachePopup() {
		super();
		setTitle("Nouvelle tâche");

		titre.setText("Ajouter une nouvelle tâche");
		soustitre.setText("Formulaire de création d'une nouvelle chose à faire.");

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
		comboBoxContexte.addItem("");
		for (IContexte c : Contexte.values()) {
			comboBoxContexte.addItem(c.toString());
		}
		
		NoeudGTD noeudCourant = (NoeudGTD)ArbreGTD.getInstance().getArbre().getLastSelectedPathComponent();
		Object cible = noeudCourant.getUserObject();
		if (cible instanceof IProjet) {
			comboBoxContexte.setSelectedItem(((IProjet) cible).getContexte());
		}

		comboBoxFrequence.addItem("");
		for (Frequence f : Frequence.values()) {
			comboBoxFrequence.addItem(f.toString());
		}
		afficherChampsSaisieTache();
	}

	/**
	 * Initialisation des listeners.
	 */
	@Override
	public void initActionListeners() {
		jButtonOK.addActionListener(this);
		jButtonAnnuler.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ApplicationGTD.getInstance().gererMessage(3,"");
				AjouterTachePopup.getInstance().dispose();
			}	
		});
	}

	/**
	 * Listener associé à la fenêtre.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		HashMap<Integer, Object> valeurs = new HashMap<Integer, Object>();

		//valeurs.put(Tache.PROJET_CONTENEUR, ajout.nomProjetSelectionne);
		valeurs.put(Tache.DATE_DEBUT, textFieldDateDebut.getText());
		valeurs.put(Tache.PRIORITE, getButtonValue());
		valeurs.put(Tache.NOTES, textAreaNotes.getText());
		valeurs.put(Tache.TAUX_EFFORT, sliderTauxEffort.getValue());
		valeurs.put(Tache.CONTEXTE, comboBoxContexte.getSelectedItem());
		valeurs.put(Tache.FREQUENCE_REP, comboBoxFrequence.getSelectedItem());
		valeurs.put(Tache.DATE_ARRET_FREQUENCE_REP, textFieldDateFinRep.getText());
		valeurs.put(Tache.TAGS, textFieldTags.getText());
		valeurs.put(Tache.URLS, textFieldLien.getText());
		valeurs.put(Tache.DATE_ECHEANCE, textFieldDateEcheance.getText());
		valeurs.put(Tache.NOM, textFieldNomTache.getText());

		ApplicationGTD.getInstance().getControler().ajouterEditerTache(valeurs, TypeAction.AJOUTER);
	}

	/**
	 * Méthode getInstance (pattern singleton).
	 * @return instance l'instance de la classe
	 */
	public final synchronized static AjouterTachePopup getInstance() {
		if (instance == null) {
			instance = new AjouterTachePopup();
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
}