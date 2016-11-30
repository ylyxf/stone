package org.siqisource.stone.runtime.sort.service;

import org.siqisource.stone.runtime.mapper.condition.SimpleCondition;
import org.siqisource.stone.runtime.sort.mapper.SortMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SortService {

	public static final int SORT_STEP = 10;

	@Autowired
	SortMapper mapper;
	
	public Integer readMaxSortNo( SimpleCondition condition,
			 String tableName){
		return this.mapper.readMaxSortNo(condition, tableName);
	}

	public void move(Integer id, Integer beforeId, SimpleCondition condition,
			String tableName) {
		Integer beforSortNo = 0;
		if (beforeId != null) {
			beforSortNo = this.mapper.readSortNo(beforeId, tableName);
		}
		this.mapper.updateOldBrotherSortlNo(condition, SORT_STEP, beforSortNo,
				tableName);
		Integer sortNo = beforSortNo + SORT_STEP;
		this.mapper.updateSortNo(id, sortNo, tableName);
	}

	public void moveToEnd(Integer id, SimpleCondition condition,
			String tableName) {
		Integer maxSortNo = this.mapper.readMaxSortNo(condition, tableName);
		this.mapper.updateSortNo(id, maxSortNo + SORT_STEP, tableName);
	}

	public void up(Integer id, SimpleCondition condition, String tableName) {
		Integer beforeId = this.mapper.readBeforeId(id, condition, tableName);
		if (beforeId != null) {
			Integer beforeSortNo = this.mapper.readSortNo(beforeId, tableName);
			Integer currSortNo = this.mapper.readSortNo(id, tableName);
			this.mapper.updateSortNo(id, beforeSortNo, tableName);
			this.mapper.updateSortNo(beforeId, currSortNo, tableName);
		}
	}

	public void down(Integer id, SimpleCondition condition, String tableName) {
		Integer afterId = this.mapper.readAfterId(id, condition,tableName);
		if (afterId != null) {
			Integer afterSortNo = this.mapper.readSortNo(afterId, tableName);
			Integer currSortNo = this.mapper.readSortNo(id, tableName);
			this.mapper.updateSortNo(id, afterSortNo, tableName);
			this.mapper.updateSortNo(afterId, currSortNo, tableName);
		}
	}
}
