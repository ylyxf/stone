package org.siqisource.stone.web.constants;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class JspConstantsListener implements
		ApplicationListener<ContextRefreshedEvent>, ServletContextAware {

	private ServletContext application;

	public static final Logger logger = LoggerFactory
			.getLogger(JspConstantsListener.class);

	public void setServletContext(ServletContext servletContext) {
		this.application = servletContext;

	}

	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();

		Map<String, Object> beans = applicationContext
				.getBeansWithAnnotation(JspConstants.class);

		for (Map.Entry<String, Object> entry : beans.entrySet()) {

			String contextName = entry.getKey();
			Object bean = entry.getValue();

			JspConstants jspContext = AnnotationUtils.findAnnotation(
					bean.getClass(), JspConstants.class);
			contextName = "".equals(jspContext.value()) ? contextName
					: jspContext.value();

			Field[] fields = bean.getClass().getFields();
			Map<String, String> fieldsMap = new HashMap<String, String>();
			for (Field field : fields) {
				try {
					fieldsMap.put(field.getName(), field.get(null).toString());
				} catch (Exception e) {
					logger.error("set jsp application constants name "
							+ contextName + " ,fieldName :" + field.getName());
				}
			}

			application.setAttribute(contextName, fieldsMap);
			logger.info("set jsp application attribute name " + contextName
					+ " ,value :" + bean);
		}
	}

}
