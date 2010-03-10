package fr.univnantes.alma.gtd.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.univnantes.alma.gtd.controler.ressources.IControlerRessources;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Contexte;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Tache;

public class OngletContexte extends JPanel {
	IControlerRessources gestR;
	JList listeContexte = new JList();
	JList listeTache = new JList();
	JTextField titre = new JTextField();
	OngletProjet opr;
	
	JLabel tnomtache = new JLabel();
	JLabel tpriorite = new JLabel();
	JLabel teffort = new JLabel();
	JLabel tetat = new JLabel();
	JLabel dreveil = new JLabel();
	JLabel decheance = new JLabel();
	
	public OngletContexte(JPanel pcontexte, IControlerRessources gestres, OngletProjet op) throws HeadlessException {
		gestR = gestres;
		opr = op;
		titre.setPreferredSize(new Dimension(15, 10));
		pcontexte.setLayout(new BorderLayout());
		
		/*Partie Gauche*/
		JButton ajout = new JButton("Ajouter");
		JButton supp = new JButton("Supprimer");
		JScrollPane paneliste = new JScrollPane(listeContexte);
		listeContexte.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeTache.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listeTache.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList templ = (JList) e.getSource();
				Tache t = (Tache) templ.getSelectedValue();
				tnomtache.setText(t.getNom());
				tpriorite.setText(String.valueOf(t.getPriorite()));
				teffort.setText(String.valueOf(t.getEffort()));
				dreveil.setText(t.getReveil().toString());
				decheance.setText(t.getEcheance().toString());
				tetat.setText(t.getEtatTache().toString());
				
			}
		});
		listeContexte.setListData(gestR.getListeContexte());
		listeContexte.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList templ = (JList) e.getSource();
				Contexte tempc = (Contexte)templ.getSelectedValue();
				listeTache.setListData(gestR.getListeTache(tempc));
			}
		});
		JPanel west = new JPanel();
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		JSplitPane splitpanier = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JPanel hautsplit = new JPanel(new BorderLayout());
		Border borderhaut=BorderFactory.createTitledBorder("Ajouter");
		splitpanier.setTopComponent(hautsplit);		
		JPanel bassplit = new JPanel(new BorderLayout());
		Border borderbas=BorderFactory.createTitledBorder("Consulter");
		splitpanier.setBottomComponent(bassplit);
		hautsplit.setBorder(borderhaut);
		bassplit.setBorder(borderbas);
		ajout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gestR.addContexte(titre.getText());
				OngletContexte.this.refresh();
			}
		});
		supp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Contexte obj = (Contexte)listeContexte.getSelectedValue();
				if (obj==null){
					JOptionPane.showOptionDialog(OngletContexte.this, "Selectionner un contexte pour pouvoir le supprimer",
							"Suppression d'un contexte", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				}else{
					int n = JOptionPane.showOptionDialog(OngletContexte.this, "Voulez-vous supprimer le contexte?",
							"Suppression d'un contexte", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (n==0){
						gestR.deleteContexte(obj);
						OngletContexte.this.refresh();
					}
				}
			}
		});
		west.add(splitpanier);
		hautsplit.add(ajout,BorderLayout.SOUTH);
		hautsplit.add(titre,BorderLayout.CENTER);
		bassplit.add(paneliste,BorderLayout.CENTER);
		bassplit.add(supp,BorderLayout.SOUTH);
		pcontexte.add(west,BorderLayout.WEST);
		
		
		/*Partie Centrale*/
		JScrollPane panelisteT = new JScrollPane(listeTache);
		JPanel center = new JPanel();
		Border bordercentre=BorderFactory.createTitledBorder("Taches d'un contexte");
		center.setBorder(bordercentre);
		center.setLayout(new BorderLayout());
		JSplitPane centresplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		centresplit.setLeftComponent(panelisteT);
		JPanel paneldetail = new JPanel();
		centresplit.setRightComponent(paneldetail);
		center.add(centresplit,BorderLayout.CENTER);		
		
		
		paneldetail.setLayout(new GridLayout(9,2));
		paneldetail.add(new JLabel("nom"));
		paneldetail.add(tnomtache);
		paneldetail.add(new JLabel("priorite"));
		paneldetail.add(tpriorite);
		paneldetail.add(new JLabel("effort"));
		paneldetail.add(teffort);
		paneldetail.add(new JLabel("etat"));
		paneldetail.add(tetat);
		paneldetail.add(new JLabel("réveil"));
		paneldetail.add(dreveil);
		paneldetail.add(new JLabel("echeance"));
		paneldetail.add(decheance);
		pcontexte.add(center,BorderLayout.CENTER);
		
		
		/*Partie Haute*/
		JPanel haut = new JPanel(new BorderLayout());
		center.add(haut,BorderLayout.NORTH);
		JButton agir = new JButton("Agir");
		haut.add(agir,BorderLayout.CENTER);
		agir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Contexte c = (Contexte)listeContexte.getSelectedValue();
				if (c==null){
					JOptionPane.showOptionDialog(OngletContexte.this, "Selectionner un contexte pour" +
							" pouvoir consulter la tache la plus urgente",
							"Agir", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				}else{
					Tache t = gestR.agir(gestR.getListeTache(c));
					if(t!=null)
					JOptionPane.showOptionDialog(OngletContexte.this, "La tache la plus urgente de " +
							"ce contexte est : "+t.getNom(),
							"Agir", JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE, null, null, null);
				}
				
			}
		});
		
	}
	public void refresh(){
		listeContexte.setListData(gestR.getListeContexte());
		listeTache = new JList();
		opr.refresh();
	}
	public void refreshMe(){
		listeContexte.setListData(gestR.getListeContexte());
		listeTache = new JList();
	}
}
