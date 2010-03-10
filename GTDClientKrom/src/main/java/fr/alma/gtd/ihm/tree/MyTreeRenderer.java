/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.alma.gtd.ihm.tree;

import fr.alma.gtd.entities.Projet;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Le_Clubber
 */
public class MyTreeRenderer extends DefaultTreeCellRenderer {

	ImageIcon folder_opened;
	ImageIcon folder_closed;
	ImageIcon folder_ordered_opened;
	ImageIcon folder_ordered_closed;

	public MyTreeRenderer() {
		super();
		folder_opened = new ImageIcon(getClass().getResource("/img/folder_opened.png"));
		folder_closed = new ImageIcon(getClass().getResource("/img/folder_closed.png"));
		folder_ordered_opened = new ImageIcon(getClass().getResource("/img/folder_ordered_opened.png"));
		folder_ordered_closed = new ImageIcon(getClass().getResource("/img/folder_ordered_closed.png"));
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		if (!leaf) {
			DefaultMutableTreeNode node=(DefaultMutableTreeNode) value;
			try {
				Projet p = (Projet) node.getUserObject();
				if (p != null) {
					if (!p.getOrdonne()) {
						if (expanded) {
							setIcon(folder_opened);
						} else {
							setIcon(folder_closed);
						}
					} else {
						if (expanded) {
							setIcon(folder_ordered_opened);
						} else {
							setIcon(folder_ordered_closed);
						}
					}
				}
			} catch (ClassCastException e) {
			}
		} else {
			setIcon(null);
		}
		return this;
	}

}
