package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCountryList {

	@SerializedName("countries")
	@Expose
	private List<WSCountry> countries = null;

	public List<WSCountry> getCountries() {
		return countries;
	}

	public void setCountries(List<WSCountry> countries) {
		this.countries = countries;
	}

}