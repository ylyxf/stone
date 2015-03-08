package org.siqisource.stone.web.jspservice;

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
public class JspServiceListener implements
		ApplicationListener<ContextRefreshedEvent>, ServletContextAware {

	private ServletContext application;

	public static final Logger logger = LoggerFactory
			.getLogger(JspServiceListener.class);

	public void setServletContext(ServletContext servletContext) {
		this.application = servletContext;

	}

	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();

		Map<String, Object> beans = applicationContext
				.getBeansWithAnnotation(JspService.class);

		for (Map.Entry<String, Object> entry : beans.entrySet()) {

			String contextName = entry.getKey();
			Object bean = entry.getValue();

			JspService jspContext = AnnotationUtils.findAnnotation(
					bean.getClass(), JspService.class);
			contextName = "".equals(jspContext.value()) ? contextName
					: jspContext.value();

			application.setAttribute(contextName, bean);
			logger.info("set jsp application attribute name " + contextName
					+ " ,value :" + bean);
		}
	}


}
