package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSNotificationList {

	@SerializedName("notifications")
	@Expose
	private List<WSNotification> notifications = null;
	
	@SerializedName("notificationsCount")
	@Expose
	private Integer notificationsCount;

	public List<WSNotification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<WSNotification> notifications) {
		this.notifications = notifications;
	}

	public Integer getNotificationsCount() {
		return notificationsCount;
	}

	public void setNotificationsCount(Integer notificationsCount) {
		this.notificationsCount = notificationsCount;
	}

}

