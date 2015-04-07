package org.siqisource.stone.ui;

public class Notify {

	private String type;

	private String message;

	private String title;

	public Notify(String type, String message, String title) {
		super();
		this.type = type;
		this.message = message;
		this.title = title;
	}

	public Notify(String message, String title) {
		this("success", message, title);
	}

	public Notify(String message) {
		this("success", message, "操作成功");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
