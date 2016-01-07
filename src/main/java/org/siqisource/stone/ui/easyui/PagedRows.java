package org.siqisource.stone.ui.easyui;

import java.util.List;

public class PagedRows<T> {
 
	public PagedRows(Integer total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}

	private Integer total = 0;

	private List<T> rows;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

}
