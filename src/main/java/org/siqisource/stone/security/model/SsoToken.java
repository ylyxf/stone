package org.siqisource.stone.security.model;

import org.apache.shiro.authc.AuthenticationToken;

public class SsoToken implements AuthenticationToken {
	
	private static final long serialVersionUID = 4390134120240537601L;

	public SsoToken(String account) {
		super();
		this.account = account;
	}

	private String account;
	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public Object getPrincipal() {
		return this.account;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

}
