package fr.alma.ihm.vues.generale.popup;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import fr.alma.controleur.Controleur;

/**
 * Classe affichant un calendrier sous la forme d'une fenêtre popup.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class CalendarPopup extends JDialog {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 44904550706455228L;

	/**
	 * instance de la classe (pattern singleton).
	 */
	private static CalendarPopup instance = null;

	/**
	 * Le Jcalendar et son champ texte associé
	 */
	protected static JCalendar cal;
	private JTextField dateField;
	
	/**
	 * Constructeur.
	 */
	private CalendarPopup() {
		JPanel containerPrincipal = new JPanel();
		cal = new JCalendar();
		containerPrincipal.add(cal);
		setSize(250,200);
		setContentPane(containerPrincipal);
		setTitle("Calendrier");
		setDefaultCloseOperation(CalendarPopup.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		pack();
		setVisible(true);
		setModal(true);
	}

	/**
	 * Méthode getInstance (pattern singleton).
	 * @return instance l'instance de la classe
	 */
	public final synchronized static CalendarPopup getInstance() {
		if (instance == null){
			instance = new CalendarPopup();
		}
		instance.toFront();
		return instance;
	}
	
	/**
	 * Modifie le champ texte de la date en lui ajoutant un listener
	 * @param dateField le champ de saisie pour la date
	 */
	public void setDateField(JTextField dateField) {
		this.dateField = dateField;		
		instance.addMouseListener(new CalendarPopupListener());
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
	 * Listener associé au calendrier
	 * @author Université de Nantes
	 * @since 2009
	 * @version 0.1
	 */
	public class CalendarPopupListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			System.out.println(arg0.getSource().toString());
			dateField.setText(Controleur.df.format(cal.getDate()));
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			dateField.setText(Controleur.df.format(cal.getDate()));
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			dateField.setText(Controleur.df.format(cal.getDate()));
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}
	
	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	
	public static JCalendar getCal() {
		return cal;
	}

	public static void setCal(JCalendar cal) {
		CalendarPopup.cal = cal;
	}

}
