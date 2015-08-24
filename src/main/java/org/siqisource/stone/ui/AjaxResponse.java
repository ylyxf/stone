package org.siqisource.stone.ui;

public class AjaxResponse {

	public AjaxResponse(Object data) {
		this.type = "success";
		this.data = data;
	}

	public AjaxResponse(String type, Object data) {
		this.type = type;
		this.data = data;
	}

	private Object data;

	private String type;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
