package org.siqisource.stone.dict.service;

import org.siqisource.stone.dict.mapper.DictMapper;
import org.siqisource.stone.dict.model.Dict;
import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DictService extends AbstractService<Dict> {

	@Autowired
	DictMapper mapper;

	@Autowired
	DictItemService dictItemService;

	@Override
	protected MybatisMapper<Dict> getMapper() {
		return this.mapper;
	}

	@Override
	public void insert(Dict dict) {
		if (this.read(dict.getCode()) != null) {
			throw new BusinessException("字典编码" + dict.getCode() + "已存在，不能重复添加");
		}
		getMapper().insert(dict);
	}

	@Override
	@Transactional
	public void deleteBatch(Object[] idList) {
		super.deleteBatch(idList);
		for (Object code : idList) {
			SimpleCondition condition = new SimpleCondition();
			condition.andEqual("dictCode", code);
			dictItemService.deleteBatch(condition);
		}
	}
}
