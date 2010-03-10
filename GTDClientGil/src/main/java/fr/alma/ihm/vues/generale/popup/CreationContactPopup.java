package fr.alma.ihm.vues.generale.popup;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import fr.alma.ihm.vues.ApplicationGTD;

/**
 * Classe affichant une fenêtre popup pour la création d'un nouveau contact.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class CreationContactPopup extends JDialogGTD implements ActionListener {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 7136238333404139234L;
	
	/**
	 * instance de la classe (pattern singleton).
	 */
	private static CreationContactPopup instance = null;

	/**
	 * Champs de saisies.
	 */
	private JTextField jTextFieldNom;
	private JTextField jTextFieldEmail;
	private JTextComponent jTextFieldAdresse;
	private JTextComponent jTextFieldTel;
	
	/**
	 * Constructeur.
	 */
	private CreationContactPopup() {
		super();
		setTitle("Nouveau contact");
		setSize(400, 250);

		titre.setText("Création d'un nouveau contact");
		soustitre.setLineWrap(true);
		soustitre.setSize(300, 64);
		soustitre.setText("Ajouter un contact à votre carnet d'adresse pour vous aider à réaliser vos tâches.");
		ImageIcon imgContact = new ImageIcon(getClass().getResource("/images/contact_48.png"));
		afficheTitre(imgContact, new Dimension(400, 64));
		afficheCorps();
		doGTDLayout();
		initActionListeners();

		setLocationRelativeTo(null);
		pack();
		setVisible(true);
		jTextFieldNom.requestFocusInWindow();
	}

	/**
	 * affiche le corps de la fenêtre
	 */
	@Override
	public void afficheCorps() {
		jTextFieldNom = new JTextField();
		jTextFieldEmail = new JTextField();
		jTextFieldAdresse = new JTextField();
		jTextFieldTel = new JTextField();
		
		afficherLigne("<html>Nom du contact<font color=\"red\"> *</font></html>", jTextFieldNom);
		afficherLigne("Email", jTextFieldEmail);
		afficherLigne("Adresse", jTextFieldAdresse);
		afficherLigne("Téléphone", jTextFieldTel);
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
				CreationContactPopup.getInstance().dispose();
			}
		});
	}
	
	/**
	 * Méthode getInstance (pattern singleton).
	 * @return instance l'instance de la classe
	 */
	public static CreationContactPopup getInstance() {
		if (instance == null){
			instance = new CreationContactPopup();
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

	/**
	 * Listener associé à la fenêtre.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String nom = jTextFieldNom.getText();
		String email = jTextFieldEmail.getText();
		String adresse = jTextFieldAdresse.getText();
		String tel = jTextFieldTel.getText();
		ApplicationGTD.getInstance().getControler().creerContact(nom, email, adresse, tel);
	}
}
