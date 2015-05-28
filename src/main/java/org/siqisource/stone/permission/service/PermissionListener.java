package org.siqisource.stone.permission.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.siqisource.stone.permission.annotation.Permission;
import org.siqisource.stone.permission.annotation.PermissionClass;
import org.siqisource.stone.permission.model.PermissionClassEntity;
import org.siqisource.stone.permission.model.PermissionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Component
public class PermissionListener implements
		ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private PermissionService permissionService;

	public static final Logger logger = LoggerFactory
			.getLogger(PermissionListener.class);

	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();

		Map<String, Object> beans = applicationContext
				.getBeansWithAnnotation(PermissionClass.class);

		for (Map.Entry<String, Object> entry : beans.entrySet()) {

			Object bean = entry.getValue();
			analysePermissionClass(bean);
		}
	}

	public void analysePermissionClass(Object bean) {

		PermissionClass permissionClass = AnnotationUtils.findAnnotation(
				bean.getClass(), PermissionClass.class);
		PermissionClassEntity permissionClassEntity = new PermissionClassEntity();
		permissionClassEntity.setName(permissionClass.value());
		
		List<PermissionEntity> permissionEntityList = new ArrayList<PermissionEntity>();

		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				for (Annotation anno : field.getAnnotations()) {
					if (anno instanceof Permission) {
						PermissionEntity permissionEntity = new PermissionEntity();
						Permission permission = (Permission) anno;
						permissionEntity.setName(permission.value());
						String permissionValue = String.valueOf(field.get(bean
								.getClass()));
						permissionEntity.setValue(permissionValue);
						permissionEntityList.add(permissionEntity);
					}
				}
			} catch (Exception e) {
				logger.error("get the permission value failed，the class is "
						+ bean.getClass() + ",and the field is "
						+ field.getName());
			}
		}

		if (permissionEntityList.size() > 0) {
			permissionClassEntity.setPermissionEntityList(permissionEntityList);
			permissionService.addPermissionEntity(permissionClassEntity);
		}
	}

}
