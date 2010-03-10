package fr.alma.ihm.vues.agenda.mois;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Classe représentant une vue qui affiche une durée égale à 1 mois.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class JPanMois extends JPanel {

	/**
	 * UID généré. 
	 */
	private static final long serialVersionUID = -9027932421454426L;
	
	/**
	 * Taille statiques.
	 */
	private static final Integer HAUTEUR_JOUR = 85;
	private static final Integer LARGEUR_JOUR = 115;
	private static final Integer HAUTEUR_ENTETE_JOUR = 15;

	public JPanMois() {
		super();
		Dimension d1 = new Dimension(795, 510);
		setBounds(0, 0, 795, 510);
		setSize(d1);
		setPreferredSize(d1);
	}
	
	/** 
	 * Dessine le fond
	 * @param g : Graphics */
	@Override
	public void paintComponent(Graphics g) {

		// Couleur du fond
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 795, 510);

		// Couleur de l'entête des jours
		g.setColor(new Color(240, 240, 240));

		// Trace les bandes de couleur pour écrire les numéros des jours
		for (int i=0; i<6; i++) {
			g.fillRect(0, i*HAUTEUR_JOUR, 795, HAUTEUR_ENTETE_JOUR);
		}

		// Couleur du quadrillage séparant chaque jour
		Color ligne = new Color(220, 220, 255);
		g.setColor(ligne);

		// Trace les lignes horizontales séparant chaque jour
		for (int i=0; i<6; i++) {
			g.setColor(ligne);
			g.drawLine(0, i*HAUTEUR_JOUR, 795, i*HAUTEUR_JOUR);
			g.drawLine(0, i*HAUTEUR_JOUR+HAUTEUR_ENTETE_JOUR, 795, i*HAUTEUR_JOUR+HAUTEUR_ENTETE_JOUR);

			//g.setColor(jourColor);
			//String heure = i.toString() + ":00";
			//g.drawString(heure, SEPARATION_HEURE_TACHE-40, i*HAUTEUR_HEURE+20);
		}
		
		// Trace les lignes verticales séparant chaque jour
		for (int i=0; i<7; i++) {
			g.setColor(ligne);
			g.drawLine(i*LARGEUR_JOUR, 0, i*LARGEUR_JOUR, 510);
		}
	}
}
