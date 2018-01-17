package com.cubic.nisjava.apiobjects;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSViewAccountNotificationPreferences {

	@SerializedName("contacts")
	@Expose
	private List<WSContact> contacts = null;

	public List<WSContact> getContacts() {
		return contacts;
	}

	public void setContacts(List<WSContact> contacts) {
		this.contacts = contacts;
	}

	
}
