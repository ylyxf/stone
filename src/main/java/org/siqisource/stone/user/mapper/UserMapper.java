package org.siqisource.stone.user.mapper;

import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.siqisource.stone.user.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends SingleKeyMapper<User, Integer> {

	public int countByPhoneNumber();
}
