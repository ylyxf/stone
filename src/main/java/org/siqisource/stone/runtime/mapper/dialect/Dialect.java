package org.siqisource.stone.runtime.mapper.dialect;

import org.siqisource.stone.runtime.mapper.annotation.KeyGenerator;

public interface Dialect {

	public String getDefualtSchema();

	public String getKeySelector(KeyGenerator keyGenerator, String sequence);

	public String getSelectKeyOrder();

	public String getLimitString(String sql, int skipResults, int maxResults);

}
