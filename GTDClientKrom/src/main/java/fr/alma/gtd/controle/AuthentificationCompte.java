/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.alma.gtd.controle;

import fr.alma.gtd.ihm.gestionComptes.LoginInfo;
import java.util.Observable;

/**
 *
 * @author Fusio
 */
public class AuthentificationCompte extends Observable implements Runnable {

	private LoginInfo loginInfo;

	public AuthentificationCompte(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	@Override
	public void run() {
		GestionCompte.setDaoProperty(loginInfo.getLogin());
		GestionCompte gestion = new GestionCompte(loginInfo.getLogin());
		this.setChanged();
		if (gestion.existeCompte() && gestion.compteCorrect() && gestion.comparaisonMdp(loginInfo.getMdp())) {
			this.notifyObservers(gestion);
		} else {
			this.notifyObservers();
		}
	}

}
