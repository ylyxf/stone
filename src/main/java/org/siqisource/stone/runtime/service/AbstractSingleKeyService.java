package org.siqisource.stone.runtime.service;

import java.util.List;

import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.siqisource.stone.runtime.mapper.condition.PartitiveFields;
import org.siqisource.stone.runtime.mapper.condition.SqlKey;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractSingleKeyService<M, K> extends AbstractGeneralService<M> implements SingleKeyService<M, K> {

	private static PartitiveFields LOGIC_DELETE = new PartitiveFields();

	static {
		LOGIC_DELETE.put(SqlKey.LOGIC_DELETED, true);
	}

	/**
	 * Subclass need tell parent which mapper it is using.
	 * 
	 * @return
	 */
	protected abstract SingleKeyMapper<M, K> getMapper();

	/**
	 * read object from database
	 * 
	 * @param id
	 *            primary key(s) of the table
	 * @return object
	 */
	public M read(K id) {
		return getMapper().read(id);
	}

	/**
	 * update database
	 * 
	 * @param model
	 */
	public void update(M model) {
		getMapper().update(model);
	}

	/**
	 * update some fields of the table
	 * 
	 * @param fields
	 * @param id
	 */
	public void updatePartitive(PartitiveFields fields, K id) {
		fields.prepareUpdateSql();
		getMapper().updatePartitive(fields, id);
	}

	/**
	 * update batch records
	 * 
	 * @param condition
	 */
	@Transactional
	public void updateBatch(List<M> models) {
		for (M model : models) {
			getMapper().update(model);
		}

	}

	/**
	 * delete from database
	 * 
	 * @param id
	 *            primary key(s) of the table
	 */
	public void delete(K id) {
		getMapper().delete(id);
	}

	/**
	 * delete batch record from database
	 * 
	 * @param condition
	 */
	@Transactional
	public void deleteBatch(K[] idList) {
		for (K id : idList) {
			this.delete(id);
		}
	}

	/**
	 * delete from logic
	 * 
	 * @param id
	 *            primary key(s) of the table
	 */
	public void logicDelete(K id) {
		this.updatePartitive(LOGIC_DELETE, id);
	}

	/**
	 * delete batch record from logic
	 * 
	 * @param condition
	 */
	@Transactional
	public void logicDeleteBatch(K[] idList) {
		for (K id : idList) {
			this.logicDelete(id);
		}
	}

}
