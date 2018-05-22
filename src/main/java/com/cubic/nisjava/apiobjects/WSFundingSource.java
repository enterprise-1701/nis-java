package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSFundingSource {

	@SerializedName("creditCard")
	@Expose
	private WSCreditCard creditCard;
	@SerializedName("setAsPrimary")
	@Expose
	private Boolean setAsPrimary;
	@SerializedName("billingAddressId")
	@Expose
	private String billingAddressId;
	@SerializedName("fundingSourceId")
	@Expose
	private Integer fundingSourceId;
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("statusDescription")
	@Expose
	private String statusDescription;

	public WSCreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(WSCreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public Boolean getSetAsPrimary() {
		return setAsPrimary;
	}

	public void setSetAsPrimary(Boolean setAsPrimary) {
		this.setAsPrimary = setAsPrimary;
	}

	public String getBillingAddressId() {
		return billingAddressId;
	}

	public void setBillingAddressId(String billingAddressId) {
		this.billingAddressId = billingAddressId;
	}

	public Integer getFundingSourceId() {
		return fundingSourceId;
	}

	public void setFundingSourceId(Integer fundingSourceId) {
		this.fundingSourceId = fundingSourceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

}