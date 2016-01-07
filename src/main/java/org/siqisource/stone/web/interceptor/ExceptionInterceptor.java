package org.siqisource.stone.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.siqisource.stone.exceptions.ProgramException;
import org.siqisource.stone.web.Notice;
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
		logger.error("error:",ex);
		ModelAndView mv = new ModelAndView("error/Error");
		
		//向前台传递Notice
		if(ex instanceof Notice){
			mv.addObject("notice", ex);
		}else{
			Notice noticeEx = new ProgramException(ex.getMessage(), ex);
			mv.addObject("notice", noticeEx);
		}
		
		mv.addObject("error", ex);
		return mv;
	}

}
