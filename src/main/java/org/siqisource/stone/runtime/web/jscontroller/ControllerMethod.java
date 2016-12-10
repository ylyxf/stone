package org.siqisource.stone.runtime.web.jscontroller;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

public class ControllerMethod {

	public ControllerMethod(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod) {
		super();
		this.requestMappingInfo = requestMappingInfo;
		this.handlerMethod = handlerMethod;
	}

	RequestMappingInfo requestMappingInfo;

	HandlerMethod handlerMethod;

	public RequestMappingInfo getRequestMappingInfo() {
		return requestMappingInfo;
	}

	public void setRequestMappingInfo(RequestMappingInfo requestMappingInfo) {
		this.requestMappingInfo = requestMappingInfo;
	}

	public HandlerMethod getHandlerMethod() {
		return handlerMethod;
	}

	public void setHandlerMethod(HandlerMethod handlerMethod) {
		this.handlerMethod = handlerMethod;
	}

}
