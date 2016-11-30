package org.siqisource.stone.role.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.siqisource.stone.role.model.RoleOperation;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleOperationMapper extends SingleKeyMapper<RoleOperation,Integer>{

	public List<RoleOperation> listOperation(@Param("userId")Integer userId,@Param("operationCode")String operationCode);
}
