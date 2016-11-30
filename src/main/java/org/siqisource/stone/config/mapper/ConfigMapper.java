package org.siqisource.stone.config.mapper;

import org.apache.ibatis.annotations.Param;
import org.siqisource.stone.config.model.ConfigEntity;
import org.siqisource.stone.runtime.mapper.GeneralMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigMapper extends GeneralMapper<ConfigEntity> {

	public ConfigEntity readConfigEntity(@Param("code") String code, @Param("classCode") String classCode);

}
