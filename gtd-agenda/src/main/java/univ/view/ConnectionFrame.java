package univ.view;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.util.AuthenticationException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;
import univ.util.Filter;
import univ.util.Tools;
import univ.view.listener.ActionConnectListener;

/**
 * Classe gérant l'affichage de la fenêtre de connexion
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class ConnectionFrame extends JFrame {

	/**
	 *
	 */
	public JTextField fieldLocal;
	/**
	 *
	 */
	public JTextField fieldUrl;
	/**
	 *
	 */
	public JTextField fieldLogin;
	/**
	 *
	 */
	public JPasswordField fieldPwd;
	/**
	 *
	 */
	public JRadioButton url;
	/**
	 *
	 */
	public JRadioButton local;
	/**
	 *
	 */
	public JCheckBox checkBox;

	/**
	 *
	 */
	public ConnectionFrame() {
		super();
		buildLookAndFeel();
		buildFrame();
		buildContent();
	}

	/**
	 * Selection d'un style correspondant à l'OS
	 */
	private void buildLookAndFeel() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			// ON NE FAIT RIEN DE SPECIAL
		}
	}

	/**
	 * Création de la fenêtre générale
	 */
	private void buildFrame() {
		setTitle("UnivCalendar");
		setSize(new Dimension(350, 220));
		setPreferredSize(new Dimension(350, 220));
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("img/icon-app.jpg");
		setIconImage(icon.getImage());
		setLayout(new BorderLayout());
	}

	/**
	 * Création du contenu
	 */
	private void buildContent() {
		JPanel icsChooser = new JPanel(new MigLayout());
		JPanel googleLogin = new JPanel(new MigLayout());

		add(icsChooser, BorderLayout.NORTH);
		icsChooser.add(new JLabel("ICS local : "));
		fieldLocal = new JTextField();
		fieldLocal.addMouseListener((new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent me) {
				fieldLocal.setText(chooseFile());
			}

			@Override
			public void mousePressed(MouseEvent me) {
			}

			@Override
			public void mouseReleased(MouseEvent me) {
			}

			@Override
			public void mouseEntered(MouseEvent me) {
			}

			@Override
			public void mouseExited(MouseEvent me) {
			}
		}));
		icsChooser.add(fieldLocal, "wrap, grow, width 300px");
		icsChooser.add(new JLabel("ICS URL : "));
		fieldUrl = new JTextField();
		icsChooser.add(fieldUrl, "wrap, grow, width 300px");
		local = new JRadioButton("Local");
		local.setSelected(true);
		url = new JRadioButton("URL");
		ButtonGroup group = new ButtonGroup();
		group.add(local);
		group.add(url);
		icsChooser.add(local);
		icsChooser.add(url);

		add(googleLogin, BorderLayout.CENTER);
		googleLogin.add(new JLabel("Email google : "));
		fieldLogin = new JTextField();
		googleLogin.add(fieldLogin, "wrap, grow, width 200px");
		googleLogin.add(new JLabel("Mot de passe : "));
		fieldPwd = new JPasswordField();
		googleLogin.add(fieldPwd, "wrap, grow, width 200px");


		JPanel bottom = new JPanel(new MigLayout());
		add(bottom, BorderLayout.SOUTH);
		checkBox = new JCheckBox();
		JButton connect = new JButton("Connexion");
		connect.addActionListener(new ActionConnectListener(this));
		bottom.add(new JLabel("Enregistrer les informations "));
		bottom.add(checkBox);
		bottom.add(connect);
		load();
	}

	private String chooseFile() {
		JFileChooser choix = new JFileChooser();
		choix.setAcceptAllFileFilterUsed(false);
		choix.addChoosableFileFilter(new Filter(new String[]{"ics"}, "Fichier ICS (*.ics)"));
		choix.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int retour = choix.showOpenDialog(this);
		if (retour == JFileChooser.APPROVE_OPTION) {
			return choix.getSelectedFile().getAbsolutePath();
		} else {
			return "";
		}
	}

	/**
	 * Méthode qui permet de connecter l'utilisateur et d'ouvrir la fenêtre
	 * principale
	 *
	 * @param login L'adresse mail Google de l'utilisateur
	 * @param pwd Le mot de passe de l'utilisateur
	 * @param ics Le chemin de l'ICS, soit le fichier, soit l'URL
	 * @param localIcs Un booléen permettant de connaître le type d'ICS
	 */
	public void connect(String login, String pwd, String ics, boolean localIcs) {
		boolean save = checkBox.isSelected();
		if (save) {
			save();
		} else {
			String filePath = System.getProperty("user.dir") + File.separator + "conf.ini";
			File file = new File(filePath);
		}
		CalendarService myService = new CalendarService("");
		try {
			myService.setUserCredentials(login, pwd);
			MainFrame main = new MainFrame(login, pwd, ics, localIcs);
			main.setVisible(true);
		} catch (AuthenticationException e) {
			JOptionPane.showMessageDialog(this, "Connexion impossible", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Méthode pour enregistrer les préférences dans un fichier conf.ini
	 */
	private void save() {
		ArrayList<String> array = new ArrayList<>();
		boolean localIcs = local.isSelected();
		boolean save = checkBox.isSelected();
		String ics = localIcs ? fieldLocal.getText() : fieldUrl.getText();
		String login = fieldLogin.getText();
		String pwd = fieldPwd.getText();
		pwd = Tools.encrypt(pwd, login);
		array.add(localIcs ? "ics-local" : "ics-url");
		array.add(ics);
		array.add(login);
		array.add(pwd);
		array.add(save ? "save" : "not-save");
		try {
			Tools.writeFile("conf.ini", array);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, "Impossible d'enregistrer les paramètres", "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Méthode pour charger les préférences à partir d'un fichier conf.ini
	 */
	private void load() {
		try {
			ArrayList<String> array = Tools.readFile("conf.ini");
			if (array.size() >= 5) {
				boolean localIcs = array.get(0).equals("ics-local");
				String ics = array.get(1);
				String login = array.get(2);
				String pwd = array.get(3);
				pwd = Tools.decrypt(pwd, login);
				boolean save = array.get(4).equals("save");
				if (save) {
					if (localIcs) {
						fieldLocal.setText(ics);
					} else {
						fieldUrl.setText(ics);
					}
					local.setSelected(localIcs);
					url.setSelected(!localIcs);
					fieldLogin.setText(login);
					fieldPwd.setText(pwd);
					checkBox.setSelected(save);
				}
			}
		} catch (Exception ex) {
		}
	}
}
