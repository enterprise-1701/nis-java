package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSNotificationTypeContainer {

	@SerializedName("notificationTypes")
	@Expose
	private List<WSNotificationType> notificationTypes = null;

	public List<WSNotificationType> getNotificationTypes() {
		return notificationTypes;
	}

	public void setNotificationTypes(List<WSNotificationType> notificationTypes) {
		this.notificationTypes = notificationTypes;
	}
}
