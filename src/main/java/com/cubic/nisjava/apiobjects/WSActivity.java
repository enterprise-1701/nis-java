package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSActivity {

	@SerializedName("activityId")
	@Expose
	private Integer activityId;
	
	@SerializedName("type")
	@Expose
	private String type;
	
	@SerializedName("actionType")
	@Expose
	private String actionType;
	
	@SerializedName("createDateTime")
	@Expose
	private String createDateTime;
	
	@SerializedName("channel")
	@Expose
	private String channel;
	
	@SerializedName("userName")
	@Expose
	private String userName;
	
	@SerializedName("activityData")
	@Expose
	private WSActivityData activityData;

	private static String diff;
	
	public static String getDiff() {
		return diff;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public WSActivityData getActivityData() {
		return activityData;
	}

	public void setActivityData(WSActivityData activityData) {
		this.activityData = activityData;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actionType == null) ? 0 : actionType.hashCode());
		result = prime * result + ((activityData == null) ? 0 : activityData.hashCode());
		result = prime * result + ((activityId == null) ? 0 : activityId.hashCode());
		result = prime * result + ((channel == null) ? 0 : channel.hashCode());
		result = prime * result + ((createDateTime == null) ? 0 : createDateTime.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WSActivity other = (WSActivity) obj;
		if (actionType == null) {
			if (other.actionType != null) {
				diff = String.format("BAD ActionType - EXPECTED null, FOUND %s", other.actionType );
				return false;
			}
		} else if (!actionType.equals(other.actionType)) {
			diff = String.format("BAD ActionType - EXPECTED %s, FOUND %s", this.actionType, other.actionType );
			return false;
		}
		if (activityData == null) {
			if (other.activityData != null) {
				diff = String.format("BAD ActivityData - %s", WSActivityData.getDiff() );
				return false;
			}
		} else if (!activityData.equals(other.activityData)) {
			diff = String.format("BAD ActivityData - %s", WSActivityData.getDiff() );
			return false;
		}
		if (activityId == null) {
			if (other.activityId != null) {
				diff = String.format("BAD ActivityId - EXPECTED null, FOUND %s", other.activityId );
				return false;
			}
		} else if (!activityId.equals(other.activityId)) {
			diff = String.format("BAD ActivityId - EXPECTED %s, FOUND %s", this.activityId, other.activityId );
			return false;
		}
		if (channel == null) {
			if (other.channel != null) {
				diff = String.format("BAD Channel - EXPECTED null, FOUND %s", other.channel );
				return false;
			}
		} else if (!channel.equals(other.channel)) {
			diff = String.format("BAD Channel - EXPECTED %s, FOUND %s", this.channel, other.channel );
			return false;
		}
		
		if (createDateTime == null) {
			if (other.createDateTime != null) {
				diff = String.format("BAD createDateTime - EXPECTED null, FOUND %s", other.createDateTime );
				return false;
			}
		} else if (!createDateTime.equals(other.createDateTime)) {
			diff = String.format("BAD createDateTime - EXPECTED %s, FOUND %s", this.createDateTime, other.createDateTime );
			return false;
		}
		
		if (type == null) {
			if (other.type != null) {
				diff = String.format("BAD type - EXPECTED null, FOUND %s", other.type );
				return false;
			}
		} else if (!type.equals(other.type)) {
			diff = String.format("BAD type - EXPECTED %s, FOUND %s", this.type, other.type );
			return false;
		}
			
		if (userName == null) {
			if (other.userName != null) {
				diff = String.format("BAD userName - EXPECTED null, FOUND %s", other.userName );
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			diff = String.format("BAD userName - EXPECTED %s, FOUND %s", this.userName, other.userName );
			return false;
		}
		return true;
	}

}
