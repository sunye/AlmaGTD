package fr.alma.ihm.vues.agenda.semaine;

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
public class AgendaSemaineViewPort extends JViewport {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 2156492464948768797L;
	
	/**
	 * Taille statiques.
	 */
	private static final Integer HAUTEUR_HEURE = 60;
	private static final Integer SEPARATION_HEURE_TACHE = 60;
	private static final Integer LARGEUR_JOUR = 100;
	
	//private final String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};

	/**
	 * Constructeur.
	 */
	public AgendaSemaineViewPort() {
		Dimension d = new Dimension(795, HAUTEUR_HEURE*24);
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

		// Trace les lignes verticales séparant chaque jour
		for (int i=0; i<7; i++) {
			g.setColor(ligne);
			g.drawLine(SEPARATION_HEURE_TACHE+i*LARGEUR_JOUR, 0, SEPARATION_HEURE_TACHE+i*LARGEUR_JOUR, HAUTEUR_HEURE*24);
			
			// TODO
			//g.drawString(jours[i], x, y);
		}
	}
}
