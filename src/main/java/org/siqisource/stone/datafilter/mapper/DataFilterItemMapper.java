package org.siqisource.stone.datafilter.mapper;

import org.siqisource.stone.datafilter.model.DataFilterItem;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface DataFilterItemMapper extends SingleKeyMapper<DataFilterItem,Integer>{

}
