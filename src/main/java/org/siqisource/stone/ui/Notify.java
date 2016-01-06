package org.siqisource.stone.ui;

public class Notify extends AjaxResponse {

	public Notify() {
		super("success", "");
		this.title = "操作成功";
		this.message = "操作成功";
	}

	public Notify(String title, String message) {
		super("success", "");
		this.title = title;
		this.message = message;
	}

	public Notify(String message, Object data) {
		super("success", data);
		this.title = "操作成功";
		this.message = message;
	}

	public Notify(String message) {
		super("success", "");
		this.title = "操作成功";
		this.message = message;
	}

	private String title;

	private String message;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
