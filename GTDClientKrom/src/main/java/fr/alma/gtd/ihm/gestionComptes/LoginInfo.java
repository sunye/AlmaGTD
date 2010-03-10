/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.alma.gtd.ihm.gestionComptes;

/**
 *
 * @author E023963X
 */
public class LoginInfo {

	private String login;
	private String mdp;
	private String mdpConfirme;
	private boolean annule;

	public LoginInfo() {
	}

	public LoginInfo(String login, String mdp) {
		this.login = login;
		this.mdp = mdp;
		this.annule = false;
	}

	public LoginInfo(String login, String mdp, String mdpConfirme) {
		this.login = login;
		this.mdp = mdp;
		this.mdpConfirme = mdpConfirme;
		this.annule = false;
	}

	public String getLogin() {
		return login;
	}

	public String getMdp() {
		return mdp;
	}

	public String getMdpConfirme() {
		return mdpConfirme;
	}

	public boolean estAnnule() {
		return annule;
	}

	public void setAnnule(boolean annule) {
		this.annule = annule;
	}

	public boolean loginRenseigne() {
		if (this.getLogin() != null) {
			return !this.getLogin().isEmpty();
		} else {
			return false;
		}
	}

	public boolean compareMdp() {
		return this.mdp.equals(this.mdpConfirme);
	}

}
