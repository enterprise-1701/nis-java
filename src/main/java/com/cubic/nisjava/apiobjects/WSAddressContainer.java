package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSAddressContainer {

	@SerializedName("address")
	@Expose
	private WSAddress address;

	public WSAddress getAddress() {
		return address;
	}

	public void setAddress(WSAddress address) {
		this.address = address;
	}

}
