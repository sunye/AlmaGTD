
package fr.alma.gtd.util;

//Start of user code Visitor.import
import fr.alma.gtd.entities.Projet;
import fr.alma.gtd.entities.Tache;	
//End of user code
	
/**
 * interface Visitor
 */
public interface Visitor  {

	public void visitTache (Tache tache);
	public void visitProjet (Projet projet);

}
