package org.siqisource.stone.permission.service;

import java.util.ArrayList;
import java.util.List;

import org.siqisource.stone.permission.model.PermissionClassEntity;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

	private static List<PermissionClassEntity> PERMISSION_CLASS_ENTITY_LIST = new ArrayList<PermissionClassEntity>();

	public void addPermissionEntity(PermissionClassEntity permissionClassEntity) {
		PERMISSION_CLASS_ENTITY_LIST.add(permissionClassEntity);
	}

	public List<PermissionClassEntity> getPermissionEntityList() {
		return PERMISSION_CLASS_ENTITY_LIST;
	}
}
