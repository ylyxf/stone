package org.siqisource.stone.ui;

public class Page {

	private int start;
	
	private int limit;

	public Page() {

	}

	public Page(String range) {
		if (range == null || "".equals(range)) {
			this.start = 0;
			this.limit = 10;
		} else {
			range = range.replaceAll("items=", "");
			String[] parsed = range.split("-");
			this.start = new Integer(parsed[0]);
			this.limit = new Integer(parsed[1]) - this.start + 1;
		}
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
