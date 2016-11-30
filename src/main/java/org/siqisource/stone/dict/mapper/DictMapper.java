package org.siqisource.stone.dict.mapper;

import org.siqisource.stone.dict.model.Dict;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface DictMapper extends SingleKeyMapper<Dict, String> {

}
