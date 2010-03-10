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

	@Override
	public Contact findContact(Integer id) {
		System.out.println("1");
		return contactDao.find(id);
	}

	@Override
	public Contexte findContexte(Integer id) {
		System.out.println("2");
		return contexteDao.find(id);
	}

	@Override
	public Projet findProjet(Integer id) {
		System.out.println("3");
		return projetDao.find(id);
	}

	@Override
	public Tache findTache(Integer id) {
		System.out.println("4");
		return tacheDao.find(id);
	}

	@Override
	public Utilisateur findUtilisateur(Integer id) {
		System.out.println("5");
		return utilisateurDao.find(id);
	}

	@Override
	public Idee findIdee(Integer id) {
		System.out.println("6");
		return ideeDao.find(id);
	}

	@Override
	public Boolean update(GestionnaireUtilisateurs entity) {
		System.out.println("7");
		return gestionnaireUtilisateursDao.update(entity);
	}

	@Override
	public Boolean delete(Utilisateur entity) {
		System.out.println("8");
		this.delete((GestionnaireRessources)entity.getIGestionnaireRessources());
		return utilisateurDao.delete(entity);
	}

	@Override
	public Boolean deleteAllUtilisateurs() {
		System.out.println("9");
		return utilisateurDao.deleteAll();
	}

	@Override
	public List<Utilisateur> findAllUtilisateurs() {
		System.out.println("10");
		return utilisateurDao.findAll();
	}

	@Override
	public Boolean update(Utilisateur entity) {
		System.out.println("11");
		return utilisateurDao.update(entity);
	}

	@Override
	public Integer create(GestionnaireRessources entity) {
		System.out.println("12");
		return gestionnaireRessourcesDao.create(entity);
	}

	@Override
	//TODO A revoir avec les delete en cascade
	public Boolean delete(GestionnaireRessources entity) {
		System.out.println("13");
		for (Contact c : entity.getContacts())
			this.delete(c);
		for (Projet p : entity.getProjets())
			this.delete(p);
		for (Contexte c : entity.getContextes())
			this.delete(c);
		for (Idee i : entity.getIdees())
			this.delete(i);
		return gestionnaireRessourcesDao.delete(entity);
	}

	@Override
	public Boolean deleteAllGestionnaireRessources() {
		System.out.println("14");
		return gestionnaireRessourcesDao.deleteAll();
	}

	@Override
	public List<GestionnaireRessources> findAllGestionnaireRessources() {
		System.out.println("15");
		return gestionnaireRessourcesDao.findAll();
	}

	@Override
	public GestionnaireRessources findGestionnaireRessources(Integer id) {
		System.out.println("16");
		return gestionnaireRessourcesDao.find(id);
	}

	@Override
	public Boolean update(GestionnaireRessources entity) {
		System.out.println("17");
		return gestionnaireRessourcesDao.update(entity);
	}

	@Override
	public Boolean delete(Idee entity) {
		System.out.println("18");
		return ideeDao.delete(entity);
	}

	@Override
	public Boolean delete(Contact entity) {
		System.out.println("19");
		return contactDao.delete(entity);
	}

	@Override
	public Boolean delete(Contexte entity) {
		System.out.println("20");
		return contexteDao.delete(entity);
	}

	@Override
	public Boolean delete(Projet entity) {
		System.out.println("21");
		return projetDao.delete(entity);
	}

	@Override
	public Boolean delete(Tache entity) {
		System.out.println("22");
		return tacheDao.delete(entity);
	}

	@Override
	public Boolean deleteAllContacts() {
		System.out.println("23");
		return contactDao.deleteAll();
	}

	@Override
	public Boolean deleteAllContextes() {
		System.out.println("24");
		return contexteDao.deleteAll();
	}

	@Override
	public Boolean deleteAllIdees() {
		System.out.println("26");
		return ideeDao.deleteAll();
	}

	@Override
	public Boolean deleteAllProjets() {
		System.out.println("27");
		return projetDao.deleteAll();
	}

	@Override
	public Boolean deleteAllTache() {
		System.out.println("28");
		return tacheDao.deleteAll();
	}

	@Override
	public List<Contact> findAllContacts() {
		System.out.println("29");
		return contactDao.findAll();
	}

	@Override
	public List<Contexte> findAllContextes() {
		System.out.println("30");
		return contexteDao.findAll();
	}

	@Override
	public List<Idee> findAllIdees() {
		System.out.println("31");
		return ideeDao.findAll();
	}

	@Override
	public List<Projet> findAllProjets() {
		System.out.println("32");
		return projetDao.findAll();
	}

	@Override
	public List<Tache> findAllTaches() {
		System.out.println("33");
		return tacheDao.findAll();
	}

	@Override
	public Boolean update(Idee entity) {
		System.out.println("34");
		return ideeDao.update(entity);
	}

	@Override
	public Boolean update(Contact entity) {
		System.out.println("35");
		return contactDao.update(entity);
	}

	@Override
	public Boolean update(Contexte entity) {
		System.out.println("36");
		return contexteDao.update(entity);
	}

	@Override
	public Boolean update(Projet entity) {
		System.out.println("37");
		return projetDao.update(entity);
	}

	@Override
	public void create(Projet projet) {
		projetDao.create(projet);
		
	}

	@Override
	public void valide() {
		HibernateUtil.valide();
	}

	@Override
	public Integer create(GestionnaireUtilisateurs entity) {
		return gestionnaireUtilisateursDao.create(entity);
	}

	@Override
	public GestionnaireUtilisateurs findGestionnaireUtilisateurs(Integer id) {
		return gestionnaireUtilisateursDao.find(id);
	}

	@Override
	public void create(EtatTache entity) {
		etatTacheDao.create(entity);
	}

	@Override
	public AFaire findAFaire(Integer id) {
		return etatTacheDao.findAFaire(id);
	}

	@Override
	public Boolean update(Tache entity) {
		tacheDao.update(entity);
		return true;
	}

	@Override
	public Deleguee findDeleguee(Integer id) {
		return etatTacheDao.findDeleguee(id);
	}

	@Override
	public EnAttente findEnAttente(Integer id) {
		return etatTacheDao.findEnAttente(id);
	}

	@Override
	public Finie findFinie(Integer id) {
		return etatTacheDao.findFinie(id);
	}
	
	

}
