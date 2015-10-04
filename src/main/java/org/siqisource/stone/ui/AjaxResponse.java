package org.siqisource.stone.ui;

import java.util.UUID;

import org.siqisource.stone.web.Notice;

public class AjaxResponse implements Notice{
	
	protected UUID id = UUID.randomUUID();

	protected String title;

	protected String content;

	protected String icon;

	protected String type;

	protected Object data;

	public AjaxResponse(Object data) {
		this.type = "success";
		this.data = data;
	}

	public AjaxResponse(String type, Object data) {
		this.type = type;
		this.data = data;
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
