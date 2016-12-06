package org.siqisource.stone.runtime.mapper.autoconfig;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.siqisource.stone.runtime.exceptions.ProgramException;
import org.siqisource.stone.runtime.mapper.GeneralMapper;
import org.siqisource.stone.runtime.mapper.dialect.Dialect;
import org.siqisource.stone.runtime.mapper.dialect.PostgreSqlDialect;
import org.siqisource.stone.runtime.mapper.plugins.PaginationInterceptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmartMapperSpringConfig {

	@Value("${stone.db.dialect:}")
	private String dialectClazz = "";

	@Bean(name = "dialect")
	public Dialect getDialect() {
		try {
			Dialect dialect = null;
			if (StringUtils.isBlank(dialectClazz)) {
				dialect = new PostgreSqlDialect();
			}else{
				dialect = (Dialect) Class.forName(dialectClazz).newInstance();
			}
			return dialect;
		} catch (Exception e) {
			throw new ProgramException("stone.db.dialect.dialectClazz属性值为"+dialectClazz+",无法初始化这个类",e);
		}
	}

	@Bean(name = "paginationInterceptor")
	public PaginationInterceptor paginationInterceptor(@Qualifier("dialect") Dialect dialect) {
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		paginationInterceptor.setDialect(dialect);
		return paginationInterceptor;
	}

	@Bean(name = "modelRegistry")
	public SmartMapperRegistry modelRegistry(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory,
			@Qualifier("dialect") Dialect dialect, GeneralMapper<?>[] keylessMappers) {
		SmartMapperRegistry mybatisModelRegistry = new SmartMapperRegistry();
		mybatisModelRegistry.setSqlSessionFactory(sqlSessionFactory);
		mybatisModelRegistry.setGeneralMappers(keylessMappers);
		mybatisModelRegistry.setDialect(dialect);
		mybatisModelRegistry.init();
		return mybatisModelRegistry;
	}

}