package org.siqisource.stone.config.mapper;

import org.apache.ibatis.annotations.Param;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.config.model.ConfigEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigMapper extends MybatisMapper<ConfigEntity> {

	public ConfigEntity readConfigEntity(@Param("code") String code,
			@Param("classCode") String classCode);

}
