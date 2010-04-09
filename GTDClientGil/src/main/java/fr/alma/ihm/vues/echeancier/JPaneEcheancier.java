package fr.alma.ihm.vues.echeancier;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.alma.controleur.Controleur;
import fr.alma.modele.noyau.Tache;
import fr.alma.modele.persistance.DaoFactory;
import fr.alma.modele.persistance.dao.TacheDao;

/**
 * Zone d'affichage des tâches, triées par date d'échéance.
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public class JPaneEcheancier extends JScrollPane {

	/**
	 * UID généré 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Echeancier echeancier;
	private List<Tache> taches;

	/**
	 * Constructeur.
	 */
	public JPaneEcheancier() {
		super();
		setBorder(BorderFactory.createTitledBorder("Jour"));
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		taches = new ArrayList<Tache>();
		Dimension d1 = new Dimension(500, 510);
		setBounds(0, 0, 500, 510);
		setSize(d1);
		setPreferredSize(d1);

		setViewportBorder(BorderFactory.createLineBorder(Color.GRAY));
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
		ArrayList<Echeancier> l = new ArrayList<Echeancier>();
		taches = getListeTacheEcheance();
		for(Tache t : taches){
			l.add(new Echeancier(t.getNom(), t.getContexte(), Controleur.DATEFORMAT.format(t.getDateEcheance())));
		}
		for (int i=0; i< l.size();i++){
			GridBagConstraints gbc1 = new GridBagConstraints();
			gbc1.gridx = i; gbc1.gridy = 0; gbc1.anchor=GridBagConstraints.CENTER;
			gbc1.fill = GridBagConstraints.HORIZONTAL;
			p1.add(l.get(i),gbc1);
		}

		// Création de la zone principale (contenant un ascenseur)
		setViewportView(p1);
	}

	private final List<Tache> getListeTacheEcheance(){
		ArrayList<Tache> l = new ArrayList<Tache>();
		TacheDao dao = DaoFactory.createTacheDao();
		l = (ArrayList<Tache>) dao.recupererTout();
		recupererEcheance(l);
		trierTaches();
		return l;
	}

	private final void recupererEcheance(List<Tache> tacheList){
		for(Tache t : tacheList){
			if(t.getDateEcheance() != null){
				taches.add(t);
			}
		}
	}

	private final void trierTaches(){
		Tache[] ltrie = new Tache[taches.size()];
		int k = 0;
		for(Tache t : taches){
			ltrie[k] = t;
			k++;
		}
		int MAX = k;
		int i = 0;
		int p = 0;
		int j =0;
		Tache x;
		for (i = 1; i < MAX; i++) 
		{
			x = ltrie[i]; 
			p = i-1;
			while (ltrie[p].getDateEcheance().after(x.getDateEcheance()) && p-- > 0) {}
			p++;    
			for (j = i-1; j >= p; j--) {
				ltrie[j+1] = ltrie[j]; 
			}   
			ltrie[p] = x;
		}
		ArrayList<Tache> retour = new ArrayList<Tache>();
		for(int y=0;y<MAX;y++){
			retour.add(ltrie[y]);
		}
		this.setTaches(retour);
	}

	public Echeancier getEcheancier() {
		return echeancier;
	}

	public void setEcheancier(Echeancier echeancier) {
		this.echeancier = echeancier;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}
}
