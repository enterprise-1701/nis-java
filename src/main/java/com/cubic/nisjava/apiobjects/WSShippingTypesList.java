package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSShippingTypesList {

	@SerializedName("shippingTypes")
	@Expose
	private List<WSShippingType> shippingTypes = null;

	public List<WSShippingType> getShippingTypes() {
		return shippingTypes;
	}

	public void setShippingTypes(List<WSShippingType> shippingTypes) {
		this.shippingTypes = shippingTypes;
	}
}
