package org.siqisource.stone.runtime.web.jscontroller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * to do for more
 * 
 * @author yulei
 *
 */
@Controller
public class JsControllerRegistry {

	@Autowired
	RequestMappingHandlerMapping requestMappingHandlerMapping;

	private final Map<String, List<ControllerMethod>> clazzMethodMap = new HashMap<String, List<ControllerMethod>>();

	private final Map<String, String> clazzJsCache = new HashMap<String, String>();

	@RequestMapping("/jscontroller/{beanName}.js")
	@ResponseBody
	public String jsController(@PathVariable("beanName") String beanName) {
		String script = clazzJsCache.get(beanName);
		if (script == null) {
			script = generateScript(beanName);
			if (StringUtils.isNotBlank(script)) {
				clazzJsCache.put(beanName, script);
			}
		}
		return script;
	}

	private String generateScript(String beanName) {
		List<ControllerMethod> controllerMethodList = clazzMethodMap.get(beanName);

		if (controllerMethodList == null) {
			return "";
		}

		StringBuffer script = new StringBuffer();
		script.append("var ");
		script.append(beanName);
		script.append("= {\n");

		for (int i = 0, iSize = controllerMethodList.size(); i < iSize; i++) {
			ControllerMethod controllerMethod = controllerMethodList.get(i);
			RequestMappingInfo requestMappingInfo = controllerMethod.getRequestMappingInfo();
			HandlerMethod handlerMethod = controllerMethod.getHandlerMethod();
			Method method = handlerMethod.getMethod();

			String methodName = method.getName();
			script.append(methodName);
			script.append("Url:");
			script.append("'");
			script.append("'\n,");

			script.append(methodName);

			ResponseBody responseBody = AnnotationUtils.findAnnotation(method, ResponseBody.class);
			if (responseBody == null) {
				// 直接post请求
			} else {
				// ajax post请求
				script.append(":function(param,callBack){\n");
				script.append("$.post(this.");
				script.append(methodName);
				script.append("Url,param,callBack");
				script.append(");");
				script.append("\n}");
			}

			if (i + 1 < iSize) {
				script.append(",");
			}
		}

		script.append("\n};");
		return script.toString();
	}

	@PostConstruct
	public void register() {
		Map<RequestMappingInfo, HandlerMethod> mapping = requestMappingHandlerMapping.getHandlerMethods();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> enrty : mapping.entrySet()) {

			HandlerMethod handlerMethod = enrty.getValue();

			Class<?> beanType = handlerMethod.getBeanType();
			JsController jsController = AnnotationUtils.findAnnotation(beanType, JsController.class);
			if (jsController == null) {
				continue;
			}

			String[] beanNames = requestMappingHandlerMapping.getApplicationContext().getBeanNamesForType(beanType);
			if (beanNames.length < 0) {
				continue;
			}

			String firstBeanName = beanNames[0];
			List<ControllerMethod> methodList = clazzMethodMap.get(firstBeanName);
			if (methodList == null) {
				methodList = new ArrayList<ControllerMethod>();
				clazzMethodMap.put(firstBeanName, methodList);
			}

			ControllerMethod controllerMethod = new ControllerMethod(enrty.getKey(), handlerMethod);
			methodList.add(controllerMethod);

		}
	}

}
