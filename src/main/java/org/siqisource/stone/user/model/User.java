package org.siqisource.stone.user.model;

import java.util.List;


public class User {

	/** 用户Id */
	private java.lang.Integer id;

	/** 账户 */
	private java.lang.String account;

	/** 密码 */
	private java.lang.String password;

	/** 用户名 */
	private java.lang.String name;

	/** 手机号码 */
	private java.lang.String phone;

	/** 电子邮箱 */
	private java.lang.String email;

	/** 是否可用 */
	private java.lang.Boolean enabled;

	/** 是否删除 */
	private java.lang.Boolean logicDeleted;

	/** 序号 */
	private Integer sortNo;
	
	/** 用户所在的所有群组 */
	private List<GroupUserView> groupUserList;


	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getAccount() {
		return this.account;
	}

	public void setAccount(java.lang.String account) {
		this.account = account;
	}

	public java.lang.String getPassword() {
		return this.password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getPhone() {
		return this.phone;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	public java.lang.String getEmail() {
		return this.email;
	}

	public void setEmail(java.lang.String email) {
		this.email = email;
	}

	public java.lang.Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(java.lang.Boolean enabled) {
		this.enabled = enabled;
	}

	public java.lang.Boolean getLogicDeleted() {
		return logicDeleted;
	}

	public void setLogicDeleted(java.lang.Boolean logicDeleted) {
		this.logicDeleted = logicDeleted;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public List<GroupUserView> getGroupUserList() {
		return groupUserList;
	}

	public void setGroupUserList(List<GroupUserView> groupUserList) {
		this.groupUserList = groupUserList;
	}

}
