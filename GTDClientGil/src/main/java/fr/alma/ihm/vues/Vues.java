package fr.alma.ihm.vues;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import fr.alma.ihm.vues.agenda.Agenda;
import fr.alma.ihm.vues.echeancier.JPaneEcheancier;
import fr.alma.ihm.vues.generale.Recapitulatif;

/**
 * Classe Vues, représente les différentes vues que l'on a choisi de montrer 
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public class Vues extends JPanel {

	/**
	 * UID généré 
	 */
	private static final long serialVersionUID = -4129093679117694190L;
	
	/**
	 * Vue générale 
	 */
	private Recapitulatif vueGenerale;
	
	/**
	 * Vue générale 
	 */
	private Component vueEcheancier;
	
	/**
	 * Vue générale 
	 */
	private Agenda vueAgenda;

	/**
	 * Constructeur.
	 */
	public Vues(){
		setLayout(new BorderLayout());

		//Ajout des onglets
		vueGenerale = new Recapitulatif();
		vueEcheancier = new JPaneEcheancier();
		vueAgenda = new Agenda();

		JTabbedPane ongletsVues = new JTabbedPane();
		ongletsVues.addTab("Generale", null, vueGenerale, null);
		ongletsVues.addTab("Agenda", null, vueAgenda, null);
		ongletsVues.addTab("Echeancier", null, vueEcheancier, null);
		add(ongletsVues,BorderLayout.CENTER);
	}
	
	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	
	public Recapitulatif getVueGenerale() {
		return vueGenerale;
	}

	public void setVueGenerale(Recapitulatif vueGenerale) {
		this.vueGenerale = vueGenerale;
	}

	public Component getVueEcheancier() {
		return vueEcheancier;
	}

	public void setVueEcheancier(Component vueEcheancier) {
		this.vueEcheancier = vueEcheancier;
	}

	public Agenda getVueAgenda() {
		return vueAgenda;
	}

	public void setVueAgenda(Agenda vueAgenda) {
		this.vueAgenda = vueAgenda;
	}
}
