package org.siqisource.stone.runtime.sort.mapper;

import org.apache.ibatis.annotations.Param;
import org.siqisource.stone.runtime.mapper.condition.SimpleCondition;
import org.springframework.stereotype.Repository;

@Repository
public interface SortMapper {

	public Integer readSortNo(@Param("id") Integer id,
			@Param("tableName") String tableName);

	public Integer readMaxSortNo(@Param("condition") SimpleCondition condition,
			@Param("tableName") String tableName);

	public Integer readBeforeId(@Param("id") Integer id,
			@Param("condition") SimpleCondition condition,
			@Param("tableName") String tableName);

	public Integer readAfterId(@Param("id") Integer id,
			@Param("condition") SimpleCondition condition,
			@Param("tableName") String tableName);

	public void updateOldBrotherSortlNo(
			@Param("condition") SimpleCondition condition,
			@Param("step") Integer step, @Param("sortNo") Integer beforeSortNo,
			@Param("tableName") String tableName);

	public void updateSortNo(@Param("id") Integer id,
			@Param("sortNo") Integer sortNo,
			@Param("tableName") String tableName);

}
