package fr.alma.ihm.vues.generale.arbre;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import fr.alma.ihm.vues.ApplicationGTD;
import fr.alma.ihm.vues.generale.JToolBarGTD;
import fr.alma.ihm.vues.generale.PanneauTacheProjet;
import fr.alma.modele.ModeleAbstrait;
import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.ITache;
import fr.alma.modele.noyau.Projet;
import fr.alma.modele.noyau.Tache;
import fr.alma.modele.persistance.DaoFactory;
import fr.alma.modele.persistance.dao.ProjetDao;
import fr.alma.modele.persistance.dao.TacheDao;

/**
 * Classe représentant une arborescence personnalisée.
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public final class ArbreGTD extends JPanel {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = -3438203046159767508L;

	/**
	 * instance de la classe (pattern singleton).
	 */
	private static ArbreGTD instance = null;

	/**
	 * représentation de l'arbre sosu forme de noeud et d'un model swing
	 */
	private NoeudGTD noeudRacine;
	private NoeudGTD noeudCorbeille;
	private DefaultTreeModel treeModel;
	private JTree arbre;

	/**
	 * container pour visualiser le contenu d'une tâche ou un projet
	 */
	private PanneauTacheProjet panneauTacheProjet;
	
	/**
	 * barre d'outils
	 */
	private JToolBarGTD toolBar;

	/**
	 * Constructeur privé.
	 */
	private ArbreGTD() {
		super(new GridLayout(1,0));
		treeModel = new DefaultTreeModel(noeudRacine);
		setArbre(new JTree(treeModel));
		getArbre().setShowsRootHandles(false);
		getArbre().setRowHeight(32);
		getArbre().setCellRenderer(new RenduNoeudGTD());
		getArbre().addTreeSelectionListener(new ArbreGTDListener());

		JScrollPane scrollPane = new JScrollPane(getArbre());
		add(scrollPane);
	}

	/** 
	 * Insère dans l'arbre les projets et les tâches existant dans la BDD. 
	 * @param racine la racine de l'arbre
	 */
	public void peuplerArbre(NoeudGTD racine) {
		if (racine.getUserObject() instanceof IProjet) {
			ProjetDao p = DaoFactory.createProjetDao();
			List<Projet> projets = p.recupererSousProjets(((IProjet) racine.getUserObject()).getId());

			TacheDao t = DaoFactory.createTacheDao();
			List<Tache> taches = t.recupererTaches(((IProjet) racine.getUserObject()).getId());
			for (ITache tache : taches) {
				addObject(racine, tache, true);
			}

			for(IProjet sp : projets) {
				NoeudGTD noeudFils = addObject(racine, sp, true);
				peuplerArbre(noeudFils);
			}
		}
	}

	/** 
	 * Déplace le noeud sélectionné dans la corbeille, s'il est supprimable.
	 */
	public void mettreDansCorbeille() {
		TreePath currentSelection = getArbre().getSelectionPath();
		if (currentSelection != null) {
			NoeudGTD currentNode = (NoeudGTD)(currentSelection.getLastPathComponent());
			Object nodeInfo = currentNode.getUserObject();
			if (nodeInfo instanceof IProjet) {
				String nom = ((IProjet) nodeInfo).getNom(); 
				if (nom.equals("GTD") || nom.equals("Corbeille") || nom.equals("Panier")) {
					return;
				}
			}
			NoeudGTD parent = (NoeudGTD)(currentNode.getParent());
			if (parent != null) {
				ApplicationGTD.getInstance().getControler().mettreDansCorbeille(nodeInfo);
				treeModel.removeNodeFromParent(currentNode);
			}
		}
		peuplerArbre(noeudCorbeille);
		treeModel.reload();
	}
	
	/** 
	 * Vide la corbeille
	 */
	public void viderCorbeille() {
		noeudCorbeille.removeAllChildren();
		treeModel.reload();
	}

	/** 
	 * initialisation de l'arbre
	 */
	public void initialiser() {
		IProjet root = ModeleAbstrait.getProjetRacine();
		noeudRacine = new NoeudGTD(root);
		treeModel.setRoot(noeudRacine);
		peuplerArbre(noeudRacine);
		noeudCorbeille = (NoeudGTD) treeModel.getChild(noeudRacine, 1);
		treeModel.reload();
	}

	/** 
	 * Retire tous les noeuds de l'arbre
	 */
	public void supprimerTout() {
		noeudRacine.removeAllChildren();
		treeModel.setRoot(null);
		treeModel.reload();
	}

	/** 
	 * Ajoute un noeud "fils" au noeud selectionné.
	 * @param child le noeud fils.
	 */
	public NoeudGTD addObject(Object child) {
		NoeudGTD parentNode = null;
		TreePath parentPath = getArbre().getSelectionPath();
		if (parentPath == null) {
			if (child instanceof IProjet) {
				parentNode = noeudRacine;
			}
		} else {
			parentNode = (NoeudGTD)(parentPath.getLastPathComponent());
		}
		return addObject(parentNode, child, true);
	}

	/**
	 * Ajoute un objet de parent "parent"
	 * @param parent le noeud parent
	 * @param child le noeud fils
	 * @return un noeud
	 */
	public NoeudGTD addObject(NoeudGTD parent, Object child) {
		return addObject(parent, child, false);
	}

	/**
	 * Ajoute un objet de parent "parent"
	 * @param parent le noeud parent
	 * @param child le noeud fils
	 * @param shouldBeVisible ??
	 * @return Le nouveau noeud inséré dans l'arbre
	 */
	public NoeudGTD addObject(NoeudGTD parent, Object child, boolean shouldBeVisible) {
		NoeudGTD childNode = new NoeudGTD(child);
		NoeudGTD prt = parent;
		
		if (prt == null) {
			prt = new NoeudGTD(ModeleAbstrait.getProjetRacine());
		}

		treeModel.insertNodeInto(childNode, prt, prt.getChildCount());

		if (shouldBeVisible) {
			getArbre().scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		return childNode;
	}

	//-------------------------------------------------------------------------

	/**
	 * Méthode getInstance (pattern singleton).
	 * @return instance l'instance de la classe
	 */
	public static ArbreGTD getInstance() {
		if (instance == null){
			instance = new ArbreGTD();
		}
		return instance;
	}

	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************

	public void setPanneauTacheProjet(PanneauTacheProjet panneauTacheProjet) {
		this.panneauTacheProjet = panneauTacheProjet;
	}

	public void setToolBar(JToolBarGTD toolBar) {
		this.toolBar = toolBar;
	}

	public PanneauTacheProjet getPanneauTacheProjet() {
		return panneauTacheProjet;
	}

	public JToolBarGTD getToolBar() {
		return toolBar;
	}

	public void setArbre(JTree arbre) {
		this.arbre = arbre;
	}

	public JTree getArbre() {
		return arbre;
	}
}
