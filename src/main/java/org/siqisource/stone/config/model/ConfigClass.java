package org.siqisource.stone.config.model;

import java.util.List;

public class ConfigClass {

	/* 类名 */
	private java.lang.String code;

	/* 标签 */
	private java.lang.String label;

	/* 说明 */
	private java.lang.String comment;
	
	/*序号*/
	private  java.lang.Integer sortNo;

	private List<Config> configList;

	public java.lang.String getCode() {
		return this.code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getLabel() {
		return this.label;
	}

	public void setLabel(java.lang.String label) {
		this.label = label;
	}

	public java.lang.String getComment() {
		return this.comment;
	}

	public void setComment(java.lang.String comment) {
		this.comment = comment;
	}
	
	public java.lang.Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(java.lang.Integer sortNo) {
		this.sortNo = sortNo;
	}

	public List<Config> getConfigList() {
		return configList;
	}

	public void setConfigList(List<Config> configList) {
		this.configList = configList;
	}
	
}
