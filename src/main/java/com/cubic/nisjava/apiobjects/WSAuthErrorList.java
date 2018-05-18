package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSAuthErrorList {

	@SerializedName("authCode")
	@Expose
	private String authCode;
	@SerializedName("authErrors")
	@Expose
	private List<WSAuthError> authErrors = null;

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public List<WSAuthError> getAuthErrors() {
		return authErrors;
	}

	public void setAuthErrors(List<WSAuthError> authErrors) {
		this.authErrors = authErrors;
	}

}