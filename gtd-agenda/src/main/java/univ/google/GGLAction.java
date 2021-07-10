package univ.google;

import univ.calendar.Event;

/**
 * Classe définissant les appels aux actions possibles sur le google calendar.
 *
 * @author Noémi Salaün <noemi.salaun@etu.univ-nantes.fr>
 */
public class GGLAction {

	/**
	 *
	 */
	public static final String UPDATE = "update";
	/**
	 *
	 */
	public static final String DELETE = "delete";
	/**
	 *
	 */
	public static final String INSERT = "insert";
	private String type;
	private Event event;
	private Event oldEvent;

	/**
	 *
	 * @param e
	 * @param t
	 */
	public GGLAction(Event e, String t) {
		this(e, t, null);
	}

	/**
	 *
	 * @param e
	 * @param t
	 * @param old
	 */
	public GGLAction(Event e, String t, Event old) {
		event = e;
		if (t.equals(INSERT) || t.equals(DELETE)) {
			type = t;
		} else {
			type = UPDATE;
			if (old != null) {
				oldEvent = old;
			}
		}
	}

	/**
	 *
	 * @return
	 */
	@Override
	public String toString() {
		if (type.equals(UPDATE) && oldEvent != null) {
			return "Type : " + type + "\n\tOLD : " + oldEvent.toString() + "\n\tNEW : " + event.toString();
		} else {
			return "Type : " + type + "\n" + event.toString();
		}
	}

	/**
	 *
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 *
	 * @param type
	 */
	public void setType(String type) {
		if (type.equals(UPDATE) || type.equals(INSERT) || type.equals(DELETE)) {
			this.type = type;
		} else {
			this.type = UPDATE;
		}
	}

	/**
	 *
	 * @return
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 *
	 * @param event
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	/**
	 *
	 * @return
	 */
	public Event getOldEvent() {
		return oldEvent;
	}

	/**
	 *
	 * @param oldEvent
	 */
	public void setOldEvent(Event oldEvent) {
		this.oldEvent = oldEvent;
	}
}
