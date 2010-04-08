package fr.alma.ihm;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import fr.alma.ihm.vues.ApplicationGTD;
import fr.alma.ihm.vues.Documentation;
import fr.alma.ihm.vues.PreferencePopup;
import fr.alma.ihm.vues.generale.JToolBarGTD;
import fr.alma.ihm.vues.generale.arbre.ArbreGTD;
import fr.alma.ihm.vues.generale.arbre.NoeudGTD;
import fr.alma.ihm.vues.generale.popup.AjouterProjetPopup;
import fr.alma.ihm.vues.generale.popup.AjouterTachePopup;
import fr.alma.ihm.vues.generale.popup.ConnexionPopup;
import fr.alma.ihm.vues.generale.popup.CreationContactPopup;
import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.ITache;

/**
 * Classe Menu (menu de l'application)
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public class Menu extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 5243063231651903214L;

	/**
	 * Commandes du menu
	 */
	private static final String NOUVEAU_PROJET = "Nouveau projet";
	private static final String NOUVELLE_TACHE = "Nouvelle tâche";
	private static final String NOUVEAU_CONTACT = "Nouveau contact";
	private static final String DECONNECTER = "Déconnecter";
	private static final String CONNECTER = "Connecter";
	private static final String SUPPRIMER = "Supprimer élément";
	private static final String EDITER = "Éditer élément";
	private static final String QUITTER = "Quitter";
	private static final String PREFERENCES = "Préférences";
	private static final String DOCUMENTATION = "Documentation";
	private static final String ABOUT = "A propos";

	/**
	 * Objets graphique lié aux différentes commandes du menu
	 */
	private JMenuItem itemConnecter;
	private JMenuItem itemDeconnecter;
	private JMenuItem itemQuitter;
	private JMenuItem itemAjouterProjet;
	private JMenuItem itemAjouterTache;
	private JMenuItem itemAjouterContact;
	private JMenuItem itemSupprimerElement;
	private JMenuItem itemEditerElement;

	private JMenuItem itemPreferences;
	private JMenuItem itemDocumentation;
	private JMenuItem itemAbout;

	/**
	 * différents onglets du menu
	 */
	private JMenu ongletEdition;
	private JMenu ongletConnexion;


	private JToolBarGTD toolbar;

	/**
	 * Constructeur.
	 */
	public Menu(JToolBarGTD toolbar) {

		this.toolbar = toolbar;

		JMenu ongletFichier = new JMenu("Fichier");
		ongletEdition = new JMenu("Édition");
		ongletConnexion = new JMenu("Services distants");
		JMenu ongletOptions = new JMenu("Options");
		JMenu ongletAide = new JMenu("?");

		/** Fichier */
		ongletFichier.setMnemonic(KeyEvent.VK_F);
		JMenu sousMenuAjouter = new JMenu("Nouveau");
		itemConnecter = new JMenuItem("Connecter", KeyEvent.VK_C);
		itemDeconnecter = new JMenuItem("Déconnecter", KeyEvent.VK_D);
		itemQuitter = new JMenuItem("Quitter", KeyEvent.VK_M);

		itemQuitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,Event.CTRL_MASK, true));
		itemQuitter.setIcon(new ImageIcon(getClass().getResource("/images/menu/quitter.png")));

		/** Sous-menu Fichier\Nouveau */
		itemAjouterProjet = new JMenuItem("Projet...", KeyEvent.VK_P);
		itemAjouterTache = new JMenuItem("Tâche...", KeyEvent.VK_T);
		itemAjouterContact = new JMenuItem("Contact...", KeyEvent.VK_C);

		itemAjouterProjet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,Event.ALT_MASK, true));
		itemAjouterTache.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,Event.ALT_MASK, true));
		itemAjouterContact.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,Event.ALT_MASK, true));

		sousMenuAjouter.add(itemAjouterProjet);
		sousMenuAjouter.add(itemAjouterTache);
		sousMenuAjouter.addSeparator();
		sousMenuAjouter.add(itemAjouterContact);

		ongletFichier.add(sousMenuAjouter);
		ongletFichier.addSeparator();
		ongletFichier.add(itemConnecter);
		ongletFichier.add(itemDeconnecter);
		ongletFichier.addSeparator();
		ongletFichier.add(itemQuitter);

		/** Edition */
		ongletEdition.setMnemonic(KeyEvent.VK_E);
		itemSupprimerElement = new JMenuItem("Supprimer l'élément sélectionné", KeyEvent.VK_S);
		itemEditerElement = new JMenuItem("Modifier l'élément sélectionné", KeyEvent.VK_M);

		ongletEdition.add(itemSupprimerElement);
		ongletEdition.add(itemEditerElement);

		/** Connexion */
		ongletConnexion.setMnemonic(KeyEvent.VK_C);
		JMenuItem itemRecuperer = new JMenuItem("Tout récupérer du serveur", KeyEvent.VK_R);
		JMenuItem itemEnvoyer = new JMenuItem("Tout envoyer sur le serveur", KeyEvent.VK_E);

		JCheckBoxMenuItem itemSynchro = new JCheckBoxMenuItem("Synchronisation automatique avec le serveur");
		itemSynchro.setMnemonic(KeyEvent.VK_S);
		itemSynchro.setSelected(true);

		ongletConnexion.add(itemRecuperer);
		ongletConnexion.add(itemEnvoyer);
		ongletConnexion.add(itemSynchro);

		/** Options */
		ongletOptions.setMnemonic(KeyEvent.VK_O);
		itemPreferences = new JMenuItem("Préférences...", KeyEvent.VK_P);
		itemPreferences.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,Event.CTRL_MASK, true));
		itemPreferences.setIcon(new ImageIcon(getClass().getResource("/images/menu/preferences.png")));

		ongletOptions.add(itemPreferences);

		/** Aide */
		ongletAide.setMnemonic(KeyEvent.VK_COMMA);
		JMenuItem itemPriseEnMain = new JMenuItem("Prise en main du logiciel", KeyEvent.VK_P);
		itemDocumentation = new JMenuItem("Documentation", KeyEvent.VK_D);
		itemAbout = new JMenuItem("À propos", KeyEvent.VK_O);
		itemAbout.setIcon(new ImageIcon(getClass().getResource("/images/menu/infos.png")));

		ongletAide.add(itemPriseEnMain);
		ongletAide.add(itemDocumentation);
		ongletAide.addSeparator();
		ongletAide.add(itemAbout);

		//initialisation du menu
		add(ongletFichier);
		add(ongletEdition);
		add(ongletConnexion);
		add(ongletOptions);
		add(ongletAide);

		itemAjouterTache.addActionListener(this);
		itemAjouterProjet.addActionListener(this);
		itemAjouterContact.addActionListener(this);
		itemConnecter.addActionListener(this);
		itemDeconnecter.addActionListener(this);
		itemSupprimerElement.addActionListener(this);
		itemEditerElement.addActionListener(this);
		itemQuitter.addActionListener(this);
		itemPreferences.addActionListener(this);
		itemDocumentation.addActionListener(this);
		itemAbout.addActionListener(this);

		itemAjouterTache.setActionCommand(NOUVELLE_TACHE);
		itemAjouterProjet.setActionCommand(NOUVEAU_PROJET);
		itemAjouterContact.setActionCommand(NOUVEAU_CONTACT);
		itemConnecter.setActionCommand(CONNECTER);
		itemDeconnecter.setActionCommand(DECONNECTER);
		itemSupprimerElement.setActionCommand(SUPPRIMER);
		itemEditerElement.setActionCommand(EDITER);
		itemQuitter.setActionCommand(QUITTER);
		itemPreferences.setActionCommand(PREFERENCES);
		itemDocumentation.setActionCommand(DOCUMENTATION);
		itemAbout.setActionCommand(ABOUT);

		setEstConnecte(false);
	}

	/**
	 * Listener sur les différents objets du menu
	 * @param action l'evenement
	 */
	@Override
	public void actionPerformed(ActionEvent action) {
		String cmd = action.getActionCommand();
		if (cmd.equals(NOUVEAU_PROJET)) {

			AjouterProjetPopup.getInstance();

		} else if (cmd.equals(NOUVELLE_TACHE)) {

			AjouterTachePopup.getInstance();

		} else if (cmd.equals(NOUVEAU_CONTACT)) {

			CreationContactPopup.getInstance();

		} else if (cmd.equals(DECONNECTER)) {

			ApplicationGTD.getInstance().getControler().deconnecter();

		} else if (cmd.equals(CONNECTER)) {

			ConnexionPopup.getInstance();

		} else if (cmd.equals(SUPPRIMER)) {

			ArbreGTD.getInstance().mettreDansCorbeille();

		} else if (cmd.equals(EDITER)) {
			NoeudGTD node = (NoeudGTD)ArbreGTD.getInstance().getArbre().getLastSelectedPathComponent();
			Object nodeInfo = node.getUserObject();
			if (nodeInfo instanceof IProjet) {
				String nom = ((IProjet) nodeInfo).getNom(); 
				if (nom.equals("GTD") || nom.equals("Corbeille") || nom.equals("Panier")) {
					return;
				} else {
					toolbar.getEditerProjet().doClick();
				}
			} else if (nodeInfo instanceof ITache) {
				toolbar.getEditerTache().doClick();
			}

		} else if (cmd.equals(PREFERENCES)) {

			PreferencePopup.getInstance();

		} else if (cmd.equals(DOCUMENTATION)) {

			JDialog doc = new Documentation();
			doc.setVisible(true);

		} else if (cmd.equals(ABOUT)) {

			JDialog fenetreDetails = new JDialog();
			JPanel panelPrincipal = new JPanel();
			JLabel message = new JLabel();
			message.setText("Version 1.0");		
			panelPrincipal.add(message);
			fenetreDetails.setSize(200, 100);
			fenetreDetails.setTitle("À propos");
			fenetreDetails.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			fenetreDetails.setLocationRelativeTo(null);
			fenetreDetails.setResizable(false);
			fenetreDetails.setContentPane(panelPrincipal);
			fenetreDetails.setVisible(true);

		} else if (cmd.equals(QUITTER)) {

			System.exit(0);	

		}
	}

	/**
	 * Méthode setEstConnecte permettant de gérer graphiquement les différents objets du menu
	 * (visible ou non) selon sur quel objet on se situe.
	 * @param b le booleen pour savoir quels objets activer ou désactiver
	 */
	public final void setEstConnecte(Boolean b) {
		if (b.equals(true)) {
			itemAjouterProjet.setEnabled(true);
			itemAjouterTache.setEnabled(true);
			itemAjouterContact.setEnabled(true);
			itemConnecter.setEnabled(false);
			itemDeconnecter.setEnabled(true);

			ongletConnexion.setEnabled(true);
			ongletEdition.setEnabled(true);
		} else {
			itemAjouterProjet.setEnabled(false);
			itemAjouterTache.setEnabled(false);
			itemAjouterContact.setEnabled(false);
			itemConnecter.setEnabled(true);
			itemDeconnecter.setEnabled(false);

			ongletConnexion.setEnabled(false);
			ongletEdition.setEnabled(false);
		}
	}

	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************

	/*public JMenuItem getItemConnecter() {
		return itemConnecter;
	}

	public JMenuItem getItemDeconnecter() {
		return itemDeconnecter;
	}

	public JMenuItem getItemSupprimerElement() {
		return itemSupprimerElement;
	}

	public JMenuItem getItemEditerElement() {
		return itemEditerElement;
	}*/
}
