package fr.univnantes.alma.gtd.persistance.bdlocale;

import java.util.List;

import fr.univnantes.alma.gtd.model.gestionnaireressources.AFaire;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Contact;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Contexte;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Deleguee;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EnAttente;
import fr.univnantes.alma.gtd.model.gestionnaireressources.EtatTache;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Finie;
import fr.univnantes.alma.gtd.model.gestionnaireressources.GestionnaireRessources;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Idee;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Projet;
import fr.univnantes.alma.gtd.model.gestionnaireressources.Tache;
import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.GestionnaireUtilisateurs;
import fr.univnantes.alma.gtd.model.gestionnaireutilisateur.Utilisateur;
import fr.univnantes.alma.gtd.persistance.IPersistance;

public class PersistanceLocale implements IPersistance {
	
	private UtilisateurDAO utilisateurDao = new UtilisateurDAOImpl();
	private GestionnaireUtilisateursDAO gestionnaireUtilisateursDao = new GestionnaireUtilisateursDAOImpl();
	private GestionnaireRessourcesDAO gestionnaireRessourcesDao = new GestionnaireRessourcesDAOImpl();
	private IdeeDAO ideeDao = new IdeeDAOImpl();
	private ContextDAO contexteDao = new ContextDAOImpl();
	private ContactDAO contactDao = new ContactDAOImpl();
	private EtatTacheDAO etatTacheDao = new EtatTacheDAOImpl();
	private ProjetDAO projetDao = new ProjetDAOImpl();
	private TacheDAO tacheDao = new TacheDAOImpl();

	
	public Contact findContact(Integer id) {
		return contactDao.find(id);
	}

	
	public Contexte findContexte(Integer id) {
		return contexteDao.find(id);
	}

	
	public Projet findProjet(Integer id) {
		return projetDao.find(id);
	}

	
	public Tache findTache(Integer id) {
		return tacheDao.find(id);
	}

	
	public Utilisateur findUtilisateur(Integer id) {
		return utilisateurDao.find(id);
	}

	
	public Idee findIdee(Integer id) {
		return ideeDao.find(id);
	}

	
	public Boolean update(GestionnaireUtilisateurs entity) {
		return gestionnaireUtilisateursDao.update(entity);
	}

	
	public Boolean delete(Utilisateur entity) {
		this.delete((GestionnaireRessources)entity.getIGestionnaireRessources());
		return utilisateurDao.delete(entity);
	}

	
	public Boolean deleteAllUtilisateurs() {
		return utilisateurDao.deleteAll();
	}

	
	public List<Utilisateur> findAllUtilisateurs() {
		return utilisateurDao.findAll();
	}

	
	public Boolean update(Utilisateur entity) {
		return utilisateurDao.update(entity);
	}

	
	public Integer create(GestionnaireRessources entity) {
		return gestionnaireRessourcesDao.create(entity);
	}

	
	//TODO A revoir avec les delete en cascade
	public Boolean delete(GestionnaireRessources entity) {
		for (Contact c : entity.getContacts()){
			this.delete(c);}
		for (Projet p : entity.getProjets()){
			this.delete(p);}
		for (Contexte c : entity.getContextes()){
			this.delete(c);}
		for (Idee i : entity.getIdees()){
			this.delete(i);}
		return gestionnaireRessourcesDao.delete(entity);
	}

	
	public Boolean deleteAllGestionnaireRessources() {
		return gestionnaireRessourcesDao.deleteAll();
	}

	
	public List<GestionnaireRessources> findAllGestionnaireRessources() {
		return gestionnaireRessourcesDao.findAll();
	}

	
	public GestionnaireRessources findGestionnaireRessources(Integer id) {
		return gestionnaireRessourcesDao.find(id);
	}

	
	public Boolean update(GestionnaireRessources entity) {
		return gestionnaireRessourcesDao.update(entity);
	}

	
	public Boolean delete(Idee entity) {
		return ideeDao.delete(entity);
	}

	
	public Boolean delete(Contact entity) {
		return contactDao.delete(entity);
	}

	
	public Boolean delete(Contexte entity) {
		return contexteDao.delete(entity);
	}

	
	public Boolean delete(Projet entity) {
		return projetDao.delete(entity);
	}

	
	public Boolean delete(Tache entity) {
		return tacheDao.delete(entity);
	}

	
	public Boolean deleteAllContacts() {
		System.out.println("23");
		return contactDao.deleteAll();
	}

	
	public Boolean deleteAllContextes() {
		return contexteDao.deleteAll();
	}

	
	public Boolean deleteAllIdees() {
		return ideeDao.deleteAll();
	}

	
	public Boolean deleteAllProjets() {
		return projetDao.deleteAll();
	}

	
	public Boolean deleteAllTache() {
		return tacheDao.deleteAll();
	}

	
	public List<Contact> findAllContacts() {
		return contactDao.findAll();
	}

	
	public List<Contexte> findAllContextes() {
		return contexteDao.findAll();
	}

	
	public List<Idee> findAllIdees() {
		return ideeDao.findAll();
	}

	
	public List<Projet> findAllProjets() {
		return projetDao.findAll();
	}

	
	public List<Tache> findAllTaches() {
		return tacheDao.findAll();
	}

	
	public Boolean update(Idee entity) {
		return ideeDao.update(entity);
	}

	
	public Boolean update(Contact entity) {
		return contactDao.update(entity);
	}

	
	public Boolean update(Contexte entity) {
		System.out.println("36");
		return contexteDao.update(entity);
	}

	
	public Boolean update(Projet entity) {
		return projetDao.update(entity);
	}

	
	public void create(Projet projet) {
		projetDao.create(projet);
		
	}

	
	public void valide() {
		HibernateUtil.valide();
	}

	
	public Integer create(GestionnaireUtilisateurs entity) {
		return gestionnaireUtilisateursDao.create(entity);
	}

	
	public GestionnaireUtilisateurs findGestionnaireUtilisateurs(Integer id) {
		return gestionnaireUtilisateursDao.find(id);
	}

	
	public void create(EtatTache entity) {
		etatTacheDao.create(entity);
	}

	
	public AFaire findAFaire(Integer id) {
		return etatTacheDao.findAFaire(id);
	}

	
	public Boolean update(Tache entity) {
		tacheDao.update(entity);
		return true;
	}

	
	public Deleguee findDeleguee(Integer id) {
		return etatTacheDao.findDeleguee(id);
	}

	
	public EnAttente findEnAttente(Integer id) {
		return etatTacheDao.findEnAttente(id);
	}

	
	public Finie findFinie(Integer id) {
		return etatTacheDao.findFinie(id);
	}
	
	

}
