package univ.view;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import univ.calendar.Day;
import univ.calendar.Week;
import univ.google.GGLAction;
import univ.util.DateTime;

/**
 * Classe gérant l'affichage d'une Week dans le calendrier
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class JCalendarWeek extends JPanel {

	private DateTime startTime;
	private final int START_HOUR = 7;
	private final int END_HOUR = 21;
	private final int MINUTES_BY_SPLIT = 15;
	private final int NB_SPLIT = (END_HOUR - START_HOUR) * 60 / MINUTES_BY_SPLIT;
	private ArrayList<JCalendarDay> daysList;
	private JPanel content;

	/**
	 *
	 */
	public JCalendarWeek() {
		super(new MigLayout("", "[][grow]", "[grow]"));

		JPanel hours = new JPanel(new MigLayout("insets 0", "", "[grow]"));
		add(hours, "cell 0 0, grow, gaptop 30px");

		JPanel listHours = new JPanel(new MigLayout("insets 0", "", "[top, grow]"));
		hours.add(listHours, "grow");
		for (int hour = START_HOUR; hour < END_HOUR; hour++) {
			listHours.add(new JLabel(new Integer(hour).toString()), "cell 0 " + (hour - START_HOUR));
		}

		content = new JPanel(new GridLayout(0, 6));
		add(content, "cell 1 0, grow");

		daysList = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			daysList.add(new JCalendarDay(START_HOUR, END_HOUR, MINUTES_BY_SPLIT));
		}
		for (JCalendarDay day : daysList) {
			content.add(day);
		}
	}

	/**
	 * Permet de reset l'affichage d'une semaine vierge
	 */
	public void reset() {
		content.removeAll();

		daysList = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			daysList.add(new JCalendarDay(START_HOUR, END_HOUR, MINUTES_BY_SPLIT));
		}
		for (JCalendarDay day : daysList) {
			content.add(day);
		}
	}

	/**
	 * Génère l'affichage de la semaine
	 */
	public void build() {
		for (JCalendarDay jDay : daysList) {
			jDay.build();
		}
	}

	/**
	 * Selection de la semaine à afficher
	 *
	 * @param w La Week à afficher
	 */
	public void setWeek(Week w) {
		reset();
		startTime = w.getStartDate();

		int dayOfWeek;
		for (Day day : w.getDaysList()) {
			dayOfWeek = Integer.parseInt(day.getDayOfWeek()) - 1;
			if (dayOfWeek < 6) {
				JCalendarDay jDay = daysList.get(dayOfWeek);
				jDay.addDay(day);
			}
		}
	}

	/**
	 * Permet de récupérer la liste des actions à effectuer pour la
	 * synchronisation de la semaine
	 *
	 * @return Une liste de GGLActions
	 */
	public ArrayList<GGLAction> getSyncAction() {
		ArrayList<GGLAction> array = new ArrayList<>();
		for (JCalendarDay jDay : daysList) {
			array.addAll(jDay.getSyncAction());
		}
		return array;
	}

	/**
	 *
	 * @return
	 */
	public DateTime getStartTime() {
		return startTime;
	}
}
