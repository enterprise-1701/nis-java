package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSRiderClass {

	@SerializedName("riderClassId")
	@Expose
	private Integer riderClassId;
	@SerializedName("riderClassDescription")
	@Expose
	private String riderClassDescription;
	@SerializedName("riderClassName")
	@Expose
	private String riderClassName;

	public Integer getRiderClassId() {
		return riderClassId;
	}

	public void setRiderClassId(Integer riderClassId) {
		this.riderClassId = riderClassId;
	}

	public String getRiderClassDescription() {
		return riderClassDescription;
	}

	public void setRiderClassDescription(String riderClassDescription) {
		this.riderClassDescription = riderClassDescription;
	}

	public String getRiderClassName() {
		return riderClassName;
	}

	public void setRiderClassName(String riderClassName) {
		this.riderClassName = riderClassName;
	}

}
