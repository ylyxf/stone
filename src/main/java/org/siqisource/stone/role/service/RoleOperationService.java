package org.siqisource.stone.role.service;

import java.util.List;

import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.permission.model.PermissionClassEntity;
import org.siqisource.stone.permission.model.PermissionEntity;
import org.siqisource.stone.permission.service.PermissionService;
import org.siqisource.stone.role.mapper.RoleOperationMapper;
import org.siqisource.stone.role.model.RoleOperation;
import org.siqisource.stone.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleOperationService extends AbstractService<RoleOperation> {

	@Autowired
	RoleOperationMapper mapper;

	@Autowired
	PermissionService permissionService;

	@Override
	protected MybatisMapper<RoleOperation> getMapper() {
		return this.mapper;
	}

	public List<PermissionClassEntity> getPermissionEntityList(Integer roleId) {
		List<PermissionClassEntity> result = permissionService
				.getPermissionEntityList();
		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("roleId", roleId);
		List<RoleOperation> roleOperationList = this.list(condition);
		for (PermissionClassEntity permissionClassEntity : result) {
			for (PermissionEntity permissionEntity : permissionClassEntity
					.getPermissionEntityList()) {
				for (RoleOperation roleOperation : roleOperationList) {
					if (permissionEntity.getValue().equals(
							roleOperation.getOperationCode())) {
						permissionEntity.setEnabled(true);
					}
				}
			}
		}
		return result;
	}

	public void addOperationToRole(Integer roleId, String operationCode) {
		RoleOperation roleOperation = new RoleOperation();
		roleOperation.setRoleId(roleId);
		roleOperation.setOperationCode(operationCode);
		this.insert(roleOperation);
	}

	public void removeOperationFromRole(Integer roleId, String operationCode) {
		SimpleCondition simpleCondition = new SimpleCondition();
		simpleCondition.andEqual("roleId", roleId);
		simpleCondition.andEqual("operationCode", operationCode);
		this.deleteBatch(simpleCondition);
	}

	public List<RoleOperation> listOperation(Integer userId,
			String operationCode){
		return this.mapper.listOperation(userId, operationCode);
	}
}
