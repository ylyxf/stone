package org.siqisource.stone.role.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.siqisource.stone.role.model.RoleUser;
import org.siqisource.stone.role.model.RoleUserView;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.siqisource.stone.runtime.mapper.condition.Condition;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleUserMapper extends SingleKeyMapper<RoleUser, Integer> {

	public Integer countRoleUser(@Param("condition") Condition condition);

	public List<RoleUserView> listRoleUser(@Param("condition") Condition condition,
			@Param("rowBounds") RowBounds rowBounds);

}
