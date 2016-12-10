package org.siqisource.stone.runtime.ui;

import java.util.UUID;

/**
 * ajax call can return this object with exception handdler
 * 
 * @author yulei
 *
 */
public class AjaxResponse {

	protected UUID id = UUID.randomUUID();

	protected Object data;

	public AjaxResponse(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
