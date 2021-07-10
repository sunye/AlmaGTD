package univ.google;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import univ.calendar.Calendar;
import univ.calendar.Day;
import univ.calendar.Event;
import univ.calendar.Week;
import univ.util.DateTime;
import univ.view.MainFrame;

/**
 * Classe permettant de parser les evenements Google vers notre modele
 * univ.calendar
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class GGLParser {

	// L'URL de base des metadonnees pour un utilisateur du calendar.
	private static final String METAFEED_URL_BASE =
			"https://www.google.com/calendar/feeds/";
	// La chaine a ajouter a l'url des meta donnees pour avoir acces aux evenements du calendrier principal
	private static final String EVENT_FEED_URL_SUFFIX = "/private/full";
	// L'url d'access aux evenements de l'utilisateur du calendar. 
	// (e.g. http://www.googe.com/feeds/calendar/jdoe@gmail.com/private/full)
	private static URL eventFeedUrl = null;

	/**
	 * Definit des evenements au sens de notre modele univ.calendar a partir
	 * d'un google calendar, et selon un critere de choix booleen si l'on
	 * souhaite recuperer les cours ou les autres evenements
	 *
	 * @param service Le google calendar
	 * @param cours Vrai si l'on souhaite les cours
	 * @return
	 */
	public static Calendar parse(CalendarService service, boolean cours) {
		Calendar calendar = new Calendar();

		String userName = MainFrame.mainLogin;
		String userPassword = MainFrame.mainPwd;

		// Initialisation de l'url d'acces aux evenements
		try {
			eventFeedUrl = new URL(METAFEED_URL_BASE + userName
					+ EVENT_FEED_URL_SUFFIX);
		} catch (MalformedURLException e) {
			System.err.println("Uh oh - you've got an invalid URL.");
			e.printStackTrace();
		}

		// Authentification avec les identifiants et procedure de parsing
		try {
			service.setUserCredentials(userName, userPassword);
			CalendarQuery myQuery = new CalendarQuery(eventFeedUrl);
			myQuery.setMaxResults(5000);
			CalendarEventFeed resultFeed = service.query(myQuery, CalendarEventFeed.class);
//			CalendarEventFeed resultFeed = service.getFeed(eventFeedUrl,
//					CalendarEventFeed.class);

			for (int i = 0; i < resultFeed.getEntries().size(); i++) {
				CalendarEventEntry entry = resultFeed.getEntries().get(i);

				Event currentEvent = new Event();
				// L'evenement est par defaut un "evenement google quelconque"
				currentEvent.setType("event-ggl");

				// Definition du titre
				String summary = entry.getTitle().getPlainText();
				currentEvent.setSummary(summary);

				List<When> times = entry.getTimes();
				if (!times.isEmpty()) {
					String date = times.get(0).getStartTime().toString();
					if (date.length() > 10) {

						String[] content = entry.getPlainTextContent().toString().split("\n");
						int contentSize = content.length;

						String uid = "";
						if (contentSize != 0) {
							uid = content[0];
						}
						// Definition de l'UID
						currentEvent.setUid(uid);
						boolean isCours = false;
						// Si le champ UID correspond a un champ UID de l'ics, l'evenement est un cours
						if (uid.length() > 5 && (uid.substring(0, 6).equals("CELCAT"))) {
							isCours = true;
							currentEvent.setType("univ-ggl");
						}



						if (isCours == cours) {
							// Definition des dates de debut et de fin
							DateTime datetime = new DateTime("000000000000000");
							datetime.setYear(Integer.parseInt(date.substring(0, 4)));
							datetime.setMonth(Integer.parseInt(date.substring(5, 7)));
							datetime.setDay(Integer.parseInt(date.substring(8, 10)));

							datetime.setHour(Integer.parseInt(date.substring(11, 13)));
							datetime.setMinute(Integer.parseInt(date.substring(14, 16)));
							datetime.setSecond(Integer.parseInt(date.substring(17, 19)));

//							currentEvent.setStartTime(datetime);

							DateTime datetimeEnd = new DateTime(datetime);
							datetimeEnd.setHour(Integer.parseInt(date.substring(11, 13)) + 1);

							String dateEnd = times.get(0).getEndTime().toString();
							if (dateEnd.length() > 10) {
								datetimeEnd.setYear(Integer.parseInt(dateEnd.substring(0, 4)));
								datetimeEnd.setMonth(Integer.parseInt(dateEnd.substring(5, 7)));
								datetimeEnd.setDay(Integer.parseInt(dateEnd.substring(8, 10)));

								datetimeEnd.setHour(Integer.parseInt(dateEnd.substring(11, 13)));
								datetimeEnd.setMinute(Integer.parseInt(dateEnd.substring(14, 16)));
								datetimeEnd.setSecond(Integer.parseInt(dateEnd.substring(17, 19)));

//								currentEvent.setStartTime(datetimeEnd);
							}

							Week currentWeek = null;
							Day currentDay = null;
							currentEvent.setStartTime(datetime);
							currentEvent.setEndTime(datetimeEnd);
							if (currentDay == null || !currentEvent.inDay(currentDay)) {
								currentWeek = calendar.findWeek(datetime);
								currentDay = currentWeek.findDay(datetime);
							}
							currentDay.getEventsList().add(currentEvent);
							Collections.sort(currentDay.getEventsList());

							// Definition des champs location et description
							if (isCours && contentSize >= 3) {
								String location = content[1];
								String description = content[2];
								currentEvent.setLocation(location);
								currentEvent.setDescription(description);
							} else {
								currentEvent.setLocation("");
								currentEvent.setDescription("");
							}
						}
					}
				}
			}

		} catch (IOException e) {
			// Communications error
			System.err.println("There was a problem communicating with the service.");
			e.printStackTrace();
		} catch (ServiceException e) {
			// Server side error
			System.err.println("The server had a problem handling your request.");
			e.printStackTrace();
		}

		return calendar;
	}
}
