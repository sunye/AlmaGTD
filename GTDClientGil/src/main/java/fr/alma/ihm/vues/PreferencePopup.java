package fr.alma.ihm.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import fr.alma.ihm.vues.generale.popup.JDialogGTD;

/**
 * Classe PreferencePopup, représente la fenêtre des préférences
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public final class PreferencePopup extends JDialogGTD {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -234922324174685669L;
	
	/**
	 * Tailles statiques de la fenêtre.
	 */
	private int tailleHauteur = 325;
	private int tailleLargeur = 480; 
	
	/**
	 * instance de la classe (pattern singleton).
	 */
	private static PreferencePopup instance = null;
	
	/**
	 * panel principal de la fenêtre.
	 */
	private JPanel apercu = new JPanel();
	
	/**
	 * liste permettant de choisir différentes bannière (non géré ici).
	 */
	private JComboBox listeBanniere = new JComboBox();

	/**
	 * Constructeur.
	 */
	private PreferencePopup() {
		setSize(tailleLargeur, tailleHauteur);
		setTitle("Préférences");

		titre.setText("Modification des préférences");
		soustitre.setText("Configurer les paramètres de votre application.");
		ImageIcon imgProjet = new ImageIcon(getClass().getResource("/images/preferences_48.png"));
		afficheTitre(imgProjet, new Dimension(tailleLargeur, 64));
		afficheCorps();
		doGTDLayout();
		initActionListeners();

		setLocationRelativeTo(null);
		pack();
		setVisible(true);
	}

	/**
	 * Méthode afficheCorps permettant de gérer tous les éléments que contiennent la fenêtre.
	 */
	@Override
	public void afficheCorps() {		
		containerCentre.setLayout(new BoxLayout(containerCentre, BoxLayout.Y_AXIS));
		apercu.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		JPanel panAffichage = new JPanel(new GridBagLayout());
		JPanel panConfiguration = new JPanel();
		
		panAffichage.setBorder(BorderFactory.createTitledBorder("Affichage"));
		panConfiguration.setBorder(BorderFactory.createTitledBorder("Réseau"));
		
		///////////////////////////////////////////////////////////////////////

		JLabel langue = new JLabel("<html><u>Langue</u> :</html>");
		
		JLabel drapeauFrLabel = new JLabel("Français");
		ImageIcon imgFr = new ImageIcon(getClass().getResource("/images/drapeauFR.png"));
		drapeauFrLabel.setIcon(imgFr);
		
		JLabel drapeauEnLabel = new JLabel("Anglais");
		ImageIcon imgEn = new ImageIcon(getClass().getResource("/images/drapeauRU.png"));
		drapeauEnLabel.setIcon(imgEn);
		
		JLabel drapeauAllLabel = new JLabel("Allemand");
		ImageIcon imgAll = new ImageIcon(getClass().getResource("/images/drapeauAL.png"));
		drapeauAllLabel.setIcon(imgAll);
		
		ButtonGroup bg = new ButtonGroup();
		JRadioButton boutonsFr = new JRadioButton();	
		JRadioButton boutonsEn = new JRadioButton();
		JRadioButton boutonsAl = new JRadioButton();
		boutonsFr.setSelected(true);
		
		bg.add(boutonsFr);
		bg.add(boutonsEn);
		bg.add(boutonsAl);
		
		c.insets = new Insets(5,0,0,0);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		panAffichage.add(langue,c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		panAffichage.add(boutonsFr, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		panAffichage.add(drapeauFrLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		panAffichage.add(boutonsEn, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		panAffichage.add(drapeauEnLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 5;
		c.gridy = 0;
		panAffichage.add(boutonsAl, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 6;
		c.gridy = 0;
		panAffichage.add(drapeauAllLabel, c);
		
		JLabel banniereLabel = new JLabel("<html><u>Style</u> :</html>");
		
		listeBanniere.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				apercu.removeAll();
				apercu.setLayout(new BorderLayout());
				JLabel image = new JLabel();
				if (listeBanniere.getSelectedItem().equals("Aucune")) {

				} else if (listeBanniere.getSelectedItem().equals("Soleil couchant")) {
					image.setIcon(new ImageIcon("/images/banniereSoleil.jpg"));
				} else if (listeBanniere.getSelectedItem().equals("Noir")) {
					image = new JLabel( new ImageIcon("/images/banniereNoire.jpg"));
				}
				apercu.add(image,BorderLayout.CENTER);
				apercu.revalidate();
				apercu.repaint();
			}	
		});
		listeBanniere.addItem("Aucun");
		listeBanniere.addItem("Soleil couchant");
		listeBanniere.addItem("Noir");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		panAffichage.add(banniereLabel, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 2;
		panAffichage.add(listeBanniere, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 1;
		c.gridwidth = 3;
		c.ipady = 40;
		panAffichage.add(apercu, c);
		
		///////////////////////////////////////////////////////////////////////
		
		JLabel jLabelAdresse = new JLabel("Adresse IP du serveur");
		JTextField jTextFieldAdresse = new JTextField();
		JLabel jLabelPort = new JLabel("Port");
		JSpinner jSpinnerPort = new JSpinner();
		
		jTextFieldAdresse.setColumns(30);
		jSpinnerPort.setPreferredSize(new Dimension(50, 20));
		
		panConfiguration.add(jLabelAdresse);
		panConfiguration.add(jTextFieldAdresse);
		panConfiguration.add(jLabelPort);
		panConfiguration.add(jSpinnerPort);
		
		///////////////////////////////////////////////////////////////////////
		
		containerCentre.add(panAffichage);
		containerCentre.add(panConfiguration);
	}

	/**
	 * initialise les listeners mais gère aussi les listeners associés à la fenêtre.
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
	public static PreferencePopup getInstance() {
		if (instance == null){
			instance = new PreferencePopup();
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
			ApplicationGTD.getInstance().gererMessage(1, "Préférences prises en compte");
			PreferencePopup.getInstance().dispose();
		} else if (cmd.equals(ANNULER)) {
			ApplicationGTD.getInstance().gererMessage(3, "");
			PreferencePopup.getInstance().dispose();
		}
	}
}
