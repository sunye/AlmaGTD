package fr.alma.ihm.vues;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import fr.alma.controleur.IControleur;
import fr.alma.ihm.Menu;
import fr.alma.ihm.vues.generale.popup.ConnexionPopup;
import fr.alma.modele.AbstractModele;
import fr.alma.observer.Observer;

/**
 * Classe ApplicationGTD, représente l'application graphique entière
 * 
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public final class ApplicationGTD extends JFrame implements Observer {

	/**
	 * UID généré
	 */
	private static final long serialVersionUID = 1663009921030966939L;

	/**
	 * instance de la classe (pattern singleton)
	 */
	private static ApplicationGTD instance;

	/**
	 * Taille statique de la fenêtre
	 */
	private static int tailleHauteur = 600;
	private static int tailleLargeur = 800;

	/**
	 * Conteneur principal
	 */
	private static final JPanel CONTPRINCIPAL = new JPanel();

	/**
	 * Conteneur comportant les différentes vues (générale, agenda, échéancier)
	 */
	private Vues containerVues;

	/**
	 * Conteneur de message d'erreur (centralisé pour que les informations ne
	 * soient pas dispersées)
	 */
	private JPanel containerErreurs = new JPanel();

	/**
	 * Le menu
	 */
	private Menu menu;

	/**
	 * Instance de notre objet controleur
	 */
	private static IControleur controler;

	/**
	 * Constructeur.
	 */
	private ApplicationGTD() {
		// Le look and feel
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Dimension d = new Dimension(tailleLargeur, tailleHauteur);
		setSize(d);
		setPreferredSize(d);
		setMinimumSize(new Dimension(640, 480));
		setTitle("Getting Things Done");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(true);
		affiche();
		containerErreurs.setPreferredSize(new Dimension(tailleLargeur, 32));
		setContentPane(CONTPRINCIPAL);
		pack();
		setVisible(true);
		ConnexionPopup.getInstance();
	}

	/**
	 * Méthode affiche permettant de gérer tous les éléments que contiennent la
	 * fenêtre
	 */
	private void affiche() {
		containerVues = new Vues();
		menu = new Menu(containerVues.getVueGenerale().getToolBar());

		this.setJMenuBar(menu);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		CONTPRINCIPAL.setLayout(new BorderLayout());
		CONTPRINCIPAL.add(containerVues, BorderLayout.CENTER);
		CONTPRINCIPAL.add(containerErreurs, BorderLayout.SOUTH);
	}

	/**
	 * Méthode getInstance (pattern singleton)
	 * 
	 * @return instance l'instance de la classe
	 */
	public static ApplicationGTD getInstance() {
		synchronized (instance) {
			if (instance == null) {
				instance = new ApplicationGTD();
			}
		}

		return instance;
	}

	// **************************************************
	// GETTERS AND SETTERS
	// **************************************************

	public int getTAILLEHAUTEUR() {
		return tailleHauteur;
	}

	public int getTAILLELARGEUR() {
		return tailleLargeur;
	}

	public Menu getMenu() {
		return menu;
	}

	public JPanel getContainerErreurs() {
		return containerErreurs;
	}

	public Vues getContainerVues() {
		return containerVues;
	}

	public IControleur getControler() {
		return controler;
	}

	public void setTAILLEHAUTEUR(final int THAUTEUR) {
		tailleHauteur = THAUTEUR;
	}

	public void setTAILLELARGEUR(final int TAILLEHAUTEUR) {
		tailleLargeur = TAILLEHAUTEUR;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setContainerErreurs(JPanel containerErreurs) {
		this.containerErreurs = containerErreurs;
	}

	public void setContainerVues(Vues containerVues) {
		this.containerVues = containerVues;
	}

	public void setControler(IControleur controler) {
		ApplicationGTD.controler = controler;
	}

	// ************************************************
	// IMPLEMENTATION DU PATTERN OBSERVER
	// ************************************************
	@Override
	public void update(AbstractModele modele) {

	}

	// ************************************************
	// TEST
	// ************************************************

	/**
	 * méthode gererMessage permettant de gérer les messages d'erreurs
	 * 
	 * @param codeErreur
	 *            le code d'erreur (0 warning, 1 validation, 2 erreur)
	 * @param string
	 *            message d'erreur
	 */
	public void gererMessage(int codeErreur, String string) {
		containerErreurs.removeAll();
		JLabel message = new JLabel(string);
		if (codeErreur == 0) {
			ImageIcon imgWarning = new ImageIcon(getClass().getResource(
					"/images/warning_24.png"));
			message.setIcon(imgWarning);
		} else if (codeErreur == 1) {
			ImageIcon imgok = new ImageIcon(getClass().getResource(
					"/images/ok_24.png"));
			message.setIcon(imgok);
		} else if (codeErreur == 2) {
			ImageIcon imgPasOk = new ImageIcon(getClass().getResource(
					"/images/warning_24.png"));
			message.setIcon(imgPasOk);
		}
		containerErreurs.add(message);
		containerErreurs.revalidate();
		containerErreurs.repaint();
	}

}
