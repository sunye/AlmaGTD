package univ.view;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.util.AuthenticationException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;
import univ.calendar.Calendar;
import univ.calendar.Week;
import univ.google.GGLAction;
import univ.google.GGLCreator;
import univ.google.GGLParser;
import univ.ics.ICSFinder;
import univ.ics.ICSParser;
import univ.util.DateTime;
import univ.view.listener.ActionSyncListener;
import univ.view.listener.ActionWeekChooserListener;

/**
 * Classe gérant l'affichage de la fenêtre principale
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class MainFrame extends JFrame {

	public static String mainLogin;
	public static String mainPwd;
	
	private Calendar calendar;
	private JCalendarWeek jWeek;
	/**
	 *
	 */
	public JLabel weekNumber;
	/**
	 *
	 */
	public JLabel weekDetail;
	/**
	 *
	 */
	public static MainFrame mainFrame;

	/**
	 *
	 * @param login
	 * @param pwd
	 * @param ics
	 * @param localIcs
	 */
	public MainFrame(String login, String pwd, String ics, boolean localIcs) {
		super();
		mainFrame = this;
		ToolTipManager.sharedInstance().setDismissDelay(1000000);
		buildLookAndFeel();
		buildFrame();
		buildContent();
		buildCalendar(login, pwd, ics, localIcs);
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
		setSize(new Dimension(800, 600));
		setPreferredSize(new Dimension(800, 600));
		setLocationRelativeTo(null);
		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("img/icon-app.jpg");
		setIconImage(icon.getImage());
		setLayout(new BorderLayout());
	}

	/**
	 * Création du contenu
	 */
	private void buildContent() {
		JPanel content = new JPanel(new MigLayout("wrap 1"));
		add(content);

		// Panel top
		JPanel top = new JPanel(new MigLayout());
		content.add(top, "grow");

		JPanel topContent = new JPanel(new FlowLayout());
		top.add(topContent, "dock center");
		JButton btnSync = new JButton("Synchroniser");
		top.add(btnSync, "dock east");
		btnSync.addActionListener(new ActionSyncListener(this));

		JButton left = new JButton("<");
		left.addActionListener(new ActionWeekChooserListener(this));
		topContent.add(left);

		JPanel week = new JPanel(new GridLayout(0, 1));
		setWeekNumber(new JLabel("Semaine 45"));
		getWeekNumber().setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		week.add(getWeekNumber());
		setWeekDetail(new JLabel("Du 5/11 au 15/11"));
		getWeekDetail().setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		week.add(getWeekDetail());
		topContent.add(week);

		JButton right = new JButton(">");
		right.addActionListener(new ActionWeekChooserListener(this));
		topContent.add(right);

		// Panel bottom
		JPanel bottom = new JPanel(new MigLayout());
		bottom.setBackground(Color.ORANGE);
		content.add(bottom, "push, grow");

		jWeek = new JCalendarWeek();
		bottom.add(jWeek, "push, grow");
	}

	/**
	 * Permet de mettre à jour l'affichage selon la semaine
	 *
	 * @param w Semaine que l'on souhaite afficher
	 */
	public void setWeek(Week w) {
		weekNumber.setText("Semaine " + w.getStartDate().getWeekOfYear());
		weekDetail.setText("Du " + w.getStartDate().toString() + " au " + w.getEndDate().toString());
		jWeek.setWeek(w);
		build();
	}

	/**
	 * Déclanche l'affichage du calendrier
	 */
	public void build() {
		jWeek.build();
	}

	/**
	 *
	 * @return
	 */
	public JCalendarWeek getJWeek() {
		return jWeek;
	}

	/**
	 *
	 * @return
	 */
	public JLabel getWeekNumber() {
		return weekNumber;
	}

	/**
	 *
	 * @param weekNumber
	 */
	public void setWeekNumber(JLabel weekNumber) {
		this.weekNumber = weekNumber;
	}

	/**
	 *
	 * @return
	 */
	public JLabel getWeekDetail() {
		return weekDetail;
	}

	/**
	 *
	 * @param weekDetail
	 */
	public void setWeekDetail(JLabel weekDetail) {
		this.weekDetail = weekDetail;
	}

	/**
	 *
	 * @return
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 *
	 * @param calendar
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
		setWeek(this.calendar.findWeek(new DateTime()));
	}

	/**
	 * Permet de récupérer la liste des actions à effectuer sur Google par
	 * rapport à l'état actuel du calendrier
	 *
	 * @return
	 */
	public ArrayList<GGLAction> getSyncAction() {
		return jWeek.getSyncAction();
	}

	/**
	 * Permet de construire le calendrier à partir des informations Google et de
	 * l'ICS
	 *
	 * @param login L'adresse mail Google de l'utilisateur
	 * @param pwd Le mot de passe de l'utilisateur
	 * @param ics L'ICS universtiaire, soit en local soit en URL
	 * @param localIcs Un booléen permettant d'identifier le type d'ICS
	 */
	private void buildCalendar(String login, String pwd, String ics, boolean localIcs) {
		ArrayList<String> icsArray = null;

		if (localIcs) {
			try {
				icsArray = ICSFinder.getLocal(ics);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			try {
				icsArray = ICSFinder.getURL(ics);
			} catch (MalformedURLException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
			}
		}

		Calendar calIcs = ICSParser.parse(icsArray);

		CalendarService myService = new CalendarService("");
		try {
			myService.setUserCredentials(login, pwd);
			mainLogin = login;
			mainPwd = pwd;
		} catch (AuthenticationException e) {
			JOptionPane.showMessageDialog(this, "Connexion impossible", "Erreur de connexion", JOptionPane.ERROR_MESSAGE);
		}
		Calendar calGoogleCours = GGLParser.parse(myService, true);
		Calendar calGoogleNotCours = GGLParser.parse(myService, false);

		calGoogleCours.update(calIcs);
		calGoogleCours.merge(calGoogleNotCours);
		setCalendar(calGoogleCours);
		GGLCreator.execGGLActions(myService, calGoogleCours.getGglAction());
	}
}
