package fr.alma.gtd.util.impl;

import fr.alma.gtd.entities.Element;
import fr.alma.gtd.entities.Projet;
import fr.alma.gtd.entities.Tache;

/**
 *
 * @author E054786A
 */
public class VisitorPrioriteImpl implements fr.alma.gtd.util.Visitor {

 	/**
 	 * methode de l'interface Visitor
 	 */
	@Override
 	public void visitTache(Tache tache){
 		//Start of user code Visitor.visitTache
        if (tache.getEcheancier() == null) {
        	tache.setPrioriteDynamique(tache.getPriorite() * 100);
        } else {
            Long div = 10000000000l;
            Float mod = ((float) (tache.getEcheancier().getDateArret().getTime() - System.currentTimeMillis())) / div;
            tache.setPrioriteDynamique(Math.round(tache.getPriorite() * 100 / mod));
        }
        //End of user code
 	}

 	/**
 	 * methode de l'interface Visitor
 	 */
	@Override
 	public void visitProjet(Projet projet) {
 		//Start of user code Visitor.visitProjetOrdonne
 		for (Element e : projet.getElements()) {
			e.accept(this);
		}

 		int priorite = 0;
 		for (Element e : projet.getElements()) {
 			if (e.getPrioriteDynamique() != null && e.getPrioriteDynamique() > priorite) {
 				priorite = e.getPrioriteDynamique();
 			}
 		}
 		if (priorite > 0) {
 			projet.setPrioriteDynamique(priorite);
 		}
 		//End of user code
 	}

}
