package org.siqisource.stone.ui.dojo;

import java.lang.annotation.Annotation;
import java.util.Iterator;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class SortArgumentResolver implements HandlerMethodArgumentResolver{

	public boolean supportsParameter(MethodParameter parameter) {
		for (Annotation annotation : parameter.getParameterAnnotations()) {
			if (annotation instanceof SortParam) {
				return true;
			}
		}
		return false;
	}

	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		
		Iterator<String> parameterNames = webRequest.getParameterNames();
		
		while(parameterNames.hasNext()){
			String parameterName = parameterNames.next();
			if(parameterName.indexOf("sort(")!=-1){
				return parameterName;
			}
		}
		
		return null;
	}

}
