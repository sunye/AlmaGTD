
/**
 * généré via class.mt
 */



package fr.univnantes.alma.gtd.model.gestionnaireutilisateur;

// Start of user code for imports in Utilisateur
import fr.univnantes.alma.gtd.model.gestionnaireressources.GTDEntity;
import fr.univnantes.alma.gtd.model.gestionnaireressources.GestionnaireRessources;
import fr.univnantes.alma.gtd.model.gestionnaireressources.IGestionnaireRessources;
import fr.univnantes.alma.gtd.persistance.IPersistance;

// Start of user code for comments in Utilisateur
/**
 * 
 */
// End of user code

// Start of user code for comments in Utilisateur
/**
 * 
 */
// End of user code

public class Utilisateur extends GTDEntity { 

	private String login;
	private String pwd;
	private IGestionnaireRessources iGestionnaireRessources;
	
	
	
	
	
	// Start of user code for attributes in Utilisateur
	private IPersistance persistance = GTDEntity.PERSISTANCE;
	// End of user code
	
	// Start of user code for constructors in Utilisateur
	public Utilisateur() {
		//super();
		this.iGestionnaireRessources = new GestionnaireRessources();
		
	}
	
	public Utilisateur(String login, String pwd) {
		super();
		this.iGestionnaireRessources = new GestionnaireRessources();
		this.login = login;
		this.pwd = pwd;
	}
	
	/* // contructeur généré
	public Utilisateur(final String login, final String pwd, final IGestionnaireRessources iGestionnaireRessources) {
		super();
		this.setLogin(login);
		this.setPwd(pwd);
		this.setIGestionnaireRessources(iGestionnaireRessources);
	}
	*/
	// End of user code
	
	public String getLogin() {
		// Start of user code for getter of login
		return this.login;
		// End of user code
	}
	
	public void setLogin(String login) {
		// Start of user code for setter of login
		this.login = login;
		persistance.update(this);
		// End of user code
	}
	public String getPwd() {
		// Start of user code for getter of pwd
		return this.pwd;
		// End of user code
	}
	
	public void setPwd(String pwd) {
		// Start of user code for setter of pwd
		this.pwd = pwd;
		persistance.update(this);
		// End of user code
	}
	public IGestionnaireRessources getIGestionnaireRessources() {
		// Start of user code for getter of iGestionnaireRessources
		return this.iGestionnaireRessources;
		// End of user code
	}
	
	public void setIGestionnaireRessources(IGestionnaireRessources iGestionnaireRessources) {
		// Start of user code for setter of iGestionnaireRessources
		this.iGestionnaireRessources = iGestionnaireRessources;
		persistance.update(this);
		// End of user code
	}
	
	
	
	
}
