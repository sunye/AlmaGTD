package univ.google;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.Link;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.batch.BatchOperationType;
import com.google.gdata.data.batch.BatchUtils;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import univ.calendar.Event;

/**
 * Classe des actions sur le google calendar.
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class GGLCreator {

	// L'URL de base des metadonnees pour un utilisateur du calendar.
	private static final String METAFEED_URL_BASE =
			"https://www.google.com/calendar/feeds/";
	// La chaine a ajouter a l'url des meta donnees pour avoir acces aux evenements du calendrier principal
	private static final String EVENT_FEED_URL_SUFFIX = "/private/full";
	// L'url d'access aux evenements de l'utilisateur du calendar. 
	private static URL eventFeedUrl = null;

	/**
	 * Ajoute l'evenement du modele au google calendar.
	 *
	 * @param service Le calendrier google
	 * @param event L'evenement du modele
	 * @return
	 * @throws ServiceException
	 * @throws IOException
	 */
	public static CalendarEventEntry createEvent(CalendarService service,
			Event event) throws ServiceException, IOException {
		String userName = "atal.univ.nantes@gmail.com";

		CalendarEventEntry myEntry = new CalendarEventEntry();

		// On renseigne les champs (hors dates) tel qu'ils apparaissent dans l'ics
		myEntry.setTitle(new PlainTextConstruct(event.getSummary()));
		myEntry.setContent(new PlainTextConstruct(event.getUid() + "\n"
				+ event.getLocation() + "\n"
				+ event.getDescription() + "\n"));

		//On renseigne la date
		univ.util.DateTime start = event.getStartTime();
		univ.util.DateTime end = event.getEndTime();
		DateTime startTime = new DateTime(new Date(start.getYear() - 1900, start.getMonth() - 1, start.getDay(), start.getHour() + 1, start.getMinute(), start.getSecond()));
		DateTime endTime = new DateTime(new Date(end.getYear() - 1900, end.getMonth() - 1, end.getDay(), end.getHour() + 1, end.getMinute(), end.getSecond()));
		When eventTimes = new When();
		eventTimes.setStartTime(startTime);
		eventTimes.setEndTime(endTime);
		myEntry.addTime(eventTimes);

		try {
			eventFeedUrl = new URL(METAFEED_URL_BASE + userName
					+ EVENT_FEED_URL_SUFFIX);
		} catch (MalformedURLException e) {
			System.err.println("Uh oh - you've got an invalid URL.");
			e.printStackTrace();
		}
		return service.insert(eventFeedUrl, myEntry);
	}

	/**
	 * Met a jour la description de l'evenement dans le google calendar
	 *
	 * @param entry L'evenement google
	 * @param newTitle La nouvelle description
	 * @return
	 * @throws ServiceException
	 * @throws IOException
	 */
	public static CalendarEventEntry updateContent(CalendarEventEntry entry,
			String newTitle) throws ServiceException, IOException {
		entry.setTitle(new PlainTextConstruct(newTitle));
		return entry.update();
	}

	/**
	 * Met a jour l'evenement en entier dans le google calendar
	 *
	 * @param service Le calendrier google
	 * @param event L'evenement � changer
	 * @throws ServiceException
	 * @throws IOException
	 */
	public static void updateEvent(CalendarService service, Event event) throws ServiceException, IOException {
		String userName = "atal.univ.nantes@gmail.com";

		try {
			eventFeedUrl = new URL(METAFEED_URL_BASE + userName
					+ EVENT_FEED_URL_SUFFIX);
		} catch (MalformedURLException err) {
			System.err.println("Uh oh - you've got an invalid URL.");
			err.printStackTrace();
			return;
		}
		CalendarEventFeed resultFeed = service.getFeed(eventFeedUrl,
				CalendarEventFeed.class);

		// On cherche si un l'evenement existe deja dans le google calendar, a partir de l'uid, si oui on le supprime
		for (int i = 0; i < resultFeed.getEntries().size(); i++) {
			CalendarEventEntry entry = resultFeed.getEntries().get(i);
			if (entry.getPlainTextContent().toString().split("\n")[0].equals(event.getUid())) {
				deleteEntry(service, entry);
			}
		}
		createEvent(service, event);
	}

	/**
	 * Supprime l'evenement du calendrier google. Provient de l'exemple google.
	 *
	 * @param service Le calendrier google
	 * @param eventToDelete L'evenement a supprimer
	 * @throws ServiceException
	 * @throws IOException
	 */
	public static void deleteEntry(CalendarService service,
			CalendarEventEntry eventToDelete) throws ServiceException,
			IOException {

		// Add each item in eventsToDelete to the batch request.
		CalendarEventFeed batchRequest = new CalendarEventFeed();

		// Modify the entry toDelete with batch ID and operation type.
		//BatchUtils.setBatchId(eventToDelete, String.valueOf(i));
		BatchUtils.setBatchOperationType(eventToDelete, BatchOperationType.DELETE);
		batchRequest.getEntries().add(eventToDelete);

		// Get the URL to make batch requests to
		CalendarEventFeed feed = service.getFeed(eventFeedUrl,
				CalendarEventFeed.class);
		Link batchLink = feed.getLink(Link.Rel.FEED_BATCH, Link.Type.ATOM);
		URL batchUrl = new URL(batchLink.getHref());

		// Submit the batch request
		service.batch(batchUrl, batchRequest);

	}

	/**
	 *
	 * @param service
	 * @param event
	 */
	public static void deleteEvent(CalendarService service, Event event) {
		String userName = "atal.univ.nantes@gmail.com";

		try {
			eventFeedUrl = new URL(METAFEED_URL_BASE + userName
					+ EVENT_FEED_URL_SUFFIX);
		} catch (MalformedURLException err) {
			System.err.println("Uh oh - you've got an invalid URL.");
			err.printStackTrace();
			return;
		}
		CalendarEventFeed resultFeed = null;
		try {
			resultFeed = service.getFeed(eventFeedUrl,
					CalendarEventFeed.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// On cherche si un l'evenement existe deja dans le google calendar, a partir de l'uid, si oui on le supprime
		for (int i = 0; i < resultFeed.getEntries().size(); i++) {
			CalendarEventEntry entry = resultFeed.getEntries().get(i);
			if (entry.getPlainTextContent().toString().split("\n")[0].equals(event.getUid())) {
				try {
					deleteEntry(service, entry);
				} catch (ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 *
	 * @param service
	 * @param gglActions
	 */
	public static void execGGLActions(CalendarService service, ArrayList<GGLAction> gglActions) {
		for (int i = 0; i < gglActions.size(); i++) {
			GGLAction ggla = gglActions.get(i);
			String type = ggla.getType();
			switch (type) {

				case GGLAction.DELETE:
					deleteEvent(service, ggla.getEvent());
					break;

				case GGLAction.INSERT:
					try {
						createEvent(service, ggla.getEvent());
					} catch (ServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				case GGLAction.UPDATE:
					try {
						updateEvent(service, ggla.getEvent());
					} catch (ServiceException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				default:
				//
			}
		}
	}
}