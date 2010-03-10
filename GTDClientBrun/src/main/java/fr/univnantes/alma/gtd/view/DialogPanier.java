package fr.univnantes.alma.gtd.view;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.univnantes.alma.gtd.controler.ressources.IControlerRessources;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EtatTache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Idee;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Projet;


public class DialogPanier extends JDialog{
	
	
	interface Formulaire {
		public String getNom();
	}
	
	private class FormulaireContexte extends JPanel implements Formulaire {
		
		JTextField nomC = new JTextField(15);
		
		public FormulaireContexte(){
			super();
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			this.add(new JLabel("nom du contexte"));
			this.add(nomC);
		}
		
		public String getNom() {
			return "Créer contexte";
		}
		
		@Override
		public String toString(){
			return this.getNom();
		}
	}
	
	private class FormulaireTache extends JPanel implements Formulaire {
		
		
		public JTextField nomT = new JTextField(15);
		public JComboBox prior = new JComboBox(new Integer[]{1,2,3,4,5});
		public JComboBox eff = new JComboBox();
		public JComboBox et = new JComboBox(new EtatTache[]{EtatTache.AFAIRE,EtatTache.DELEGUEE,EtatTache.ENATTENTE,EtatTache.FINIE});
		public DatePanel rev = new DatePanel();
		public DatePanel ech = new DatePanel();
		public JComboBox projpar = new JComboBox();
		
		public FormulaireTache() {
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			this.add(new JLabel("Nom de la tache"));
			this.add(nomT);
			this.add(new JLabel("Priorite"));
			this.add(prior);
			this.add(new JLabel("Effort"));
			this.add(eff);
			this.add(new JLabel("Etat"));
			this.add(et);
			this.add(new JLabel("Reveil"));
			this.add(rev);
			this.add(new JLabel("Echeance"));
			this.add(ech);
			this.add(new JLabel("Projet parent"));
			this.add(projpar);			
			
			for(Integer i = 0;i<100;i++){
				eff.addItem(i);
			}
			Vector<Projet> listetemp =gestR.getListeProjet();
			
			for(Projet ptemp : listetemp){
				projpar.addItem(ptemp);
			}
		}
		
		public String getNom() {
			return "Créer Tache";
		}
		
		@Override
		public String toString(){
			return this.getNom();
		}
	}
	
	private class FormulaireProjet extends JPanel implements Formulaire {
		public JTextField nomP = new JTextField(15);
		
		public FormulaireProjet() {
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			JLabel choixnom = new JLabel("Choisissez le nom du projet");
			this.add(choixnom);
			this.add(nomP);
		}
		
		public String getNom() {
			return "Créer Projet";
		}
		
		@Override
		public String toString(){
			return this.getNom();
		}
	}
	
	IControlerRessources gestR;
	JPanel gauche = new JPanel();
	JButton confirmer = new JButton("Creer");
	JButton annuler = new JButton("Annuler");
	JPanel basdial = new JPanel(new FlowLayout());
	
	JPanel centre = new JPanel(new FlowLayout());
	JLabel prem = new JLabel("Faites un choix a gauche");
	Idee el;
	OngletPanier papa;
	
	JList listepos;
	

	
	
	public DialogPanier(OngletPanier ongletPanier,IControlerRessources g, Idee e){
		super(ongletPanier);
		papa = ongletPanier;
		el = e;
		this.gestR = g;
		this.setModal(true);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(gauche,BorderLayout.WEST);
		this.getContentPane().add(centre,BorderLayout.CENTER);

		centre.add(prem);
		annuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DialogPanier.this.setVisible(false);
				
			}
		});
		
		final FormulaireTache formTache = new FormulaireTache();		
		final FormulaireProjet formProj = new FormulaireProjet();
		final FormulaireContexte formConte = new FormulaireContexte();
		
		basdial.add(confirmer);
		basdial.add(annuler);
		
		listepos = new JList(new Formulaire[]{formProj,formTache,formConte});
		

		listepos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listepos.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				JPanel p = (JPanel)listepos.getSelectedValue();
				centre.removeAll();
				centre.add(p);
				DialogPanier.this.pack();
			}
		});
		confirmer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int choix = listepos.getSelectedIndex();
				Boolean result = false;
				switch (choix){
					case 0 : result = gestR.addProject(formProj.nomP.getText());break;
					case 1 : 
						result = gestR.addTache(formTache.nomT.getText(),
								(Integer)formTache.prior.getSelectedItem(),
								(Integer)formTache.eff.getSelectedItem(),
								(EtatTache)formTache.et.getSelectedItem(),
								formTache.rev.getDate(),
								formTache.ech.getDate(),
								(Projet)formTache.projpar.getSelectedItem());
							break;
					case 2 : result = gestR.addContexte(formConte.nomC.getText());break;
				}
				if (result){
					gestR.deleteBasket(el);
					papa.refresh();
					DialogPanier.this.dispose();
				}
			}
		});
		this.getContentPane().add(basdial,BorderLayout.SOUTH);
		gauche.add(listepos);
		
		
		this.pack();
		this.setLocationRelativeTo(this.getOwner());
		this.setVisible(true);
	}



}
