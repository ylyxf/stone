package org.siqisource.stone.user.service;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.orm.PartitiveFields;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.service.AbstractService;
import org.siqisource.stone.user.mapper.UserMapper;
import org.siqisource.stone.user.model.GroupUser;
import org.siqisource.stone.user.model.GroupUserView;
import org.siqisource.stone.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends AbstractService<User> {

	@Autowired
	UserMapper mapper;

	@Autowired
	GroupUserService groupUserService;

	@Override
	protected MybatisMapper<User> getMapper() {
		return this.mapper;
	}

	public User currentUser() {
		Subject subject = SecurityUtils.getSubject();
		Object principal = subject.getPrincipal();
		if (principal instanceof User) {
			return (User) principal;
		} else {
			String userName = (String) subject.getPrincipal();
			return this.readUserByAccount(userName);
		}
	}

	public User readUserByAccount(String account) {
		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("account", account);
		return this.readOne(condition);
	}

	public User readUser(Integer userId) {
		User user = this.read(userId);
		List<GroupUserView> groupUserList = groupUserService
				.listGroupUserByUserId(userId);
		user.setGroupUserList(groupUserList);
		return user;
	}

	@Transactional
	public void updateUser(User user, Integer defualtGroupId) {
		// 更新User信息
		checkUser(user);
		this.update(user);
		// 清空默认部门字段
		PartitiveFields clearDefaultFields = new PartitiveFields();
		clearDefaultFields.put("isDefault", false);
		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("userId", user.getId());
		groupUserService.updateBatch(clearDefaultFields, condition);
		// 更新默认部门字段
		PartitiveFields defaultFields = new PartitiveFields();
		defaultFields.put("isDefault", true);
		SimpleCondition defaultCondition = new SimpleCondition();
		defaultCondition.andEqual("userId", user.getId());
		defaultCondition.andEqual("groupId", defualtGroupId);
		groupUserService.updateBatch(defaultFields, defaultCondition);
	}

	@Transactional
	public void addUser(User user, Integer defualtGroupId) {
		// 更新User信息
		checkUser(user);
		this.insert(user);
		// 增加默认部门
		GroupUser groupUser = new GroupUser();
		groupUser.setGroupId(defualtGroupId);
		groupUser.setUserId(user.getId());
		groupUser.setIsDefault(true);
		groupUserService.insert(groupUser);
	}

	@Transactional
	public void deleteUser(Integer userId) {
		// 删除User信息
		this.logicDelete(userId);
		// 增加默认部门
		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("userId", userId);
		groupUserService.deleteBatch(condition);
	}

	@Transactional
	public void removeUser(Integer userId, Integer groupId) {
		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("userId", userId);
		condition.andEqual("groupId", groupId);
		if (groupUserService.count(condition) == 1) {
			throw new BusinessException("选中的用户仅存在于当前群组，请使用删除功能删除此用户");
		}
		GroupUser groupUser = groupUserService.readOne(condition);
		if (groupUser.getIsDefault()) {
			throw new BusinessException("选中用户的当前群组是默认群组，请将默认群组指定为其它群组后再移除");
		}
		groupUserService.deleteBatch(condition);
	}

	private void checkUser(User user) {
		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("account", user.getAccount());
		if (this.count(condition) >= 1) {
			throw new BusinessException("账号" + user.getAccount() + "已存在");
		}
	}
}
