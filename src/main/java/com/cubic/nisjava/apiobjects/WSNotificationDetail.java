package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSNotificationDetail {

	@SerializedName("notificationId")
	@Expose
	private Integer notificationId;

	@SerializedName("contact")
	@Expose
	private WSContact contact;

	@SerializedName("mobileDevice")
	@Expose
	private WSMobileDevice mobileDevice;

	@SerializedName("message")
	@Expose
	private String message;

	@SerializedName("alternateMessage")
	@Expose
	private String alternateMessage;

	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	public WSContact getContact() {
		return contact;
	}

	public void setContact(WSContact contact) {
		this.contact = contact;
	}

	public WSMobileDevice getMobileDevice() {
		return mobileDevice;
	}

	public void setMobileDevice(WSMobileDevice mobileDevice) {
		this.mobileDevice = mobileDevice;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAlternateMessage() {
		return alternateMessage;
	}

	public void setAlternateMessage(String alternateMessage) {
		this.alternateMessage = alternateMessage;
	}

}

