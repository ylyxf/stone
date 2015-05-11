package org.siqisource.stone.user.service;

import java.util.ArrayList;
import java.util.List;

import org.siqisource.stone.group.model.Group;
import org.siqisource.stone.group.service.GroupService;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.orm.SqlKey;
import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.service.AbstractService;
import org.siqisource.stone.ui.easyui.TreeNode;
import org.siqisource.stone.user.mapper.GroupUserMapper;
import org.siqisource.stone.user.model.GroupUser;
import org.siqisource.stone.user.model.GroupUserView;
import org.siqisource.stone.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupUserService extends AbstractService<GroupUser> {

	@Autowired
	GroupUserMapper mapper;

	@Autowired
	GroupService groupService;

	@Override
	protected MybatisMapper<GroupUser> getMapper() {
		return this.mapper;
	}

	public List<User> listUserByGroupId(Integer groupId, Condition conditon) {
		return this.mapper.listUserByGroupId(groupId, conditon);
	}

	public List<GroupUserView> listGroupUserByUserId(Integer userId) {
		return this.mapper.listGroupUserByUserId(userId);
	}

	public List<TreeNode> listGroupUserTreeNode(Condition condition,
			Integer groupId) {
		List<Group> groupList = groupService.list(condition);
		List<TreeNode> result = new ArrayList<TreeNode>();
		for (Group group : groupList) {
			TreeNode treeNode = toTreeNode(group);
			result.add(treeNode);
		}

		SimpleCondition userCondition = new SimpleCondition();
		userCondition.andNotEqual(SqlKey.LOGIC_DELETED, true);
		userCondition.orderAsc("sortNo");
		List<User> userList = this.listUserByGroupId(groupId, userCondition);
		for (User user : userList) {
			TreeNode treeNode = toTreeNode(user);
			result.add(treeNode);
		}
		
		return result;
	}

	private TreeNode toTreeNode(User user) {
		TreeNode treeNode = new TreeNode();
		treeNode.setId("u_" + user.getId());
		treeNode.setText(user.getName());
		treeNode.setState(TreeNode.STATA_OPEN);
		treeNode.setIconCls("icon-man");
		return treeNode;
	}

	private TreeNode toTreeNode(Group group) {
		TreeNode treeNode = new TreeNode();
		treeNode.setId("g_" + group.getId());
		treeNode.setText(group.getName());

		if (!group.getIsLeaf()) {
			treeNode.setState(TreeNode.STATA_CLOSE);
		} else {
			SimpleCondition condition = new SimpleCondition();
			condition.andEqual("groupId", group.getId());
			if (this.count(condition) > 0) {
				treeNode.setState(TreeNode.STATA_CLOSE);
			} else {
				treeNode.setState(TreeNode.STATA_OPEN);
			}
		}
		return treeNode;
	}

}
