package fr.univnantes.alma.gtd.view;


	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.univnantes.alma.gtd.controler.ressources.IControlerRessources;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Idee;

	public class OngletPanier extends JFrame {

		
		JPanel panHaut;
		JPanel panGauche;
		JPanel panDroite;		
		JButton bagir, bajouterProjet;

		/** ELEMENT DE L ONGLET PANIER **/
		IControlerRessources gestres;
		OngletContexte ocontexte;
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
		


		public OngletPanier(JPanel ppanier, IControlerRessources gestres2,
				OngletContexte contex) throws HeadlessException {		
		
			gestres = gestres2;
			ocontexte = contex;
			textcon.setLineWrap(true);
			content.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			textcont.setLineWrap(true);
			panHaut = new JPanel();
			panGauche = new JPanel();
			panDroite = new JPanel();
			

			ajout.addActionListener(new ActionListener() {

			
				public void actionPerformed(ActionEvent arg0) {
					gestres.addBasket(titre.getText(), textcont.getText());
					listepanier.setListData(gestres.getElements());
	  
				}
			});
			modifier.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					gestres.updateBasket((Idee)listepanier.getSelectedValue(), textcon.getText());
					listepanier.setListData(gestres.getElements());
				}
				
			});
			supprimer.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					gestres.deleteBasket((Idee)listepanier.getSelectedValue());
					listepanier.setListData(gestres.getElements());
				}
				
			});
			traiter.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(listepanier.getSelectedValue()!=null)
					panDial = new DialogPanier(OngletPanier.this,gestres,(Idee)listepanier.getSelectedValue());
					
				}
			});
			JScrollPane paneliste = new JScrollPane(listepanier);
			listepanier.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listepanier.setListData(gestres.getElements());
			listepanier.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					JList list = (JList) e.getSource();
					Idee el = (Idee) list.getSelectedValue();
					if(el != null){
					textcon.setText(el.getDescription());
					}else{
						textcon.setText("");
					}
					
				}
			});
			JSplitPane splitpanier = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			JPanel hautsplit = new JPanel(new BorderLayout());
			Border borderhaut=BorderFactory.createTitledBorder("Ajouter un element");
			splitpanier.setTopComponent(hautsplit);		
			JPanel bassplit = new JPanel(new BorderLayout());
			Border borderbas=BorderFactory.createTitledBorder("Consulter un element");
			splitpanier.setBottomComponent(bassplit);
			hautsplit.setBorder(borderhaut);
			bassplit.setBorder(borderbas);
			
			
			JPanel hautpanier = new JPanel(new FlowLayout());
			JPanel saisipanier = new JPanel();
			saisipanier.setLayout(new BoxLayout(saisipanier, BoxLayout.Y_AXIS));
			saisipanier.add(titre);
			saisipanier.add(content);
			

			hautsplit.add(saisipanier,BorderLayout.CENTER);
			JPanel panajout = new JPanel(new FlowLayout());
			panajout.add(ajout);
			hautsplit.add(panajout,BorderLayout.EAST);
			
			
			JPanel baspanier = new JPanel(new FlowLayout());
			baspanier.add(modifier);
			baspanier.add(traiter);
			baspanier.add(supprimer);
			bassplit.add(baspanier,BorderLayout.SOUTH);
			bassplit.add(paneliste,BorderLayout.WEST);
			bassplit.add(con,BorderLayout.CENTER);
			
			
			ppanier.setLayout(new BorderLayout());
			ppanier.add(splitpanier,BorderLayout.CENTER);
			
		}
		
		public void refresh(){
			listepanier.setListData(gestres.getElements());
			ocontexte.refresh();
		}

	}
