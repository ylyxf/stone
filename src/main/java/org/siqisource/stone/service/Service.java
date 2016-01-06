package org.siqisource.stone.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.siqisource.stone.orm.PartitiveFields;
import org.siqisource.stone.orm.condition.Condition;

public interface Service<T> {

	/**
	 * insert object to database
	 * 
	 * @param model
	 */
	public void insert(T model);

	/**
	 * insert object to database batch
	 * 
	 * @param model
	 */
	public void insertBatch(List<T> models);

	/**
	 * insert some fields of the table
	 * 
	 * @param fields
	 */
	public void insertPartitive(@Param("fields") PartitiveFields fields);

	/**
	 * read object from database
	 * 
	 * @param id
	 *            primary key(s) of the table
	 * @return object
	 */
	public T read(@Param("id") Object... id);


	/**
	 * read object from database
	 * 
	 * @param Condition
	 *            condition
	 * 
	 * @return first record of result set
	 */
	public T readOne(Condition condition);

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
	List<T> list(Condition condition, RowBounds rowBounds);

	/**
	 * list the querying result
	 * 
	 * @param condition
	 * @return
	 */
	List<T> list(Condition condition);

	/**
	 * update database
	 * 
	 * @param model
	 */
	public void update(T model);
	
	/**
	 * update database
	 * 
	 * @param model
	 */
	public void updateBatch(List<T> models);

	/**
	 * update some fields of the table
	 * 
	 * @param fields
	 * @param id
	 */
	public void updatePartitive(PartitiveFields fields,
			@Param("id") Object... id);

	/**
	 * update batch records
	 * 
	 * @param condition
	 */
	public void updateBatch(PartitiveFields fields,
			@Param("condition") Condition condition);

	/**
	 * delete from database
	 * 
	 * @param id
	 *            primary key(s) of the table
	 */
	public void delete(Object... id);


	/**
	 * delete batch record from database
	 * 
	 * @param condition
	 */
	public void deleteBatch(Condition condition);

	/**
	 * delete batch record from database
	 * 
	 * @param condition
	 */
	public void deleteBatch(Object[] idList);
	
	/**
	 * delete from logic
	 * 
	 * @param id
	 *            primary key(s) of the table
	 */
	public void logicDelete(Object... id);


	/**
	 * delete batch record from logic
	 * 
	 * @param condition
	 */
	public void logicDeleteBatch(Condition condition);

	/**
	 * delete batch record from logic
	 * 
	 * @param condition
	 */
	public void logicDeleteBatch(Object[] idList);
	
}
