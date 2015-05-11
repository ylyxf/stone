package org.siqisource.stone.web;

import javax.servlet.ServletContext;

import org.siqisource.stone.web.jspservice.JspService;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.ServletContextAware;

@JspService("path")
@Component
public class Path implements ServletContextAware {

	/** physicalPath */
	private static String physicalPath;

	/** contextPath */
	private static String contextPath;

	public void setServletContext(ServletContext servletContext) {

		physicalPath = servletContext.getRealPath("/");

		if (ReflectionUtils.findMethod(ServletContext.class, "getContextPath") != null) {
			contextPath = servletContext.getContextPath();
		} else {
			contextPath = "";
		}

		int iLastSlash = contextPath.lastIndexOf('/');
		if (iLastSlash != -1 && iLastSlash == contextPath.length() - 1) {
			contextPath = contextPath.substring(0, iLastSlash);
		}
	}

	public static String getPhysicalPath() {
		return physicalPath;
	}

	public static void setPhysicalPath(String physicalPath) {
		Path.physicalPath = physicalPath;
	}

	public static String getContextPath() {
		return contextPath;
	}

	public static void setContextPath(String contextPath) {
		Path.contextPath = contextPath;
	}

}
