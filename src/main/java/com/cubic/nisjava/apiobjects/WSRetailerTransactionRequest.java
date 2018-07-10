/**
 * @author 203610
 * Jun 14, 2018
 */
package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSRetailerTransactionRequest 
{
	@SerializedName("deviceId")
	@Expose
	private String deviceId;
	
	@SerializedName("date")
	@Expose
	private String date;

	@SerializedName("userId")
	@Expose
	private String userId;
	
	public String getDeviceId() {
	return deviceId;
	}

	public void setDeviceId(String deviceId) {
	this.deviceId = deviceId;
	}

	public String getDate() {
	return date;
	}

	public void setDate(String date) {
	this.date = date;
	}
	
	public String getUserId() {
	return userId;
	}

	public void setUserId(String userId) 
	{
		this.userId=userId;		
	}
}
