package org.siqisource.stone.datafilter.mapper;

import org.siqisource.stone.datafilter.model.DataFilter;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface DataFilterMapper extends SingleKeyMapper<DataFilter,Integer>{

}
