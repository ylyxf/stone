package org.siqisource.stone.config.model;

public class ConfigEntity {

	/* 类名 */
	private java.lang.String classCode;

	/* 类的属性名 */
	private java.lang.String code;

	/* label */
	private java.lang.String label;

	/* 值 */
	private java.lang.String value;

	/* 说明 */
	private java.lang.String comment;

	/* 排序 */
	private java.lang.Integer sortNo;
	
	private Boolean encode = false;
	
	public java.lang.String getClassCode() {
		return classCode;
	}

	public void setClassCode(java.lang.String classCode) {
		this.classCode = classCode;
	}

	public java.lang.String getCode() {
		return code;
	}

	public void setCode(java.lang.String code) {
		this.code = code;
	}

	public java.lang.String getLabel() {
		return label;
	}

	public void setLabel(java.lang.String label) {
		this.label = label;
	}

	public java.lang.String getValue() {
		return value;
	}

	public void setValue(java.lang.String value) {
		this.value = value;
	}

	public java.lang.String getComment() {
		return comment;
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

	public Boolean getEncode() {
		return encode;
	}

	public void setEncode(Boolean encode) {
		this.encode = encode;
	}
	
}
