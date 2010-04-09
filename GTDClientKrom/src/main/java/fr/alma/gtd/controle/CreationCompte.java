/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.alma.gtd.controle;

import fr.alma.gtd.dao.impl.ContexteDaoImpl;
import fr.alma.gtd.dao.impl.ProjetDaoImpl;
import fr.alma.gtd.entities.Compte;
import fr.alma.gtd.entities.Contexte;
import fr.alma.gtd.entities.Projet;
import fr.alma.gtd.ihm.gestionComptes.LoginInfo;
import java.util.Observable;

/**
 *
 * @author Fusio
 */
public class CreationCompte extends Observable implements Runnable {

	private LoginInfo loginInfo;

	public CreationCompte(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	@Override
	public void run() {
		GestionCompte.setDaoProperty(loginInfo.getLogin());
		GestionCompte gestion = new GestionCompte(loginInfo.getLogin());
		if (!gestion.existeCompte()) {
			this.setChanged();

			Compte compte = new Compte(loginInfo.getLogin(), Hashage.hasher(loginInfo.getMdp()));
			gestion.getDao().create(compte);

			Projet projetRacine = new Projet(false, "<Projet global>", null);
			new ProjetDaoImpl().create(projetRacine);

			Contexte aucunContexte = new Contexte("<Aucun contexte>");
			new ContexteDaoImpl().create(aucunContexte);
		}
		this.notifyObservers(loginInfo);
	}

}
