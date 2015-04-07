package org.siqisource.stone.role.service;

import org.siqisource.stone.role.mapper.RoleMapper;
import org.siqisource.stone.role.model.Role;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractService<Role>   {

	@Autowired
	RoleMapper mapper;

	@Override
	protected MybatisMapper<Role> getMapper() {
		return this.mapper;
	}
}	
