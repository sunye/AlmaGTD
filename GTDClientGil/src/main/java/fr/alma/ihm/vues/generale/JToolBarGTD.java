package fr.alma.ihm.vues.generale;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import fr.alma.ihm.vues.ApplicationGTD;
import fr.alma.ihm.vues.generale.arbre.ArbreGTD;
import fr.alma.ihm.vues.generale.popup.AjouterProjetPopup;
import fr.alma.ihm.vues.generale.popup.AjouterTachePopup;
import fr.alma.ihm.vues.generale.popup.EditerProjetPopup;
import fr.alma.ihm.vues.generale.popup.EditerTachePopup;

/**
 * Classe JToolBarGTD, représente la barre d'outils (actions des utilisateurs)
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public class JToolBarGTD extends JToolBar implements ActionListener {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -4385162520218475480L;

	/**
	 * Commandes, actions des utilisateurs
	 */
	private static final String AJOUTER_TACHE = "Ajouter tache";
	private static final String AJOUTER_PROJET = "Ajouter projet";
	private static final String EDITER_TACHE = "Editer tache";
	private static final String EDITER_PROJET = "Editer projet";
	private static final String SUPPRIMER_TACHE = "Supprimer tache";
	private static final String SUPPRIMER_PROJET = "Supprimer projet";
	private static final String VIDER_CORBEILLE = "Vider corbeille";

	/**
	 * boutons associés aux actions
	 */
	private JButton ajouterTache;
	private JButton ajouterProjet;
	private JButton editerTache;
	private JButton editerProjet;
	private JButton supprTache;
	private JButton supprProjet;
	private JButton viderCorbeille;

	/**
	 * Constructeur avec paramètre.
	 * @param arbre arborescence graphique
	 */
	public JToolBarGTD(ArbreGTD arbre) {

		/** Bouton Ajout tâche */
		ImageIcon imgPlus = new ImageIcon(getClass().getResource("/images/vueGenerale/ajouter.png"));
		ajouterTache = new JButton("Ajouter tâche", imgPlus);
		ajouterTache.setMnemonic(KeyEvent.VK_T);

		/** Bouton Ajout projet */
		ImageIcon imgAjouterProjet = new ImageIcon(getClass().getResource("/images/vueGenerale/ajouterProjet.png"));
		ajouterProjet = new JButton("Ajouter projet", imgAjouterProjet);		
		ajouterProjet.setMnemonic(KeyEvent.VK_P);

		/** Bouton Editer tâche */
		ImageIcon imgEditer = new ImageIcon(getClass().getResource("/images/vueGenerale/editer.png"));
		editerTache = new JButton("Éditer tâche", imgEditer);
		editerTache.setMnemonic(KeyEvent.VK_E);

		/** Bouton Éditer projet */
		editerProjet = new JButton("Éditer projet", imgEditer);
		editerProjet.setMnemonic(KeyEvent.VK_E);

		/** Bouton Supprimer tâche */
		ImageIcon imgSuppr = new ImageIcon(getClass().getResource("/images/vueGenerale/supprimer.png"));
		supprTache = new JButton("Supprimer tâche", imgSuppr);
		supprTache.setMnemonic(KeyEvent.VK_S);

		/** Bouton Supprimer projet */
		ImageIcon imgSupprProjet = new ImageIcon(getClass().getResource("/images/vueGenerale/supprimerProjet.png"));
		supprProjet = new JButton("Supprimer projet", imgSupprProjet);
		supprProjet.setMnemonic(KeyEvent.VK_S);

		/** Bouton Vider la corbeille */
		viderCorbeille = new JButton("Vider la corbeille", imgSuppr);
		viderCorbeille.setMnemonic(KeyEvent.VK_V);

		ajouterTache.setOpaque(true);
		ajouterProjet.setOpaque(true);
		editerTache.setOpaque(true);
		editerTache.setOpaque(true);
		supprProjet.setOpaque(true);
		supprTache.setOpaque(true);
		viderCorbeille.setOpaque(true);

		setOpaque(false);
		setBorder(new LineBorder(Color.LIGHT_GRAY));

		initActionListeners();
	}

	/**
	 * méthode initActionListeners
	 * Initialisation des listeners et association des commandes.
	 */
	private void initActionListeners() {
		ajouterTache.addActionListener(this);
		ajouterProjet.addActionListener(this);
		editerTache.addActionListener(this);
		editerProjet.addActionListener(this);
		supprTache.addActionListener(this);
		supprProjet.addActionListener(this);
		viderCorbeille.addActionListener(this);

		ajouterTache.setActionCommand(AJOUTER_TACHE);
		ajouterProjet.setActionCommand(AJOUTER_PROJET);
		editerTache.setActionCommand(EDITER_TACHE);
		editerProjet.setActionCommand(EDITER_PROJET);
		supprTache.setActionCommand(SUPPRIMER_TACHE);
		supprProjet.setActionCommand(SUPPRIMER_PROJET);
		viderCorbeille.setActionCommand(VIDER_CORBEILLE);
	}

	/**
	 * Listener associé aux différentes actions
	 */
	@Override
	public void actionPerformed(ActionEvent action) {
		String cmd = action.getActionCommand();
		if (cmd.equals(AJOUTER_TACHE)) {
			AjouterTachePopup.getInstance();
		} else if (cmd.equals(AJOUTER_PROJET)) {
			AjouterProjetPopup.getInstance();
		} else if (cmd.equals(EDITER_TACHE)) {
			EditerTachePopup.getInstance();
		} else if (cmd.equals(EDITER_PROJET)) {
			EditerProjetPopup.getInstance();
		} else if (cmd.equals(SUPPRIMER_TACHE) || cmd.equals(SUPPRIMER_PROJET)) {
			ArbreGTD.getInstance().mettreDansCorbeille();
		} else if (cmd.equals(VIDER_CORBEILLE)) {
			ApplicationGTD.getInstance().getControler().viderCorbeille();
		}
	}

	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************

	public JButton getAjouterTache() {
		return ajouterTache;
	}

	public JButton getAjouterProjet() {
		return ajouterProjet;
	}

	public JButton getEditerTache() {
		return editerTache;
	}

	public JButton getEditerProjet() {
		return editerProjet;
	}

	public JButton getSupprTache() {
		return supprTache;
	}

	public JButton getSupprProjet() {
		return supprProjet;
	}

	public JButton getViderCorbeille() {
		return viderCorbeille;
	}
}