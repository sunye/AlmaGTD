package fr.alma.ihm.vues.generale.popup;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Classe abstraite permettant d'ajouter ou d'éditer un projet.
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public abstract class AbstractAjouterEditerProjet extends JDialogGTD implements ActionListener {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 1816400549588711868L;

	/**
	 * Tailles statiques de la fenêtre.
	 */
	private int tailleHauteur = 300;
	private int tailleLargeur = 480; 
	
	/**
	 * Champs de saisies
	 */
	protected JTextField jTextFieldNomProjet = new JTextField();
	protected JComboBox jComboBoxContexte = new JComboBox();
	protected JTextArea jTextAreaNotes = new JTextArea();
	
	/**
	 * Constructeur.
	 */
	public AbstractAjouterEditerProjet() {
		setSize(tailleLargeur, tailleHauteur);
		ImageIcon imgProjet = new ImageIcon(getClass().getResource("/images/vueGenerale/ajouterProjet_48.png"));
		afficheTitre(imgProjet, new Dimension(tailleLargeur, 64));
		jTextAreaNotes.setRows(3);
		jTextAreaNotes.setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}
	
	/**
	 * affiche le contenu
	 */
	public void afficherChampsSaisieProjet() {
		afficherLigne("<html>Nom du projet<font color=\"red\"> *</font></html>", jTextFieldNomProjet);
		afficherListe("Contexte par défaut", jComboBoxContexte);
		afficherLigne("Notes", jTextAreaNotes);
		afficherLegende();
	}

	@Override
	public abstract void afficheCorps();

	@Override
	public abstract void initActionListeners();

	@Override
	public abstract void actionPerformed(ActionEvent arg0);
}
