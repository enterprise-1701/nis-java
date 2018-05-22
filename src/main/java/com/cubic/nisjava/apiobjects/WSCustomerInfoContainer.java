package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCustomerInfoContainer {

	@SerializedName("customerInfo")
	@Expose
	private WSCustomerInfo customerInfo;
	@SerializedName("fundingSources")
	@Expose
	private WSFundingSourceList fundingSources;
	@SerializedName("oneAccountInfo")
	@Expose
	private WSOneAccountInfo oneAccountInfo;

	public WSCustomerInfo getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(WSCustomerInfo customerInfo) {
		this.customerInfo = customerInfo;
	}

	public WSFundingSourceList getFundingSources() {
		return fundingSources;
	}

	public void setFundingSources(WSFundingSourceList fundingSources) {
		this.fundingSources = fundingSources;
	}

	public WSOneAccountInfo getOneAccountInfo() {
		return oneAccountInfo;
	}

	public void setOneAccountInfo(WSOneAccountInfo oneAccountInfo) {
		this.oneAccountInfo = oneAccountInfo;
	}

}
