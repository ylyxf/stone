package org.siqisource.stone.runtime.mapper.autoconfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.siqisource.stone.runtime.mapper.GeneralMapper;
import org.siqisource.stone.runtime.mapper.dialect.Dialect;
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
			Dialect dialect = (Dialect) Class.forName(dialectClazz).newInstance();
			return dialect;
		} catch (Exception e) {
			throw new RuntimeException(e);
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
		mybatisModelRegistry.setKeylessMappers(keylessMappers);
		mybatisModelRegistry.setDialect(dialect);
		mybatisModelRegistry.init();
		return mybatisModelRegistry;
	}

}