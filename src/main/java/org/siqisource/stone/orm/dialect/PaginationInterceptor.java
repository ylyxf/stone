package org.siqisource.stone.orm.dialect;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.RowBounds;
import org.siqisource.stone.orm.dialect.Dialect.Type;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {

	private Type databaseType = Dialect.Type.H2;

	public void setDatabaseType(Dialect.Type databaseType) {
		this.databaseType = databaseType;
	}

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();
		MetaObject metaStatementHandler = MetaObject
				.forObject(statementHandler);
		RowBounds rowBounds = (RowBounds) metaStatementHandler
				.getValue("delegate.rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}

		Dialect dialect = null;
		switch (databaseType) {
		case MYSQL:
			dialect = new MySqlDialect();
			break;
		case ORACLE:
			dialect = new OracleDialect();
			break;
		case POSTGRESQL:
			dialect = new PostgreSqlDialect();
			break;
		case H2:
			dialect = new H2Dialect();
			break;
		case SQLSERVER:
			dialect = new SqlServerDialect();
			break; 
		default:
		}

		String originalSql = (String) metaStatementHandler
				.getValue("delegate.boundSql.sql");
		metaStatementHandler.setValue("delegate.boundSql.sql", dialect
				.getLimitString(originalSql, rowBounds.getOffset(),
						rowBounds.getLimit()));
		metaStatementHandler.setValue("delegate.rowBounds.offset",
				RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit",
				RowBounds.NO_ROW_LIMIT);
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {

	}

}
