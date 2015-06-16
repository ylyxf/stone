package org.siqisource.stone.datafilter.service;

import org.siqisource.stone.datafilter.mapper.EnumValueMapper;
import org.siqisource.stone.datafilter.model.EnumValue;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnumValueService extends AbstractService<EnumValue>   {

	@Autowired
	EnumValueMapper mapper;

	@Override
	protected MybatisMapper<EnumValue> getMapper() {
		return this.mapper;
	}
}	
