package fr.alma.ihm.vues.generale.popup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.tree.TreePath;

import fr.alma.controleur.IControleur.TypeAction;
import fr.alma.ihm.vues.ApplicationGTD;
import fr.alma.ihm.vues.generale.arbre.ArbreGTD;
import fr.alma.ihm.vues.generale.arbre.NoeudGTD;
import fr.alma.modele.noyau.Contexte;
import fr.alma.modele.noyau.IContexte;
import fr.alma.modele.noyau.IProjet;

/**
 * Classe affichant une popup pour ajouter un nouveau projet.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class EditerProjetPopup extends AbstractAjouterEditerProjet {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 8308120609948236353L;

	/**
	 * instance de la classe (pattern singleton).
	 */
	private static EditerProjetPopup instance = null;
	
	/**
	 * Le projet selectionné
	 */
	private IProjet projet;

	/**
	 * Constructeur.
	 */
	private EditerProjetPopup() {
		super();
		setTitle("Éditer projet");

		titre.setText("Éditer un nouveau projet");
		soustitre.setText("Modifier un projet existant");
		
		TreePath currentSelection = ArbreGTD.getInstance().getArbre().getSelectionPath();
		NoeudGTD currentNode = (NoeudGTD)(currentSelection.getLastPathComponent());
		this.projet = (IProjet) currentNode.getUserObject();

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
		jTextFieldNomProjet.setText(projet.getNom());

		jComboBoxContexte.addItem("");
		for (IContexte c : Contexte.values()) {
			jComboBoxContexte.addItem(c.toString());
		}
		jComboBoxContexte.setSelectedItem(projet.getContexte());
		
		jTextAreaNotes.setText(projet.getNotes());
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
		valeurs.put(IProjet.NOM, jTextFieldNomProjet.getText());
		valeurs.put(IProjet.CONTEXTE_DEFAUT, jComboBoxContexte.getSelectedItem());
		valeurs.put(IProjet.NOTES, jTextAreaNotes.getText());
		ApplicationGTD.getInstance().getControler().ajouterEditerProjet(valeurs, TypeAction.EDITER);
	}

	/**
	 * Méthode getInstance (pattern singleton).
	 * @return instance l'instance de la classe
	 */
	public static EditerProjetPopup getInstance() {
		if (instance == null){
			instance = new EditerProjetPopup();
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
	
	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	
	public void setProjet(IProjet projet) {
		this.projet = projet;
	}

	public IProjet getProjet() {
		return projet;
	}
}