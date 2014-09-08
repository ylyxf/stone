package org.siqisource.stone.orm.dialect;

public class MySqlDialect extends Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ") + " limit " + offset + " ," + limit;
		return sql;
	}

}
