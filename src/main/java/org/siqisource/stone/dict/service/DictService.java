package org.siqisource.stone.dict.service;

import org.siqisource.stone.dict.mapper.DictMapper;
import org.siqisource.stone.dict.model.Dict;
import org.siqisource.stone.runtime.exceptions.BusinessException;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.siqisource.stone.runtime.mapper.condition.SimpleCondition;
import org.siqisource.stone.runtime.service.AbstractSingleKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DictService extends AbstractSingleKeyService<Dict,String> {

	@Autowired
	DictMapper mapper;

	@Autowired
	DictItemService dictItemService;

	@Override
	protected SingleKeyMapper<Dict,String> getMapper() {
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
	public void deleteBatch(String[] idList) {
		super.deleteBatch(idList);
		for (Object code : idList) {
			SimpleCondition condition = new SimpleCondition();
			condition.andEqual("dictCode", code);
			dictItemService.delete(condition);
		}
	}

}
