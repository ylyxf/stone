package org.siqisource.stone.sort.mapper;

import org.apache.ibatis.annotations.Param;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.springframework.stereotype.Repository;

@Repository
public interface SortMapper {

	public Integer readBeforeSortNo(@Param("id") Integer beforeId,
			@Param("tableName") String tableName);

	public void updateOldBrotherSortlNo(
			@Param("condition") SimpleCondition condition,
			@Param("step") Integer step, @Param("sortNo") Integer beforeSortNo,
			@Param("tableName") String tableName);

	public void updateSortNo(@Param("id")  Integer id,
			@Param("sortNo") Integer sortNo,
			@Param("tableName") String tableName);

}
