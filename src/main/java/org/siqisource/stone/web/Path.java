package org.siqisource.stone.web;

import java.io.File;

import javax.servlet.ServletContext;

import org.siqisource.stone.web.jspservice.JspService;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.ServletContextAware;

@JspService("path")
@Component
public class Path implements ServletContextAware {

	/** physicalPath */
	private String physicalPath;

	/** contextPath */
	private String contextPath;

	public void setServletContext(ServletContext servletContext) {

		physicalPath = servletContext.getRealPath("/");

		if (ReflectionUtils.findMethod(ServletContext.class, "getContextPath") != null) {
			contextPath = servletContext.getContextPath();
		} else {
			contextPath = "";
		}

		int contextLastSlash = contextPath.lastIndexOf('/');
		if (contextLastSlash != -1
				&& contextLastSlash == contextPath.length() - 1) {
			contextPath = contextPath.substring(0, contextLastSlash);
		}

		int physicalLastSlash = physicalPath.lastIndexOf(File.separator);
		if (physicalLastSlash != -1
				&& physicalLastSlash == physicalPath.length() - 1) {
			physicalPath = physicalPath.substring(0, physicalLastSlash);
		}
	}

	public String getPhysicalPath() {
		return physicalPath;
	}

	public String getContextPath() {
		return contextPath;
	}

}
