package fr.univnantes.alma.gtd.view;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import fr.univnantes.alma.gtd.controler.ressources.IControlerRessources;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Contact;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Contexte;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EtatTache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Projet;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Tache;

public class OngletProjet extends JPanel {
	/** ELEMENT DE L ONGLET PROJET **/


	IControlerRessources gestR;

	/*Elements du panel haut*/
	JPanel phaut = new JPanel();
	JButton agir = new JButton("Agir");
	JButton ajoutprojet = new JButton("Ajouter projet");
	JButton supprprojet = new JButton("Supprimer projet");
	JButton ajouttache = new JButton("Ajouter tache");
	JButton supprtache = new JButton("Supprimer tache");
	JButton ajoutsousprojet = new JButton("Ajouter sous-projet");

	/*Element du panel gauche*/

	/*Element du panel de droite*/
	JPanel pdroite = new JPanel();
	JPanel pdroite1 = new JPanel();
	JPanel pdroite2 = new JPanel(new FlowLayout());
	JPanel pdroite3 = new JPanel();

	/*Element du panel de droite 1*/
	Border border=BorderFactory.createTitledBorder("Detail du projet");
	JPanel petitpaneld1 = new JPanel();
	JPanel panelnote = new JPanel();
	JTextField nouvellenote = new JTextField("Nouvelle note");
	JPanel pboutonnote = new JPanel();
	JButton ajoutnote = new JButton("+");
	JButton supprnote = new JButton("-");
	JPanel panelcontact = new JPanel(); 
	JComboBox contacts = new JComboBox();
	JTextField nouveaucontact = new JTextField("Nouveau contact");
	JPanel pboutoncontact = new JPanel();
	JButton ajoutcontact = new JButton("+");
	JButton supprcontact = new JButton("-");
	JLabel lnomprojet = new JLabel("Nom du projet :");
	JTextField nomprojet = new JTextField("Nom du projet");
	JLabel lcontexte = new JLabel("Nom du contexte :");
	JList listenote = new JList();
	JScrollPane scrolllistenote = new JScrollPane(listenote);
	JList listecontact = new JList();
	JScrollPane scrolllistecontact = new JScrollPane(listecontact);
	JPanel pbouton = new JPanel();
	JButton validerprojet = new JButton("Valider les modifications");
	JButton annulerprojet = new JButton("Annuler");
	JPanel pnomproj = new JPanel();
	JPanel pnomcontexte = new JPanel();
	JPanel pnotecontact = new JPanel();
	JButton deletecontactgest = new JButton("Supprimer le contact de la base");
	JButton addcontactprojet = new JButton("Ajouter contact au projet");
	JComboBox contexte = new JComboBox();
	JPanel panelcontactprojet = new JPanel();


	/*Element du panel de droite 2*/
	JTree arbretache = new JTree();
	//JTree arbretache = new JTree(((Projet)listeprojet.getSelectedValue()).getListtache());
	JScrollPane scrollarbretache = new JScrollPane(arbretache);

	/*Element du panel de droite 3*/
	Border border3=BorderFactory.createTitledBorder("Detail de la tache");
	JLabel lnomtache = new JLabel("Nom de la tache");
	JTextField tnomtache = new JTextField(15);
	JLabel lpriorite = new JLabel("Priorite de la tache");
	JComboBox tpriorite =  new JComboBox(new Integer[]{1,2,3,4,5});
	JLabel leffort = new JLabel("Effort");
	JComboBox teffort = new JComboBox();
	JLabel letat = new JLabel("Etat");
	JComboBox tetat = new JComboBox(new EtatTache[]{EtatTache.AFAIRE,EtatTache.DELEGUEE,EtatTache.ENATTENTE,EtatTache.FINIE});

	JLabel lreveil = new JLabel("Reveil");
	DatePanel dreveil = new DatePanel();
	JLabel lecheance = new JLabel("Echeance");
	DatePanel decheance = new DatePanel();
	JLabel letiquette = new JLabel("Etiquette");

	JComboBox cbetiquette = new JComboBox();
	JLabel llien = new JLabel("Liens");
	JComboBox cblien = new JComboBox();

	JPanel panelBouton = new JPanel(); 
	JPanel panelBouton2 = new JPanel(); 
	JButton valider = new JButton("Valider les modifications");
	JButton annuler = new JButton("Annuler");


	public OngletProjet(JPanel pprojet, IControlerRessources gestres) throws HeadlessException {

		/*Initialisation*/
		gestR = gestres;
		/*
		Vector<Contexte> listetemp =gestR.getListeContexte();
		for(Contexte ctemp : listetemp){
			contexte.addItem(ctemp);
		}
		//*/
		pprojet.setLayout(new BorderLayout());
		pdroite.setLayout(new BoxLayout(pdroite, BoxLayout.Y_AXIS));


		/*Panel du haut*/
		phaut.add(agir);
		phaut.add(ajoutprojet);
		phaut.add(supprprojet);
		phaut.add(ajouttache);
		phaut.add(supprtache);
		phaut.add(ajoutsousprojet);
		pprojet.add(phaut, BorderLayout.NORTH);



		/*Panel de gauche*/
		//pprojet.add(scrolllisteprojet, BorderLayout.WEST);

		scrollarbretache.setLayout(new ScrollPaneLayout());
		arbretache.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.createTree();
		pprojet.add(scrollarbretache, BorderLayout.WEST);


		arbretache.addTreeSelectionListener(new TreeSelectionListener(){

			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				arbretache.getLastSelectedPathComponent();

				/* if nothing is selected */ 
				if ( node == null) return;


				/* React to the node selection. */
				//TODO verifier la coherence
				if (node.getUserObject().getClass().getName()=="fr.univnantes.alma.gtd.model.gestionnaireressources.Tache"){

					Tache t = (Tache) node.getUserObject();
					if (t!=null){
						tetat = new JComboBox(t.prochainsEtats().toArray());
						tnomtache.setText(t.getNom());
						tpriorite.setSelectedItem(t.getPriorite());
						teffort.setSelectedItem(t.getEffort());
						tetat.setSelectedItem(t.getEtatTache());
						dreveil.setDate(t.getReveil());
						decheance.setDate(t.getEcheance());
						cbetiquette = new JComboBox();
						if (t.getNotes()!=null)
							for ( String s : t.getNotes()){
								cbetiquette.addItem(s);
							}
						cblien = new JComboBox();	
						if (t.getLiens() != null)
							for ( String s : t.getLiens()){
								cblien.addItem(s);
							}					

					}
				}else{

					Projet p = (Projet) node.getUserObject();
					if (p!=null){
						listecontact.setListData(p.getContacts().toArray());
						listenote.setListData(p.getNotes().toArray());
						nomprojet.setText(p.getNom());
						Vector<Contexte> listetemp =gestR.getListeContexte();
						contexte.setSelectedIndex(listetemp.indexOf(p.getContexte()));


					}

				}

			}
		});



		/*Panel de droite 1*/
		pdroite1.setBorder(border);
		pdroite1.setLayout(new BoxLayout(pdroite1, BoxLayout.X_AXIS));
		pnomproj.setLayout(new BoxLayout(pnomproj, BoxLayout.Y_AXIS));
		pnomproj.add(lnomprojet);
		pnomproj.add(nomprojet);
		pdroite1.add(pnomproj);
		pnomproj.add(lcontexte);
		for (Contexte c : gestR.getListeContexte()){
			contexte.addItem(c);
		}
		pnomproj.add(contexte);
		/*pdroite1.add(petitpaneld1);
		petitpaneld1.setLayout(new BoxLayout(petitpaneld1, BoxLayout.Y_AXIS));
		petitpaneld1.add(nomprojet);
		petitpaneld1.add(contexte);*/
		//pdroite1.add(panelnote);
		//pdroite1.add(panelcontact);
		panelnote.setLayout(new BoxLayout(panelnote, BoxLayout.Y_AXIS));
		panelcontact.setLayout(new BoxLayout(panelcontact, BoxLayout.Y_AXIS));
		panelcontactprojet.setLayout(new BoxLayout(panelcontactprojet, BoxLayout.X_AXIS));
		panelnote.add(scrolllistenote);
		panelcontact.add(scrolllistecontact);
		for (Contact c : gestR.getContacts()){
			contacts.addItem(c);
		}
		panelcontact.add(contacts);
		panelcontactprojet.add(addcontactprojet);
		panelcontactprojet.add(deletecontactgest);
		panelcontact.add(panelcontactprojet);
		panelcontact.add(addcontactprojet);
		panelnote.add(nouvellenote);
		panelcontact.add(nouveaucontact);
		panelnote.add(pboutonnote);
		pboutonnote.setLayout(new BoxLayout(pboutonnote, BoxLayout.X_AXIS));
		pboutonnote.add(ajoutnote);
		pboutonnote.add(supprnote);
		panelnote.add(validerprojet);

		/*panelcontact.add(scrolllistecontact);
		 */
		panelcontact.add(pboutoncontact);
		pboutoncontact.setLayout(new BoxLayout(pboutoncontact, BoxLayout.X_AXIS));
		pboutoncontact.add(ajoutcontact);
		pboutoncontact.add(supprcontact);
		panelcontact.add(annulerprojet);

		pnotecontact.setLayout(new BoxLayout(pnotecontact, BoxLayout.X_AXIS));
		pnotecontact.add(panelnote);
		pnotecontact.add(panelcontact);

		//	pdroite11.setLayout(new BoxLayout(pdroite11, BoxLayout.Y_AXIS));
		/*pdroite1.add(validerprojet);
		pdroite1.add(annulerprojet);*/
		/*pdroite11.add(pdroite1);
		pdroite11.add(pbouton);*/
		pdroite1.add(pnotecontact);
		pdroite.add(pdroite1);
		ajoutnote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				arbretache.getLastSelectedPathComponent();

				/* if nothing is selected */ 
				//TODO verifier la coherence
				if (( node == null)
						||((node.getUserObject().getClass().getName()!="fr.univnantes.alma.gtd.model.gestionnaireressources.Projet"))){
					JOptionPane.showOptionDialog(OngletProjet.this, "Selectionner un projet pour ajouter une note",
							"Ajout d'une note", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				}else{
					Projet p = (Projet) node.getUserObject();
					gestR.addNoteToProject(p, nouvellenote.getText());
					listenote.setListData(gestR.getProjetNotes(p));

				}

			}
		});

		supprnote.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showOptionDialog(OngletProjet.this, "Voulez-vous supprimer la note?",
						"Suppression d'une note", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (n==0){
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)
					arbretache.getLastSelectedPathComponent();

					/* if nothing is selected */ 
					if ( node == null) return;

					Projet p = (Projet) node.getUserObject();
					gestR.deleteNoteFromProject(p, (String)listenote.getSelectedValue());
					listenote.setListData(gestR.getProjetNotes(p));
				}				
			}
		});
		ajoutcontact.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				arbretache.getLastSelectedPathComponent();

				/* if nothing is selected */ 
				//TODO verifier la coherence
				if (( node == null)
						||(node.getUserObject().getClass().getName()!="fr.univnantes.alma.gtd.model.gestionnaireressources.Projet")
				){
					JOptionPane.showOptionDialog(OngletProjet.this, "Selectionner un projet pour ajouter un contact",
							"Ajout d'un contact", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				}else{
					Projet p = (Projet) node.getUserObject();
					gestR.addContactToProject(p, nouveaucontact.getText());
					listecontact.setListData(gestR.getProjetContacts(p));				

				}			

			}
		});
		addcontactprojet.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				arbretache.getLastSelectedPathComponent();

				/* if nothing is selected */ 
				//TODO verifier la coherence
				if (( node == null)
						||(node.getUserObject().getClass().getName()!="fr.univnantes.alma.gtd.model.gestionnaireressources.Projet")
				){
					JOptionPane.showOptionDialog(OngletProjet.this, "Selectionner un projet pour ajouter un contact",
							"Ajout d'un contact", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				}else{
					Projet p = (Projet) node.getUserObject();
					Contact c = (Contact) contacts.getSelectedItem();
					if (c!=null){
						gestR.addContactToProject(p, c.toString());
						listecontact.setListData(gestR.getProjetContacts(p));
					}
					
				}
			}

		});
		deletecontactgest.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Contact c = (Contact) contacts.getSelectedItem();
				if (c!=null){
					gestR.deleteContact(c);
					//TODO : MAJ combo box-
				}
				
			}
			
		});
		supprcontact.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int n = JOptionPane.showOptionDialog(OngletProjet.this, "Voulez-vous supprimer le contact?",
						"Suppression d'un contact", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (n==0){
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)
					arbretache.getLastSelectedPathComponent();

					/* if nothing is selected */ 
					//TODO ajouter introspection
					if ( node == null) return;

					Projet p = (Projet) node.getUserObject();
					gestR.deleteContactFromProject(p, (Contact)listecontact.getSelectedValue());

					listecontact.setListData(gestR.getProjetContacts(p));
				}	
			}
		});








		/*Element du panel de droite 2*/
		//Projet p = (Projet) listeprojet.getSelectedValue();




		/*Panel de droite 3*/
		pdroite3.setBorder(border3);
		for(Integer i = 0;i<100;i++){
			teffort.addItem(i);
		}

		//TODO : Modifier pour n'afficher que le lien et etiquette de la tache

		/*
		TreePath path = arbretache.getSelectionPath();
		Tache t = (Tache) path.getLastPathComponent();
		 */
		/*for (Tache t : p.getListtache()){

			for (String s : t.getListetiquette()){
				cbetiquette.addItem(s);
			}
			for (String s : t.getListlien()){
				cblien.addItem(s);
			}

		}*/
		pdroite3.setLayout(new GridLayout(9,2));

		pdroite3.add(lnomtache);
		pdroite3.add(tnomtache);
		pdroite3.add(lpriorite);
		pdroite3.add(tpriorite);
		pdroite3.add(leffort);
		pdroite3.add(teffort);
		pdroite3.add(letat);
		pdroite3.add(tetat);
		pdroite3.add(lreveil);
		pdroite3.add(dreveil);
		pdroite3.add(lecheance);
		pdroite3.add(decheance);
		pdroite3.add(letiquette);
		pdroite3.add(cbetiquette);
		pdroite3.add(llien);
		pdroite3.add(cblien);
		panelBouton.add(valider);
		panelBouton2.add(annuler);
		pdroite3.add(panelBouton);
		pdroite3.add(panelBouton2);

		/*Panel de droite*/


		pdroite.add(pdroite3);

		pprojet.add(pdroite);

		/*Action listenner*/

		listenote.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				JList list = (JList) e.getSource();
				String n = (String)list.getSelectedValue();
				if (n!=null)
					nouvellenote.setText(n);				
			}
		});
		listecontact.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				JList list = (JList) e.getSource();
				Contact c = (Contact)list.getSelectedValue();
				if (c!=null)
					nouveaucontact.setText(c.getNom());		
			}
		});

		agir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				arbretache.getLastSelectedPathComponent();
				//TODO verifier la coherence
				if (( node == null)
						||((node.getUserObject().getClass().getName()!=
						"fr.univnantes.alma.gtd.model.gestionnaireressources.Projet"))){
					JOptionPane.showOptionDialog(OngletProjet.this, "Selectionner un projet pour pouvoir agir",
							"Agir", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				}else{
					Projet p = (Projet) node.getUserObject();
					Tache t = gestR.getProjetUrgentTask(p);
					if (t==null){
						String msg = "Le projet ne contient pas de tache";
						JOptionPane.showOptionDialog(OngletProjet.this, msg,
								"Agir", JOptionPane.DEFAULT_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, null, null);

					}else{
						String msg = "La tache la plus urgente du projet "+p.getNom()+" est : "+t.getNom();
						JOptionPane.showOptionDialog(OngletProjet.this, msg,
								"Agir", JOptionPane.DEFAULT_OPTION,
								JOptionPane.INFORMATION_MESSAGE, null, null, null);
					}

				}			

			}
		});
		ajoutprojet.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Vector<Contexte> temp = gestR.getListeContexte();
				Contexte tempc = null;
				for(Contexte c : temp){
					if(c.getTitre().equals(OngletProjet.this.contexte.getSelectedItem().toString())){
						tempc = c;
					}
				}
				if(tempc == null){
					gestR.addContexte(contexte.getSelectedItem().toString());
					temp = gestR.getListeContexte();
					for(Contexte c : temp){
						if(c.getTitre().equals(OngletProjet.this.contexte.getSelectedItem().toString())){
							tempc = c;
						}
					}
				}
				Projet p = new Projet(nomprojet.getText(), null, tempc);
				gestR.addProjet(p);
				createTree();				
			}
		});

		supprprojet.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {	
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				arbretache.getLastSelectedPathComponent();
				//TODO verifier la coherence
				if (( node == null)
						||((node.getUserObject().getClass().getName()!="fr.univnantes.alma.gtd.model.gestionnaireressources.Projet")
								&&(node.getUserObject().getClass().getName()!="type.Projet"))){
					JOptionPane.showOptionDialog(OngletProjet.this, "Selectionner un projet pour pouvoir le supprimer",
							"Suppression d'un projet", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				}else{
					int n = JOptionPane.showOptionDialog(OngletProjet.this, "Voulez-vous supprimer le projet?",
							"Suppression d'un projet", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (n==0){
						gestR.deleteProject((Projet) node.getUserObject());
						createTree();
					}

				}			
			}			
		});
		ajouttache.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				arbretache.getLastSelectedPathComponent();

				/* if nothing is selected */ 
				//TODO verifier la coherence
				if (( node == null)
						||((node.getUserObject().getClass().getName()!=
						"fr.univnantes.alma.gtd.model.gestionnaireressources.Projet"))){
					JOptionPane.showOptionDialog(OngletProjet.this, "Selectionner un projet pour ajouter une tache",
							"Ajout d'une tache", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				}else{


					Projet p = (Projet) node.getUserObject();
					gestR.addTache(tnomtache.getText(), (Integer)tpriorite.getSelectedItem(),
							(Integer) teffort.getSelectedItem(),
							(EtatTache) tetat.getSelectedItem(), dreveil.getDate(), decheance.getDate(), p);					
					createTree();
				}

			}
		});
		supprtache.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				arbretache.getLastSelectedPathComponent();

				/* if nothing is selected */ 
				//TODO verifier la coherence
				if (( node == null)
						||((node.getUserObject().getClass().getName()!=
						"fr.univnantes.alma.gtd.model.gestionnaireressources.Tache"))){
					/* if nothing is selected */ 
					JOptionPane.showOptionDialog(OngletProjet.this, "Selectionner une tache pour la supprimer",
							"Suppression d'une tache", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				}else{
					int n = JOptionPane.showOptionDialog(OngletProjet.this, "Voulez-vous supprimer la tache?",
							"Suppression d'une tache", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null, null);

					if (n==0){//OK
						gestR.deleteTache((Tache) node.getUserObject());						
						createTree();
					}
				}
			}
		});
		ajoutsousprojet.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				arbretache.getLastSelectedPathComponent();

				/* if nothing is selected */ 
				//TODO verifier la coherence
				if (( node == null)
						||((node.getUserObject().getClass().getName()!=
						"fr.univnantes.alma.gtd.model.gestionnaireressources.Projet"))){
					JOptionPane.showOptionDialog(OngletProjet.this, "Selectionner un projet pour ajouter un sous-projet",
							"Ajout d'un sous-projet", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				}else{

					gestR.addProjet(nomprojet.getText(), OngletProjet.this.contexte.getSelectedItem().toString(), (Projet) node.getUserObject());
					createTree();
				}
			}
		});
		validerprojet.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				arbretache.getLastSelectedPathComponent();
				//TODO verifier la coherence
				if (( node == null)||((node.getUserObject().getClass().getName()!=
				"fr.univnantes.alma.gtd.model.gestionnaireressources.Projet"))) {
					JOptionPane.showOptionDialog(OngletProjet.this, "Selectionner un projet pour modifier ses donn�es",
							"Modification des donnees d'un projet", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
					return;
				}
				/*Modif contact*/

				Projet p = (Projet) node.getUserObject();
				Contact c = (Contact)listecontact.getSelectedValue();
				if (c!=null){
					gestR.deleteContactFromProject(p, c);
					gestR.addContactToProject(p, nouveaucontact.getText());
					listecontact.setListData(gestR.getProjetContacts(p));
				}

				/*Modif note*/
				String n = (String)listenote.getSelectedValue();
				if (n!=null){
					gestR.deleteNoteFromProject(p, n);
					gestR.addNoteToProject(p, n);
					listenote.setListData(gestR.getProjetNotes(p));
				}

				/*Modif nom projet et contexte*/

				gestR.setProjectContext(p, OngletProjet.this.contexte.getSelectedItem().toString());
				gestR.setProjetName(p,nomprojet.getText());			

				createTree();

			}
		});
		valider.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				/*Suppression de la tache selectionner*/
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				arbretache.getLastSelectedPathComponent();

				/* if nothing is selected */ 
				//TODO verifier la coherence
				if (( node == null)
						||((node.getUserObject().getClass().getName()!=
						"fr.univnantes.alma.gtd.model.gestionnaireressources.Tache"))){
					/* if nothing is selected */ 
					JOptionPane.showOptionDialog(OngletProjet.this, "Selectionner une tache pour la modifier",
							"Modification d'une tache", JOptionPane.DEFAULT_OPTION,
							JOptionPane.ERROR_MESSAGE, null, null, null);
				}else{
					Tache t = (Tache) node.getUserObject();

					Projet p = gestR.getTaskProjet(t);

					gestR.deleteTache(t);
					gestR.addTache(tnomtache.getText(), (Integer)tpriorite.getSelectedItem(),
							(Integer) teffort.getSelectedItem(),
							(EtatTache) tetat.getSelectedItem(), dreveil.getDate(), decheance.getDate(), p);
					createTree();
				}

			}
		});
		annuler.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)
				arbretache.getLastSelectedPathComponent();

				/* if nothing is selected */ 
				if ( node == null) return;

				/* React to the node selection. */
				//TODO verifier la coherence
				if (node.getUserObject().getClass().getName()==
				"fr.univnantes.alma.gtd.model.gestionnaireressources.Tache"){

					Tache t = (Tache) node.getUserObject();
					if (t!=null){
						tnomtache.setText(t.getNom());
						tpriorite.setSelectedItem(t.getPriorite());
						teffort.setSelectedItem(t.getEffort());
						tetat.setSelectedItem(t.getEtatTache());
						//TODO : date
						cbetiquette = new JComboBox();
						for ( String s : t.getNotes()){
							cbetiquette.addItem(s);
						}
						cblien = new JComboBox();						
						for ( String s : t.getLiens()){
							cblien.addItem(s);
						}		
						createTree();
					}
				}
			}
		});
	}

	private void createTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(new Projet("Les projets"));
		DefaultMutableTreeNode projet = null;
		DefaultMutableTreeNode tache = null;
		DefaultMutableTreeNode sousprojet = null;
		if (gestR.getListeProjet().size()>0)
			for (Projet p : gestR.getListeProjet()){
				projet = new DefaultMutableTreeNode(p);


				if (gestR.getProjectTasks(p).size()>0)
					for (Tache t : gestR.getProjectTasks(p)){
						tache = new DefaultMutableTreeNode(t);
						projet.add(tache);
					}
				if (gestR.getProjectSubProject(p).size()>0)
					for (Projet proj : gestR.getProjectSubProject(p)){
						sousprojet = new DefaultMutableTreeNode(proj);
						for (Tache t : gestR.getProjectTasks(proj)){
							tache = new DefaultMutableTreeNode(t);
							sousprojet.add(tache);
						}
						projet.add(sousprojet);
					}
				root.add(projet);
			}
		DefaultTreeModel model = new DefaultTreeModel(root);
		arbretache.setModel(model);
	}
	/*Element du panel droit*/
	public void refresh(){
		createTree();
		
		/*
		contexte.removeAllItems();
		Vector<Contexte> listetemp =gestR.getListeContexte();
		for(Contexte ctemp : listetemp){
			contexte.addItem(ctemp);
		}
		 */
	}
}
