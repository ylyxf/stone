package org.siqisource.stone.user.model;

import java.util.List;

import org.siqisource.stone.runtime.mapper.annotation.Table;

@Table("st_user")
public class User {

	/** 用户Id */
	private Integer id;

	/** 账户 */
	private String account;

	/** 密码 */
	private String password;

	/** 用户名 */
	private String name;

	/** 类型 */
	private String type;

	/** 电话号码 */
	private String phone;

	/** 手机号码 */
	private String mobilePhone;

	/** 电子邮箱 */
	private String email;

	/** 是否可用 */
	private Boolean enabled;

	/** 是否删除 */
	private Boolean logicDeleted;

	/** 序号 */
	private Integer sortNo;

	/** 用户所在的所有群组 */
	private List<GroupUserView> groupUserList;
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getLogicDeleted() {
		return logicDeleted;
	}

	public void setLogicDeleted(Boolean logicDeleted) {
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

	@Override
	public String toString() {
		return this.name;
	}

}
