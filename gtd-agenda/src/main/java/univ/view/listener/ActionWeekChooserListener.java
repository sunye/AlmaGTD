package univ.view.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import univ.calendar.Week;
import univ.util.DateTime;
import univ.view.MainFrame;

/**
 * Listener permettant de naviguer entre les semaines
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class ActionWeekChooserListener implements ActionListener {

	private MainFrame main;

	/**
	 *
	 * @param m
	 */
	public ActionWeekChooserListener(MainFrame m) {
		main = m;
	}

	/**
	 *
	 * @param ae
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		DateTime weekStart = main.getJWeek().getStartTime();
		JButton button = (JButton) ae.getSource();
		if (button.getText().equals("<")) {
			weekStart = weekStart.addDay(-7);
		} else {
			weekStart = weekStart.addDay(7);
		}
		Week week = main.getCalendar().findWeek(weekStart);
		main.setWeek(week);
	}
}
