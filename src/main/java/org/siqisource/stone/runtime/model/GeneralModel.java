package org.siqisource.stone.runtime.model;

import java.util.Date;
import java.util.UUID;

public class GeneralModel {

	protected UUID id;

	protected Integer sortNo;

	protected Date creator;

	protected Date createTime;

	protected Date reviseTime;

	protected String reviser;

	protected String remark;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getReviseTime() {
		return reviseTime;
	}

	public void setReviseTime(Date reviseTime) {
		this.reviseTime = reviseTime;
	}

	public String getReviser() {
		return reviser;
	}

	public void setReviser(String reviser) {
		this.reviser = reviser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
