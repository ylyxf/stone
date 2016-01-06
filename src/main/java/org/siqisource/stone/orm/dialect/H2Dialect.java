package org.siqisource.stone.orm.dialect;

public class H2Dialect extends Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ") + " limit " + offset + " ," + limit;
		return sql;
	}

}
