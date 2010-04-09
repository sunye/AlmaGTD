package fr.alma.ihm.vues.generale;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.alma.controleur.Controleur;
import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.ITache;

/**
 * Classe PanneauTacheProjet affichant dans la vue générale une tâche ou un projet.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class PanneauTacheProjet extends JPanel {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -5524058170765534141L;

	/**
	 * méthode afficher
	 * @param o l'objet selectionner
	 */
	public void afficher(Object o) {
		if (o instanceof ITache) {
			afficherTache((ITache) o);
		} else if (o instanceof IProjet) {
			if (((IProjet) o).getNom().equals("GTD")) {
				afficherRacine((IProjet) o);
			} else if (((IProjet) o).getNom().equals("Panier")) {
				afficherPanier((IProjet) o);
			} else if (((IProjet) o).getNom().equals("Corbeille")) {
				afficherCorbeille((IProjet) o);
			} else {
				afficherProjet((IProjet) o);
			}
		}
	}

	/**
	 * afficherCorbeille, affiche graphiquement le contenu de la corbeille.
	 * @param o l'objet selectionner.
	 */
	private void afficherCorbeille(IProjet o) {
		removeAll();
		revalidate();
		repaint();
	}

	/**
	 * afficherPanier, affiche graphiquement le contenu du panier.
	 * @param o l'objet selectionner.
	 */
	private void afficherPanier(IProjet o) {
		removeAll();
		revalidate();
		repaint();
	}

	/**
	 * afficherRacine, affiche graphiquement le contenu de la racine de l'arborescence.
	 * @param o l'objet selectionner.
	 */
	private void afficherRacine(IProjet o) {
		removeAll();
		revalidate();
		repaint();
	}

	/**
	 * afficherTache, affiche graphiquement le contenu d'une tâche.
	 * @param o l'objet selectionner.
	 */
	private void afficherTache(ITache o) {
		removeAll();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel p1 = new JPanel(new GridLayout(5, 2));
		JPanel p2 = new JPanel(new GridLayout(2, 2));
		JPanel p3 = new JPanel(new GridLayout(2, 2));
		JPanel p4 = new JPanel(new GridLayout(3, 2));
		
		//---------------------------------------------------------------------
		p1.setBorder(BorderFactory.createTitledBorder("Informations principales"));
		//---------------------------------------------------------------------
		JLabel jLabelNom = new JLabel("Nom");
		JLabel jLabelContexte = new JLabel("Contexte");
		JLabel jLabelNotes = new JLabel("Notes");
		JLabel jLabelDDebut = new JLabel("Date de début");
		JLabel jLabelDEchance = new JLabel("Date d'échéance");
		
		JLabel jLabelValNom = new JLabel(o.getNom());
		JLabel jLabelValContexte;
		if(o.getContexte() == null){
			jLabelValContexte = new JLabel("Pas de contexte précisé");
		}
		else{
			jLabelValContexte = new JLabel(o.getContexte().toString());
		}
		JLabel jLabelValNotes = new JLabel(o.getNotes());
		JLabel jLabelValDDebut;
		if (o.getDateDebut() == null) {
			jLabelValDDebut = new JLabel("(Non renseignée)");
		} else {
			jLabelValDDebut = new JLabel(Controleur.DATEFORMAT.format(o.getDateDebut()));
		}
		JLabel jLabelValDEchance;
		if (o.getDateEcheance() == null) {
			jLabelValDEchance = new JLabel("(Non renseignée)");
		} else {
			jLabelValDEchance = new JLabel(Controleur.DATEFORMAT.format(o.getDateEcheance()));
		}

		p1.add(jLabelNom);
		p1.add(jLabelValNom);
		p1.add(jLabelContexte);
		p1.add(jLabelValContexte);
		p1.add(jLabelNotes);
		p1.add(jLabelValNotes);
		p1.add(jLabelDDebut);
		p1.add(jLabelValDDebut);
		p1.add(jLabelDEchance);
		p1.add(jLabelValDEchance);
		
		//---------------------------------------------------------------------
		p2.setBorder(BorderFactory.createTitledBorder("Efforts requis pour accomplir cette tâche"));
		//---------------------------------------------------------------------
		JLabel jLabelPriorite = new JLabel("Priorité");
		JLabel jLabelTauxEffort = new JLabel("Taux d'effort demandé");
		JLabel jLabelValPriorite;
		if (o.getPriorite().equals(-1)) {
			jLabelValPriorite = new JLabel("(Non renseigné)");
		} else {
			jLabelValPriorite = new JLabel(o.getPriorite().toString());
		}
		JLabel jLabelValTauxEffort = new JLabel(o.getTauxEffort().toString());

		p2.add(jLabelPriorite);
		p2.add(jLabelValPriorite);
		p2.add(jLabelTauxEffort);
		p2.add(jLabelValTauxEffort);
		
		//---------------------------------------------------------------------
		p3.setBorder(BorderFactory.createTitledBorder("Périodicité"));
		//---------------------------------------------------------------------
		JLabel jLabelFrequence = new JLabel("Fréquence de répétition cette tâche");
		JLabel jLabelDArret = new JLabel("Date d'arret de cette répétition");
		
		JLabel jLabelValFrequence = new JLabel(o.getPeriodicite().getFrequence());
		JLabel jLabelValDArret;
		if (o.getPeriodicite().getDateFinRepetition() == null) {
			jLabelValDArret = new JLabel("(Non renseignée)");
			if (o.getPeriodicite().getFrequence().equals("Pas de fréquence")) {
				jLabelValFrequence = new JLabel(o.getPeriodicite().getFrequence());
			} else {
				o.getPeriodicite().setFrequence("Pas de fréquence");
				jLabelValFrequence = new JLabel(o.getPeriodicite().getFrequence());
			}
			jLabelValFrequence = new JLabel(o.getPeriodicite().getFrequence());
		} else {
			jLabelValDArret = new JLabel(Controleur.DATEFORMAT.format(o.getPeriodicite().getDateFinRepetition()));
			if (o.getPeriodicite().getFrequence().equals("Pas de fréquence")) {
				o.getPeriodicite().setFrequence("Evénement ponctuel");
				jLabelValFrequence = new JLabel(o.getPeriodicite().getFrequence());
			} else {
				jLabelValFrequence = new JLabel(o.getPeriodicite().getFrequence());
			}
			
		}

		p3.add(jLabelFrequence);
		p3.add(jLabelValFrequence);
		p3.add(jLabelDArret);
		p3.add(jLabelValDArret);
		
		//---------------------------------------------------------------------
		p4.setBorder(BorderFactory.createTitledBorder("Divers"));
		//---------------------------------------------------------------------
		JLabel jLabelContacts = new JLabel("Contacts");
		JLabel jLabelLiens = new JLabel("Liens");
		JLabel jLabelTags = new JLabel("Tags");
		JLabel jLabelValContacts = new JLabel("Pas de Contacts");
		JPanel panLiens = new JPanel();
		panLiens.setLayout(new BoxLayout(panLiens, BoxLayout.Y_AXIS));
		if (o.getUrls().size() == 0) {
			panLiens.add(new JLabel("Pas d'Urls"));
		} else {
			for(String s : o.getUrls()){
				panLiens.add(new JLabel(s));
			}
		}
		JLabel jLabelValTags;
		if(o.getTags().size() == 0){
			jLabelValTags = new JLabel("Pas de Tag");
		}
		else{
			String liste = "";
			for(String s : o.getTags()){
				liste += s+" ";
			}
			jLabelValTags = new JLabel(liste);
		}
		
		
		
		p4.add(jLabelContacts);
		p4.add(jLabelValContacts);
		p4.add(jLabelLiens);
		p4.add(panLiens);
		p4.add(jLabelTags);
		p4.add(jLabelValTags);
		
		//---------------------------------------------------------------------
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		
		revalidate();
		repaint();
	}
	
	/**
	 * afficherCorbeille, affiche graphiquement le contenu d'un projet.
	 * @param o l'objet selectionner.
	 */
	private void afficherProjet(IProjet p) {
		removeAll();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panResumeProjet = new JPanel(new GridLayout(3, 2));
		JPanel panListeTaches = new JPanel();
		
		panResumeProjet.setBorder(BorderFactory.createTitledBorder("Résumé du projet"));
		panListeTaches.setBorder(BorderFactory.createTitledBorder("Liste des tâches du projet"));
		
		//---------------------------------------------------------------------
		JLabel jLabelNom = new JLabel("Nom du projet");
		JLabel jLabelContexte = new JLabel("Contexte par défaut");
		JLabel jLabelNotes = new JLabel("Notes");
		
		JLabel jLabelValNom = new JLabel(p.getNom());
		JLabel jLabelValContexte;
		if(p.getContexte() == null){
			jLabelValContexte = new JLabel("Pas de Contexte");
		}
		else{
			jLabelValContexte = new JLabel(p.getContexte().toString());
		}
		JTextArea jTextAreaNotes = new JTextArea(p.getNotes());
		jTextAreaNotes.setEditable(false);
		
		JPanel panNom = new JPanel(new GridLayout(1, 2));
		JPanel panContexte = new JPanel(new GridLayout(1, 2));
		JPanel panNotes = new JPanel(new GridLayout(1, 2));
		
		panNom.add(jLabelNom);
		panNom.add(jLabelValNom);
		panContexte.add(jLabelContexte);
		panContexte.add(jLabelValContexte);
		panNotes.add(jLabelNotes);
		panNotes.add(jTextAreaNotes);
		
		panResumeProjet.add(panNom);
		panResumeProjet.add(panContexte);
		panResumeProjet.add(panNotes);
		
		//---------------------------------------------------------------------
		// TODO liste des tâches
		//---------------------------------------------------------------------
		
		add(panResumeProjet);
		add(panListeTaches);
		
		revalidate();
		repaint();
	}

}
