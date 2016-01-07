package org.siqisource.stone.tree.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.siqisource.stone.sort.service.SortService;
import org.siqisource.stone.tree.mapper.TreeMapper;
import org.siqisource.stone.tree.model.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreeService {

	private static final int SORT_STEP = 1000;

	@Autowired
	TreeMapper mapper;

	@Autowired
	SortService service;

	public Map<Integer, Integer> copy(Integer nodeId, String tableName) {
		return new HashMap<Integer, Integer>();
	}

	public List<TreeNode> listChildren(Integer nodeId, String tableName) {
		return this.mapper.listChildren(nodeId, tableName);
	}

	public List<TreeNode> listAllChildren(Integer nodeId, String tableName) {
		List<TreeNode> result = new ArrayList<TreeNode>();
		List<TreeNode> children = this.listChildren(nodeId, tableName);
		for (TreeNode treeNode : children) {
			result.addAll(this.listAllChildren(treeNode.getId(), tableName));
		}
		result.addAll(children);
		return result;
	}

	public void deleteAllChildren(Integer nodeId, String tableName) {
		List<TreeNode> allChildren = listAllChildren(nodeId, tableName);
		Collections.sort(allChildren, new LevelComparator());
		for (TreeNode node : allChildren) {
			this.mapper.delete(node.getId(), tableName);
		}
	}

	public List<TreeNode> listForefather(Integer nodeId, String tableName) {
		List<TreeNode> result = new ArrayList<TreeNode>();
		TreeNode parentNode = this.mapper.readParentNode(nodeId, tableName);
		while (parentNode != null) {
			result.add(parentNode);
			parentNode = this.mapper.readParentNode(parentNode.getId(),
					tableName);
		}
		return result;
	}

	public void removeNode(Integer nodeId, String tableName) {
		TreeNode treeNode = this.mapper.readNode(nodeId, tableName);
		Integer parentNodeId = treeNode.getParentId();
		TreeNode parentNode = this.mapper.readNode(parentNodeId, tableName);
		if (parentNode != null && this.countChild(parentNodeId, tableName) == 0) {
			this.mapper.updateIsLeaf(parentNodeId, true, tableName);
		}
		// 删除所有子节点
		deleteAllChildren(nodeId, tableName);
	}

	public void addNode(TreeNode node, String tableName) {
		this.addNode(node, null, tableName);
	}

	/**
	 * 
	 * @param node
	 * @param beforeNodeId
	 */
	public void addNode(TreeNode node, Integer beforeNodeId, String tableName) {
		Integer parentNodeId = node.getParentId();
		Integer nodeId = node.getId();
		// 设置sortNo
		int sortNo = 0;
		if (beforeNodeId != null) {// 找到前一个节点的sortNo,再加step
			TreeNode beforeNode = this.mapper.readNode(beforeNodeId, tableName);
			Integer beforeSortNo = beforeNode.getSortNo();
			this.mapper.updateOldBrotherSortlNo(parentNodeId, SORT_STEP,
					beforeSortNo, tableName);
			sortNo = beforeSortNo + SORT_STEP;
		} else {// 找到最大的节点，并加一
			sortNo = this.readChildrenMaxSortNo(parentNodeId, tableName) + 1;
		}
		this.mapper.updateSortNo(nodeId, sortNo, tableName);
		// 设置levelNo
		int levelNo = 0;
		TreeNode parentNode = this.mapper.readNode(parentNodeId, tableName);
		if (parentNode != null) {
			levelNo = parentNode.getLevelNo() + 1;
		}
		this.mapper.updateLevelNo(nodeId, levelNo, tableName);
		// 设置leaf
		Boolean isLeaf = true;
		Boolean children = this.countChild(node.getId(), tableName) > 0 ? true
				: false;
		isLeaf = !children;
		this.mapper.updateIsLeaf(nodeId, isLeaf, tableName);
		// 设置父节点的isLeaf
		if (parentNode != null) {
			this.mapper.updateIsLeaf(parentNodeId, false, tableName);
		}
		// 设置子节点的LevelNo
		if (children) {
			this.mapper.updateChildrenLevelNo(levelNo, levelNo + 1, tableName);
		}
	}

	public Integer countChild(Integer nodeId, String tableName) {
		Integer count = this.mapper.countChild(nodeId, tableName);
		return count == null ? 0 : count;
	}

	public Integer readChildrenMaxSortNo(Integer parentId, String tableName) {
		Integer sortNo = this.mapper.readChildrenMaxSortNo(parentId, tableName);
		return sortNo == null ? 0 : sortNo;
	}

	public class LevelComparator implements Comparator<TreeNode> {
		public int compare(TreeNode node1, TreeNode node2) {
			return (node1.getLevelNo() > node2.getLevelNo()) ? 1 : -1;
		}
	}

}
