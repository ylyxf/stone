package org.siqisource.stone.dict.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DictListener implements ApplicationListener<ContextRefreshedEvent> {

	public static final Logger logger = LoggerFactory
			.getLogger(DictListener.class);

	@Autowired
	DictItemService dictItemService;

	public void onApplicationEvent(ContextRefreshedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();

		Map<String, Dictable> beans = applicationContext
				.getBeansOfType(Dictable.class);

		for (Map.Entry<String, Dictable> entry : beans.entrySet()) {
			dictItemService.addDict(entry.getValue());
		}
	}
}
