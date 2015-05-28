package org.siqisource.stone.config.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.siqisource.stone.config.annotation.Config;
import org.siqisource.stone.config.annotation.ConfigClass;
import org.siqisource.stone.config.model.ConfigClassEntity;
import org.siqisource.stone.config.model.ConfigEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class ConfigListener implements
		ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ConfigService configService;

	public static final Logger logger = LoggerFactory
			.getLogger(ConfigListener.class);

	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();

		Map<String, Object> beans = applicationContext
				.getBeansWithAnnotation(ConfigClass.class);

		for (Map.Entry<String, Object> entry : beans.entrySet()) {
			Object bean = entry.getValue();
			ConfigClassEntity configClassEntity = analyseConfigClass(bean);
			configService.addConfigClass(bean, configClassEntity);
			
		}
	}

	/**
	 * 解析bean有两点用途： 1、为了设置Bean的值 2、为了统计Bean的信息，用于在界面上设置配置
	 * 
	 * @param bean
	 */
	public ConfigClassEntity analyseConfigClass(Object bean) {

		// 统计配置信息
		ConfigClassEntity configClassEntity = new ConfigClassEntity();
		final List<ConfigEntity> configEntityList = new ArrayList<ConfigEntity>();
		configClassEntity.setConfigEntityList(configEntityList);

		final String classCode = bean.getClass().getName();
		ConfigClass configClass = AnnotationUtils.findAnnotation(
				bean.getClass(), ConfigClass.class);
		configClassEntity.setLabel(configClass.label());
		configClassEntity.setCode(classCode);

		ReflectionUtils.doWithFields(bean.getClass(),
				new ReflectionUtils.FieldCallback() {

					@Override
					public void doWith(Field field)
							throws IllegalArgumentException,
							IllegalAccessException {

						for (Annotation anno : field.getAnnotations()) {
							if (anno instanceof Config) {
								// 统计配置信息
								ConfigEntity configEntity = new ConfigEntity();
								Config config = (Config) anno;
								configEntity.setClassCode(classCode);
								configEntity.setCode(field.getName());
								configEntity.setLabel(config.label());
								configEntity.setComment(config.comment());

								Integer sortNo = Integer.parseInt(config
										.sortNo());
								configEntity.setSortNo(sortNo);

								Boolean encode = Boolean.parseBoolean(config
										.encode());
								configEntity.setEncode(encode);
								configEntityList.add(configEntity);
							}
						}
					}
				});
		return configClassEntity;

	}
}
