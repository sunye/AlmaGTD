package univ.util;

import java.util.Calendar;
import univ.calendar.Day;
import univ.calendar.Week;

/**
 * Classe représentant une Date et une Heure, utilisée par l'ensemble de notre
 * modèle
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class DateTime {

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private int second;

	/**
	 *
	 */
	public DateTime() {
		this(false);
	}

	/**
	 *
	 * @param withTime
	 */
	public DateTime(boolean withTime) {
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH) + 1;
		day = c.get(Calendar.DAY_OF_MONTH);
		if (withTime) {
			hour = c.get(Calendar.HOUR);
			minute = c.get(Calendar.MINUTE);
			second = c.get(Calendar.SECOND);
		}
	}

	/**
	 *
	 * @param dateTime
	 */
	public DateTime(String dateTime) {
		year = Integer.parseInt(dateTime.substring(0, 4));
		month = Integer.parseInt(dateTime.substring(4, 6));
		day = Integer.parseInt(dateTime.substring(6, 8));
		hour = Integer.parseInt(dateTime.substring(9, 11));
		minute = Integer.parseInt(dateTime.substring(11, 13));
		second = Integer.parseInt(dateTime.substring(13, 15));
	}

	/**
	 *
	 * @param cloneDateTime
	 */
	public DateTime(DateTime cloneDateTime) {
		year = cloneDateTime.getYear();
		month = cloneDateTime.getMonth();
		day = cloneDateTime.getDay();
		hour = cloneDateTime.getHour();
		minute = cloneDateTime.getMinute();
		second = cloneDateTime.getSecond();
	}

	/**
	 * Renvoi le jour de la semaine en chiffre
	 *
	 * @return Le numéro du jour de la semaine
	 */
	public String getDayOfWeek() {
		return getDayOfWeek(false);
	}

	/**
	 * Renvoi le jour de la semaine en chiffre ou en texte
	 *
	 * @param inText Permet de choisir entre un retour en chiffre ou en texte
	 * @return Le numéro du jour de la semaine, ou le nom du jour, suivant la
	 * valeur de inText
	 */
	public String getDayOfWeek(boolean inText) {
		String ret;
		//Utilisation de la classe java.util.Calendar
		int dayOfWeek;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);
		dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		dayOfWeek = dayOfWeek - 1;
		if (dayOfWeek == 0) {
			dayOfWeek = 7; // Gestion du dimanche
		}
		ret = new Integer(dayOfWeek).toString();
		if (inText) {
			switch (dayOfWeek) {
				case 1:
					ret = "Lundi";
					break;
				case 2:
					ret = "Mardi";
					break;
				case 3:
					ret = "Mercredi";
					break;
				case 4:
					ret = "Jeudi";
					break;
				case 5:
					ret = "Vendredi";
					break;
				case 6:
					ret = "Samedi";
					break;
				case 7:
					ret = "Dimanche";
					break;
			}
		}
		return ret;
	}

	/**
	 * Permet de connaitre le numéro de la semaine dans une année
	 *
	 * @return Le numéro de la semaine
	 */
	public int getWeekOfYear() {
		//Utilisation de la classe java.util.Calendar
		int weekOfYear;
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);
		weekOfYear = c.get(Calendar.WEEK_OF_YEAR);
		return weekOfYear;
	}

	/**
	 * Permet d'ajouter un nombre de jours à une date, en prenant en compte les
	 * années bisextiles, etc...
	 *
	 * @param add Le nombre de jours à ajouter
	 * @return Une nouvelle DateTime avec les jours ajoutés
	 */
	public DateTime addDay(int add) {
		//Utilisation de la classe java.util.Calendar
		DateTime dateAdded = new DateTime(this);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		c.set(Calendar.DAY_OF_MONTH, day);
		c.add(Calendar.DATE, add);
		dateAdded.setYear(c.get(Calendar.YEAR));
		dateAdded.setMonth(c.get(Calendar.MONTH) + 1);
		dateAdded.setDay(c.get(Calendar.DAY_OF_MONTH));
		return dateAdded;
	}

	/**
	 * Test si la DateTime correspond au Day
	 *
	 * @param day La Day à comparer
	 * @return Vrai si la DateTime correpond, faux sinon
	 */
	public boolean inDay(Day day) {
		return (this.compareTo(day.getDate()) == 0);
	}

	/**
	 * Test si la DateTime est comprise dans la Week
	 *
	 * @param week La Week à comparer
	 * @return Vrai si la DateTime est comprise, faux sinon
	 */
	public boolean inWeek(Week week) {
		return (this.compareTo(week.getStartDate()) >= 0 && this.compareTo(week.getEndDate()) <= 0);
	}

	/**
	 *
	 * @param withTime
	 * @return
	 */
	public String toString(boolean withTime) {
		if (withTime) {
			return Integer.toString(day) + "/"
					+ Integer.toString(month) + "/"
					+ Integer.toString(year) + " "
					+ Integer.toString(hour) + ":"
					+ Integer.toString(minute) + ":"
					+ Integer.toString(second);
		} else {
			return Integer.toString(day) + "/"
					+ Integer.toString(month) + "/"
					+ Integer.toString(year);
		}
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return toString(false);
	}

	/**
	 * Retourne une chaine avec tout les attributs concaténés simplement
	 *
	 * @param withTime Permet définir si on veut l'heure en plus de la date
	 * @return
	 */
	public String toSimpleString(boolean withTime) {
		if (withTime) {
			return Integer.toString(year)
					+ Integer.toString(month)
					+ Integer.toString(day)
					+ Integer.toString(hour)
					+ Integer.toString(minute)
					+ Integer.toString(second);
		} else {
			return Integer.toString(year)
					+ Integer.toString(month)
					+ Integer.toString(day);
		}
	}

	/**
	 *
	 * @return
	 */
	public String toSimpleString() {
		return toSimpleString(false);
	}

	/**
	 *
	 * @param otherDateTime
	 * @param compareTime
	 * @return
	 */
	public int compareTo(DateTime otherDateTime, boolean compareTime) {
		if (year > otherDateTime.getYear()) {
			return 1;
		} else if (year < otherDateTime.getYear()) {
			return -1;
		} else {
			if (month > otherDateTime.getMonth()) {
				return 1;
			} else if (month < otherDateTime.getMonth()) {
				return -1;
			} else {
				if (day > otherDateTime.getDay()) {
					return 1;
				} else if (day < otherDateTime.getDay()) {
					return -1;
				} else {
					if (compareTime) {
						if (hour > otherDateTime.getHour()) {
							return 1;
						} else if (hour < otherDateTime.getHour()) {
							return -1;
						} else {
							if (minute > otherDateTime.getMinute()) {
								return 1;
							} else if (minute < otherDateTime.getMinute()) {
								return -1;
							} else {
								if (second > otherDateTime.getSecond()) {
									return 1;
								} else if (second < otherDateTime.getSecond()) {
									return -1;
								} else {
									return 0;
								}
							}
						}
					} else {
						return 0;
					}
				}
			}
		}
	}

	/**
	 *
	 * @param otherDateTime
	 * @return
	 */
	public int compareTo(DateTime otherDateTime) {
		return compareTo(otherDateTime, false);
	}

	/**
	 *
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 *
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 *
	 * @return
	 */
	public int getMonth() {
		return month;
	}

	/**
	 *
	 * @param month
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 *
	 * @return
	 */
	public int getDay() {
		return day;
	}

	/**
	 *
	 * @param day
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 *
	 * @return
	 */
	public int getHour() {
		return hour;
	}

	/**
	 *
	 * @param hour
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}

	/**
	 *
	 * @return
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 *
	 * @param minute
	 */
	public void setMinute(int minute) {
		this.minute = minute;
	}

	/**
	 *
	 * @return
	 */
	public int getSecond() {
		return second;
	}

	/**
	 *
	 * @param second
	 */
	public void setSecond(int second) {
		this.second = second;
	}
}
