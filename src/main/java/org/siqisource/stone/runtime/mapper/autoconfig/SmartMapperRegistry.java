package org.siqisource.stone.runtime.mapper.autoconfig;

import java.io.InputStream;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.siqisource.stone.runtime.mapper.GeneralMapper;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.siqisource.stone.runtime.mapper.dialect.Dialect;
import org.siqisource.stone.runtime.mapper.model.Model;
import org.siqisource.stone.runtime.mapper.plugins.UUIDTypeHandler;
import org.siqisource.stone.runtime.mapper.utils.ModelClazzReader;
import org.siqisource.stone.runtime.mapper.utils.TypeResolver;

public class SmartMapperRegistry {

	private SqlSessionFactory sqlSessionFactory;

	private Dialect dialect;

	private GeneralMapper<?>[] generalMappers;

	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	public Dialect getDialect() {
		return dialect;
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

	public GeneralMapper<?>[] getGeneralMappers() {
		return generalMappers;
	}

	public void setGeneralMappers(GeneralMapper<?>[] generalMappers) {
		this.generalMappers = generalMappers;
	}

	public void init() {
		registerTypeHandler();
		try {
			registerMapper();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void registerTypeHandler() {
		TypeHandlerRegistry typeHandlerRegistry = this.sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
		typeHandlerRegistry.register(UUIDTypeHandler.class);
	}

	private void registerMapper() throws SQLException {
		SqlSession session = this.sqlSessionFactory.openSession();
		DatabaseMetaData metaData = session.getConnection().getMetaData();
		for (GeneralMapper<?> generalMapper : generalMappers) {
			Class<?>[] interfaceClazzArray = generalMapper.getClass().getInterfaces();
			for (Class<?> interfaceClazz : interfaceClazzArray) {
				if (GeneralMapper.class.isAssignableFrom(interfaceClazz)) {
					Class<?>[] arguments = TypeResolver.resolveRawArguments(GeneralMapper.class,
							generalMapper.getClass());
					Class<?> modelClazz = arguments[0];
					boolean isSingleKey = SingleKeyMapper.class.isAssignableFrom(interfaceClazz) ? true : false;
					Model model = ModelClazzReader.readModel(interfaceClazz, modelClazz, metaData, this.dialect,
							isSingleKey);

					XMLBuilder xmlBuilder = new XMLBuilder(model, this.dialect);
					InputStream xml = xmlBuilder.genXml();
					Configuration configuration = sqlSessionFactory.getConfiguration();
					XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(xml, configuration,
							"autogen." + model.getName(), configuration.getSqlFragments());
					xmlMapperBuilder.parse();
				}
			}
		}
		session.close();
	}

}
