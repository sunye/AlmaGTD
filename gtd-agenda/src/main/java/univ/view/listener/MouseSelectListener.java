/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package univ.view.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import univ.view.JCalendarEvent;

/**
 * Listener qui gère la sélection d'un évènement
 *
 * @author Noémi Salaün, Joseph Lark
 */
public class MouseSelectListener implements MouseListener {

	private JCalendarEvent event;

	/**
	 *
	 * @param e
	 */
	public MouseSelectListener(JCalendarEvent e) {
		event = e;
	}

	/**
	 *
	 * @param me
	 */
	@Override
	public void mouseClicked(MouseEvent me) {
		if (!event.isSelected()) {
			event.getParentDay().setSelected(event);
		}
	}

	/**
	 *
	 * @param me
	 */
	@Override
	public void mousePressed(MouseEvent me) {
	}

	/**
	 *
	 * @param me
	 */
	@Override
	public void mouseReleased(MouseEvent me) {
	}

	/**
	 *
	 * @param me
	 */
	@Override
	public void mouseEntered(MouseEvent me) {
	}

	/**
	 *
	 * @param me
	 */
	@Override
	public void mouseExited(MouseEvent me) {
	}
}
