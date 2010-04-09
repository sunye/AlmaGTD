package fr.alma.controleur;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.tree.DefaultTreeModel;

import fr.alma.ihm.vues.ApplicationGTD;
import fr.alma.ihm.vues.generale.arbre.ArbreGTD;
import fr.alma.ihm.vues.generale.arbre.NoeudGTD;
import fr.alma.ihm.vues.generale.popup.AjouterProjetPopup;
import fr.alma.ihm.vues.generale.popup.AjouterTachePopup;
import fr.alma.ihm.vues.generale.popup.ConnexionPopup;
import fr.alma.ihm.vues.generale.popup.CreationComptePopup;
import fr.alma.ihm.vues.generale.popup.CreationContactPopup;
import fr.alma.ihm.vues.generale.popup.EditerProjetPopup;
import fr.alma.ihm.vues.generale.popup.EditerTachePopup;
import fr.alma.modele.Modele;
import fr.alma.modele.AbstractModele;
import fr.alma.modele.noyau.Frequence;
import fr.alma.modele.noyau.IContact;
import fr.alma.modele.noyau.IProjet;
import fr.alma.modele.noyau.ITache;
import fr.alma.modele.noyau.Periodicite;

/**
 * Controleur permettant de contrôler l'application.
 * 
 * @author Université de Nantes
 * @since 2009
 * @version 1.0
 */
public class Controleur implements IControleur {

	/**
	 * Le modèle abstrait du controleur.
	 */
	protected Modele modele;

	/**
	 * 
	 */
	protected IProjet projetPere;

	/**
	 * Format de la date, outil nécessaire lors des transformations du type Dare
	 * en type string
	 */
	public static final DateFormat DATEFORMAT = new SimpleDateFormat(
			"dd/MM/yyyy", Locale.FRANCE);
        private static final int MINPASSWORDLEN = 8;

	/**
	 * Constructeur.
	 * 
	 * @param modele
	 *            le modèle de notre application
	 */
	public Controleur(Modele modele) {
		this.modele = modele;
	}

	/**
	 * Contrôle de la connexion
	 * 
	 * @param login
	 *            le login utilisateur
	 * @param mdp
	 *            le mot de passe utilisateur
	 */
	@Override
	public void connecter(final String login, final char[] mdp) {
		// on force le login à ne pas avoir d'espace afin de respecter les
		// normes
		// et à le contrôler plus aisément
		String login2 = login.replaceAll(" ", "");
		if (login2.length() != 0 && mdp.length != 0) {
			// vérifier la bonne connexion signifie regarder ne bd si
			// l'utilisateur est enregistré
			Long idUtilisateur = this.modele.existeUtilisateur(login2, mdp);
			if (idUtilisateur == null) {
				ApplicationGTD
				.getInstance()
				.gererMessage(2,
						"La connexion a échoué - L'utilisateur n'est pas reconnu");
			} else {
				AbstractModele.setIdUtilisateur(idUtilisateur);
				AbstractModele.setProjetRacine(modele.getGestionnaireTaches()
						.getProjetRacine());
				ArbreGTD.getInstance().initialiser();
				ApplicationGTD.getInstance().gererMessage(1,
						"La connexion est réussie");
				ApplicationGTD.getInstance().getContainerVues()
						.getVueGenerale().peuplerListeContacts();
				ApplicationGTD.getInstance().getMenu().setEstConnecte(true);
				ConnexionPopup.getInstance().dispose();
				
			}
		} else {
			
			ApplicationGTD
					.getInstance()
					.gererMessage(2,
							"La connexion a échoué - Les champs obligatoires ne doivent pas etre vides");
		}
	}

	/**
	 * Contrôle de la déconnexion
	 */
	@Override
	public void deconnecter() {
		ArbreGTD.getInstance().supprimerTout();
		ApplicationGTD.getInstance().getContainerVues().getVueGenerale()
				.getPanneauTacheProjet().removeAll();
		ApplicationGTD.getInstance().getContainerVues().getVueGenerale()
				.getToolBar().removeAll();
		ApplicationGTD.getInstance().getContainerVues().getVueGenerale()
				.getListModel().removeAllElements();
		ApplicationGTD.getInstance().getMenu().setEstConnecte(false);
		ApplicationGTD.getInstance().gererMessage(-1, "");
	}

	/**
	 * Contrôle d'une création de compte
	 * 
	 * @param login
	 *            le login utilisateur
	 * @param password
	 *            mdp le mot de passe utilisateur
	 * @param password2
	 *            mdp de confirmation
	 * @param email
	 *            adresse email de l'utilisateur si besoin
	 */
	@Override
	public void creerCompte(final String login, final char[] password,
			final char[] password2, final String email) {

		// verif si compte existe pas deja avec le meme nom
		// this.modele.verifBonneCreationDeCompte(login);

		// vérification qu les noms de passe correspondent
		Boolean mdp_identiques = Boolean.TRUE;
                // Vérification de la longueur du mot de passe.
                if(password.length < MINPASSWORDLEN){
                    ApplicationGTD
					.getInstance()
					.gererMessage(2,
							"Le mot de passe a une longueur inférieure à 8.");
                }


		if (password.length == password2.length) {
			for (int i = 0; i < password.length; i++) {
				mdp_identiques = (password[i] == password2[i]);
				if (!mdp_identiques) {
					break;
				}
			}
		} else {
			mdp_identiques = Boolean.FALSE;
		}

		if (mdp_identiques) {
			// on force le login à ne pas avoir d'espace afin de respecter les
			// normes
			// et à le contrôler plus aisément
			final String login2 = login.replaceAll(" ", "");
			// les champs de saisies ne doivent pas etre vide
			if (login2.length() == 0 || password.length == 0
					|| password2.length == 0) {
				ApplicationGTD
						.getInstance()
						.gererMessage(2,
								"La connexion a échoué - Les champs obligatoires ne doivent pas etre vide");
			} else if (login2.length() < 4){
                            ApplicationGTD
						.getInstance()
						.gererMessage(2,
								"Le nom d'utilisateur doit au moins être de taille 4.");
                        } else {
				modele.creerCompte(login2, password, email);
				ApplicationGTD.getInstance().gererMessage(1,
						"La création du compte est réussie");
				CreationComptePopup.getInstance().dispose();
			}
		} else {
			ApplicationGTD
					.getInstance()
					.gererMessage(2,
							"La création du compte a échoué - Mots de passe différents");
		}
	}

	/**
	 * Contrôle d'une nouvelle tâche ou de l'édition d'une tâche existante.
	 * 
	 * @param valeurs
	 *            les valeurs enregistrés des différents champs de saisies lors
	 *            de l'ajout ou de l'edition d'une tache
	 * @param action
	 *            l'action effectuée (ajouter ou editer)
	 */
	@Override
	public void ajouterEditerTache(Map<Integer, Object> valeurs, TypeAction action) {

		Date dArretFreqRep = null;
		Date dDebut = null;
		Date dEcheance = null;
		Integer priorite = null;
		String notes = null;
		List<String> tags = null;
		List<String> urls = null;
		Integer tauxEffort = null;
		Frequence frequence = null;
		String nom = null;
		List<String> contacts = null;
		String contexte = "";

		for (Integer cle : valeurs.keySet()) {
			switch (cle) {
			case ITache.CONTEXTE:
				contexte = (String) valeurs.get(cle);
				break;

			case ITache.DATE_ARRET_FREQUENCE_REP:
				try {
					synchronized (DATEFORMAT) {
							dArretFreqRep = DATEFORMAT.parse((String) valeurs.get(cle));
					}
				} catch (ParseException e) {
					Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.WARNING, "Date d'arret de la fréquence de répétition invalide");
				}
				break;

			case ITache.DATE_DEBUT:
				try {
					synchronized (DATEFORMAT) {
						dDebut = DATEFORMAT.parse((String) valeurs.get(cle));
					}
				} catch (ParseException e) {
					Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.WARNING, "Date de début invalide");
				}
				break;

			case ITache.DATE_ECHEANCE:
				try {
					synchronized (DATEFORMAT) {
						dEcheance = DATEFORMAT.parse((String) valeurs.get(cle));	
					}
				} catch (ParseException e) {
					Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.WARNING, "Date d'échéance invalide");
				}
				break;

			case ITache.FREQUENCE_REP:
				final String freq = (String) valeurs.get(cle);
				if ("Journalière".equals(freq)) {
					frequence = Frequence.JOURNALIERE;
				} else if ("Hebdomadaire".equals(freq)) {
					frequence = Frequence.HEBDOMADAIRE;
				} else if ("Mensuelle".equals(freq)) {
					frequence = Frequence.MENSUELLE;
				} else {
					frequence = Frequence.EVENEMENT_PONCTUEL;
				}
				break;

			case ITache.NOTES:
				notes = (String) valeurs.get(cle);
				break;

			case ITache.PRIORITE:
				priorite = (Integer) valeurs.get(cle);
				break;

			case ITache.TAGS:
				final String tTemp[] = ((String) valeurs.get(cle)).split(" ");
				tags = new ArrayList<String>();
				for (String tag : tTemp) {
					tags.add(tag);
				}
				break;

			case ITache.TAUX_EFFORT:
				tauxEffort = (Integer) valeurs.get(cle);
				break;

			case ITache.URLS:
				final String uTemp[] = ((String) valeurs.get(cle)).split(" ");
				urls = new ArrayList<String>();
				for (String url : uTemp) {
					urls.add(url);
				}
				break;

			case ITache.CONTACTS:
				final String uContact[] = ((String) valeurs.get(cle)).split(",");
				contacts = new ArrayList<String>();
				for (String c : uContact) {
					contacts.add(c);
					System.out.println("c = " + c);
				}
				break;

			case ITache.NOM:
				nom = (String) valeurs.get(cle);
				break;
			default:
				break;
			}
		}

		// on force le login à ne pas avoir d'espace afin de respecter les
		// normes
		// et à le contrôler plus aisément
		final String nomTemp = nom.replaceAll(" ", "");

		if (action.equals(TypeAction.AJOUTER)) {

			if (nomTemp.length() == 0) {
				ApplicationGTD
						.getInstance()
						.gererMessage(2,
								"La création de la tâche a échoué - Le nom de la tâche ne doit pas etre vide");
			} else {
				NoeudGTD noeudCourant = (NoeudGTD) ArbreGTD.getInstance()
						.getArbre().getLastSelectedPathComponent();
				IProjet projet = null;
				if (noeudCourant != null) {
					projet = (IProjet) noeudCourant.getUserObject();
				}

				modele.creerTache(nom, projet, contexte, notes, dDebut,
						dEcheance, priorite, tauxEffort, null, frequence,
						dArretFreqRep, urls, tags);
				ApplicationGTD.getInstance().gererMessage(1,
						"La création de la tâche a réussi");
				AjouterTachePopup.getInstance().dispose();
				((DefaultTreeModel) ArbreGTD.getInstance().getArbre()
						.getModel()).reload();
			}
		} else if (action.equals(TypeAction.EDITER)) {

			if (nomTemp.length() == 0) {
				ApplicationGTD
						.getInstance()
						.gererMessage(
								2,
								"La modification de la tâche a échoué - Le nom de la tâche ne doit pas etre vide");
			} else {
				NoeudGTD node = (NoeudGTD) ArbreGTD.getInstance().getArbre()
						.getLastSelectedPathComponent();
				ITache t = (ITache) node.getUserObject();
				t.setNom(nom);
				t.setContexte(contexte);
				t.setDateDebut(dDebut);
				t.setDateEcheance(dEcheance);
				t.setNotes(notes);
				t.setPeriodicite(new Periodicite(frequence, dArretFreqRep));
				t.setUrls(urls);
				t.setTags(tags);
				t.setPriorite(priorite);
				t.setTauxEffort(tauxEffort);
				modele.editerTache(t);
				ApplicationGTD.getInstance().getContainerVues()
						.getVueGenerale().getPanneauTacheProjet().revalidate();
				ApplicationGTD.getInstance().getContainerVues()
						.getVueGenerale().getPanneauTacheProjet().repaint();
				ApplicationGTD.getInstance().gererMessage(1,
						"La modification de la tâche a réussi");
				((DefaultTreeModel) ArbreGTD.getInstance().getArbre()
						.getModel()).reload();
				EditerTachePopup.getInstance().dispose();
			}
		}
	}

	/**
	 * Contrôle d'un nouveau projet
	 * 
	 * @param valeurs
	 *            les valeurs enregistrés des différents champs de saisies lors
	 *            de l'ajout ou de l'edition d'un projet
	 * @param action
	 *            l'action effectuée (ajouter ou editer)
	 */
	@Override
	public void ajouterEditerProjet(Map<Integer, Object> valeurs, TypeAction action) {

		String nom = null;
		String notes = null;
		String contexte = "";

		for (Integer cle : valeurs.keySet()) {
			switch (cle) {
			case IProjet.CONTEXTE_DEFAUT:
				contexte = (String) valeurs.get(cle);
				break;

			case IProjet.NOTES:
				notes = (String) valeurs.get(cle);
				break;

			case IProjet.NOM:
				nom = (String) valeurs.get(cle);
				break;
			default:
				break;
			}
		}

		final String nomTemp = nom.replaceAll(" ", "");

		if (action.equals(TypeAction.AJOUTER)) {

			if (nomTemp.length() == 0) {
				ApplicationGTD
						.getInstance()
						.gererMessage(2,
								"La création du projet a échoué - Le nom du projet ne doit pas etre vide");
			} else {
				ApplicationGTD.getInstance().gererMessage(1,
						"La création du projet a réussi");
				NoeudGTD node = (NoeudGTD) ArbreGTD.getInstance().getArbre()
						.getLastSelectedPathComponent();
				Object nodeInfo = node.getUserObject();
				if (nodeInfo instanceof IProjet) {
					this.projetPere = (IProjet) nodeInfo;
					modele.creerProjet(nom, contexte, notes, projetPere);
				} else {
					modele.creerProjet(nom, contexte, notes, AbstractModele
							.getProjetRacine());
				}
				AjouterProjetPopup.getInstance().dispose();
			}

		} else if (action.equals(TypeAction.EDITER)) {

			if (nomTemp.length() == 0) {
				ApplicationGTD
						.getInstance()
						.gererMessage(2,
								"La modification du projet a échoué - Le nom du projet ne doit pas etre vide");
			} else {
				NoeudGTD node = (NoeudGTD) ArbreGTD.getInstance().getArbre()
						.getLastSelectedPathComponent();
				IProjet p = (IProjet) node.getUserObject();
				p.setNom(nom);
				p.setContexte(contexte);
				p.setNotes(notes);
				modele.editerProjet(p);
				ApplicationGTD.getInstance().getContainerVues()
						.getVueGenerale().getPanneauTacheProjet().revalidate();
				ApplicationGTD.getInstance().getContainerVues()
						.getVueGenerale().getPanneauTacheProjet().repaint();
				ApplicationGTD.getInstance().gererMessage(1,
						"La modification du projet a réussi");
				((DefaultTreeModel) ArbreGTD.getInstance().getArbre()
						.getModel()).reload();
				EditerProjetPopup.getInstance().dispose();
			}
		}
	}

	/**
	 * Contrôle d'un nouveau contact
	 * 
	 * @param nom
	 *            le nom du contact
	 * @param email
	 *            l'adresse mail du contact
	 * @param adresse
	 *            l'adresse du contact
	 * @param tel
	 *            numéro de téléphone du contact
	 */
	@Override
	public void creerContact(String nom, String email, String adresse,
			String tel) {
		// verif si contact existe pas deja avec le meme nom
		// this.modele.verifExistanceContact(nom);

		// on force le nom du contact à ne pas avoir d'espace afin de respecter
		// les normes
		// et à le contrôler plus aisément
		String nomTemp = nom.replaceAll(" ", "");
		if (nomTemp.length() == 0) {
			ApplicationGTD.getInstance().gererMessage(2,"La création du contact a échoué - Le nom du contact ne doit pas etre vide");
			
		} else {
			IContact c = modele.creerContact(nom, email, adresse, tel);
			ApplicationGTD.getInstance().gererMessage(1, "La création du contact a réussi");
			ApplicationGTD.getInstance().getContainerVues().getVueGenerale().getListModel().addElement(c);

			if (AjouterTachePopup.isNullInstance() == false) {
				Object data[] = { nom, email, new Boolean(true) };
				AjouterTachePopup.getInstance().getContact().getListeContacts()
						.addRow(data);
				AjouterTachePopup.getInstance().getContact().getListeContacts()
						.fireTableDataChanged();
			} else if (EditerTachePopup.isNullInstance() == false) {
				Object data[] = { nom, email, new Boolean(true) };
				EditerTachePopup.getInstance().getContact().getListeContacts()
						.addRow(data);
				EditerTachePopup.getInstance().getContact().getListeContacts()
						.fireTableDataChanged();
			}

			CreationContactPopup.getInstance().dispose();
			
		}
	}

	/**
	 * Contrôle d'une suppression d'un contact
	 * 
	 * @param contact
	 *            le nom du contact à supprimer
	 */
	@Override
	public void supprimerContact(IContact contact) {
		boolean estValide = true;
		this.modele.supprimerContact(contact);
		if (estValide) {
			ApplicationGTD.getInstance().gererMessage(1,
					"La suppression du contact a réussi");
			ApplicationGTD.getInstance().getContainerVues().getVueGenerale()
					.getListModel().removeElement(contact);
		} else {
			ApplicationGTD.getInstance().gererMessage(2,
					"La suppression du contact a échoué");
		}
	}

	/**
	 * Contrôle du bouton commit
	 */
	@Override
	public void commit() {
		boolean estValide = true;
		this.modele.commit();
		if (estValide) {
			ApplicationGTD.getInstance().gererMessage(1,
					"L'envoi de données a réussi");
		} else {
			ApplicationGTD.getInstance().gererMessage(2,
					"L'envoi de données a échoué");
		}
	}

	/**
	 * Contrôle du bouton update
	 */
	@Override
	public void update() {
		boolean estValide = true;
		this.modele.update();
		if (estValide) {
			ApplicationGTD.getInstance().gererMessage(1,
					"La récupération de données a réussi");
		} else {
			ApplicationGTD.getInstance().gererMessage(2,
					"La récupération de données a échoué");
		}
	}

	/**
	 * Contrôle de la synchronisation avec le serveur
	 */
	@Override
	public void synchro() {
		boolean estValide = true;
		this.modele.synchro();
		if (estValide) {
			ApplicationGTD.getInstance().gererMessage(1,
					"La synchronisation a réussi");
		} else {
			ApplicationGTD.getInstance().gererMessage(2,
					"La synchronisation a échoué");
		}
	}

	/**
	 * Contrôle de la mise en corbeille d'un objet (tache ou projet)
	 */
	@Override
	public void mettreDansCorbeille(Object o) {
		modele.mettreDansCorbeille(o);
	}

	/**
	 * Contrôle de la suppression de tous les éléménts présents dans la
	 * corbeille
	 */
	@Override
	public void viderCorbeille() {
		NoeudGTD node = (NoeudGTD) ArbreGTD.getInstance().getArbre()
				.getLastSelectedPathComponent();
		IProjet corbeille = (IProjet) node.getUserObject();
		modele.viderCorbeille(corbeille);
		ArbreGTD.getInstance().viderCorbeille();
	}
}