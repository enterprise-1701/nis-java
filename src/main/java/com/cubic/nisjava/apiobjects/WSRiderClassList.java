package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSRiderClassList {

	@SerializedName("riderClasses")
	@Expose
	private List<WSRiderClass> riderClasses = null;

	public List<WSRiderClass> getRiderClasses() {
		return riderClasses;
	}

	public void setRiderClasses(List<WSRiderClass> riderClasses) {
		this.riderClasses = riderClasses;
	}

}