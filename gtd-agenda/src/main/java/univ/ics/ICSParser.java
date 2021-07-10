package univ.ics;

import java.util.ArrayList;
import java.util.Collections;
import univ.calendar.Calendar;
import univ.calendar.Day;
import univ.calendar.Event;
import univ.calendar.Week;
import univ.util.DateTime;

/**
 * Classe permettant de parser l'ICS vers notre modèle univ.calendar
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class ICSParser {

	private static final String VEVENT = "VEVENT";

	/**
	 *
	 * @param ics
	 * @return
	 */
	public static Calendar parse(ArrayList<String> ics) {
		Calendar calendar = new Calendar();
		String line;
		Week currentWeek = null;
		Day currentDay = null;
		Event currentEvent = null;
		for (int i = 0; i < ics.size(); i++) {
			line = ics.get(i);
			if (isBegin(line)) {
				if (getValue(line).equalsIgnoreCase(VEVENT)) {
					currentEvent = new Event();
					currentEvent.setType(Event.TYPE_UNIV_ICS);
				}
			}
			// Si la ligne est une Date de début d'event, on recherche la semaine
			// et le jour correspondant à l'event et on l'ajoute
			if (isDtStart(line)) {
				DateTime date = new DateTime(getValue(line));
				date.setHour(date.getHour() + 1);
				currentEvent.setStartTime(date);
				if (currentDay == null || !currentEvent.inDay(currentDay)) {
					currentWeek = calendar.findWeek(date);
					currentDay = currentWeek.findDay(date);
				}
				currentDay.getEventsList().add(currentEvent);
				Collections.sort(currentDay.getEventsList());
			}
			if (isDtEnd(line)) {
				DateTime date = new DateTime(getValue(line));
				date.setHour(date.getHour() + 1);
				currentEvent.setEndTime(date);
			}
			if (isUID(line)) {
				currentEvent.setUid(getValue(line));
			}
			if (isSummary(line)) {
				currentEvent.setSummary(getValue(line));
			}
			if (isLocation(line)) {
				currentEvent.setLocation(getValue(line));
			}
			if (isDescription(line)) {
				currentEvent.setDescription(getValue(line));
			}
			if (isCategories(line)) {
				// ON NE FAIT RIEN
			}
			if (isEnd(line)) {
				if (getValue(line).equalsIgnoreCase(VEVENT)) {
					currentEvent = null;
				}
			}
		}

		return calendar;
	}

	private static boolean isBegin(String line) {
		return line.toLowerCase().startsWith("BEGIN:".toLowerCase());
	}

	private static boolean isDtStart(String line) {
		return line.toLowerCase().startsWith("DTSTART:".toLowerCase());
	}

	private static boolean isDtEnd(String line) {
		return line.toLowerCase().startsWith("DTEND:".toLowerCase());
	}

	private static boolean isUID(String line) {
		return line.toLowerCase().startsWith("UID:".toLowerCase());
	}

	private static boolean isSummary(String line) {
		return line.toLowerCase().startsWith("SUMMARY:".toLowerCase());
	}

	private static boolean isLocation(String line) {
		return line.toLowerCase().startsWith("LOCATION:".toLowerCase());
	}

	private static boolean isDescription(String line) {
		return line.toLowerCase().startsWith("DESCRIPTION:".toLowerCase());
	}

	private static boolean isCategories(String line) {
		return line.toLowerCase().startsWith("CATEGORIES:".toLowerCase());
	}

	private static boolean isEnd(String line) {
		return line.toLowerCase().startsWith("END:".toLowerCase());
	}

	private static String getValue(String line) {
		int position = line.indexOf(":");
		return line.substring(position + 1, line.length());
	}
}
