package org.siqisource.stone.config.service;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.siqisource.stone.config.mapper.ConfigMapper;
import org.siqisource.stone.config.model.Config;
import org.siqisource.stone.exceptions.ProgramException;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.orm.PartitiveFields;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

@Service
public class ConfigService extends AbstractService<Config> {

	@Autowired
	ConfigMapper mapper;

	@Override
	protected MybatisMapper<Config> getMapper() {
		return this.mapper;
	}

	@Transactional
	public void saveConfigList(List<Config> configList) {
		for (Config config : configList) {
			PartitiveFields fields = new PartitiveFields();
			fields.put("value", config.getValue());
			this.updatePartitive(fields, config.getClassCode(),
					config.getCode());
		}
	}

	public void writeConfig(final Object object) {
		ReflectionUtils.doWithFields(object.getClass(),
				new ReflectionUtils.FieldCallback() {

					@Override
					public void doWith(Field field)
							throws IllegalArgumentException,
							IllegalAccessException {
						String className = object.getClass().getName();
						String code = field.getName();
						Config config = read(code);
						if (config != null) {
							PartitiveFields fields = new PartitiveFields();
							fields.put("value",
									String.valueOf(field.get(object)));
							updatePartitive(fields, className, code);
						}
					}

				});
	}

	public void readConfig(final Object object) {
		String className = object.getClass().getName();
		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("classCode", className);
		List<Config> configList = this.list(condition);
		for (Config config : configList) {
			final String code = config.getCode();
			final String value = config.getValue();
			ReflectionUtils.doWithFields(object.getClass(),
					new ReflectionUtils.FieldCallback() {

						@Override
						public void doWith(Field field)
								throws IllegalArgumentException,
								IllegalAccessException {
							if (field.getName().equals(code)) {
								ReflectionUtils.setField(field, object,
										getTypedValue(field, value));
							}

						}

					});
		}
	}

	private Object getTypedValue(Field field, String value) {
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