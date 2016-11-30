package org.siqisource.stone.dict.mapper;

import org.siqisource.stone.dict.model.DictItem;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface DictItemMapper extends SingleKeyMapper<DictItem, Integer> {

}
