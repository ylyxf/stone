package org.siqisource.stone.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class ContextTemplate implements
		ApplicationListener<ContextRefreshedEvent> {

	private Map<String, Object> components = new HashMap<String, Object>();

	public String parse(String value) {
		return this.parse(value, value);
	}

	public String parse(String name, String value) {
		return this.parse(name, value, components);
	}

	public String parse(String value, Map<String, Object> root) {
		return this.parse(value, value, root);
	}

	public String parse(String name, String value, Map<String, Object> root) {
		try {
			Template template = new Template(name, new StringReader(value),
					new Configuration(Configuration.getVersion()));
			StringWriter writer = new StringWriter();
			template.process(root, writer);
			return writer.toString();
		} catch (Exception e) {
			return value;
		}
	}

	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		Map<String, Object> tempComponents = applicationContext
				.getBeansWithAnnotation(Component.class);
		components.putAll(tempComponents);
		Map<String, Object> serviceComponents = applicationContext
				.getBeansWithAnnotation(Service.class);
		components.putAll(serviceComponents);
	}
}
