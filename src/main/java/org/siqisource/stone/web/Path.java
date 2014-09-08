package org.siqisource.stone.web;

import javax.servlet.ServletContext;

import org.siqisource.stone.web.jspservice.JspService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.context.ServletContextAware;

@Component
@JspService("path")
public class Path implements ServletContextAware {

	/** physicalPath */
	private String physicalPath;

	/** contextPath */
	private String contextPath;

	@Value("${path.jslib}")
	private String jslibPath;

	public void setServletContext(ServletContext servletContext) {

		physicalPath = servletContext.getRealPath("/");
		
		if(ReflectionUtils.findMethod(ServletContext.class, "getContextPath")!=null){
			contextPath = servletContext.getContextPath();
		}else{
			contextPath = "";
		}

		int iLastSlash = contextPath.lastIndexOf('/');
		if (iLastSlash != -1 && iLastSlash == contextPath.length() - 1) {
			contextPath = contextPath.substring(0, iLastSlash);
		}
	}

	public String getPhysicalPath() {
		return physicalPath;
	}

	public void setPhysicalPath(String physicalPath) {
		this.physicalPath = physicalPath;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getJslibPath() {
		return jslibPath;
	}

	public void setJslibPath(String jslibPath) {
		this.jslibPath = jslibPath;
	}

}
