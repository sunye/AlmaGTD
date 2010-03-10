package fr.alma.ihm.vues.echeancier;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Classe représentant un échéancier de toutes les tâches à faire.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class Echeancier extends JPanel {

	private JPanel imageTache;
	private JPanel nomTache;
	private JLabel labelNom;
	private JLabel labelDate;
	private JLabel labelContext;
	private JPanel dateEcheanceTache;
	private JPanel contexteTache;
	private JLabel labelImage;

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -7476246186541375011L;

	/**
	 * Constructeur.
	 */
	public Echeancier(String nom,String Contexte,String Date) {
		initGUI(nom,Contexte,Date);
	}

	private void initGUI(String nom,String Contexte,String Date) {

		GridBagLayout thisLayout = new GridBagLayout();
		thisLayout.rowWeights = new double[] {0.1};
		thisLayout.rowHeights = new int[] {7};
		thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
		thisLayout.columnWidths = new int[] {7, 7, 7, 7};
		this.setLayout(thisLayout);
		this.setPreferredSize(new java.awt.Dimension(702, 158));
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		Icon iconeTache = new ImageIcon(getClass().getResource("/images/arbre/feuilleArbre_32.png"));
		BorderLayout imageTacheLayout = new BorderLayout();
		JLabel image = new JLabel(iconeTache);
		imageTache = new JPanel();
		this.add(image, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		imageTache.setLayout(imageTacheLayout);

		labelImage = new JLabel();
		labelImage.setText("Une belle image");
		labelImage.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		labelImage.setPreferredSize(new java.awt.Dimension(162, 154));
		imageTache.add(labelImage, BorderLayout.EAST);

		nomTache = new JPanel();
		BorderLayout jPanel1Layout = new BorderLayout();
		this.add(nomTache, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		nomTache.setLayout(jPanel1Layout);

		labelNom = new JLabel();
		nomTache.add(labelNom, BorderLayout.CENTER);
		labelNom.setText(nom);
		labelNom.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		contexteTache = new JPanel();
		BorderLayout contexteTacheLayout = new BorderLayout();
		contexteTache.setLayout(contexteTacheLayout);
		this.add(contexteTache, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

		labelContext = new JLabel();
		FlowLayout labelContextLayout = new FlowLayout();
		labelContext.setLayout(labelContextLayout);
		contexteTache.add(labelContext, BorderLayout.CENTER);
		labelContext.setText(Contexte);
		labelContext.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

		dateEcheanceTache = new JPanel();
		BorderLayout dateEcheanceTacheLayout = new BorderLayout();
		this.add(dateEcheanceTache, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
		dateEcheanceTache.setLayout(dateEcheanceTacheLayout);

		labelDate = new JLabel();
		dateEcheanceTache.add(labelDate, BorderLayout.CENTER);
		labelDate.setText(Date);
		labelDate.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	}
}
