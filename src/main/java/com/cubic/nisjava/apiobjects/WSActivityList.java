package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSActivityList {

	@SerializedName("activities")
	@Expose
	private List<WSActivity> activities = null;
	@SerializedName("totalCount")
	@Expose
	private Integer totalCount;

	public List<WSActivity> getActivities() {
		return activities;
	}

	public void setActivities(List<WSActivity> activities) {
		this.activities = activities;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

}
