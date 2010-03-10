package fr.alma.ihm.vues.generale.arbre;

import javax.swing.tree.DefaultMutableTreeNode;

import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.ITache;

/**
 * Classe représentant un noeud de l'arbre des tâches et des projets.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class NoeudGTD extends DefaultMutableTreeNode {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 7377426798344786351L;
	
	/**
	 * l'objet représentant le noeud.
	 */
	private Object node;
	
	/**
	 * Constructeur.
	 */
	public NoeudGTD(Object o) {
		super(o);
		this.node = o;
	}
	
	/**
	 * redéfinition de toString()
	 */
	@Override
	public String toString() {
		if (node instanceof ITache) {
			return ((ITache) node).getNom();
		} else if (node instanceof IProjet) {
			return ((IProjet) node).getNom();
		} else {
			return "Non connecté";
		}
	}
}
