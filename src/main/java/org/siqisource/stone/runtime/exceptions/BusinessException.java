package org.siqisource.stone.runtime.exceptions;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 6348874469445775161L;

	private Object data;

	public BusinessException(Object data) {
		super();
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
