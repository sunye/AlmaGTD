package fr.alma.ihm.vues.agenda.jour;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JViewport;

/**
 * Classe représentant une vue qui affiche une durée égale à 1 jour.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class AgendaJourViewPort extends JViewport {

	/**
	 * UID généré 
	 */
	private static final long serialVersionUID = -1048541254639364319L;
	
	/**
	 * Tailles statiques
	 */
	private static final Integer HAUTEUR_HEURE = 60;
	private static final Integer SEPARATION_HEURE_TACHE = 60;
	
	/**
	 * Constructeur.
	 */
	public AgendaJourViewPort() {
		Dimension d = new Dimension(300, HAUTEUR_HEURE*24);
		setPreferredSize(d);
		setDoubleBuffered(true);
	}
	
	/** 
	 * Dessine le fond
	 * @param g : Graphics */
	@Override
	public void paintComponent(Graphics g) {
		
		// Couleur de la partie "Heures de la journée"
		Color fondH = new Color(235, 235, 240);
		g.setColor(fondH);
		g.fillRect(0, 0, SEPARATION_HEURE_TACHE, HAUTEUR_HEURE*24);
		
		// Couleur de la partie "Tâches de la journée"
		Color fondT = new Color(240, 240, 230);
		g.setColor(fondT);
		g.fillRect(SEPARATION_HEURE_TACHE, 0, 795, HAUTEUR_HEURE*24);
		
		// Couleur de la police des heures
		Color heureColor = new Color(0, 0, 0);
		
		// Ligne verticale séparant les heures des tâches
		Color ligne = new Color(220, 220, 220);
		g.setColor(ligne);
		g.drawLine(SEPARATION_HEURE_TACHE, 0, SEPARATION_HEURE_TACHE, 795);
		
		// Trace les lignes horizontales séparant chaque heure
		for (Integer i=0; i<24; i++) {
			g.setColor(ligne);
			g.drawLine(0, i*HAUTEUR_HEURE, 795, i*HAUTEUR_HEURE);
			
			g.setColor(heureColor);
			String heure = i.toString() + ":00";
			g.drawString(heure, SEPARATION_HEURE_TACHE-40, i*HAUTEUR_HEURE+20);
		}
	}
}
