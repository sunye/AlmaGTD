package univ.calendar;

import java.util.ArrayList;
import java.util.Collections;
import univ.util.DateTime;

/**
 * Classe représentant une semaine. Elle contient la liste des jours qui la
 * composent.
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class Week implements Comparable {

	private ArrayList<Day> daysList;
	/**
	 * La date du lundi de la semaine
	 */
	private DateTime startDate;
	/**
	 * La date du dimanche de la semaine
	 */
	private DateTime endDate;

	/**
	 *
	 * @param d
	 */
	public Week(DateTime d) {
		daysList = new ArrayList<>();
		startDate = d;
		endDate = d.addDay(6);

		DateTime dayDate = new DateTime(startDate);
		Day day;
		// On initialise la semaine avec ses jours
		for (int i = 0; i < 6; i++) {
			day = new Day(dayDate);
			daysList.add(day);
			dayDate = dayDate.addDay(1);
		}
		Collections.sort(daysList);
	}

	/**
	 * Permet de récupérer la Day contenant la DateTime. La Day est créée si
	 * elle n'est pas trouvée
	 *
	 * @param date La date que doit contenir la Day à trouver
	 * @return La Day correspondante
	 */
	public Day findDay(DateTime date) {
		boolean finded = false;
		Day day = null;
		// On parcourt l'ensemble des Days du Calendar pour voir si elle existe déjà
		for (int i = 0; i < daysList.size(); i++) {
			day = daysList.get(i);
			if (date.inDay(day)) {
				finded = true;
				break;
			}
		}
		// Si elle n'est pas trouvée, on la crée
		if (!finded) {
			day = new Day(date);
			daysList.add(day);
			Collections.sort(daysList);
		}
		return day;
	}

	/**
	 *
	 * @param t
	 * @return
	 */
	@Override
	public int compareTo(Object t) {
		Week otherWeek = (Week) t;
		return startDate.compareTo(otherWeek.getStartDate());
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		String ret = "WEEK - DateBegin : " + startDate.toString() + " - DateEnd : " + endDate.toString() + "\n";
		ret += daysList.toString();
		return ret;
	}

	/**
	 *
	 * @return
	 */
	public int getWeekOfYear() {
		return startDate.getWeekOfYear();
	}

	/**
	 *
	 * @return
	 */
	public ArrayList<Day> getDaysList() {
		return daysList;
	}

	/**
	 *
	 * @param daysList
	 */
	public void setDaysList(ArrayList<Day> daysList) {
		this.daysList = daysList;
	}

	/**
	 *
	 * @return
	 */
	public DateTime getStartDate() {
		return startDate;
	}

	/**
	 *
	 * @param startDate
	 */
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}

	/**
	 *
	 * @return
	 */
	public DateTime getEndDate() {
		return endDate;
	}

	/**
	 *
	 * @param endDate
	 */
	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}
}
