package org.siqisource.stone.ui.bt;

import org.apache.ibatis.session.RowBounds;

public class Paging {

	private Integer limit;

	private Integer offset;

	private String order;
	
	private String sort;

	public RowBounds getRowBounds() {
		return new RowBounds(offset, limit);
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	

}
