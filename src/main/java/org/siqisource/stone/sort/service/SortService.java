package org.siqisource.stone.sort.service;

import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.sort.mapper.SortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SortService {

	private static final int SORT_STEP = 10;

	@Autowired
	SortMapper mapper;

	public void move(Integer id, Integer beforeId, SimpleCondition condition,
			String tableName) {
		Integer beforSortNo = 0;
		if (beforeId != null) {
			beforSortNo = this.mapper.readBeforeSortNo(beforeId, tableName);
		}
		this.mapper.updateOldBrotherSortlNo(condition, SORT_STEP, beforSortNo,
				tableName);
		
		Integer sortNo = beforSortNo + SORT_STEP;
		this.mapper.updateSortNo(id, sortNo, tableName);
	}
}
