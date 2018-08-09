package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSEula {

	@SerializedName("eulaId")
	@Expose
	private Integer eulaId;
	@SerializedName("userType")
	@Expose
	private String userType;
	@SerializedName("userId")
	@Expose
	private String userId;
	@SerializedName("subuserId1")
	@Expose
	private String subuserId1;
	@SerializedName("acceptDateTime")
	@Expose
	private String acceptDateTime;
	@SerializedName("locale")
	@Expose
	private WSCXSLocale locale;

	public Integer getEulaId() {
		return eulaId;
	}

	public void setEulaId(Integer eulaId) {
		this.eulaId = eulaId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSubuserId1() {
		return subuserId1;
	}

	public void setSubuserId1(String subuserId1) {
		this.subuserId1 = subuserId1;
	}

	public String getAcceptDateTime() {
		return acceptDateTime;
	}

	public void setAcceptDateTime(String acceptDateTime) {
		this.acceptDateTime = acceptDateTime;
	}

	public WSCXSLocale getLocale() {
		return locale;
	}

	public void setLocale(WSCXSLocale locale) {
		this.locale = locale;
	}

}
