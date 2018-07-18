package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSShippingType {

	@SerializedName("shippingTypeId")
	@Expose
	private Integer shippingTypeId;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("feeAmount")
	@Expose
	private Integer feeAmount;

	public Integer getShippingTypeId() {
		return shippingTypeId;
	}

	public void setShippingTypeId(Integer shippingTypeId) {
		this.shippingTypeId = shippingTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(Integer feeAmount) {
		this.feeAmount = feeAmount;
	}
}
