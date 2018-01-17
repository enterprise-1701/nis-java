package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSActivityDetail {

	@SerializedName("actionType")
	@Expose
	private String actionType;
	@SerializedName("device")
	@Expose
	private String device;
	@SerializedName("sourceIpAddress")
	@Expose
	private String sourceIpAddress;
	
	private static String diff;

	public static String getDiff() {
		return diff;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getSourceIpAddress() {
		return sourceIpAddress;
	}

	public void setSourceIpAddress(String sourceIpAddress) {
		this.sourceIpAddress = sourceIpAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actionType == null) ? 0 : actionType.hashCode());
		result = prime * result + ((device == null) ? 0 : device.hashCode());
		result = prime * result + ((sourceIpAddress == null) ? 0 : sourceIpAddress.hashCode());
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
		WSActivityDetail other = (WSActivityDetail) obj;
		if (actionType == null) {
			if (other.actionType != null) {
				diff = String.format("BAD actionType = EXPECTED null, FOUND %s", other.actionType );
				return false;
			}
		} else if (!actionType.equals(other.actionType)) {
			diff = String.format("BAD actionType = EXPECTED %s, FOUND %s", this.actionType, other.actionType );
			return false;
		}
		if (device == null) {
			if (other.device != null) {
				diff = String.format("BAD device = EXPECTED null, FOUND %s", other.device );
				return false;
			}
		} else if (!device.equals(other.device)) {
			diff = String.format("BAD device = EXPECTED %s, FOUND %s", this.device, other.device );
			return false;
		}
		if (sourceIpAddress == null) {
			if (other.sourceIpAddress != null) {
				diff = String.format("BAD sourceIpAddress = EXPECTED null, FOUND %s", other.sourceIpAddress );
				return false;
			}
		} else if (!sourceIpAddress.equals(other.sourceIpAddress)) {
			diff = String.format("BAD sourceIpAddress = EXPECTED %s, FOUND %s", this.sourceIpAddress, other.sourceIpAddress );
			return false;
		}
		return true;
	}

}
