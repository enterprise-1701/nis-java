package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSAuthError {

	@SerializedName("errorKey")
	@Expose
	private String errorKey;
	@SerializedName("errorMessage")
	@Expose
	private String errorMessage;

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}