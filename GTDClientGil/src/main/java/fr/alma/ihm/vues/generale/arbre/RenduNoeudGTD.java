package fr.alma.ihm.vues.generale.arbre;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.Projet;
import fr.alma.modele.noyau.Tache;

/**
 * Classe représentant l'affichage d'un noeud dans l'arborescence. Chaque type de noeud possède un affichage qui lui est propre.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class RenduNoeudGTD extends DefaultTreeCellRenderer {
	
	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -5788325552307821157L;
	
	/**
	 * images 
	 */
	private Icon iconeTache = new ImageIcon(getClass().getResource("/images/arbre/feuilleArbre_32.png"));
	private Icon iconeDossier = new ImageIcon(getClass().getResource("/images/arbre/dossierBleu_32.png"));
	private Icon iconePanier = new ImageIcon(getClass().getResource("/images/arbre/panier_32.png"));
	private Icon iconeCorbeille = new ImageIcon(getClass().getResource("/images/arbre/corbeille_vide.png"));

	/**
	 * Constructeur.
	 */
	public RenduNoeudGTD() {
		super();
	}
	
	/**
	 * installation des images
	 */
	private void setIcones(Icon i) {
		setOpenIcon(i);
		setLeafIcon(i);
		setClosedIcon(i);
	}

	/**
	 * 
	 * @param tree
	 * @param value
	 * @param sel
	 * @param estOuvert
	 * @param estFeuille
	 * @param row
	 * @param hasFocus
	 * @return Un composant
	 */
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean estOuvert, boolean estFeuille, int row, boolean hasFocus) {
		NoeudGTD node = (NoeudGTD) value;
		Object nodeInfo = node.getUserObject();
		if (nodeInfo instanceof Tache) {
			setIcones(iconeTache);
		} else if (nodeInfo instanceof Projet) {
			if (((IProjet)nodeInfo).getNom().equals("Panier")) {
				setIcones(iconePanier);	
			} else if (((IProjet)nodeInfo).getNom().equals("Corbeille")) {
				setIcones(iconeCorbeille);
			} else {
				setIcones(iconeDossier);
			}
		} else {
			setIcones(iconeDossier);
		}
		super.getTreeCellRendererComponent(tree, value, sel, estOuvert, estFeuille, row, hasFocus);
		return this;
	}

}
