package org.siqisource.stone.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.orm.PartitiveFields;
import org.siqisource.stone.orm.SqlKey;
import org.siqisource.stone.orm.condition.Condition;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractService<T> implements Service<T> {

	private static PartitiveFields LOGIC_DELETE = new PartitiveFields();

	static {
		LOGIC_DELETE.put(SqlKey.LOGIC_DELETED, true);
	}

	/**
	 * Subclass need tell parent which mapper it is using.
	 * 
	 * @return
	 */
	protected abstract MybatisMapper<T> getMapper();

	/**
	 * insert object to database
	 * 
	 * @param model
	 */
	public void insert(T model) {
		getMapper().insert(model);
	}

	/**
	 * insert object to database batch
	 * 
	 * @param model
	 */
	@Transactional
	public void insertBatch(List<T> models) {
		for(T model:models){
			this.insert(model);
		}
	}

	/**
	 * insert some fields of the table
	 * 
	 * @param fields
	 */
	public void insertPartitive(PartitiveFields fields) {
		fields.prepareInsertSql();
		getMapper().insertPartitive(fields);
	}

	/**
	 * read object from database
	 * 
	 * @param id
	 *            primary key(s) of the table
	 * @return object
	 */
	public T read(Object... id) {
		return getMapper().read(id);
	}

	/**
	 * read object from database
	 * 
	 * @param Condition
	 *            condition
	 * 
	 * @return first record of result set
	 */
	public T readOne(Condition condition) {
		List<T> result = this.list(condition, new RowBounds(0, 1));
		if (result.size() >= 1) {
			return result.get(0);
		} else {
			return null;
		}
	}

	/**
	 * count
	 * 
	 * @param condition
	 * @return
	 */
	public int count(Condition condition) {
		return getMapper().count(condition);
	}

	/**
	 * pagination list
	 * 
	 * @param condition
	 * @param rowBounds
	 * @return
	 */
	public List<T> list(Condition condition, RowBounds rowBounds) {
		return getMapper().list(condition, rowBounds);
	}

	/**
	 * list
	 * 
	 * @param condition
	 * @return
	 */
	public List<T> list(Condition condition) {
		return getMapper().list(condition);
	}

	/**
	 * update database
	 * 
	 * @param model
	 */
	public void update(T model) {
		getMapper().update(model);
	}

	/**
	 * update some fields of the table
	 * 
	 * @param fields
	 * @param id
	 */
	public void updatePartitive(PartitiveFields fields, Object... id) {
		fields.prepareUpdateSql();
		getMapper().updatePartitive(fields, id);
	}

	/**
	 * update batch records
	 * 
	 * @param condition
	 */
	@Override
	public void updateBatch(PartitiveFields fields, Condition condition) {
		fields.prepareUpdateSql();
		getMapper().updateBatch(fields, condition);
	}

	/**
	 * update batch records
	 * 
	 * @param condition
	 */
	@Transactional
	public void updateBatch(List<T> models) {
		for(T model:models){
			getMapper().update(model);
		}
		
	}

	/**
	 * delete from database
	 * 
	 * @param id
	 *            primary key(s) of the table
	 */
	public void delete(Object... id) {
		getMapper().delete(id);
	}

	/**
	 * delete batch record from database
	 * 
	 * @param condition
	 */
	public void deleteBatch(Condition condition) {
		getMapper().deleteBatch(condition);
	}

	/**
	 * delete batch record from database
	 * 
	 * @param condition
	 */
	@Transactional
	public void deleteBatch(Object[] idList) {
		for (Object id : idList) {
			this.delete(id);
		}
	}

	/**
	 * delete from logic
	 * 
	 * @param id
	 *            primary key(s) of the table
	 */
	public void logicDelete(Object... id) {
		this.updatePartitive(LOGIC_DELETE, id);
	}

	/**
	 * delete batch record from logic
	 * 
	 * @param condition
	 */
	public void logicDeleteBatch(Condition condition) {
		this.updateBatch(LOGIC_DELETE, condition);
	}

	/**
	 * delete batch record from logic
	 * 
	 * @param condition
	 */
	@Transactional
	public void logicDeleteBatch(Object[] idList) {
		for (Object id : idList) {
			this.logicDelete(id);
		}
	}

}
