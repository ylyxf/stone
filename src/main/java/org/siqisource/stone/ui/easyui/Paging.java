package org.siqisource.stone.ui.easyui;

import org.apache.ibatis.session.RowBounds;

public class Paging {

	private Integer page;

	private Integer rows;

	public RowBounds getRowBounds() {
		if (page != null && rows != null) {
			return new RowBounds((page - 1) * rows, rows);
		} else {
			return new RowBounds();
		}
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

}
