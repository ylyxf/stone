package org.siqisource.stone.exceptions;

import java.util.UUID;

import org.siqisource.stone.notice.Notice;

public class BusinessException extends RuntimeException implements Notice {

	private static final long serialVersionUID = -7665787531391371745L;

	private UUID id = UUID.randomUUID();

	private String title = "业务异常";

	private String content;

	private String icon;

	private String type;

	private Object data;

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		this.content = message;
	}

	public BusinessException(String message) {
		super(message);
		this.content = message;
	}

	public BusinessException(String message, Object data) {
		super(message);
		this.content = message;
		this.data = data;
	}

	public BusinessException(Throwable cause) {
		super(cause);
		this.content = cause.getMessage();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
