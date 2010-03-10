package fr.univnantes.alma.gtd.view;

import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fr.univnantes.alma.gtd.controler.ressources.ControlerRessources;
import fr.univnantes.alma.gtd.controler.ressources.IControlerRessources;


public class GTD extends JFrame {

	//TODO eclater l'onglet et le conteneur d'onglets
	
	JPanel panHaut;
	JPanel panGauche;
	JPanel panDroite, pcontexte, ppanier, pprojet;
	OngletProjet oprojet;
	OngletContexte ocontexte;
	OngletPanier opanier;
	JButton bagir, bajouterProjet;
	JTabbedPane onglets;
	IControlerRessources gestres;

	/** ELEMENT DE L ONGLET PANIER **/
	
	JButton ajout = new JButton("Ajouter");
	JButton modifier = new JButton("Modifier");
	JButton supprimer = new JButton("Supprimer");
	JButton traiter = new JButton("Traiter");
	JList listepanier = new JList();
	JTextField titre = new JTextField("Titre",15);
	JTextArea textcont = new JTextArea("Taper le contenu de votre element ici",5,15);
	JScrollPane content = new JScrollPane(textcont);
	JTextArea textcon = new JTextArea(5,15);
	JScrollPane con = new JScrollPane(textcon);
	DialogPanier panDial;
	


	public GTD(String login) throws HeadlessException {
		super("GTD de " + login);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		textcon.setLineWrap(true);
		content.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textcont.setLineWrap(true);
		gestres = new ControlerRessources();
		panHaut = new JPanel();
		panGauche = new JPanel();
		panDroite = new JPanel();
		pcontexte = new JPanel();
		pcontexte.setName("Contexte");
		ppanier = new JPanel();
		ppanier.setName("Panier");
		pprojet = new JPanel();
		pprojet.setName("Projet");
		onglets = new JTabbedPane();
		onglets.addTab("Panier", ppanier);
		onglets.addTab("Contexte", pcontexte);
		onglets.addTab("Projet", pprojet);
		
		/*Onglet projet*/
		oprojet = new OngletProjet(pprojet, gestres);
		/*Onglet contexe*/
		ocontexte = new OngletContexte(pcontexte, gestres, oprojet);
		/*Onglet panier*/
		opanier = new OngletPanier(ppanier,gestres,ocontexte);

		
	
		this.getContentPane().add(onglets);
		this.setLocationRelativeTo(this.getParent());
		this.setResizable(true);
		this.pack();
		this.setVisible(true);
	}
	
	public void refresh(){
		listepanier.setListData(gestres.getElements());
		ocontexte.refresh();
	}

}
