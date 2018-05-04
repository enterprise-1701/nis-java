package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCountry {

	@SerializedName("country")
	@Expose
	private String country;
	@SerializedName("description")
	@Expose
	private String description;
	@SerializedName("states")
	@Expose
	private List<WSState> states = null;
	@SerializedName("config")
	@Expose
	private WSConfig config;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<WSState> getStates() {
		return states;
	}

	public void setStates(List<WSState> states) {
		this.states = states;
	}

	public WSConfig getConfig() {
		return config;
	}

	public void setConfig(WSConfig config) {
		this.config = config;
	}

}
