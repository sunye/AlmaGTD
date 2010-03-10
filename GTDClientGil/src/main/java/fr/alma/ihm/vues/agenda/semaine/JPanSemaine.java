package fr.alma.ihm.vues.agenda.semaine;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

/**
 * Classe représentant une vue qui affiche une durée égale à 1 semaine.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class JPanSemaine extends JScrollPane {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -1575588360882239047L;
	
	/**
	 * 
	 */
	private AgendaSemaineViewPort jv;

	/**
	 * Constructeur.
	 */
	public JPanSemaine() {
		super();
		setBorder(BorderFactory.createTitledBorder("Semaine"));
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		Dimension d1 = new Dimension(795, 510);
		setBounds(0, 0, 795, 510);
		setSize(d1);
		setPreferredSize(d1);

		setViewportBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		// Création de la zone principale (contenant un ascenseur)
		jv = new AgendaSemaineViewPort();
		setViewportView(jv);
	}
}
