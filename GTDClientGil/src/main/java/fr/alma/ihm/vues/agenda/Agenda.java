package fr.alma.ihm.vues.agenda;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;

/**
 * Classe représentant un Agenda avec 3 vues correspondant à 3 durées : jour/semaine/mois
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class Agenda extends JPanel {

	/**
	 * UID généré 
	 */
	private static final long serialVersionUID = 4849391260436243262L;

	/** Barre d'outils permettant de switcher rapidement de mode d'affichage. */
	private JToolBar toolBar;
	
	/** Subdivision de la vue Agenda avec un calendrier et une zone d'affichage. */
	private JCalendar jcal;
	private JDayChooser jdc;
	private JPanel panAgenda;
	
	/** Gestionnaire de calques contenant les 3 modes d'affichage. */
	private JLayeredAgenda zoneAffichage;

	/** Constructeur par défaut, sans paramètre. */
	public Agenda() {
		super();
		setLayout(new BorderLayout());
		toolBar = new JToolBar(JToolBar.HORIZONTAL);
		panAgenda = new JPanel();
		panAgenda.setLayout(new BorderLayout());
		zoneAffichage = new JLayeredAgenda();
		affiche();
	}

	/**
	 * Méthode affiche permettant de gérer tous les éléments que contiennent l'agenda
	 */
	private void affiche() {
		// Création des boutons
		ImageIcon imgCalJour = new ImageIcon(getClass().getResource("/images/vueAgenda/day_48.png"));
		JButton jbj = new JButton("Jour", imgCalJour);

		ImageIcon imgCalSemaine = new ImageIcon(getClass().getResource("/images/vueAgenda/week_48.png"));
		JButton jbs = new JButton("Semaine", imgCalSemaine);
		
		ImageIcon imgCalMois = new ImageIcon(getClass().getResource("/images/vueAgenda/month_48.png"));
		JButton jbm = new JButton("Mois", imgCalMois);
		
		// Rattachement des actions des boutons
		jbj.addActionListener(new AfficherLayer("jour"));
		jbs.addActionListener(new AfficherLayer("semaine"));
		jbm.addActionListener(new AfficherLayer("mois"));

		// Paramétrage de la toolbar en haut de l'écran
		toolBar.add(jbj);
		toolBar.add(jbs);
		toolBar.add(jbm);
		toolBar.setBorder(new LineBorder(Color.BLACK));

		// Paramétrage du calendrier sur la gauche
		// Pour pouvoir afficher un calendrier sans déformation, il faut l'encapsuler dans un panneau contenant
		// le calendrier et un panneau vide. Puis, étendre le panneau vide pour qu'il occupe le maximum de place
		JPanel panGauche = new JPanel(new BorderLayout());
		
		/** TEST : ça marche pas le clic sur une date, faut cliquer dans une zone où ya pas de date pour que ça active le précédent choix ...
		Pourtant j'ai ajouté un listener sur tout ce qui était possible !
		--> A creuser */
		jcal = new JCalendar();
		jcal.addMouseListener(new AffichageJour());
		
		JMonthChooser jmc = jcal.getMonthChooser();
		jmc.addMouseListener(new AffichageJour());
		
		jdc = jcal.getDayChooser();
		jdc.addMouseListener(new AffichageJour());
		
		JPanel jpan = jdc.getDayPanel();
		jpan.addMouseListener(new AffichageJour());
		/** FIN TEST */
		
		panGauche.add(jcal, BorderLayout.NORTH);
		panGauche.add(new JPanel(), BorderLayout.CENTER);

		panAgenda.add(panGauche, BorderLayout.WEST);
		panAgenda.add(zoneAffichage, BorderLayout.CENTER);

		add(toolBar, BorderLayout.NORTH);
		add(panAgenda);
	}

	/**
	 * Listener d'affichage selon le jour, la semaine ou le mois
	 */
	private class AfficherLayer implements ActionListener {

		// TEMPORAIRE : A MODIFIER AVEC LA GESTION DU CONTROLEUR IHM
		private String cmd;

		public AfficherLayer(String cmd) {
			this.cmd = cmd;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (cmd.equals("jour")) {
				zoneAffichage.moveToFront(zoneAffichage.jPanJour);
			} else if (cmd.equals("semaine")) {
				zoneAffichage.moveToFront(zoneAffichage.jPanSemaine);
			} else if (cmd.equals("mois")) {
				zoneAffichage.moveToFront(zoneAffichage.jPanMois);
			}
		}
	}
	
	/**
	 * Listener d'affichage du jour sur l'agenda
	 */
	private class AffichageJour implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			jcal.getDate();
			zoneAffichage.jPanJour.setBorder(BorderFactory.createTitledBorder(jcal.getDate().toString()));
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
	}
}
