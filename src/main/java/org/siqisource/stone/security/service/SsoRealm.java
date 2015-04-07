package org.siqisource.stone.security.service;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.siqisource.stone.security.model.SsoToken;

public class SsoRealm extends AuthenticatingRealm {

	public SsoRealm() {
		super();
		setCredentialsMatcher(new AllowAllCredentialsMatcher());
		setAuthenticationTokenClass(SsoToken.class);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String account = (String) token.getPrincipal();
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account,
				null, account);
		return info;
	}

}
