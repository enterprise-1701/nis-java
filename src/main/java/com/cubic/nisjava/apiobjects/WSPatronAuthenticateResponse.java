package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSPatronAuthenticateResponse {
	private String patronAccountId;
	private String authCode;
	@SerializedName("userId")
	@Expose
	private String userId;
	public String getPatronAccountId() {
		return patronAccountId;
	}
	public void setPatronAccountId(String patronAccountId) {
		this.patronAccountId = patronAccountId;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public WSHdr getHdr() {
		return hdr;
	}
	public void setHdr(WSHdr hdr) {
		this.hdr = hdr;
	}
	private WSHdr hdr;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
