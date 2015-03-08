package org.siqisource.stone.config.service;

import java.util.List;

import org.siqisource.stone.config.mapper.ConfigClassMapper;
import org.siqisource.stone.config.model.ConfigClass;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigClassService extends AbstractService<ConfigClass> {

	@Autowired
	ConfigClassMapper mapper;

	@Autowired
	ConfigService configService;

	@Override
	protected MybatisMapper<ConfigClass> getMapper() {
		return this.mapper;
	}

	public List<ConfigClass> listWithConfig(Condition condition) {
		List<ConfigClass> result = this.list(condition);
		for (ConfigClass configClass : result) {
			SimpleCondition subCondition = new SimpleCondition();
			subCondition.andEqual("classCode", configClass.getCode());
			configClass.setConfigList(configService.list(condition));
		}
		return result;
	}
}
