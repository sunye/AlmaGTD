package fr.alma.ihm.vues.generale.popup;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.table.TableColumnModel;

import fr.alma.ihm.vues.ApplicationGTD;
import fr.alma.ihm.vues.generale.ListeContacts;
import fr.alma.modele.noyau.IContact;

/**
 * Classe affichant une popup de sélection de contacts sous forme de cases à cocher.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1 
 */
public class SelectionContactsPopup extends JDialogGTD implements ActionListener {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -226333258724898805L;

	/**
	 * Actions, commandes des utilisateurs.
	 */
	private static final String CREATION_CONTACT = "Création contact";
	private static final String SUPPRESSION_CONTACT = "Suppression contact";

	/**
	 * Taille de la fenêtre.
	 */
	private int TAILLE_HAUTEUR = 400;
	private int TAILLE_LARGEUR = 480;

	/**
	 * La liste de contacts recupérée via la BD.
	 */
	private ListeContacts listeContacts = new ListeContacts();

	/**
	 * Le tableau ou seront recensé tous les contacts afin de selectionner ceux voulu.
	 */
	private JTable tableauContacts;
	
	/**
	 * boutons d'actions
	 */
	private JButton creationContact;
	private JButton supprimerContact;

	private AbstractAjouterEditerTache popupOrigine;

	/**
	 * Constructeur avec paramètre.
	 * @param popupOrigine la fenêtre d'origine
	 */
	public SelectionContactsPopup(AbstractAjouterEditerTache popupOrigine) {
		super();
		this.popupOrigine = popupOrigine;
		setSize(TAILLE_LARGEUR, TAILLE_HAUTEUR);
		setTitle("Liste de contacts");
		afficheCorps();
		doGTDLayout();
		initActionListeners();
		pack();
		setVisible(true);
	}

	/**
	 * affiche le corps de la fenêtre
	 */
	@Override
	public void afficheCorps() {
		containerPrincipal.setLayout(new BorderLayout());

		ImageIcon imgPlus = new ImageIcon(getClass().getResource("/images/vueGenerale/ajouterContact.png"));
		ImageIcon imgSuppr = new ImageIcon(getClass().getResource("/images/vueGenerale/supprimerContact.png"));
		ImageIcon imgSupprDes = new ImageIcon(getClass().getResource("/images/vueGenerale/supprimerContactDesactive.png"));

		creationContact = new JButton("Créer un nouveau contact", imgPlus);
		supprimerContact = new JButton("Supprimer les contacts sélectionnés", imgSuppr);
		supprimerContact.setDisabledIcon(imgSupprDes);
		supprimerContact.setEnabled(false);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.add(creationContact);
		toolBar.add(supprimerContact);

		tableauContacts = new JTable(listeContacts);

		// Défini la largeur des colonnes
		int[] columnWidth = {(int)(TAILLE_LARGEUR*3/10), (int)(TAILLE_LARGEUR*5.5/10), (int)(TAILLE_LARGEUR*1.5/10)};
		TableColumnModel mod = tableauContacts.getColumnModel();
		for (int i=0; i<3; i++) {
			mod.getColumn(i).setPreferredWidth(columnWidth[i]);
		}

		JScrollPane scrollPanePourTableau = new JScrollPane(tableauContacts);
		JPanel panelFonctionnalitesTableau = new JPanel(new BorderLayout());
		panelFonctionnalitesTableau.add(toolBar, BorderLayout.NORTH);
		panelFonctionnalitesTableau.add(scrollPanePourTableau, BorderLayout.CENTER);

		containerCentre = new JPanel();
		containerCentre.add(panelFonctionnalitesTableau);
	}

	/**
	 * Initialisation des listeners.
	 */
	@Override
	public void initActionListeners() {
		jButtonOK.addActionListener(this);
		jButtonAnnuler.addActionListener(this);
		creationContact.addActionListener(this);
		supprimerContact.addActionListener(this);
		
		jButtonOK.setActionCommand(OK);
		jButtonAnnuler.setActionCommand(ANNULER);
		creationContact.setActionCommand(CREATION_CONTACT);
		supprimerContact.setActionCommand(SUPPRESSION_CONTACT);

		tableauContacts.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) { supprimerContact.setEnabled(true); }

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseClicked(MouseEvent arg0) { supprimerContact.setEnabled(true); }
		});
	}

	/**
	 * Listener associé à la fenêtre.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if (cmd.equals(OK)) {

			String contacts = "";
			for (int i=0; i < tableauContacts.getRowCount(); i++){
				if ((Boolean) listeContacts.getValueAt(i, 2)) {
					contacts += listeContacts.getValueAt(i, 0) + ", ";
				}
			}
			// Si au moins une case est cochée
			if (!contacts.equals("")) {
				// Suppression de la virgule en trop en fin de chaine
				popupOrigine.textFieldContacts.setText(contacts.substring(0, contacts.length() - 2));
			}
			//AjouterTachePopup.getInstance().getContact().dispose();
			popupOrigine.getContact().dispose();

		} else if (cmd.equals(ANNULER)) {

			ApplicationGTD.getInstance().gererMessage(3,"");
			AjouterTachePopup.getInstance().getContact().dispose();

		} else if (cmd.equals(CREATION_CONTACT)) {

			CreationContactPopup.getInstance();

		} else if (cmd.equals(SUPPRESSION_CONTACT)) {
			try {
				String message = "Désirez-vous supprimer le";
				if (tableauContacts.getSelectedRows().length>1) {
					message += "s contacts sélectionnés ?";
				} else {
					message += " contact sélectionné ?";
				}
				//if (JOptionPane.showConfirmDialog(null, message, "Attention !", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
				boolean lignesSelectionnes = true;
				while (lignesSelectionnes) {
					int premiereLigneSelectionnee = tableauContacts.getSelectedRow();
					if (premiereLigneSelectionnee == -1) {
						lignesSelectionnes = false;
					} else {
						listeContacts.removeRow(premiereLigneSelectionnee);
						IContact c = (IContact) ApplicationGTD.getInstance().getContainerVues().getVueGenerale().getListModel().get(premiereLigneSelectionnee);
						ApplicationGTD.getInstance().getControler().supprimerContact(c);
					}
				}
				//}
				supprimerContact.setEnabled(false);
			} catch (HeadlessException h) {
				System.out.println(h.toString());
			}
		}
	}
	
	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	

	public ListeContacts getListeContacts() {
		return listeContacts;
	}

	public void setPopupOrigine(AbstractAjouterEditerTache popupOrigine) {
		this.popupOrigine = popupOrigine;
	}

	public AbstractAjouterEditerTache getPopupOrigine() {
		return popupOrigine;
	}
}
