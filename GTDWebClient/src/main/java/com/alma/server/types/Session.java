package com.alma.server.types;

import java.io.Serializable;
import java.util.UUID;

import org.loststone.toodledo.util.AuthToken;

public class Session implements Serializable {
	
	private static final long serialVersionUID = 2414348783814937539L;

	private String idSession;
	
	private String loginGTD;

	private String passwordGTD;
	private String keyGTD;

	private String loginToodleDo;
	private String passwordToodleDo;
	private AuthToken tokenToodleDo;

	public Session() {
		this.idSession = UUID.randomUUID().toString();
	}
	
	public Session(String loginGTD, String passwordGTD, String loginToodleDo,
			String passwordToodleDo) {
		this.idSession = UUID.randomUUID().toString();
		this.loginGTD = loginGTD;
		this.passwordGTD = passwordGTD;
		this.loginToodleDo = loginToodleDo;
		this.passwordToodleDo = passwordToodleDo;
	}

	public String getIdSession() {
		return idSession;
	}

	public void setIdSession(String idSession) {
		this.idSession = idSession;
	}

	public String getLoginGTD() {
		return loginGTD;
	}

	public void setLoginGTD(String loginGTD) {
		this.loginGTD = loginGTD;
	}

	public String getPasswordGTD() {
		return passwordGTD;
	}

	public void setPasswordGTD(String passwordGTD) {
		this.passwordGTD = passwordGTD;
	}

	public String getKeyGTD() {
		return keyGTD;
	}

	public void setKeyGTD(String keyGTD) {
		this.keyGTD = keyGTD;
	}

	public String getLoginToodleDo() {
		return loginToodleDo;
	}

	public void setLoginToodleDo(String loginToodleDo) {
		this.loginToodleDo = loginToodleDo;
	}

	public String getPasswordToodleDo() {
		return passwordToodleDo;
	}

	public void setPasswordToodleDo(String passwordToodleDo) {
		this.passwordToodleDo = passwordToodleDo;
	}

	public AuthToken getTokenToodleDo() {
		return tokenToodleDo;
	}

	public void setTokenToodleDo(AuthToken keyToodleDo) {
		this.tokenToodleDo = keyToodleDo;
	}

}
