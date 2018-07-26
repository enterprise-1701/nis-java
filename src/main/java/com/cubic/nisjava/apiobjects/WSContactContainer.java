package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSContactContainer {

	@SerializedName("contact")
	@Expose
	private WSCustomerContact contact;

	public WSCustomerContact getContact() {
		return contact;
	}

	public void setContact(WSCustomerContact contact) {
		this.contact = contact;
	}

}
