package org.siqisource.stone.exceptions;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -7665787531391371745L;
	
	private Object businessData;

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(String message,Object businessData) {
		super(message);
		this.businessData = businessData;
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public Object getBusinessData() {
		return businessData;
	}

	public void setBusinessData(Object businessData) {
		this.businessData = businessData;
	}

}
