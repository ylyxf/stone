package org.siqisource.stone.dict.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.siqisource.stone.dict.mapper.DictItemMapper;
import org.siqisource.stone.dict.model.DictItem;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.service.AbstractService;
import org.siqisource.stone.web.jspservice.JspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@JspService
public class DictItemService extends AbstractService<DictItem> {

	@Autowired
	DictItemMapper mapper;

	@Override
	protected MybatisMapper<DictItem> getMapper() {
		return this.mapper;
	}

	private Map<String, Dictable> dictableServiceMap = new HashMap<String, Dictable>();

	protected void addDict(Dictable dictableService) {
		this.dictableServiceMap.put(dictableService.getDictName(),
				dictableService);
	}

	public List<DictItem> getDictItemList(String dictCode) {
		return this.getDictItemList(dictCode, null);
	}

	public List<DictItem> getDictItemList(String dictCode, Map<String,Object> dictParams) {
		Dictable dictable = dictableServiceMap.get(dictCode);
		List<DictItem> dictItemList = null;
		if (dictable != null) {
			dictItemList = dictable.getDictItemList(dictParams);
		} else {
			SimpleCondition condition = new SimpleCondition();
			condition.andEqual("dictCode", dictCode);
			dictItemList = this.list(condition);
		}
		
		if(dictItemList == null){
			return new ArrayList<DictItem>(0);
		}
		String filter = (String)dictParams.get("_filter");
		if (StringUtils.isNotBlank(filter)) {

			List<String> dictCodes = Arrays.asList(filter.split(","));
			Iterator<DictItem> iterator = dictItemList.iterator();
			while (iterator.hasNext()) {
				if (dictCodes.contains(iterator.next().getValue())) {
					iterator.remove();
				}
			}
		}

		return dictItemList;
	}
}
