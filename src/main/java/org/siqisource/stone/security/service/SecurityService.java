package org.siqisource.stone.security.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.siqisource.stone.datafilter.service.DataFilterService;
import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.role.model.RoleOperation;
import org.siqisource.stone.role.service.RoleOperationService;
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

	@Autowired
	RoleOperationService roleOperationService;

	@Autowired
	DataFilterService dataFilterService;

	public User getCurrentUser() {
		return userService.currentUser();
	}

	public static String getCurrentAccout() {
		Subject subject = SecurityUtils.getSubject();
		return (String) subject.getPrincipal();
	}

	public SimpleCondition getPermCondition(String permissionCode) {
		SimpleCondition simpleCondition = new SimpleCondition();
		User user = this.getCurrentUser();
		List<RoleOperation> roleOperationList = roleOperationService
				.listOperation(user.getId(), permissionCode);
		for (RoleOperation roleOperation : roleOperationList) {
			Integer dataFilterId = roleOperation.getDataFilterId();
			if (dataFilterId != null && dataFilterId != 0) {
				Condition condition = dataFilterService
						.readAsConditon(dataFilterId);
				simpleCondition.addCondition(condition);
			}
		}
		return simpleCondition;
	}
}
