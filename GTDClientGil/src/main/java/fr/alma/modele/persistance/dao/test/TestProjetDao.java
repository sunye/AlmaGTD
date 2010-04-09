package fr.alma.modele.persistance.dao.test;

import java.io.InputStream;
import java.util.Properties;

import fr.alma.modele.noyau.Contexte;
import fr.alma.modele.noyau.Projet;
import fr.alma.modele.persistance.DaoFactory;
import fr.alma.modele.persistance.dao.ProjetDao;
import fr.alma.modele.persistance.dao.impl.AbstractDao;

public class TestProjetDao {
	private ProjetDao dao;
	public void setUp() throws Exception {
		Properties props = new Properties();
		try {
			InputStream is = ClassLoader
			.getSystemResourceAsStream("test-specifics.properties");
			props.load(is);
			System.out.println(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AbstractDao.ajouterProprietesSpecifiques(props);
		dao = DaoFactory.createProjetDao();
		dao.supprimerTout();
	}
	public void creerProjet(){
		Projet p = new Projet("projetTest", Contexte.MAISON.name(), "notes projet");
		dao.creer(p);
	}
}
