package univ.calendar;

import java.awt.Color;

/**
 * Classe permettant d'ajouter des informations à un event. Ces infos seront
 * utilisées par l'IHM pour mettre en forme le calendrier
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class EventInfos {

	/**
	 *
	 */
	public static final Color GOOGLE_UNIV = new Color(0, 128, 210);
	/**
	 *
	 */
	public static final Color ICS_UNIV = new Color(65, 185, 255);
	/**
	 *
	 */
	public static final Color GOOGLE_EVENT = new Color(140, 140, 140);
	private Event event;
	/**
	 * Index de la colonne d'affichage de l'Event *
	 */
	private int column;
	/**
	 * Permet de savoir quel Event on garde en cas d'Events simultanés *
	 */
	private int width;

	/**
	 *
	 * @param e
	 * @param c
	 * @param w
	 */
	public EventInfos(Event e, int c, int w) {
		event = e;
		column = c;
		width = w;
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
	public int getColumn() {
		return column;
	}

	/**
	 *
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 *
	 * @return
	 */
	public int getWidth() {
		return width;
	}

	/**
	 *
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}
}
