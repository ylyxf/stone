package org.siqisource.stone.permission.service;

import java.util.ArrayList;
import java.util.List;

import org.siqisource.stone.permission.model.PermissionClassEntity;
import org.siqisource.stone.permission.model.PermissionEntity;
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

	public String getPermissionClassName(String permissionCode) {
		for (PermissionClassEntity permissionClassEntity : PERMISSION_CLASS_ENTITY_LIST) {
			List<PermissionEntity> permissionEntityList = permissionClassEntity
					.getPermissionEntityList();
			for(PermissionEntity permissionEntity :permissionEntityList){
				String permissionValue = permissionEntity.getValue();
				if(permissionValue.equals(permissionCode)){
					return permissionClassEntity.getName();
				}
				
			}
		}
		return "";
	}

	public String getPermissionName(String permissionCode) {
		for (PermissionClassEntity permissionClassEntity : PERMISSION_CLASS_ENTITY_LIST) {
			List<PermissionEntity> permissionEntityList = permissionClassEntity
					.getPermissionEntityList();
			for(PermissionEntity permissionEntity :permissionEntityList){
				String permissionValue = permissionEntity.getValue();
				if(permissionValue.equals(permissionCode)){
					return permissionEntity.getName();
				}
				
			}
		}
		return "";
	}
}
