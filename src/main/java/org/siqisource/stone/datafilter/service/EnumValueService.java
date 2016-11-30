package org.siqisource.stone.datafilter.service;

import org.siqisource.stone.datafilter.mapper.EnumValueMapper;
import org.siqisource.stone.datafilter.model.EnumValue;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.siqisource.stone.runtime.service.AbstractSingleKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnumValueService extends AbstractSingleKeyService<EnumValue,Integer>   {

	@Autowired
	EnumValueMapper mapper;

	@Override
	protected SingleKeyMapper<EnumValue,Integer> getMapper() {
		return this.mapper;
	}
}	
