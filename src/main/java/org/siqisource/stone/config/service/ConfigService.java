package org.siqisource.stone.config.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.codec.Base64;
import org.siqisource.stone.config.annotation.Config;
import org.siqisource.stone.config.mapper.ConfigMapper;
import org.siqisource.stone.config.model.ConfigClassEntity;
import org.siqisource.stone.config.model.ConfigEntity;
import org.siqisource.stone.exceptions.ProgramException;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.orm.PartitiveFields;
import org.siqisource.stone.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

@Service
public class ConfigService extends AbstractService<ConfigEntity> {

	@Autowired
	ConfigMapper mapper;

	private Map<String, ConfigClassEntity> configClassEntityMap = new HashMap<String, ConfigClassEntity>();

	private Map<String, Object> configBeanMap = new HashMap<String, Object>();

	@Override
	protected MybatisMapper<ConfigEntity> getMapper() {
		return this.mapper;
	}

	public List<ConfigClassEntity> listConfigClassEntity() {
		return new ArrayList<ConfigClassEntity>(configClassEntityMap.values());
	}

	public ConfigClassEntity readConfigClassEntity(String classCode) {
		ConfigClassEntity configClassEntity = configClassEntityMap
				.get(classCode);
		return configClassEntity;
	}

	public void addConfigClass(Object bean, ConfigClassEntity configClassEntity) {
		String code = configClassEntity.getCode();
		configClassEntityMap.put(code, configClassEntity);
		configBeanMap.put(code, bean);
		//初始化Bean的值
		readConfig(bean);
	}

	@Transactional
	public void saveConfigClassEntity(ConfigClassEntity configClassEntity) {
		String classCode = configClassEntity.getCode();
		List<ConfigEntity> configList = configClassEntity.getConfigEntityList();
		for (ConfigEntity configEntity : configList) {
			PartitiveFields fields = new PartitiveFields();
			String code = configEntity.getCode();
			String value = configEntity.getValue();
			if (configEntity.getEncode() && StringUtils.isNotBlank(value)) {
				value = Base64.encodeToString(value.getBytes());
			}
			fields.put("value", value);
			this.updatePartitive(fields, code, classCode);

		}
		// 更新对应的容器中的Bean的值
		Object bean = configBeanMap.get(configClassEntity);
		readConfig(bean);
	}

	/**
	 * 根据配置类更新数据库的配置值
	 * 
	 * @param object
	 */
	public void writeConfig(final Object object) {
		ReflectionUtils.doWithFields(object.getClass(),
				new ReflectionUtils.FieldCallback() {

					@Override
					public void doWith(Field field)
							throws IllegalArgumentException,
							IllegalAccessException {
						String classCode = object.getClass().getName();
						String code = field.getName();
						ConfigEntity configEntity = read(code, classCode);
						if (configEntity != null) {
							PartitiveFields fields = new PartitiveFields();
							String value = String.valueOf(field.get(object));
							if (configEntity.getEncode()
									&& StringUtils.isNotBlank(value)) {
								value = Base64.encodeToString(value.getBytes());
							}
							fields.put("value", value);
							updatePartitive(fields, code, classCode);
						}
					}

				});
	}

	/**
	 * 根据配置，读取配置库的配置值
	 */
	public void readConfig(final Object bean) {
		final String classCode = bean.getClass().getName();

		ReflectionUtils.doWithFields(bean.getClass(),
				new ReflectionUtils.FieldCallback() {

					@Override
					public void doWith(Field field)
							throws IllegalArgumentException,
							IllegalAccessException {

						for (Annotation anno : field.getAnnotations()) {
							if (anno instanceof Config) {
								String code = field.getName();
								ConfigEntity configEntity = readConfigEntity(
										code, classCode);
								String value = configEntity.getValue();
								ReflectionUtils.setField(field, bean,
										getTypedValue(field, value));

							}
						}

					}

				});

	}

	private ConfigEntity readConfigEntity(String code, String classCode) {
		// 从配置信息中找到它
		ConfigEntity result = null;
		final ConfigClassEntity configClassEntity = configClassEntityMap
				.get(classCode);
		for (ConfigEntity configEntity : configClassEntity
				.getConfigEntityList()) {
			if (code.equals(configEntity.getCode())) {
				result = configEntity;
			}
		}
		// 从数据库中找到值，并对Value进行赋值
		ConfigEntity configEntity = read(code, classCode);
		if (configEntity == null) {
			configEntity = new ConfigEntity();
			configEntity.setClassCode(classCode);
			configEntity.setCode(code);
			configEntity.setValue("");
			this.insert(configEntity);
		}
		configEntity.setValue(configEntity.getValue());
		return result;
	}

	public Object getTypedValue(Field field, String value) {
		if (field.getType().equals(String.class)) {
			return value;
		} else if (field.getType().equals(Integer.class)) {
			return Integer.parseInt(value);
		} else if (field.getType().equals(Float.class)) {
			return Float.parseFloat(value);
		} else if (field.getType().equals(Date.class)) {
			DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return formater.parse(value);
			} catch (ParseException e) {
				throw new ProgramException("配置项的值：" + value + ",不是yyyy-MM-dd型");
			}
		} else if (field.getType().equals(Time.class)) {
			DateFormat formater = new SimpleDateFormat("HH:mm:ss");
			try {
				return formater.parse(value);
			} catch (ParseException e) {
				throw new ProgramException("配置项的值：" + value + ",不是HH:mm:ss型");
			}
		} else if (field.getType().equals(Timestamp.class)) {
			DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return formater.parse(value);
			} catch (ParseException e) {
				throw new ProgramException("配置项的值：" + value
						+ ",不是yyyy-MM-dd HH:mm:ss型");
			}
		}
		throw new ProgramException("不被支持的配置项的类型：" + field.getType() + "。");
	}

}
