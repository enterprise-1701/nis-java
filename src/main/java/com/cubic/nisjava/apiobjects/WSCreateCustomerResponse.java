package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCreateCustomerResponse {

	@SerializedName("customerId")
	@Expose
	private String customerId;
	@SerializedName("contactId")
	@Expose
	private String contactId;
	@SerializedName("oneAccountId")
	@Expose
	private Integer oneAccountId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public Integer getOneAccountId() {
		return oneAccountId;
	}

	public void setOneAccountId(Integer oneAccountId) {
		this.oneAccountId = oneAccountId;
	}
}
