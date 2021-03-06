package org.siqisource.stone.runtime.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.siqisource.stone.runtime.mapper.condition.Condition;
import org.siqisource.stone.runtime.mapper.condition.PartitiveFields;

public interface GeneralService<M> {

	/**
	 * insert object to database
	 * 
	 * @param model
	 */
	public void insert(M model);

	/**
	 * insert object to database batch
	 * 
	 * @param model
	 */
	public void insertBatch(List<M> models);

	/**
	 * insert some fields of the table
	 * 
	 * @param fields
	 */
	public void insertPartitive(@Param("fields") PartitiveFields fields);

	/**
	 * read object from database
	 * 
	 * @param Condition
	 *            condition
	 * 
	 * @return first record of result set
	 */
	public M readOne(Condition condition);

	/**
	 * count the querying record
	 * 
	 * @param condition
	 * @return
	 */
	int count(Condition condition);

	/**
	 * list the pagination querying result
	 * 
	 * @param condition
	 * @param rowBounds
	 * @return
	 */
	List<M> list(Condition condition, RowBounds rowBounds);

	/**
	 * list the querying result
	 * 
	 * @param condition
	 * @return
	 */
	List<M> list(Condition condition);

	/**
	 * update batch records
	 * 
	 * @param condition
	 */
	public void updatePartitive(PartitiveFields fields, @Param("condition") Condition condition);

	/**
	 * delete batch record from database
	 * 
	 * @param condition
	 */
	public void delete(Condition condition);

	/**
	 * delete batch record from logic
	 * 
	 * @param condition
	 */
	public void logicDelete(Condition condition);

}
