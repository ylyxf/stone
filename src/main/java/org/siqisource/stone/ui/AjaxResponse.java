package org.siqisource.stone.ui;

public class AjaxResponse extends Notify {

	private Object data;
	
	public AjaxResponse(String message) {
		super(message);
	}

	public AjaxResponse(String message, Object data) {
		super(message);
		this.data = data;
	}

	public AjaxResponse(String title, String message, Object data) {
		super(message, title);
		this.data = data;
	}

	public AjaxResponse(String type, String message, String title, Object data) {
		super(type, message, title);
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
