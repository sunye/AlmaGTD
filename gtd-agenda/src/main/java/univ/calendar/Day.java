package univ.calendar;

import java.util.ArrayList;
import univ.util.DateTime;

/**
 * Classe représentant un jour de notre calendrier. Un jour contient la liste
 * des évènements de la journée.
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class Day implements Comparable {

	/**
	 * La liste des évènements de la journée
	 */
	private ArrayList<Event> eventsList;
	/**
	 * La date du jour
	 */
	private DateTime date;

	/**
	 *
	 * @param d
	 */
	public Day(DateTime d) {
		eventsList = new ArrayList<>();
		date = d;
	}

	/**
	 * Permet de tester l'existance d'une Day dans une Week
	 *
	 * @param week La week dans laquelle on veut chercher la Day
	 * @return Vrai si la Week contient la Day, faux sinon
	 */
	public boolean inWeek(Week week) {
		return (date.compareTo(week.getStartDate()) >= 1 && date.compareTo(week.getEndDate()) <= -1);
	}

	/**
	 * Recherche dans la Day si un event est déjà présent grâce à son UID
	 *
	 * @param event L'Event qui est recherché
	 * @return L'Event si il est trouvé, ou null sinon
	 */
	public Event findEvent(Event event) {
		Event ret = null;
		for (Event e : eventsList) {
			if (e.getUid().equals(event.getUid())) {
				ret = e;
				break;
			}
		}
		return ret;
	}

	/**
	 *
	 * @param t
	 * @return
	 */
	@Override
	public int compareTo(Object t) {
		Day otherDay = (Day) t;
		return date.compareTo(otherDay.getDate());
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		String ret = "\tDAY - Date : " + date.getDayOfWeek() + " " + date.toString() + "\n";
		ret += eventsList.toString() + "\n";
		return ret;
	}

	/**
	 *
	 * @return
	 */
	public String getDayOfWeek() {
		return getDayOfWeek(false);
	}

	/**
	 *
	 * @param inText
	 * @return
	 */
	public String getDayOfWeek(boolean inText) {
		return date.getDayOfWeek(inText);
	}

	/**
	 *
	 * @return
	 */
	public ArrayList<Event> getEventsList() {
		return eventsList;
	}

	/**
	 *
	 * @param eventsList
	 */
	public void setEventsList(ArrayList<Event> eventsList) {
		this.eventsList = eventsList;
	}

	/**
	 *
	 * @return
	 */
	public DateTime getDate() {
		return date;
	}

	/**
	 *
	 * @param date
	 */
	public void setDate(DateTime date) {
		this.date = date;
	}
}
