package org.siqisource.stone.orm.dialect;

public abstract class Dialect {

	public static enum Type {
		MYSQL,
		ORACLE,
		POSTGRESQL,
		H2,
		SQLSERVER
	}

	public abstract String getLimitString(String sql, int skipResults,
			int maxResults);

}
