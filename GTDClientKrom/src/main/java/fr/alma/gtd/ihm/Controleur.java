package fr.alma.gtd.ihm;

import fr.alma.gtd.util.impl.VisitorPrioriteImpl;
import fr.alma.gtd.entities.Contact;
import fr.alma.gtd.ihm.renseignement.PanelRenseignement;
import fr.alma.gtd.controle.AuthentificationCompte;
import fr.alma.gtd.controle.CreationCompte;
import fr.alma.gtd.controle.GestionCompte;
import fr.alma.gtd.dao.ChoseAFaireDao;
import fr.alma.gtd.dao.ContactDao;
import fr.alma.gtd.dao.ContexteDao;
import fr.alma.gtd.dao.ElementDao;
import fr.alma.gtd.dao.ProjetDao;
import fr.alma.gtd.dao.TacheDao;
import fr.alma.gtd.dao.impl.ChoseAFaireDaoImpl;
import fr.alma.gtd.dao.impl.ContactDaoImpl;
import fr.alma.gtd.dao.impl.ContexteDaoImpl;
import fr.alma.gtd.dao.impl.ElementDaoImpl;
import fr.alma.gtd.dao.impl.ProjetDaoImpl;
import fr.alma.gtd.dao.impl.TacheDaoImpl;
import fr.alma.gtd.entities.AFaireUnJour;
import fr.alma.gtd.entities.ATraiter;
import fr.alma.gtd.entities.Avancement;
import fr.alma.gtd.entities.ChoseAFaire;
import fr.alma.gtd.entities.Compte;
import fr.alma.gtd.entities.Contexte;
import fr.alma.gtd.entities.Echeancier;
import fr.alma.gtd.entities.Element;
import fr.alma.gtd.entities.Frequence;
import fr.alma.gtd.entities.Projet;
import fr.alma.gtd.entities.Tache;
import fr.alma.gtd.ihm.gestionComptes.ChargementCompte;
import fr.alma.gtd.ihm.gestionComptes.DialogChargement;
import fr.alma.gtd.ihm.gestionComptes.LoginInfo;
import fr.alma.gtd.ihm.gestionComptes.NouveauCompte;
import fr.alma.gtd.ihm.renseignement.InfosEcheancier;
import fr.alma.gtd.ihm.renseignement.InfosRenseignement;
import fr.alma.gtd.util.Visitor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;

public class Controleur implements Observer {

	private static Controleur instance = new Controleur();
	private Compte compte;
	private FenetrePrincipale vue;
	private DialogChargement dialog;

	private Controleur() {
		this.compte = null;
		this.dialog = new DialogChargement(vue, false);
	}

	public static Controleur getInstance() {
		return instance;
	}

	public void setVue(FenetrePrincipale fen) {
		this.vue = fen;
	}

	// ------------------------ partie panel faire
	// --------------------------------------
	public LinkedList<JCheckBox> constructionListeCheckBox() {
		ContexteDao dao = new ContexteDaoImpl();
		LinkedList<JCheckBox> listeCheckBox = new LinkedList<JCheckBox>();
		if (this.compte != null) {
			for (Contexte caf : dao.findAll()) {
				JCheckBox cb = new JCheckBox(caf.toString());
				cb.setSelected(true);
				listeCheckBox.add(cb);
			}
		}
		return listeCheckBox;
	}

	public void constructionListeTaches(DefaultListModel elementsListe) {
		TacheDao dao = new TacheDaoImpl();
		if (this.compte != null) {
			for (Tache tache : dao.findAll()) {
				elementsListe.addElement(tache);
			}
		}
	}

	// ------------------------------------------------------------------------------------

	// ------------------------ partie panel traiter
	// --------------------------------------
	public void constructionArbreTaches(DefaultMutableTreeNode racine) {
		// Ajout des projets et des taches
		ProjetDao projetDao = new ProjetDaoImpl();
		if (this.compte == null) {
			racine.setUserObject(null);
		} else {
			racine.setUserObject(projetDao.findProjetRacine());
			constructionArbreTachesBis(racine, projetDao.findProjetRacine());
		}
	}

	private void constructionArbreTachesBis(DefaultMutableTreeNode racine,
			Projet projet) {
		ElementDao dao = new ElementDaoImpl();
		for (Element elem : dao.findAllOfProjet(projet)) {
			DefaultMutableTreeNode rep;
			if (elem.estTache()) {
				rep = new DefaultMutableTreeNode(elem, false);
			} else {
				rep = new DefaultMutableTreeNode(elem);
				constructionArbreTachesBis(rep, (Projet) elem);
			}
			racine.add(rep);
		}
	}

	public void constructionListeChoseATraiter(DefaultListModel elementsListe) {
		ChoseAFaireDao dao = new ChoseAFaireDaoImpl();
		if (this.compte != null) {
			for (ChoseAFaire caf : dao.findATraiter()) {
				elementsListe.addElement(caf);
			}
		}
	}

	// ------------------------------------------------------------------------------------

	public void chargerCompte(ChargementCompte chargementCompte) {
		LoginInfo loginInfo = chargementCompte.showDialog();
		while (!(loginInfo.loginRenseigne() || loginInfo.estAnnule())) {
			JOptionPane.showMessageDialog(null, " login n'a pas été renseigné.", "Informations incorrectes", JOptionPane.WARNING_MESSAGE);
			loginInfo = chargementCompte.showDialog();
		}

		if (!loginInfo.estAnnule()) {
			GestionCompte gestion = new GestionCompte(loginInfo.getLogin());
			if (!gestion.existeCompte()) {
				JOptionPane.showMessageDialog(null, "Le compte \"" + gestion.getLogin() + "\" n'existe pas.", "Echec de la connexion au compte", JOptionPane.ERROR_MESSAGE);
			} else {
				this.connexionCompte(loginInfo);
			}
		}
	}

	public void connexionCompte(LoginInfo loginInfo) {
		dialog.setVisible(true);
		AuthentificationCompte authentification = new AuthentificationCompte(loginInfo);
		authentification.addObserver(this);
		Thread t = new Thread(authentification);
		t.start();
	}

	public void authentificationCompte(GestionCompte gestion) {
		this.compte = gestion.getCompte();
		this.vue.compteCharge(true);

		genererDonnees(); // Ajoute des donnees au nouveau compte pour les jeux de tests

		actualiserVue();
	}
	
	private void genererDonnees() {
		ContexteDao contexteDao = new ContexteDaoImpl();
		if (contexteDao.findAll().size() == 1) {
			long id1_contexte = contexteDao.create(new Contexte("Maison"));
			long id2_contexte = contexteDao.create(new Contexte("Téléphone"));
			long id3_contexte = contexteDao.create(new Contexte("Lieu de travail"));

			ProjetDao projetDao = new ProjetDaoImpl();
			Projet projet = new Projet(true, "Démarrage projet", contexteDao.find(id3_contexte));
			projet.setProjetConteneur(projetDao.findProjetRacine());
			long id1_projet = projetDao.create(projet);
			projet = new Projet(false, "Compte rendu de réunion", contexteDao.find(id3_contexte));
			projet.setProjetConteneur(projetDao.find(id1_projet));
			long id2_projet = projetDao.create(projet);
			
			TacheDao tacheDao = new TacheDaoImpl();
			Tache tache = new Tache(2, 20, Avancement.AFAIRE, "Téléphoner au client", contexteDao.find(id2_contexte));
			tache.setProjetConteneur(projetDao.find(id1_projet));
			tacheDao.create(tache);
			tache = new Tache(1, 50, Avancement.AFAIRE, "Tondre la pelouse", contexteDao.find(id1_contexte));
			tache.setProjetConteneur(projetDao.findProjetRacine());
			tacheDao.create(tache);
			tache = new Tache(3, 30, Avancement.AFAIRE, "Rédiger le plan", contexteDao.find(id3_contexte));
			tache.setProjetConteneur(projetDao.find(id2_projet));
			tacheDao.create(tache);
		
			ChoseAFaireDao cafDao = new ChoseAFaireDaoImpl();
			cafDao.create(new ATraiter("Rappeler le DRH"));
			cafDao.create(new ATraiter("Consulter ses emails"));
			cafDao.create(new AFaireUnJour("Apprendre le russe"));
			
			ContactDao contactDao = new ContactDaoImpl();
			contactDao.create(new Contact("Lucas", "Boris"));
			contactDao.create(new Contact("Bremard", "Nicolas"));
		}
	}

	public void nouveauCompte(NouveauCompte nouveauCompte) {
		LoginInfo loginInfo = nouveauCompte.showDialog();
		while (!(loginInfo.loginRenseigne() && loginInfo.compareMdp() || loginInfo.estAnnule())) {
			String message;
			if (!loginInfo.loginRenseigne()) {
				message = "Le login n'a pas été renseigné.";
			} else {
				message = "Erreur de saisie du mot de passe.";
			}
			JOptionPane.showMessageDialog(null, message, "Informations incorrectes", JOptionPane.WARNING_MESSAGE);
			loginInfo = nouveauCompte.showDialog();
		}

		if (!loginInfo.estAnnule()) {
			GestionCompte gestion = new GestionCompte(loginInfo.getLogin());
			if (gestion.existeCompte()) {
				JOptionPane.showMessageDialog(null, "Le compte \"" + gestion.getLogin() + "\" existe déjà.", "Echec de la création du compte", JOptionPane.ERROR_MESSAGE);
			} else {
				dialog.setVisible(true);
				CreationCompte creation = new CreationCompte(loginInfo);
				creation.addObserver(this);
				Thread t = new Thread(creation);
				t.start();
			}
		}
	}

	public void addChoseAFaire(String caf) {
		ChoseAFaireDao dao = new ChoseAFaireDaoImpl();
		dao.create(new ATraiter(caf));
		actualiserVue();
	}

	private void actualiserVue() {
		vue.actaliser();
	}

	public void deconnexion() {
		this.compte = null;
		GestionCompte.deconnexion();
		actualiserVue();
		vue.compteCharge(false);
	}

	public void selectionnerTachesContextes(LinkedList<JCheckBox> listeActives,
			DefaultListModel elementsListe) {
		TacheDao tacheDao = new TacheDaoImpl();
		List<String> noms = new LinkedList<String>();
		for (JCheckBox checkBox : listeActives) {
			noms.add(checkBox.getText());
		}

		elementsListe.clear();
		for (Tache tache : tacheDao.findATraiterWithContexts(noms)) {
			elementsListe.addElement(tache);
		}
	}

	private Element DefaultMutableTreeNode2Element(DefaultMutableTreeNode dmtn) {
		return (Element) dmtn.getUserObject();
	}

	public void deplacerElement(DefaultMutableTreeNode node, DefaultMutableTreeNode cible, int index) {
		DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
		Projet ancienConteneur = (Projet) DefaultMutableTreeNode2Element(parent);
		Projet nouveauConteneur = (Projet) DefaultMutableTreeNode2Element(cible);
		Element element = DefaultMutableTreeNode2Element(node);

		// vérifie si on est plus hors index lors d'une réorganisation par ex
		if ((index == nouveauConteneur.getElements().size()) && (ancienConteneur.getId() == nouveauConteneur.getId())) {
			index = index - 1;
		}
		
		ancienConteneur.removeElements(element);
		nouveauConteneur.ajoutElementIndex(element, index);
		element.setProjetConteneur(nouveauConteneur);

		ElementDao dao = new ElementDaoImpl();		
		dao.update(element);
		dao.update(ancienConteneur);
		dao.update(nouveauConteneur);
	}

	public void suppressionElement(DefaultMutableTreeNode node) {
		Long id = DefaultMutableTreeNode2Element(node).getId();
		ElementDao dao = new ElementDaoImpl();
		dao.delete(id);
	}

	public void supprimerCAF(Object objet) {
		ChoseAFaireDao dao = new ChoseAFaireDaoImpl();
		ChoseAFaire caf = (ChoseAFaire) objet;
		dao.delete(caf.getId());
		actualiserVue();
	}

	public void supprimerTache(Object object) {
		Long id = ((Element) object).getId();
		ElementDao dao = new ElementDaoImpl();
		dao.delete(id);
		actualiserVue();
	}

	public String getDescription(Tache tache) {
		return "<html>Nom : " + tache.getNom()
				+ "<br>Notes : " + tache.getNotes()
				+ "<br>Contexte : " + tache.getContexte()
				+ "<br>Priorité statique : " + tache.getPriorite()
				+ "<br>Priorité dynamique : " + tache.getPrioriteDynamique()
				+ "<br>Projet conteneur : " + tache.getProjetConteneur()
				+ "</html>";
	}

	public String getNomChoseAFaire(Object source) {
		ChoseAFaire caf = (ChoseAFaire) source;
		if (caf != null) {
			return caf.getDescription();
		} else {
			return null;
		}
	}

	public void constructionListeChoseAFaireUnJour(DefaultListModel elementsListe) {
		ChoseAFaireDao dao = new ChoseAFaireDaoImpl();
		if (this.compte != null) {
			for (ChoseAFaire caf : dao.findAFaireUnJour()) {
				elementsListe.addElement(caf);
			}
		}
	}

	public void aTraiter(Object object) {
		ChoseAFaireDao dao = new ChoseAFaireDaoImpl();
		AFaireUnJour caf = (AFaireUnJour) object;
		ATraiter cafATraiter = new ATraiter(caf.getDescription());
		dao.create(cafATraiter);
		dao.delete(caf.getId());
		actualiserVue();
	}

	public void aFaireUnJour(Object object) {
		ChoseAFaireDao dao = new ChoseAFaireDaoImpl();
		ATraiter cafATraiter = (ATraiter) object;
		AFaireUnJour cafAFaireUnJour = new AFaireUnJour(cafATraiter.getDescription());
		dao.create(cafAFaireUnJour);
		dao.delete(cafATraiter.getId());
		actualiserVue();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o.getClass().getSimpleName().equals("CreationCompte")) {
			this.connexionCompte((LoginInfo) arg);
		} else if (o.getClass().getSimpleName().equals("AuthentificationCompte")) {
			if (arg == null) {
				JOptionPane.showMessageDialog(null, "Mot de passe incorrect.", "Echec de la connexion au compte", JOptionPane.ERROR_MESSAGE);
			} else {
				GestionCompte gestion = (GestionCompte) arg;
				this.authentificationCompte(gestion);
			}
			this.dialog.setVisible(false);
		}
	}
	
	// --------------------------------------------------------------
	// Renseignements

	private PanelRenseignement panelRenseignement;

	public void setPanelRenseignement(PanelRenseignement panelRenseignement) {
		this.panelRenseignement = panelRenseignement;
	}

	public Object ajoutContexte(String contexte) {
		ContexteDao contexteDao = new ContexteDaoImpl();
		if (contexteDao.exist(contexte)) {
			JOptionPane.showMessageDialog(null, "Un contexte de ce nom existe déjà.", "Echec de la création du contexte", JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			long id_contexte = contexteDao.create(new Contexte(contexte));
			panelRenseignement.updateRenseignements();
			vue.actualiserContextes();
			return contexteDao.find(id_contexte);
		}		
	}
	
	public DefaultMutableTreeNode constructionArbreProjets() {
		// Ajout des projets et des taches
		ProjetDao projetDao = new ProjetDaoImpl();
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode(projetDao.findProjetRacine());
		constructionArbreProjetsBis(racine, projetDao.findProjetRacine());
		return racine;
	}

	private void constructionArbreProjetsBis(DefaultMutableTreeNode racine, Projet projet) {
		ElementDao dao = new ElementDaoImpl();
		for (Element elem : dao.findAllOfProjet(projet)) {
			if (!elem.estTache()) {
				DefaultMutableTreeNode rep = new DefaultMutableTreeNode(elem);
				constructionArbreProjetsBis(rep, (Projet) elem);
				racine.add(rep);
			}
		}
	}

	public void constructionComboContexte(JComboBox comboContexte) {
		comboContexte.removeAllItems();
		if (this.compte != null) {
			ContexteDao dao = new ContexteDaoImpl();
			for (Contexte caf : dao.findAll()) {
				comboContexte.addItem(caf);
			}
		}
	}

	public void constructionComboProjet(JComboBox comboProjet) {
		comboProjet.removeAllItems();
		if (this.compte != null) {
			ProjetDao dao = new ProjetDaoImpl();
			for (Projet projet : dao.findAll()) {
				comboProjet.addItem(projet);
			}
		}
	}

	public void ajoutElement(InfosRenseignement infos) {
		if (infos.getNom().length() == 0) {
			JOptionPane.showMessageDialog(null, "Veuiller renseigner le nom de la tâche", "Erreur", JOptionPane.ERROR_MESSAGE);
		} else {
			ElementDao elementDao = new ElementDaoImpl();
			Element element = null;
			if (infos.getEstTache()) {
				element = new Tache(infos.getPriorite(), infos.getTauxEffort(), Avancement.AFAIRE, infos.getNom(), infos.getContexte());
			} else {
				element = new Projet(infos.getEstProjetOrdonne(), infos.getNom(), infos.getContexte());
			}
			element.setNotes(infos.getDescription());
			element.setProjetConteneur(infos.getProjetParent());
			InfosEcheancier infosEcheancier = infos.getEcheancier();
			if (infosEcheancier.getEstActive()) {
				// TODO fréquence de l'échéancier
				element.setEcheancier(new Echeancier(infosEcheancier.getDateDebut(), Frequence.MENSUELLE, infosEcheancier.getDateFin()));
			}
			elementDao.create(element);
			element.setContacts(infos.getContacts());
			elementDao.update(element);
			actualiserVue();
		}
	}

	public List<Contact> getListeContacts() {
		ContactDao dao = new ContactDaoImpl();
		return dao.findAll();
	}

	public void ajoutContact(Contact contact) {
		ContactDao contactDao = new ContactDaoImpl();
		contactDao.create(contact);
	}

	public void supprimerContact(Object object) {
		Long id = ((Contact) object).getId();
		ContactDao dao = new ContactDaoImpl();
		// TODO supprimer correctement ds les taches et projets...
		dao.delete(id);
		actualiserVue();
	}

	public boolean supprimerContexte(Object object) {
		Contexte contexte = (Contexte) object;
		Long id = contexte.getId();
		ContexteDao contexteDao = new ContexteDaoImpl();

		Contexte contexteNul = contexteDao.findContexteNul();

		ElementDao elementDao = new ElementDaoImpl();
		Collection<Element> elements = elementDao.findWithContext(contexte);
		if (!elements.isEmpty()) {
			int reponse = JOptionPane.showOptionDialog(null, "Supprimer le contexte \"" + contexte.getNom() + "\" entraînera la modification\ndes tâches et projets qui lui était associé.\nContinuer ?", "Confirmation de la suppression du contexte", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, null, null);
			if (reponse == JOptionPane.YES_OPTION) {
				for (Element element : elements) {
					element.setContexte(contexteNul);
					elementDao.update(element);
				}
			} else {
				return false;
			}
		}
		contexteDao.delete(id);
		actualiserVue();
		return true;
	}

	public List<Contexte> getListeContextes() {
		ContexteDao dao = new ContexteDaoImpl();
		return dao.findAll();
	}

	public void organiserTaches() {
		ProjetDao projetDao = new ProjetDaoImpl();
		Projet racine = projetDao.findProjetRacine();
		Visitor v = new VisitorPrioriteImpl();
		racine.accept(v);
		projetDao.update(racine);
	}

	void actualiserContact(Object object) {
		Contact contact = (Contact) object;
		ContactDao dao = new ContactDaoImpl();
		dao.update(contact);
	}

	public Collection<Contact> getContacts(Tache tache) {
//		ElementDao dao = new ElementDaoImpl();
//		Collection<Contact> c = dao.getContacts(tache);
//		return c;
		
		Collection<Contact> contacts = tache.getContacts();
		
		System.out.println("BREAK POINT");
		
		return contacts;
		
	}
	
}
