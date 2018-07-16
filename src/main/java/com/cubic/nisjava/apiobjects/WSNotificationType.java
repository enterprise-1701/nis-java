package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSNotificationType {

	@SerializedName("notificationType")
	@Expose
	private String notificationType;
	@SerializedName("notificationDescription")
	@Expose
	private String notificationDescription;
	@SerializedName("longDescription")
	@Expose
	private String longDescription;
	@SerializedName("category")
	@Expose
	private WSCategory category;
	@SerializedName("subCategory")
	@Expose
	private WSSubCategory subCategory;
	@SerializedName("active")
	@Expose
	private Boolean active;
	@SerializedName("channelReqTypes")
	@Expose
	private List<WSChannelReqType> channelReqTypes = null;

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

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<WSChannelReqType> getChannelReqTypes() {
		return channelReqTypes;
	}

	public void setChannelReqTypes(List<WSChannelReqType> channelReqTypes) {
		this.channelReqTypes = channelReqTypes;
	}
}
