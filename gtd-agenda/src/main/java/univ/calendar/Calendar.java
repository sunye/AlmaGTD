package univ.calendar;

import java.util.ArrayList;
import java.util.Collections;
import univ.google.GGLAction;
import univ.util.DateTime;

/**
 * Classe représentant un calendrier complet. Ce calendrier correspond soit à
 * l'ICS, soit aux évènements Google. Un calendrier contient la liste des
 * semaines.
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class Calendar {

	/**
	 * Liste des semaines composant le calendrier
	 */
	private ArrayList<Week> weeksList;
	/**
	 * Liste des actions à effectuer sur Google pour maintenant l'agenda à jour
	 */
	private ArrayList<GGLAction> gglAction;

	/**
	 *
	 */
	public Calendar() {
		weeksList = new ArrayList<>();
		gglAction = new ArrayList<>();
	}

	/**
	 * Permet de récupérer la Week contenant la DateTime. La Week est créée si
	 * elle n'est pas trouvée
	 *
	 * @param date La date que doit contenir la Week à trouver
	 * @return La Week correspondante
	 */
	public Week findWeek(DateTime date) {
		int dayOfWeek;
		boolean finded = false;
		Week week = null;
		// On parcourt l'ensemble des Weeks du Calendar pour voir si elle existe déjà
		for (int i = 0; i < getWeeksList().size(); i++) {
			week = getWeeksList().get(i);
			if (date.inWeek(week)) {
				finded = true;
				break;
			}
		}
		// Si elle n'est pas trouvée, on la crée
		if (!finded) {
			dayOfWeek = Integer.parseInt(date.getDayOfWeek());
			if (dayOfWeek != 1) {
				// Si on est pas lundi, on soustrait la position du jour
				date = date.addDay(-(dayOfWeek - 1));
			}
			week = new Week(date);
			getWeeksList().add(week);
			Collections.sort(getWeeksList());
		}
		return week;
	}

	/**
	 * Permet de mettre à jour un Calendar avec un nouveau Calendar. Le Calendar
	 * passé en paramètre est prioritaire sur le Calendar appelant la méthode
	 * pour la fusion des Events identique.
	 *
	 * @param otherCalendar Le Calendar de mise à jour
	 */
	public void update(Calendar otherCalendar) {
		Week currentWeek;
		Day currentDay;
		Event currentEvent;
		// On parcourt l'ensemble du calendrier à fusionner pour trouver les Event à créer ou à modifier
		for (Week week : otherCalendar.getWeeksList()) {
			for (Day day : week.getDaysList()) {
				for (Event event : day.getEventsList()) {
					event.checked = true;
					DateTime date = event.getStartTime();
					currentWeek = findWeek(date);
					currentDay = currentWeek.findDay(date);
					currentEvent = currentDay.findEvent(event);	// On recherche si l'Event existe déjà
					if (currentEvent == null) {
						// Si il n'existe pas on l'ajoute						
						gglAction.add(new GGLAction(event, GGLAction.UPDATE));
						currentDay.getEventsList().add(event);
						Collections.sort(currentDay.getEventsList());
					} else {
						// Si l'Event existe déjà on vérifie si il doit être modifié
						currentEvent.checked = true;
						if (!currentEvent.equals(event)) {
							gglAction.add(new GGLAction(event, GGLAction.UPDATE, currentEvent));
							currentDay.getEventsList().remove(currentEvent);
							currentDay.getEventsList().add(event);
							Collections.sort(currentDay.getEventsList());
						}
					}

				}

			}
		}
		Event event;
		// On partourt ensuite tout le Calendar initial pour déterminer les Event à supprimer
		for (Week week : weeksList) {
			for (Day day : week.getDaysList()) {
				for (int i = 0; i < day.getEventsList().size(); i++) {
					event = day.getEventsList().get(i);
					if (!event.checked) {
						day.getEventsList().remove(event);
						gglAction.add(new GGLAction(event, GGLAction.DELETE));
						i--; // On désincrémente le compteur pour prendre en compte la ligne qui vient d'etre supprimée
					}
				}
			}
		}
	}

	/**
	 * Permet de fusionner un Calendar avec le Calendar courant
	 *
	 * @param otherCalendar Le calendar à fusionner
	 */
	public void merge(Calendar otherCalendar) {

		Week currentWeek;
		Day currentDay;

		for (Week week : otherCalendar.getWeeksList()) {
			for (Day day : week.getDaysList()) {
				for (Event event : day.getEventsList()) {
					DateTime date = event.getStartTime();
					currentWeek = findWeek(date);
					currentDay = currentWeek.findDay(date);
					currentDay.getEventsList().add(event);
					Collections.sort(currentDay.getEventsList());
				}
			}
		}
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		String ret = "CALENDAR\n";
		ret += getWeeksList().toString();
		return ret;
	}

	/**
	 *
	 * @return
	 */
	public ArrayList<Week> getWeeksList() {
		return weeksList;
	}

	/**
	 *
	 * @param weeksList
	 */
	public void setWeeksList(ArrayList<Week> weeksList) {
		this.weeksList = weeksList;
	}

	/**
	 *
	 * @return
	 */
	public ArrayList<GGLAction> getGglAction() {
		return gglAction;
	}
}
