package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSContactContainer {

	@SerializedName("contact")
	@Expose
	private WSContact_ contact;

	public WSContact_ getContact() {
		return contact;
	}

	public void setContact(WSContact_ contact) {
		this.contact = contact;
	}

}
