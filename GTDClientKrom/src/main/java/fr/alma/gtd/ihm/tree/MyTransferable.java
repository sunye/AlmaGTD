/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.alma.gtd.ihm.tree;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import javax.swing.tree.DefaultMutableTreeNode;

public class MyTransferable implements Transferable {

	DefaultMutableTreeNode node;

	public DefaultMutableTreeNode getObject() {
		return node;
	}
	public static DataFlavor data = create();

	public static DataFlavor create() {
		String mimeType = DataFlavor.javaJVMLocalObjectMimeType + ";class=" + DefaultMutableTreeNode.class.getName();
		try {
			return new DataFlavor(mimeType);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public MyTransferable(DefaultMutableTreeNode o) {
		node = o;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (flavor.equals(DataFlavor.stringFlavor)) {
			return node.toString();
		} else {
			return node;
		}
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[]{data, DataFlavor.stringFlavor};
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		if (flavor.equals(data)) {
			return true;
		} else if (flavor.equals(DataFlavor.stringFlavor)) {
			return true;
		}
		return false;
	}
}
