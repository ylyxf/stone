package org.siqisource.stone.group.service;

import java.util.ArrayList;
import java.util.List;

import org.siqisource.stone.group.mapper.GroupMapper;
import org.siqisource.stone.group.model.Group;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.orm.PartitiveFields;
import org.siqisource.stone.orm.SqlKey;
import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.service.AbstractService;
import org.siqisource.stone.ui.easyui.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService extends AbstractService<Group> {

	@Autowired
	GroupMapper mapper;

	@Override
	protected MybatisMapper<Group> getMapper() {
		return this.mapper;
	}

	public TreeNode toTreeNode(Group group) {
		TreeNode treeNode = new TreeNode();
		treeNode.setId(String.valueOf(group.getId()));
		treeNode.setText(group.getName());

		if (!group.getIsLeaf()) {
			treeNode.setState(TreeNode.STATA_CLOSE);
		} else {
			treeNode.setState(TreeNode.STATA_OPEN);
		}
		return treeNode;
	}

	public List<TreeNode> listTreeNode(Condition condition) {
		List<Group> groupList = this.list(condition);
		List<TreeNode> result = new ArrayList<TreeNode>();
		for (Group group : groupList) {
			TreeNode treeNode = toTreeNode(group);
			result.add(treeNode);
		}
		return result;
	}

	@Override
	@Transactional
	public void insert(Group group) {
		super.insert(group);
		int parentId = group.getParentId();
		PartitiveFields fields = new PartitiveFields();
		fields.put("isLeaf", false);
		this.updatePartitive(fields, parentId);
	}
	
	@Override
	@Transactional
	public void logicDelete(Object... id) {
		super.logicDelete(id);

		Group group = this.read(id);
		int parentId = group.getParentId();

		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("parentId", parentId);
		condition.andNotEqual(SqlKey.LOGIC_DELETED, true);
		if (this.count(condition) == 0) {
			PartitiveFields fields = new PartitiveFields();
			fields.put("isLeaf", true);
			this.updatePartitive(fields, parentId);
		}
	}
}
