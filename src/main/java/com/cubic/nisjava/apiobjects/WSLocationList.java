package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSLocationList {

	@SerializedName("locations")
	@Expose
	private List<WSLocation> locations = null;

	public List<WSLocation> getLocations() {
		return locations;
	}

	public void setLocations(List<WSLocation> locations) {
		this.locations = locations;
	}

}
