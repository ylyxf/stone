package org.siqisource.stone.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.siqisource.stone.runtime.mapper.condition.Condition;
import org.siqisource.stone.user.model.GroupUser;
import org.siqisource.stone.user.model.GroupUserView;
import org.siqisource.stone.user.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupUserMapper extends SingleKeyMapper<GroupUser,Integer> {

	public List<User> listUserByGroupId(
			@Param("groupId") Integer groupId,
			@Param("condition") Condition conditon);

	public List<GroupUserView> listGroupUserByUserId(@Param("userId") Integer userId);
}
