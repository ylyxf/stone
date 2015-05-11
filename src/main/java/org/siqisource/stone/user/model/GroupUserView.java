package org.siqisource.stone.user.model;

import org.siqisource.stone.group.model.Group;

public class GroupUserView extends Group {

	/* 用户名 */
	private String userName;

	/* 群组名 */
	private String groupName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
}