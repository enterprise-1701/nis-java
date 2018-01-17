package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSPreference {

	@SerializedName("notificationType")
	@Expose
	private String notificationType;

	@SerializedName("notificationDescription")
	@Expose
	private String notificationDescription;

	@SerializedName("category")
	@Expose
	private WSCategory category;

	@SerializedName("subCategory")
	@Expose
	private WSSubCategory subCategory;

	@SerializedName("optIn")
	@Expose
	private Boolean optIn;

	@SerializedName("channels")
	@Expose
	private List<WSChannel> channels = null;

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public String getNotificationDescription() {
		return notificationDescription;
	}

	public void setNotificationDescription(String notificationDescription) {
		this.notificationDescription = notificationDescription;
	}

	public WSCategory getCategory() {
		return category;
	}

	public void setCategory(WSCategory category) {
		this.category = category;
	}

	public WSSubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(WSSubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Boolean getOptIn() {
		return optIn;
	}

	public void setOptIn(Boolean optIn) {
		this.optIn = optIn;
	}

	public List<WSChannel> getChannels() {
		return channels;
	}

	public void setChannels(List<WSChannel> channels) {
		this.channels = channels;
	}

}
