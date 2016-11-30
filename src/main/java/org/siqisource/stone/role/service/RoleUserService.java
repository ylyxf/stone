package org.siqisource.stone.role.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.siqisource.stone.role.mapper.RoleUserMapper;
import org.siqisource.stone.role.model.RoleUser;
import org.siqisource.stone.role.model.RoleUserView;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.siqisource.stone.runtime.mapper.condition.Condition;
import org.siqisource.stone.runtime.service.AbstractSingleKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleUserService extends AbstractSingleKeyService<RoleUser,Integer> {

	@Autowired
	RoleUserMapper mapper;

	@Override
	protected SingleKeyMapper<RoleUser,Integer> getMapper() {
		return this.mapper;
	}

	public Integer countRoleUser(Condition condition) {
		return this.mapper.countRoleUser(condition);
	}

	public List<RoleUserView> listRoleUser(Condition condition, RowBounds rowBounds) {
		return this.mapper.listRoleUser(condition, rowBounds);
	}
}
