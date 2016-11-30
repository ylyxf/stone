package org.siqisource.stone.role.service;

import org.siqisource.stone.role.mapper.RoleMapper;
import org.siqisource.stone.role.model.Role;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.siqisource.stone.runtime.service.AbstractSingleKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractSingleKeyService<Role,Integer>   {

	@Autowired
	RoleMapper mapper;

	@Override
	protected SingleKeyMapper<Role,Integer> getMapper() {
		return this.mapper;
	}
}	
