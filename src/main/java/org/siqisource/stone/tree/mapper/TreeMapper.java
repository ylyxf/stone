package org.siqisource.stone.tree.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.siqisource.stone.tree.model.TreeNode;
import org.springframework.stereotype.Repository;

@Repository
public interface TreeMapper {

	public TreeNode readNode(@Param("nodeId") Integer nodeId,
			@Param("tableName") String tableName);

	public Integer countChild(@Param("nodeId") Integer nodeId,
			@Param("tableName") String tableName);

	public List<TreeNode> listChildren(@Param("nodeId") Integer nodeId,
			@Param("tableName") String tableName);

	public TreeNode readParentNode(@Param("nodeId") Integer nodeId,
			@Param("tableName") String tableName);

	public Integer readChildrenMaxSortNo(@Param("parentId") Integer parentId,
			@Param("tableName") String tableName);

	public void updateOldBrotherSortlNo(@Param("parentId") Integer parentId,
			@Param("step") Integer step, @Param("sortNo") Integer sortNo,
			@Param("tableName") String tableName);

	public void updateSortNo(@Param("nodeId") Integer nodeId,
			@Param("sortNo") Integer sortNo,
			@Param("tableName") String tableName);

	public void updateLevelNo(@Param("nodeId") Integer nodeId,
			@Param("levelNo") Integer levelNo,
			@Param("tableName") String tableName);

	public void updateIsLeaf(@Param("nodeId") Integer nodeId,
			@Param("isLeaf") Boolean isLeaf,
			@Param("tableName") String tableName);

	public void updateChildrenLevelNo(@Param("parentId") Integer parentId,
			@Param("levelNo") Integer levelNo,
			@Param("tableName") String tableName);

	public void delete(@Param("nodeId") Integer nodeId,
			@Param("tableName") String tableName);

}
