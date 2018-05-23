package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSLineItemsList {

	@SerializedName("lineItems")
	@Expose
	private List<Object> lineItems = null;
	@SerializedName("totalCount")
	@Expose
	private Integer totalCount;

	public List<Object> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<Object> lineItems) {
		this.lineItems = lineItems;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

}