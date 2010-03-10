package fr.alma.ihm.vues.agenda.jour;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

/**
 * Classe représentant un élément scrollable
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class JPanJour extends JScrollPane {

	/**
	 * UID généré 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	private AgendaJourViewPort jv;

	/**
	 * Constructeur.
	 */
	public JPanJour() {
		super();
		setBorder(BorderFactory.createTitledBorder("Jour"));
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		Dimension d1 = new Dimension(500, 510);
		setBounds(0, 0, 500, 510);
		setSize(d1);
		setPreferredSize(d1);

		setViewportBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		// Création de la zone principale (contenant un ascenseur)
		jv = new AgendaJourViewPort();
		setViewportView(jv);
	}
}
