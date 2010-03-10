package fr.alma.ihm.vues.generale;

import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

import fr.alma.ihm.vues.ApplicationGTD;
import fr.alma.modele.noyau.Contact;

/**
 * Classe affichant une liste de contact personnalisée.
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public class ListeContacts extends DefaultTableModel {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 1680566920559559444L;

	/**
	 * Constructeur.
	 */
	public ListeContacts() {
		Vector<Vector<Object>> datas = getContacts();
		Vector<String> cols = new Vector<String>();
		cols.add(0, "Nom");
		cols.add(1, "Email");
		cols.add(2, "À ajouter");
		setDataVector(datas, cols);
	}

	/**
	 * Méthode getContacts, permettant d'enregsitrer dans un vecteur tous les contacts d'un utilisateurs
	 * utile pour l'amélioration graphique sous forme de tableau par exemple.
	 */
	private Vector<Vector<Object>> getContacts() {
		Vector<Vector<Object>> rsToVectors = new Vector<Vector<Object>>();
		
		DefaultListModel modeleContact = ApplicationGTD.getInstance().getContainerVues().getVueGenerale().getListModel();
		for (int i=0; i<modeleContact.getSize(); i++) {
			Contact c = (Contact) modeleContact.getElementAt(i);
			Vector<Object> v = new Vector<Object>(3);
			v.add(0, c.getNom());
			v.add(1, c.getEmail());
			v.add(2, false);
			rsToVectors.add(v);
		}
		return rsToVectors;
	}

	/**
	 * méthode getColumnClass
	 * Redéfinition de cette méthode pour pouvoir afficher des cases à cocher.
	 * @param col le numéro de colonne de l'élément
	 * @return la classe de l'élément de la colonne col
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Class getColumnClass(int col) {
		Vector v = (Vector) dataVector.elementAt(0);
		return v.elementAt(col).getClass();
	}
}
