package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSPersonalIdentifierInfo {

	@SerializedName("personalIdentifier")
	@Expose
	private String personalIdentifier;
	@SerializedName("personalIdentifierType")
	@Expose
	private String personalIdentifierType;

	public String getPersonalIdentifier() {
		return personalIdentifier;
	}

	public void setPersonalIdentifier(String personalIdentifier) {
		this.personalIdentifier = personalIdentifier;
	}

	public String getPersonalIdentifierType() {
		return personalIdentifierType;
	}

	public void setPersonalIdentifierType(String personalIdentifierType) {
		this.personalIdentifierType = personalIdentifierType;
	}

}