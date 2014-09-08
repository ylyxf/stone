package org.siqisource.stone.exceptions;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -7665787531391371745L;

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

}
