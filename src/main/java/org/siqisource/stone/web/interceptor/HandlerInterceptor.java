package org.siqisource.stone.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.NDC;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class HandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, 
    Object handler) throws Exception {
		System.out.println(request.getRequestURL());
		NDC.push(String.valueOf(SecurityUtils.getSubject().getPrincipal()));
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
    Object handler, Exception ex) throws Exception {
    	NDC.remove();
    }
}
