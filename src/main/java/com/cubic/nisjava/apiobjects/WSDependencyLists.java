package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSDependencyLists {

	@SerializedName("contactDependencies")
	@Expose
	private List<WSContactDependency> contactDependencies = null;
	@SerializedName("fundingSourceDependencies")
	@Expose
	private List<Object> fundingSourceDependencies = null;

	public List<WSContactDependency> getContactDependencies() {
		return contactDependencies;
	}

	public void setContactDependencies(List<WSContactDependency> contactDependencies) {
		this.contactDependencies = contactDependencies;
	}

	public List<Object> getFundingSourceDependencies() {
		return fundingSourceDependencies;
	}

	public void setFundingSourceDependencies(List<Object> fundingSourceDependencies) {
		this.fundingSourceDependencies = fundingSourceDependencies;
	}

}
