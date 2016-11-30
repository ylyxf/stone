package org.siqisource.stone.runtime.web;

import java.util.UUID;

public interface Notice {

	public UUID getId();

	public void setId(UUID id);

	public String getTitle();

	public void setTitle(String title);

	public String getMessage();

	public void setMessage(String message);

	public String getIcon();

	public void setIcon(String icon);

	public Object getData();

	public void setData(Object data);

	public String getType();

	public void setType(String type);

}
