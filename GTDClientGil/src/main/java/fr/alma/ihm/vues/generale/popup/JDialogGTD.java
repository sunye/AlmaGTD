package fr.alma.ihm.vues.generale.popup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

/**
 * Classe abstraite regroupant quelques caractéristiques communes aux popups.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public abstract class JDialogGTD extends JDialog implements ActionListener {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 2754282726800962294L;

	//permet de placer les éléments dans le gridBagLayout
	protected GridBagConstraints c = new GridBagConstraints();
	protected int y=0;

	protected JLabel titre = new JLabel();
	protected JTextArea soustitre = new JTextArea();

	/**
	 * Les différents conteneurs d'une fenêtre standard
	 */
	protected JPanel containerPrincipal = new JPanel(new BorderLayout());
	protected JPanel containerNord = new JPanel(new GridBagLayout());
	protected JPanel containerCentre = new JPanel(new GridBagLayout());
	protected JPanel containerSud;

	/**
	 * Boutons 
	 */
	protected JButton jButtonOK;
	protected JButton jButtonAnnuler;

	/**
	 * Commandes, Actions de l'utilisateur
	 */
	protected static final String OK = "Ok";
	protected static final String ANNULER = "Annuler";

	/**
	 * Constructeur.
	 */
	public JDialogGTD() {
		super();
		soustitre.setFont(new Font("Tahoma", Font.PLAIN, 11));
		soustitre.setEditable(false);

		ImageIcon imgOk = new ImageIcon(getClass().getResource("/images/ok.png"));
		ImageIcon imgAnnuler = new ImageIcon(getClass().getResource("/images/cancel.png"));

		jButtonOK = new JButton("Valider", imgOk);
		jButtonAnnuler = new JButton("Annuler", imgAnnuler);

		containerPrincipal.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setContentPane(containerPrincipal);
	}

	/**
	 * Méthode
	 * @param img image associée au titre
	 * @param dimension la taille du titre
	 */
	public void afficheTitre(ImageIcon img, Dimension dimension) {
		titre.setFont(new Font(titre.getFont().getName(), Font.BOLD, 12));
		containerNord.setPreferredSize(dimension);
		containerNord.setBackground(Color.WHITE);
		containerNord.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		GridBagConstraints cNord = new GridBagConstraints();
		cNord.gridx = 0;
		cNord.gridy = 0;
		cNord.gridheight = 1;
		cNord.insets = new Insets(10, 10, 0, 0);
		cNord.anchor = GridBagConstraints.FIRST_LINE_START;
		cNord.weightx = 0.9;
		containerNord.add(titre, cNord);

		cNord.gridx = 0;
		cNord.gridy = GridBagConstraints.RELATIVE;
		cNord.gridheight = 1;
		cNord.anchor = GridBagConstraints.LINE_START;
		cNord.weightx = 0.9;
		cNord.insets = new Insets(0, 20, 10, 10);
		containerNord.add(soustitre, cNord);

		cNord.gridx = 1;
		cNord.gridy = 0;
		cNord.gridheight = 2;
		cNord.anchor = GridBagConstraints.FIRST_LINE_END;
		cNord.weightx = 0.1;
		cNord.insets = new Insets(10, 0, 10, 10);
		containerNord.add(new JLabel(img), cNord);
	}

	/**
	 * Chaque classe concrète possède son propre corps de popup. Il est impossible de le définir dans une classe abstraite.
	 */
	public abstract void afficheCorps();

	/**
	 * Les popups ont toujours une barre en bas pour valider ou annuler le choix de l'utilisateur.
	 */
	public void doGTDLayout() {
		containerSud = new JPanel();
		containerSud.add(jButtonOK);
		containerSud.add(jButtonAnnuler);

		containerPrincipal.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		containerPrincipal.add(containerNord, BorderLayout.NORTH);
		containerPrincipal.add(containerCentre, BorderLayout.CENTER);
		containerPrincipal.add(containerSud, BorderLayout.SOUTH);
	}

	/**
	 * Chaque classe concrète doit enregistrer ses propres listeners sur les boutons OK et Annuler.
	 */
	public abstract void initActionListeners();

	/**
	 * Affiche une ligne de formulaire avec un JText.
	 * @param nomElement le nom de l'élément.
	 * @param textComponent le champ de saisie associé au nom.
	 */
	public void afficherLigne(String nomElement, JTextComponent textComponent) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 20, 0, 20);
		c.weightx = 0.3;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = y;
		containerCentre.add(new JLabel(nomElement), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.7;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = y++;
		containerCentre.add(textComponent, c);
	}

	/**
	 * Affiche une ligne de formulaire avec un JComboBox.
	 * @param nomElement le nom de l'élément.
	 * @param jcb la liste déroulante.
	 */
	public void afficherListe(String nomElement, JComboBox jcb) {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 20, 0, 20);
		c.weightx = 0.3;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = y;
		containerCentre.add(new JLabel(nomElement), c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.7;
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = y++;
		containerCentre.add(jcb, c);
	}

	/**
	 * Affiche un séparateur.
	 */
	public void afficherSeparateur() {
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10, 20, 0, 20);
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = y++;
		c.gridwidth = 3;
		containerCentre.add(new JSeparator(SwingConstants.HORIZONTAL), c);
		c.gridwidth = 1;
	}

	/**
	 * Affiche la légende.
	 */
	public void afficherLegende() {
		JLabel legende = new JLabel("<html><font color=\"red\">*</font> champs obligatoires</html>");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = y++;
		containerCentre.add(legende, c);
	}

	/**
	 * Affiche les boutons Annuler et Valider 
	 */
	public void afficherLigneOkAnnuler() {
		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 20, 0, 20);
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = y;
		containerCentre.add(jButtonOK, c);

		c.fill = GridBagConstraints.CENTER;
		c.insets = new Insets(10, 20, 0, 20);
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = y++;
		containerCentre.add(jButtonAnnuler, c);
	}
}
