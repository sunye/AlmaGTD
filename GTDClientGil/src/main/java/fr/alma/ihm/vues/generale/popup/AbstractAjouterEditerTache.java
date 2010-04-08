package fr.alma.ihm.vues.generale.popup;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Classe abstraite permettant d'ajouter ou d'éditer une tâche.
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public abstract class AbstractAjouterEditerTache extends JDialogGTD {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 2959389972482354249L;

	/**
	 * Tailles statiques de la fenêtre.
	 */
	private int tailleHauteur = 540;
	private int tailleLargeur = 480;

	/**
	 * Différents types de boutons
	 */
	protected enum TypeBouton {CALENDRIER, CONTACT};

	/**
	 * Champs de saisies
	 */
	protected JTextField textFieldNomTache = new JTextField();
	protected JComboBox comboBoxContexte = new JComboBox();
	protected JTextArea textAreaNotes = new JTextArea();
	protected JTextField textFieldDateDebut = new JTextField();
	protected JTextField textFieldDateEcheance = new JTextField();
	protected JTextField textFieldContacts = new JTextField();
	protected JSlider sliderTauxEffort = new JSlider();
	protected JComboBox comboBoxFrequence = new JComboBox();
	protected JTextField textFieldDateFinRep = new JTextField();
	protected JTextField textFieldLien = new JTextField();
	protected JTextField textFieldTags = new JTextField();
	protected JPanel panBoutonRadios = new JPanel();
	private SelectionContactsPopup contact;

	/**
	 * Constructeur.
	 */
	public AbstractAjouterEditerTache() {
		super();
		setSize(tailleLargeur, tailleHauteur);
		ImageIcon imgProjet = new ImageIcon(getClass().getResource("/images/vueGenerale/ajouterTache_48.png"));
		afficheTitre(imgProjet, new Dimension(tailleLargeur, 64));
		textAreaNotes.setRows(3);
		textAreaNotes.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}

	/**
	 * récupère la valeur d'un bouton (JCheckBox)
	 * @return la valeur du bouton
	 */
	public Integer getButtonValue() {
		Component[] boutons = panBoutonRadios.getComponents();
		Integer value = new Integer(-1);
		for (Component c : boutons) {
			JRadioButton jrb = (JRadioButton)c;
			if (jrb.isSelected()) {
				value = Integer.parseInt(jrb.getText());
			}
		}
		return value;
	}

	/**
	 * affiche tous les champs de saisies
	 */
	public void afficherChampsSaisieTache() {
		afficherLigne("<html>Nom<font color=\"red\"> *</font></html>", textFieldNomTache);
		afficherListe("Contexte", comboBoxContexte);
		afficherLigne("Notes", textAreaNotes);
		afficherLigneAvecBouton("Date de début", textFieldDateDebut, TypeBouton.CALENDRIER);
		afficherLigneAvecBouton("Date d'échéance", textFieldDateEcheance, TypeBouton.CALENDRIER);
		afficherSeparateur();
		afficherPrioriteTache();
		afficherTauxEffort();
		afficherSeparateur();
		afficherListe("Fréquence de répétition", comboBoxFrequence);
		afficherLigneAvecBouton("Date d'arret", textFieldDateFinRep, TypeBouton.CALENDRIER);
		afficherSeparateur();
		afficherLigneAvecBouton("Contacts", textFieldContacts, TypeBouton.CONTACT);
		afficherLigne("Liens", textFieldLien);
		afficherLigne("Tags", textFieldTags);
		afficherLegende();
	}
	
	/**
	 * affiche tous les champs de saisies avec boutons
	 */
	private void afficherLigneAvecBouton(String nomElement, JTextField jTextField, TypeBouton b) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 20, 0, 0);
		c.weightx = 0.3;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = y;
		containerCentre.add(new JLabel(nomElement), c);

		JButton bouton = null;
		if (b.equals(TypeBouton.CALENDRIER)) {
			ImageIcon imgCal = new ImageIcon(getClass().getResource("/images/calendrier_16.png"));
			bouton = new JButton(imgCal);
			bouton.addMouseListener(new CalendarListener(this, jTextField));
		} else if (b.equals(TypeBouton.CONTACT)) {
			ImageIcon imgContact = new ImageIcon(getClass().getResource("/images/plus_16.png"));
			bouton = new JButton(imgContact);
			bouton.addMouseListener(new ContactsListener(this));
		}

		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 20, 0, 0);
		c.weightx = 0.6;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = y;
		containerCentre.add(jTextField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 0, 0, 20);
		c.weightx = 0.1;
		c.gridwidth = 1;
		c.gridx = 2;
		c.gridy = y++;
		containerCentre.add(bouton, c);
	}

	/**
	 * affiche les boutons radios
	 */
	private void afficherPrioriteTache() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 20, 0, 20);
		c.weightx = 0.3;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = y;
		containerCentre.add(new JLabel("Priorité"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 20, 0, 20);
		c.weightx = 0.7;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = y++;
		panBoutonRadios.setLayout(new GridLayout(1, 5));
		ButtonGroup group = new ButtonGroup();
		for (Integer i=1; i<6; i++) {
			JRadioButton priorite = new JRadioButton(i.toString());
			group.add(priorite);
			panBoutonRadios.add(priorite);
		}
		containerCentre.add(panBoutonRadios, c);
	}

	/**
	 * affiche la barre de taux d'effort (JSlider).
	 */
	private void afficherTauxEffort() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 20, 0, 20);
		c.weightx = 0.3;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = y;
		sliderTauxEffort = new JSlider(0, 100);
		Properties p = new Properties();
		p.put(0, new JLabel("0 - Faible"));
		p.put(100, new JLabel("100 - Élevé"));
		sliderTauxEffort.setLabelTable(p);
		sliderTauxEffort.createStandardLabels(10, 0);
		sliderTauxEffort.setPaintLabels(true);
		containerCentre.add(new JLabel("Taux d'effort demandé"), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 20, 0, 20);
		c.weightx = 0.7;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = y++;
		containerCentre.add(sliderTauxEffort, c);
	}

	/**
	 * Classe CalendarListener permettant d'afficher un JCalendar pour le choix des dates
	 * @author Université de Nantes
	 * @since 2009
	 * @version 1.0
	 */
	class CalendarListener implements MouseListener {

		/**
		 * La fenêtre d'ajout/edition de tâche
		 */
		private AbstractAjouterEditerTache ajouterTache;
		private JTextField fieldAssocie;

		/**
		 * Constructeur avec paramètre.
		 * @param ajouterEditerTachePopup La fenêtre d'ajout/edition de tâche
		 * @param fieldAssocie le champ associé
		 */
		public CalendarListener(AbstractAjouterEditerTache ajouterEditerTachePopup, JTextField fieldAssocie) {
			this.ajouterTache = ajouterEditerTachePopup;
			this.fieldAssocie = fieldAssocie;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			CalendarPopup c = CalendarPopup.getInstance();
			c.setDateField(fieldAssocie);
			Point p = ajouterTache.getLocation();
			Rectangle r = ajouterTache.getBounds();
			p.translate(r.width+20, r.height/2 - c.getHeight()/2);
			c.setLocation(p);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}

	/**
	 * Classe CalendarListener permettant d'afficher la liste des contacts à ajouter à une tâche
	 * @author Université de Nantes
	 * @since 2009
	 * @version 1.0
	 */
	class ContactsListener implements MouseListener {

		/**
		 * La fenêtre d'ajout/edition de tâche
		 */
		private AbstractAjouterEditerTache ajouterEditerTache;

		/**
		 * Constructeur avec paramètre.
		 * @param ajouterEditerTachePopup La fenêtre d'ajout/edition de tâche
		 * @param fieldAssocie le champ associé
		 */
		public ContactsListener(AbstractAjouterEditerTache ajouterEditerTachePopup) {
			this.ajouterEditerTache = ajouterEditerTachePopup;
		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			contact = new SelectionContactsPopup(ajouterEditerTache);
			Point p = ajouterEditerTache.getLocation();
			Rectangle r = ajouterEditerTache.getBounds();
			p.translate(r.width+20, r.height/2 - contact.getHeight()/2);
			contact.setLocation(p);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}

	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************

	public SelectionContactsPopup getContact() {
		return contact;
	}

	public void setContact(SelectionContactsPopup contact) {
		this.contact = contact;
	}

	public JTextField getTextFieldContacts() {
		return textFieldContacts;
	}

	public void setTextFieldContacts(JTextField textFieldContacts) {
		this.textFieldContacts = textFieldContacts;
	}
}
