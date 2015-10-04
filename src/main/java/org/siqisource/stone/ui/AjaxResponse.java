package org.siqisource.stone.ui;

import org.siqisource.stone.notice.SimpleNotice;

public class AjaxResponse extends SimpleNotice{

	public AjaxResponse(Object data) {
		this.type = "success";
		this.data = data;
	}

	public AjaxResponse(String type, Object data) {
		this.type = type;
		this.data = data;
	}


}
