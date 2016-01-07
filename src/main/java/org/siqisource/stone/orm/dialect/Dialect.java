package org.siqisource.stone.orm.dialect;

public abstract class Dialect {

	public static enum Type {
		MySql,
		Oracle,
		PostgreSql,
		H2,
		SqlServer
	}

	public abstract String getLimitString(String sql, int skipResults,
			int maxResults);

}
