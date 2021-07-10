package univ.view;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import univ.calendar.Event;
import univ.calendar.EventInfos;
import univ.view.listener.MouseSelectListener;

/**
 * Classe gérant l'affichage d'un Event dans le calendrier
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class JCalendarEvent extends JPanel {

	private JCalendarDay parentDay;
	private EventInfos eventInfos;
	private Color color;
	private JLabel labelSummary;
	private JLabel labelLocation;
	private boolean selected;

	/**
	 *
	 * @param p
	 * @param e
	 * @param isSelected
	 */
	public JCalendarEvent(JCalendarDay p, EventInfos e, boolean isSelected) {
		super(new MigLayout("wrap"));
		parentDay = p;
		eventInfos = e;
		selected = isSelected;
		switch (eventInfos.getEvent().getType()) {
			case Event.TYPE_EVENT_GGL:
				color = EventInfos.GOOGLE_EVENT;
				break;
			case Event.TYPE_UNIV_GGL:
				color = EventInfos.GOOGLE_UNIV;
				break;
			case Event.TYPE_UNIV_ICS:
				color = EventInfos.ICS_UNIV;
				break;
			default:
				color = EventInfos.GOOGLE_EVENT;

		}
		String startHour, startMinutes, endHour, endMinutes;
		String summary = eventInfos.getEvent().getSummary();
		summary = summary.replace("\\,", ",");

		String description = eventInfos.getEvent().getDescription();
		description = description.replace("\\n", "<br/>");
		description = description.replace("\\,", ",");

		String location = eventInfos.getEvent().getLocation();

		startHour = new Integer(eventInfos.getEvent().getStartTime().getHour()).toString();
		startMinutes = new Integer(eventInfos.getEvent().getStartTime().getMinute()).toString();
		endHour = new Integer(eventInfos.getEvent().getEndTime().getHour()).toString();
		endMinutes = new Integer(eventInfos.getEvent().getEndTime().getMinute()).toString();
		if (startHour.length() < 2) {
			startHour = "0" + startHour;
		}
		if (startMinutes.length() < 2) {
			startMinutes = "0" + startMinutes;
		}
		if (endHour.length() < 2) {
			endHour = "0" + endHour;
		}
		if (endMinutes.length() < 2) {
			endMinutes = "0" + endMinutes;
		}
		setToolTipText("<html>" + eventInfos.getEvent().getStartTime().toString() + "<br/><b>De " + startHour + ":" + startMinutes + " à " + endHour + ":" + endMinutes + "<br/><br/>"
				+ summary + "</b><br/><br/>"
				+ description);
		setBackground(color);
		labelSummary = new JLabel(summary);
		labelLocation = new JLabel(location);
		add(labelSummary);
		add(labelLocation);
		addMouseListener(new MouseSelectListener(this));
	}

	/**
	 * Permet de changer l'apparence de l'évènement lorsqu'il est sélectionné
	 *
	 * @param select L'état de l'évenement, sélectionné ou pas
	 */
	public void setSelected(boolean select) {
		selected = select;
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		if (select) {
			setBackground(new Color(red, green, blue));
			labelSummary.setForeground(Color.BLACK);
			labelLocation.setForeground(Color.BLACK);
		} else {
			setBackground(new Color(red, green, blue, 150));
			labelSummary.setForeground(new Color(0, 0, 0, 150));
			labelLocation.setForeground(new Color(0, 0, 0, 150));
		}
		MainFrame.mainFrame.repaint();
	}

	/**
	 *
	 * @return
	 */
	public JCalendarDay getParentDay() {
		return parentDay;
	}

	/**
	 *
	 * @return
	 */
	public EventInfos getEventInfos() {
		return eventInfos;
	}

	/**
	 *
	 * @return
	 */
	public boolean isSelected() {
		return selected;
	}
}
