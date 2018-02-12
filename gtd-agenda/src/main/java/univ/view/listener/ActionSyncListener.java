package univ.view.listener;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.util.AuthenticationException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import univ.google.GGLAction;
import univ.google.GGLCreator;
import univ.view.MainFrame;

/**
 * Listener déclanchant la synchronisation avec Google
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class ActionSyncListener implements ActionListener {

	private static final String METAFEED_URL_BASE =
			"https://www.google.com/calendar/feeds/";
	// The string to add to the user's metafeedUrl to access the event feed for
	// their primary calendar.
	private static final String EVENT_FEED_URL_SUFFIX = "/private/full";
	// The URL for the metafeed of the specified user.
	// (e.g. http://www.google.com/feeds/calendar/jdoe@gmail.com)
	private static URL metafeedUrl = null;
	// The URL for the event feed of the specified user's primary calendar.
	// (e.g. http://www.googe.com/feeds/calendar/jdoe@gmail.com/private/full)
	private static URL eventFeedUrl = null;
	private MainFrame main;

	/**
	 *
	 * @param m
	 */
	public ActionSyncListener(MainFrame m) {
		main = m;
	}

	/**
	 *
	 * @param ae
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		// On récupère la liste des actions à effectuer pour la synchronisation
		ArrayList<GGLAction> array = new ArrayList<>();
		array = main.getSyncAction();

		CalendarService myService = new CalendarService("");
		String userName = MainFrame.mainLogin;
		String userPassword = MainFrame.mainPwd;

		try {
			myService.setUserCredentials(userName, userPassword);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		GGLCreator.execGGLActions(myService, array);

		JOptionPane.showMessageDialog(main, "Synchronisation terminée", "Synchronisation", JOptionPane.INFORMATION_MESSAGE);
	}
}
