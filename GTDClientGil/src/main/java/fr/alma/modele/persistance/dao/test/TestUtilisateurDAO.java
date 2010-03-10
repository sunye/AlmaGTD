package fr.alma.modele.persistence.dao.test;

import java.io.InputStream;
import java.util.Properties;

import fr.alma.modele.noyau.Utilisateur;
import fr.alma.modele.persistance.DaoFactory;
import fr.alma.modele.persistance.dao.UtilisateurDao;
import fr.alma.modele.persistance.dao.impl.AbstractDao;

public class TestUtilisateurDAO{
	private UtilisateurDao dao;
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
		dao = DaoFactory.createUtilisateurDao();
		dao.supprimerTout();
	}
	public void creerUtilisateur(){
		Utilisateur u = new Utilisateur("testlogin", new String("testpwd").toCharArray(), "testlogin@user.com");
		dao.creer(u);
	}
	public void tousUtilisateur(){
		for(Utilisateur u: dao.recupererTout()){
			System.out.println(u);
		}
	}
	public void existeUtilisateur(){
		System.out.println(dao.trouverUtilisateur("testlogin"));
		System.out.println(dao.trouverUtilisateur("testlogint"));
		System.out.println(dao.trouverUtilisateur("testlogin",new String("testpwd").toCharArray()));
		System.out.println(dao.trouverUtilisateur("testlogin",new String("testprwd").toCharArray()));
	}
}
