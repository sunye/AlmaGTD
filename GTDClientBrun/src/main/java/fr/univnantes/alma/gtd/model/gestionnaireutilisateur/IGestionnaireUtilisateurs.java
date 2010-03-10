
/**
 * généré via interface.mt
 */

package fr.univnantes.alma.gtd.model.gestionnaireutilisateur;

// Start of user code for imports in IGestionnaireUtlisateur
import java.util.List;

public interface IGestionnaireUtilisateurs {
	public Integer getId();
	
	public void setId(Integer id);
	
	public Integer add(final Utilisateur user);
	
	public Boolean remove(final Utilisateur user);
	
	public Utilisateur find(final Integer id);

	public List<Utilisateur> getUtilisateurs();
	
	public Utilisateur find(final String login);
}