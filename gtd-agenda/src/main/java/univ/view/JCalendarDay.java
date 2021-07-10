package univ.view;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import univ.calendar.Day;
import univ.calendar.Event;
import univ.calendar.EventInfos;
import univ.google.GGLAction;
import univ.util.DateTime;
import univ.util.Tools;

/**
 * Classe gérant l'affichage d'un jour dans le calendrier
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class JCalendarDay extends JPanel {

	// Découpage en tranche de 15min de 7h à 22h
	private int START_HOUR;
	private int END_HOUR;
	private int MINUTES_BY_SPLIT;
	private int NB_SPLIT;
	/**
	 * Tableau représentant l'ensemble des plages horaires affichées *
	 */
	private ArrayList<JCalendarEvent[]> checkList;
	/**
	 * Tableau comprenant la liste des Events du jour *
	 */
	private ArrayList<JCalendarEvent> eventsList;
	private JLabel title;
	private JPanel content;

	/**
	 *
	 * @param start_hour
	 * @param end_hour
	 * @param minutes_by_split
	 */
	public JCalendarDay(int start_hour, int end_hour, int minutes_by_split) {
		super(new MigLayout("insets 0, gapy 0", "[grow]", "[0:30px:30px][grow]"));

		START_HOUR = start_hour;
		END_HOUR = end_hour;
		MINUTES_BY_SPLIT = minutes_by_split;
		NB_SPLIT = (END_HOUR - START_HOUR) * 60 / MINUTES_BY_SPLIT;

		eventsList = new ArrayList<>();
		checkList = new ArrayList<>();
		JPanel header = new JPanel();
		add(header, "grow, cell 0 0");
		header.setBorder(BorderFactory.createLineBorder(Color.black));
		content = new JPanel(new MigLayout("insets 4px 0px 5px 5px, gapy 0", "[1px][grow]", "[grow, 0:5px:30px]"));
		add(content, "grow, cell 0 1");
		content.setBorder(BorderFactory.createLineBorder(Color.black));
		title = new JLabel();
		header.add(title);
		// La première colonne de la grille du layout est rempli avec des Panels de taille 1*1 pour fixer la grille
		for (int row = 0; row < NB_SPLIT; row++) {
			content.add(new JPanel(), "width 1px:1px:1px, grow, cell 0 " + row);
		}
	}

	/**
	 * Ajout d'une Day, avec parcourt des différents Events et gestion des
	 * Events en conflit
	 *
	 * @param day La Day à ajouter
	 */
	public void addDay(Day day) {
		int startHour, startMin, endHour, endMin, startPosition, endPosition;
		boolean selected;
		DateTime date = day.getDate();
		String dayName = date.getDayOfWeek(true) + " " + date.toString();

		title.setText(dayName);
		JCalendarEvent jEvent, tempJEvent;
		int col, row;
		boolean empty, done;
		// On parcourt tous les Events de la Day passé en paramètre
		for (Event event : day.getEventsList()) {
			startHour = event.getStartTime().getHour();
			startMin = event.getStartTime().getMinute();
			endHour = event.getEndTime().getHour();
			endMin = event.getEndTime().getMinute();
			if (startHour < START_HOUR) {
				startHour = START_HOUR;
			}
			if (endHour > END_HOUR) {
				endHour = END_HOUR;
			}
			done = false;
			if (startHour > END_HOUR || endHour < START_HOUR) {
				done = true;
			}
			startPosition = (startHour - START_HOUR) * (60 / MINUTES_BY_SPLIT) + Tools.floor(startMin, MINUTES_BY_SPLIT) / MINUTES_BY_SPLIT;
			endPosition = (endHour - START_HOUR) * (60 / MINUTES_BY_SPLIT) + Tools.floor(endMin, MINUTES_BY_SPLIT) / MINUTES_BY_SPLIT;
			row = startPosition;
			col = 0;
			jEvent = new JCalendarEvent(this, new EventInfos(event, 0, 0), false);
			while (!done) {
				selected = (col == 0);
				// On vérifie si le tableau contient assez de colonnes
				if (checkList.size() < col + 1) {
					checkList.add(new JCalendarEvent[NB_SPLIT]);
				}
				empty = true;
				// On vérifie que la colonne courante est bien vide là où on veut ajouter l'Event
				while (empty && row < endPosition) {
					empty = checkList.get(col)[row] == null;
					if (!empty) {
						// Si l'event est déjà présent sur Google (même UID) on le considère DONE
						if (checkList.get(col)[row].getEventInfos().getEvent().getUid().equals(event.getUid())) {
							done = true;
						}
					}
					row++;
				}
				// Si la position est libre, on ajoute l'Event
				if (empty) {
					jEvent.getEventInfos().setWidth(col + 1);
					jEvent.getEventInfos().setColumn(col + 1);
					jEvent.setSelected(selected);
					eventsList.add(jEvent); // On ajoute l'Event à la liste des Events
					// Pour chaque Event de la checkList on met à jour les largeurs
					for (int i = 0; i < checkList.size(); i++) {
						for (int j = startPosition; j < endPosition; j++) {
							if (i == col) {
								// Si c'est la colonne courante, on ajoute simplement l'Event courant
								checkList.get(i)[j] = jEvent;
							} else {
								// Si c'est une autre colonne, on renseigne le fait qu'il y a des Events simultanés
								tempJEvent = checkList.get(i)[j];
								if (tempJEvent != null) {
									tempJEvent.getEventInfos().setWidth(col + 1);
								}
							}
						}
					}
					done = true;
				}
				// Si la position n'est pas libre, on passe à la colonne suivante
				col++;
			}
		}
	}

	/**
	 * Construit l'affichage du calendrier
	 */
	public void build() {
		int col = 0;
		// D'abord on le vide
		for (Component component : getComponents()) {
			if (component.getClass() == JCalendarEvent.class) {
				remove(component);
			}
		}
		// Puis on le rempli
		for (JCalendarEvent jEvent : eventsList) {
			addEvent(jEvent);
			col++;
		}
	}

	/**
	 * Ajout d'un Event dans l'IHM
	 *
	 * @param ev L'Event avec ses infos complémentaires
	 */
	private void addEvent(JCalendarEvent jEvent) {
		EventInfos ev = jEvent.getEventInfos();
		Event event = jEvent.getEventInfos().getEvent();
		int startHour = event.getStartTime().getHour();
		int startMin = event.getStartTime().getMinute();
		int endHour = event.getEndTime().getHour();
		int endMin = event.getEndTime().getMinute();
		int maxCol = checkList.size();
		if (startHour < START_HOUR) {
			startHour = START_HOUR;
		}
		if (endHour > END_HOUR) {
			endHour = END_HOUR;
		}

		int startPosition = (startHour - START_HOUR) * (60 / MINUTES_BY_SPLIT) + Tools.floor(startMin, MINUTES_BY_SPLIT) / MINUTES_BY_SPLIT;
		int endPosition = (endHour - START_HOUR) * (60 / MINUTES_BY_SPLIT) + Tools.floor(endMin, MINUTES_BY_SPLIT) / MINUTES_BY_SPLIT;

		content.add(jEvent, "width 0:100%:100%, grow, cell " + ev.getColumn() + " " + startPosition + " " + maxCol / ev.getWidth() + " " + (endPosition - startPosition));
	}

	/**
	 * Permet de récupérer la liste des actions à effectuer pour la
	 * synchronisation de la journée, basé sur la selection des évènements dans
	 * l'agenda. Les actions sont forcement de type DELETE
	 *
	 * @return Une liste de GGLActions
	 */
	public ArrayList<GGLAction> getSyncAction() {
		ArrayList<GGLAction> array = new ArrayList<>();
		for (JCalendarEvent jEvent : eventsList) {
			// Pour chaque event, on regarde si on veut l'utiliser, si ce n'est pas le cas on demande une suppression
			if (!jEvent.isSelected()) {
				array.add(new GGLAction(jEvent.getEventInfos().getEvent(), GGLAction.DELETE));
			}
		}
		return array;
	}

	/**
	 * Permet de sélectionner l'évenement passé en paramètre et de déselectionné
	 * les évenements concurrents
	 *
	 * @param jEvent L'évènement que l'on souhaite sélectionner
	 */
	public void setSelected(JCalendarEvent jEvent) {
		int startHour, startMin, endHour, endMin, startPosition, endPosition;
		boolean done = false;
		JCalendarEvent tempJEvent = null;
		Event event = jEvent.getEventInfos().getEvent();
		startHour = event.getStartTime().getHour();
		startMin = event.getStartTime().getMinute();
		endHour = event.getEndTime().getHour();
		endMin = event.getEndTime().getMinute();
		if (startHour < START_HOUR) {
			startHour = START_HOUR;
		}
		if (endHour > END_HOUR) {
			endHour = END_HOUR;
		}
		if (startHour > END_HOUR || endHour < START_HOUR) {
			done = true;
		}
		startPosition = (startHour - START_HOUR) * (60 / MINUTES_BY_SPLIT) + Tools.floor(startMin, MINUTES_BY_SPLIT) / MINUTES_BY_SPLIT;
		endPosition = (endHour - START_HOUR) * (60 / MINUTES_BY_SPLIT) + Tools.floor(endMin, MINUTES_BY_SPLIT) / MINUTES_BY_SPLIT;

		if (!done) {
			for (int i = 0; i < checkList.size(); i++) {
				for (int j = startPosition; j < endPosition; j++) {
					if (checkList.get(i)[j] == jEvent) {
						checkList.get(i)[j].setSelected(true);
					} else {
						if (checkList.get(i)[j] != null) {
							checkList.get(i)[j].setSelected(false);
						}
					}
				}
			}
		}
	}
}
