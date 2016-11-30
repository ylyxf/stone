package org.siqisource.stone.role.mapper;

import org.siqisource.stone.role.model.Role;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper extends SingleKeyMapper<Role,Integer>{

}
