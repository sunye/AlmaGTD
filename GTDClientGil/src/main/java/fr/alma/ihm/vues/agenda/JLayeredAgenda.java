package fr.alma.ihm.vues.agenda;

import javax.swing.JLayeredPane;

import fr.alma.ihm.vues.agenda.jour.JPanJour;
import fr.alma.ihm.vues.agenda.mois.JPanMois;
import fr.alma.ihm.vues.agenda.semaine.JPanSemaine;

/**
 * Classe représentant un gestionnaire de calques (fr.alma.ihm.vues) personnalisé pour un Agenda.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class JLayeredAgenda extends JLayeredPane {

	/**
	 * UID généré 
	 */
	private static final long serialVersionUID = 5789090890222440182L;
	
	/**
	 * Panel du jour
	 */
	protected JPanJour jPanJour;
	
	/**
	 * Panel de la semaine
	 */
	protected JPanSemaine jPanSemaine;
	
	/**
	 * Panel du mois
	 */
	protected JPanMois jPanMois;
	
	/** 
	 * Constructeur sans paramètre. 
	 */
	public JLayeredAgenda() {
		// Paramétrage du coeur de l'agenda
		jPanJour = new JPanJour();
		jPanSemaine = new JPanSemaine();
		jPanMois = new JPanMois();
		
		add(jPanJour, JLayeredPane.DEFAULT_LAYER);
		add(jPanSemaine, JLayeredPane.DEFAULT_LAYER);
		add(jPanMois, JLayeredPane.DEFAULT_LAYER);
	}
}
