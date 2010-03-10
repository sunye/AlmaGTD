package fr.alma.modele.persistence.dao.test;

import java.util.List;

import fr.alma.modele.Modele;
import fr.alma.modele.noyau.Tache;
import fr.alma.modele.persistance.DaoFactory;
import fr.alma.modele.persistance.dao.TacheDao;

public class TestAMoi {

	/**
	 * @param args
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		Modele.setIdUtilisateur(new Long(0));
		TacheDao ta = DaoFactory.createTacheDao();
		List<Tache> l = ta.recupererTout();
		for(Tache t1 : l){
			System.out.println(t1.getNom());
		}
		Echeancier n = new Echeancier();
		n.recupererEcheance(l);
		n.trierTaches();
		for(Tache t : n.getTaches()){
			System.out.println("la tache "+t.getNom()+" a pour date : "+t.getDateEcheance().toLocaleString());
		}
		
	}

}
