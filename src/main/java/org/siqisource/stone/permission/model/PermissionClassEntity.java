package org.siqisource.stone.permission.model;

import java.util.List;

public class PermissionClassEntity {
	
	private String name;
	
	private List<PermissionEntity> permissionEntityList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PermissionEntity> getPermissionEntityList() {
		return permissionEntityList;
	}

	public void setPermissionEntityList(List<PermissionEntity> permissionEntityList) {
		this.permissionEntityList = permissionEntityList;
	}
	
}
