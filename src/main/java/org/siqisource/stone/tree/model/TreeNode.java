package org.siqisource.stone.tree.model;

public interface TreeNode {

	public Integer getId();

	public void setId(Integer id);

	public Integer getParentId();

	public void setParentId(Integer parentId);

	public String getName();

	public void setName(String name);

	public Boolean getIsLeaf();

	public void setIsLeaf(Boolean isLeaf);

	public Integer getLevelNo();

	public void setLevelNo(Integer levelNo);

	public Integer getSortNo();

	public void setSortNo(Integer sortNo);

	public void setType(String type);

	public String getType();

}
