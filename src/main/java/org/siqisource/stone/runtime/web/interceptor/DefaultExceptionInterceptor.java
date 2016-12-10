package org.siqisource.stone.runtime.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.siqisource.stone.runtime.exceptions.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class DefaultExceptionInterceptor implements HandlerExceptionResolver {

	public static final Logger logger = LoggerFactory.getLogger(DefaultExceptionInterceptor.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		if (!(ex instanceof BusinessException)) {
			logger.error("ExceptionInterceptor Catch Error:", ex);
		}

		ModelAndView mv = new ModelAndView("error/Error");
		mv.addObject("error", ex);
		return mv;
	}

}
