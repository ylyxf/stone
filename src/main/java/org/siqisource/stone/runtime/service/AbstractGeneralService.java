package org.siqisource.stone.runtime.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.siqisource.stone.runtime.mapper.GeneralMapper;
import org.siqisource.stone.runtime.mapper.condition.Condition;
import org.siqisource.stone.runtime.mapper.condition.PartitiveFields;
import org.siqisource.stone.runtime.mapper.condition.SqlKey;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractGeneralService<M> implements GeneralService<M> {

	private static PartitiveFields LOGIC_DELETE = new PartitiveFields();

	static {
		LOGIC_DELETE.put(SqlKey.LOGIC_DELETED, true);
	}

	/**
	 * Subclass need tell parent which mapper it is using.
	 * 
	 * @return
	 */
	protected abstract GeneralMapper<M> getMapper();

	/**
	 * insert object to database
	 * 
	 * @param model
	 */
	public void insert(M model) {
		getMapper().insert(model);
	}

	/**
	 * insert object to database batch
	 * 
	 * @param model
	 */
	@Transactional
	public void insertBatch(List<M> models) {
		for (M model : models) {
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
	 * @param Condition
	 *            condition
	 * 
	 * @return first record of result set
	 */
	public M readOne(Condition condition) {
		List<M> result = this.list(condition, new RowBounds(0, 1));
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
	public List<M> list(Condition condition, RowBounds rowBounds) {
		return getMapper().list(condition, rowBounds);
	}

	/**
	 * list
	 * 
	 * @param condition
	 * @return
	 */
	public List<M> list(Condition condition) {
		return getMapper().list(condition);
	}

	/**
	 * update batch records
	 * 
	 * @param condition
	 */
	@Override
	public void updatePartitive(PartitiveFields fields, Condition condition) {
		fields.prepareUpdateSql();
		getMapper().updateBatch(fields, condition);
	}

	/**
	 * delete batch record from database
	 * 
	 * @param condition
	 */
	public void delete(Condition condition) {
		getMapper().deleteBatch(condition);
	}

	/**
	 * delete batch record from logic
	 * 
	 * @param condition
	 */
	public void logicDelete(Condition condition) {
		this.updatePartitive(LOGIC_DELETE, condition);
	}

}
