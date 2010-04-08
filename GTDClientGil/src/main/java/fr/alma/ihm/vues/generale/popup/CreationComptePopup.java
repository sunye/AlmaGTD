package fr.alma.ihm.vues.generale.popup;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.alma.ihm.vues.ApplicationGTD;

/**
 * Classe affichant une popup pour créer un nouveau compte utilisateur.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public final class CreationComptePopup extends JDialogGTD {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -234922324174685669L;
	
	/**
	 * Tailles statiques de la fenêtre.
	 */
	private int tailleHauteur = 320;
	private int tailleLargeur = 480;
	
	/**
	 * instance de la classe (pattern singleton).
	 */
	private static CreationComptePopup instance = null;
	
	/**
	 * Champs de saisies.
	 */
	private JTextField jTextFieldId;
	private JPasswordField jPasswordField;
	private JPasswordField jPasswordField2;
	private JTextField jTextFieldEmail;

	/**
	 * Constructeur.
	 */
	private CreationComptePopup() {
		super();
		setSize(tailleLargeur, tailleHauteur);
		setTitle("Nouveau compte");
		
		titre.setText("Creation de Compte");
		soustitre.setLineWrap(true);
		soustitre.setSize(375, 64);
		soustitre.setText("Afin de pouvoir utiliser Getting Things Done, il est nécessaire de posséder un compte utilisateur pour conserver vos tâches sur le serveur.");
		ImageIcon imgCompte = new ImageIcon(getClass().getResource("/images/login_48.png"));
		afficheTitre(imgCompte, new Dimension(tailleLargeur, 64));
		afficheCorps();
		doGTDLayout();
		initActionListeners();

		setLocationRelativeTo(null);
		pack();
		//setModalityType(ModalityType.APPLICATION_MODAL);
		setVisible(true);
		jTextFieldId.requestFocusInWindow();
	}    

	/**
	 * affiche le corps de la fenêtre
	 */
	@Override
	public void afficheCorps() {		
		jTextFieldId = new JTextField();
		jPasswordField = new JPasswordField();
		jPasswordField2 = new JPasswordField();
		jTextFieldEmail = new JTextField();
		
		afficherLigne("<html>Identifiant<font color=\"red\"> *</font></html>", jTextFieldId);
		afficherLigne("<html>Mot de passe<font color=\"red\"> *</font></html>", jPasswordField);
		afficherLigne("<html>Confirmation du mot de passe<font color=\"red\"> *</font></html>", jPasswordField2);
		afficherLigne("<html>Adresse email</html>", jTextFieldEmail);
		afficherLegende();
	}

	/**
	 * Initialisation des listeners et implémentation des listeners associés à la fenêtre.	
	 */
	@Override
	public void initActionListeners() {
		jButtonOK.addActionListener(this);
		jButtonAnnuler.addActionListener(this);
		jButtonOK.setActionCommand(OK);
		jButtonAnnuler.setActionCommand(ANNULER);
	}
	
	/**
	 * Méthode getInstance (pattern singleton).
	 * @return instance l'instance de la classe
	 */
	public static CreationComptePopup getInstance() {
		if (instance == null){
			instance = new CreationComptePopup();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals(OK)) {
			String login = jTextFieldId.getText();
			char[] pwd = jPasswordField.getPassword();
			char[] pwd2= jPasswordField2.getPassword();
			String email = jTextFieldEmail.getText();
			ApplicationGTD.getInstance().getControler().creerCompte(login, pwd, pwd2, email);
		} else if (cmd.equals(ANNULER)) {
			ApplicationGTD.getInstance().gererMessage(3, "");
			CreationComptePopup.getInstance().dispose();
		}
	}
}

