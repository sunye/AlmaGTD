package fr.alma.ihm.vues.generale;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import fr.alma.ihm.vues.generale.arbre.ArbreGTD;
import fr.alma.modele.noyau.Contact;
import fr.alma.modele.persistance.DaoFactory;
import fr.alma.modele.persistance.dao.ContactDao;

/**
 * Classe représentant la vue principale de l'application GTD. Permet d'effectuer les principales actions.
 * @author Université de Nantes
 * @since 2009
 * @version 0.1
 */
public class Recapitulatif extends JSplitPane {

	/**
	 * UID généré.
	 */
	private static final long serialVersionUID = 7644026179388265176L;
	
	/**
	 * conteneur pour représenter le contenu d'une tâche sous forme d'un tableau.
	 */
	private JPanel panelFonctionnalitesTableau;
	private JScrollPane scrollPanePourTableau2;
	
	/**
	 * Liste pour représenter sous forme de liste, tous les contacts d'un utilisateur
	 */
	private DefaultListModel listModel;
	private JList listeContact;

	/** Arborescence située sur le côté gauche de l'application. */
    private ArbreGTD arbreGTD;

	/** Barre d'outils permettant d'ajouter/supprimer rapidement des projets ou des tâches. */
	private JToolBarGTD toolBar;

	/** Zone d'affichage où l'on détaille une tache ou un projet. */
	private PanneauTacheProjet panneauTacheProjet;
	
	/** Zone d'affichage où l'on détaille les contacts. */
	private JPanel panelContact;

	/**
	 * Constructeur.
	 */
	public Recapitulatif() {
		super();
		
		JSplitPane paneTreeContact = new JSplitPane();
		paneTreeContact.setOrientation(JSplitPane.VERTICAL_SPLIT);
		paneTreeContact.setOneTouchExpandable(true);
		paneTreeContact.setDividerLocation(300);
		
		panelContact = new JPanel();
		panelContact.setLayout(new BorderLayout());
		JLabel labelContact = new JLabel("<html><u>Contacts :</u></html>");
		
		listModel = new DefaultListModel();
		listeContact = new JList(listModel);
		listeContact.setLayoutOrientation(JList.VERTICAL);
		panelContact.add(labelContact, BorderLayout.NORTH);
		panelContact.add(listeContact, BorderLayout.CENTER);
		
		panneauTacheProjet = new PanneauTacheProjet();
		arbreGTD = ArbreGTD.getInstance();
		arbreGTD.setPanneauTacheProjet(panneauTacheProjet);

		toolBar = new JToolBarGTD(arbreGTD);
		arbreGTD.setToolBar(toolBar);
		
		paneTreeContact.add(arbreGTD, JSplitPane.TOP);
		paneTreeContact.add(panelContact, JSplitPane.BOTTOM);
		
		panelFonctionnalitesTableau = new JPanel();
		panelFonctionnalitesTableau.setLayout(new BorderLayout());
		panelFonctionnalitesTableau.add(toolBar, BorderLayout.NORTH);

		panelFonctionnalitesTableau.add(panneauTacheProjet, BorderLayout.CENTER);
		scrollPanePourTableau2 = new JScrollPane(panelFonctionnalitesTableau);
		
		this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		this.add(paneTreeContact, JSplitPane.LEFT);
		this.add(scrollPanePourTableau2, JSplitPane.RIGHT);
		this.setOneTouchExpandable(true);
		this.setDividerLocation(175);
	}
	
	/** 
	 * Charge les contacts d'un utilisateur. 
	 */
	public void peuplerListeContacts() {
		ContactDao c = DaoFactory.createContactDao();
		List<Contact> contacts = c.recupererTout();
		for(Contact contact : contacts) {
			listModel.addElement(contact);
		}
	}
	
	//**************************************************
	//		         GETTERS AND SETTERS
	//**************************************************
	
	public JPanel getPanneauTacheProjet() {
		return panneauTacheProjet;
	}

	public JPanel getPanelContact() {
		return panelContact;
	}

	public DefaultListModel getListModel() {
		return listModel;
	}

    public JList getListeContact() {
		return listeContact;
	}
	
	public JToolBarGTD getToolBar() {
		return toolBar;
	}
	
	public void setPanneauTacheProjet(PanneauTacheProjet panAffichageTacheProjet) {
		this.panneauTacheProjet = panAffichageTacheProjet;
	}
	
	public void setPanelContact(JPanel panelContact) {
		this.panelContact = panelContact;
	}
	
	public void setListModel(DefaultListModel listModel) {
		this.listModel = listModel;
	}
	
	public void setListeContact(JList listeContact) {
		this.listeContact = listeContact;
	}
}
