package fr.alma.modele.persistance;

import fr.alma.modele.persistance.dao.*;
import fr.alma.modele.persistance.dao.impl.*;

/**
 * Classe DaoFactory, factory permettant de gérer les DAO
 * @version 1.0
 * @author Université de Nantes
 */
public class DaoFactory {

	/**
	 * Creer un utilisateur DAO
	 * @return l'utilisateur DAO
	 */
	public static UtilisateurDao createUtilisateurDao(){
		return new UtilisateurDaoImpl();
	}

	/**
	 * Creer un projet DAO
	 * @return le projet DAO
	 */
	public static ProjetDao createProjetDao(){
		return new ProjetDaoImpl();
	}

	/**
	 * Creer une tache DAO
	 * @return la tache DAO
	 */
	public static TacheDao createTacheDao(){
		return new TacheDaoImpl();
	}

	/**
	 * Creer un contact DAO
	 * @return le contact DAO
	 */
	public static ContactDao createContactDao(){
		return new ContactDaoImpl();
	}
}
