package org.siqisource.stone.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionInterceptor implements HandlerExceptionResolver {

	public static final Logger logger = LoggerFactory
			.getLogger(ExceptionInterceptor.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.error(ex.getMessage());
		ex.printStackTrace();

		ModelAndView mv = new ModelAndView("index/Error");
		mv.addObject("error", ex);
		if (request.getHeader("X-Requested-With") != null) {
			mv.addObject("ajax", true);
		}
		return mv;
	}

}
