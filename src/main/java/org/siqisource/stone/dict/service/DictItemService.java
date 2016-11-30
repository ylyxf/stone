package org.siqisource.stone.dict.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.siqisource.stone.dict.mapper.DictItemMapper;
import org.siqisource.stone.dict.model.DictItem;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.siqisource.stone.runtime.mapper.condition.SimpleCondition;
import org.siqisource.stone.runtime.service.AbstractSingleKeyService;
import org.siqisource.stone.runtime.web.jspservice.JspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@JspService
public class DictItemService extends AbstractSingleKeyService<DictItem,Integer> {

	@Autowired
	DictItemMapper mapper;

	@Override
	protected SingleKeyMapper<DictItem,Integer> getMapper() {
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
