package org.siqisource.stone.security.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.user.model.User;
import org.siqisource.stone.user.service.UserService;
import org.siqisource.stone.web.jspservice.JspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@JspService("securityService")
public class SecurityService {

	@Autowired
	UserService userService;

	public User getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		String userName = (String) subject.getPrincipal();
		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("account", userName);
		return userService.readOne(condition);
	}

	public static String getCurrentAccout() {
		Subject subject = SecurityUtils.getSubject();
		return (String) subject.getPrincipal();
	}
}
