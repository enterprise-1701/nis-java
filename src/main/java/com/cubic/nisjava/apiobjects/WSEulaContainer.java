package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSEulaContainer {

	@SerializedName("list")
	@Expose
	private java.util.List<WSEula> list = null;
	@SerializedName("totalCount")
	@Expose
	private Integer totalCount;

	public java.util.List<WSEula> getList() {
		return list;
	}

	public void setList(java.util.List<WSEula> list) {
		this.list = list;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

}
