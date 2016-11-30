package org.siqisource.stone.runtime.model;

import java.util.UUID;

public class TreeNodeModel extends GeneralModel {

	/** 叶子编码 */
	protected String code;

	/** 叶子名称 */
	protected String name;

	/** 父群组id */
	protected UUID parentId;

	/** 是否叶子节点 */
	protected Boolean isLeaf;

	/** 是否叶子节点 */
	protected String idPath;

	/** 叶子类型，一棵树上不同果实的指针 */
	protected String type;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getParentId() {
		return parentId;
	}

	public void setParentId(UUID parentId) {
		this.parentId = parentId;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdPath() {
		return idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}

}
