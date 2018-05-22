package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSFundingSourceList {

	@SerializedName("fundingSources")
	@Expose
	private List<WSFundingSource> fundingSources = null;

	public List<WSFundingSource> getFundingSources() {
		return fundingSources;
	}

	public void setFundingSources(List<WSFundingSource> fundingSources) {
		this.fundingSources = fundingSources;
	}

}