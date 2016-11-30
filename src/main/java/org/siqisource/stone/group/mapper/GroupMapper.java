package org.siqisource.stone.group.mapper;

import org.siqisource.stone.group.model.Group;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMapper extends SingleKeyMapper<Group,Integer>{

}
