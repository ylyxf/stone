package org.siqisource.stone.runtime.mapper.dialect;

import org.siqisource.stone.runtime.mapper.annotation.KeyGenerator;

public class PostgreSqlDialect implements Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ") + " limit " + limit + " offset " + offset;
		return sql;
	}

	@Override
	public String getKeySelector(KeyGenerator keyGenerator, String sequence) {
		if (keyGenerator == KeyGenerator.uuid) {
			return "select md5(random()::text || clock_timestamp()::text)::uuid as id";
		} else if (keyGenerator == KeyGenerator.sequence) {
			return "select nextval('" + sequence + "') as id ";
		} else {
			throw new RuntimeException("only uuid and sequence  is supported for selectKey of postgresql  ");
		}
	}

	@Override
	public String getSelectKeyOrder() {
		return "BEFORE";
	}

	@Override
	public String getDefualtSchema() {
		return "public";
	}

}
