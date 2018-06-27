package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCustomerPostAddressResponse {

	@SerializedName("addressId")
	@Expose
	private String addressId;
	@SerializedName("isExistingAddress")
	@Expose
	private Boolean isExistingAddress;

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public Boolean getIsExistingAddress() {
		return isExistingAddress;
	}

	public void setIsExistingAddress(Boolean isExistingAddress) {
		this.isExistingAddress = isExistingAddress;
	}

}