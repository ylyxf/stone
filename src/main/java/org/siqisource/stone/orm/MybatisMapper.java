package org.siqisource.stone.orm;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.siqisource.stone.orm.condition.Condition;

/**
 * base mapper to be inherited
 * 
 * @author yulei
 * 
 * @param <T>
 */
public interface MybatisMapper<T> {

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
	public void insertBatch(@Param("list") List<T> models);

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
	 * count the querying record
	 * 
	 * @param condition
	 * @return
	 */
	int count(@Param("condition") Condition condition);

	/**
	 * list the pagination querying result
	 * 
	 * @param condition
	 * @param rowBounds
	 * @return
	 */
	List<T> list(@Param("condition") Condition condition,
			@Param("rowBounds") RowBounds rowBounds);

	/**
	 * list the querying result
	 * 
	 * @param condition
	 * @return
	 */
	List<T> list(@Param("condition") Condition condition);

	/**
	 * update database
	 * 
	 * @param model
	 */
	public void update(T model);

	/**
	 * update some fields of the table
	 * 
	 * @param fields
	 * @param id
	 */
	public void updatePartitive(@Param("fields") PartitiveFields fields,
			@Param("id") Object... id);

	/**
	 * update batch records
	 * 
	 * @param condition
	 */
	public void updateBatch(@Param("fields") PartitiveFields fields,
			@Param("condition") Condition condition);
	
	/**
	 * update batch records
	 * 
	 * @param condition
	 */
	public void updateBatchList(@Param("list") List<T> models);

	/**
	 * delete from database
	 * 
	 * @param id
	 *            primary key(s) of the table
	 */
	public void delete(@Param("id") Object... id);
	

	/**
	 * delete batch record from database
	 * 
	 * @param condition
	 */
	public void deleteBatch(@Param("condition") Condition condition);
}
