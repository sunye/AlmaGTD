package fr.alma.ihm.vues.generale.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.alma.ihm.vues.ApplicationGTD;

/**
 * Classe affichant une fenêtre de connexion au démarrage de l'application.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class ConnexionPopup extends JDialogGTD implements ActionListener {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -8044380705665053514L;

	/**
	 * Commandes, Actions de l'utilisateur
	 */
	private static final String MDP_OUBLIE = "Mdp oublié";
	private static final String CREATION_COMPTE = "Création compte";

	/**
	 * Tailles statiques de la fenêtre.
	 */
	private int TAILLE_HAUTEUR = 300;
	private int TAILLE_LARGEUR = 480; 

	/**
	 * instance de la classe (pattern singleton).
	 */
	private static ConnexionPopup instance = null;

	/**
	 * Champs, boutons de saisies
	 */
	private JTextField jTextFieldId;
	private JPasswordField jPasswordField;
	private JButton mdpoublie;
	private JButton creationDeCompte;

	/**
	 * Constructeur.
	 */
	private ConnexionPopup() {
		super();
		setSize(TAILLE_LARGEUR, TAILLE_HAUTEUR);
		setTitle("Connexion");

		titre.setText("Connexion à l'application");
		soustitre.setText("Veuillez saisir vos identifiants pour utiliser Getting Things Done");
		ImageIcon imgConnexion = new ImageIcon(getClass().getResource("/images/connexion_48.png"));
		afficheTitre(imgConnexion, new Dimension(TAILLE_LARGEUR, 64));
		afficheCorps();
		doSpecificLayout();
		initActionListeners();
		setLocationRelativeTo(null);
		pack();
		//setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
		jTextFieldId.requestFocusInWindow();
	}

	/**
	 * Affiche le corps de la fenêtre
	 */
	@Override
	public void afficheCorps() {
		jTextFieldId = new JTextField();
		jPasswordField = new JPasswordField();

		containerSud = new JPanel();

		afficherLigne("<html>Identifiant <font color=\"red\">*</font></html>", jTextFieldId);
		afficherLigne("<html>Mot de passe <font color=\"red\">*</font></html>", jPasswordField);
		afficherLegende();
	}

	/**
	 *
	 */
	private void doSpecificLayout() {

		afficherLigneOkAnnuler();

		mdpoublie = new JButton("<html><u>Mot de passe oublié ?</u></html>");
		mdpoublie.setBorderPainted(false);
		mdpoublie.setContentAreaFilled(false);
		creationDeCompte = new JButton("Création de compte");

		containerSud.add(mdpoublie);
		containerSud.add(creationDeCompte);

		containerPrincipal.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		containerPrincipal.add(containerNord, BorderLayout.NORTH);
		containerPrincipal.add(containerCentre, BorderLayout.CENTER);
		containerPrincipal.add(containerSud, BorderLayout.SOUTH);
	}

	/**
	 * initialise les listeners.
	 */
	@Override
	public void initActionListeners() {
		jButtonOK.addActionListener(this);
		jButtonOK.setActionCommand(OK);

		jButtonAnnuler.addActionListener(this);
		jButtonAnnuler.setActionCommand(ANNULER);

		mdpoublie.addActionListener(this);
		mdpoublie.setActionCommand(MDP_OUBLIE);

		creationDeCompte.addActionListener(this);
		creationDeCompte.setActionCommand(CREATION_COMPTE);
	}

	/**
	 * Listeners associé à la fenêtre.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals(OK)) {
			ApplicationGTD.getInstance().getControler().connecter(jTextFieldId.getText(), jPasswordField.getPassword());
		} else if (cmd.equals(ANNULER)) {
			ApplicationGTD.getInstance().gererMessage(0, "Il faut vous identifier pour utiliser le logiciel");
		} else if (cmd.equals(MDP_OUBLIE)) {
			ApplicationGTD.getInstance().gererMessage(0, "Fonctionnalité non implémentée ");
		} else if (cmd.equals(CREATION_COMPTE)) {
			CreationComptePopup.getInstance();
		}
	}

	/**
	 * Méthode getInstance (pattern singleton).
	 * @return instance l'instance de la classe
	 */
	public static ConnexionPopup getInstance() {
		if (instance == null){
			instance = new ConnexionPopup();
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
		instance = null;
	}
}