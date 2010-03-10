package fr.alma.ihm.vues.generale.arbre;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import fr.alma.ihm.vues.generale.JToolBarGTD;
import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.Projet;
import fr.alma.modele.noyau.Tache;

/**
 * Classe représentant un écouteur d'actions sur l'arborescence.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class ArbreGTDListener implements TreeSelectionListener {

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)ArbreGTD.getInstance().getArbre().getLastSelectedPathComponent();
		/* if nothing is selected */ 
		if (node == null) return;

		/* retrieve the node that was selected */ 
		Object nodeInfo = node.getUserObject();

		JToolBarGTD toolbar = ArbreGTD.getInstance().getToolBar();
		toolbar.removeAll();

		if (nodeInfo instanceof Tache) {
			toolbar.add(toolbar.getEditerTache());
			toolbar.add(toolbar.getSupprTache());
		} 
		else if (nodeInfo instanceof Projet) {
			if (((IProjet) nodeInfo).getNom().equals("GTD")) {
				toolbar.add(toolbar.getAjouterProjet());	
			} else if (((IProjet) nodeInfo).getNom().equals("Panier")) {
				toolbar.add(toolbar.getAjouterTache());	
			} else if (((IProjet) nodeInfo).getNom().equals("Corbeille")) {
				toolbar.add(toolbar.getViderCorbeille());
			} else {
				toolbar.add(toolbar.getAjouterTache());
				toolbar.add(toolbar.getAjouterProjet());
				toolbar.add(toolbar.getEditerProjet());
				toolbar.add(toolbar.getSupprProjet());
			}
		} else {
			toolbar.add(toolbar.getAjouterProjet());
		}
		toolbar.revalidate();
		toolbar.repaint();

		try {
			ArbreGTD.getInstance().getPanneauTacheProjet().afficher(nodeInfo);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
